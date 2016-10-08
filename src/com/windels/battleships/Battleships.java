package com.windels.battleships;

public class Battleships {
	
	private Input input;
	private Output output;
	private GameBoard gameBoard;
	private GameState gameState;
	private FileManager fm;
	
	private final int boardHeight = 10;
	private final int boardWidth = 10;
	
	private Battleships(Input anInput, Output anOutput)	{
		this.input = anInput;
		this.output = anOutput;
		this.fm = new FileManager();
		gameState = GameState.INTRO;
	}
	
	public static void main(String[] args) {
		Input in = null;
		Output out = null;
		if (args.length == 1 && args[0] == "-gui")	{
			//in = new GuiInput();
			//out = new GuiOutput();
			System.out.println("This is where I would initialize in and out to gui objects");
		}
		else if (args.length > 0)	{
			System.out.println("Invalid arguments provided");
			System.exit(1);
		}
		else	{
			in = new ConsoleInput();
			out = new ConsoleOutput();
		}
		Battleships bs = new Battleships(in, out);
		bs.run();
	}

	private void run() {
		output.printGameText(GameText.INTRO.getText());
		goToMainMenu();
		while (gameState != GameState.EXIT)	{
			obtainInputAndPerformAction();			
		}
		closeGameAndExit();
	}
	
	private void obtainInputAndPerformAction()	{
		String command = input.getInput();
			if (command.charAt(0) == '!' && command.length() >= 2)	{
				CommandManager cm = CommandManager.getCommand(command.substring(0, 2));
				if (cm != null && ((command.length() == 2 && cm != CommandManager.LOADGAME && cm != CommandManager.SAVEGAME) || (command.length() > 3 && (cm == CommandManager.LOADGAME || cm == CommandManager.SAVEGAME))))	{
					
					switch (cm) {
					case MAINMENU:	goToMainMenu(); 
									break;
					case NEWGAME:	startNewGame();
									break;
					case LISTGAMES:	listSavedGames();
									break;
					case EXITGAME:	exitGame();
									break;
					case HELPMENU:	helpMenu();
									break;
					case LOADGAME:	String loadFileName = command.substring(3);
									loadExistingGameFromFile(loadFileName);
									break;
					case SAVEGAME:	String saveFileName = command.substring(3);
									saveExistingGameToFile(saveFileName);
									break;
					}
				}
				else	{
					informInvalidCommand(command);
				}
			}
			else if (isCorrectShotFormat(command))	{
				makeMove(command);
			}
			else	{
				informInvalidCommand(command);
			}
	}
	
	
	//NOT SURE IF I WANT THIS. IF I'M USING IT, IT MIGHT IMPLY COUPLING THAT I DON'T WANT
	GameState getGameState()	{
		return this.gameState;
	}
	
	private void goToMainMenu()	{
		gameState = GameState.MAIN_MENU;
		output.printGameText(GameText.MAINMENU.getText());
		listAllCommands();
	}
	
	private void startNewGame()	{
		if (gameState == GameState.MAIN_MENU)	{			
			gameBoard = new GameBoard(boardHeight, boardWidth, new Ship[4]); //DEFAULT STANDARD GRID, CAN ADD VARIATIONS LATER
			output.renderGameBoard(gameBoard);
			gamePlayMode();
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void listSavedGames()	{
		if (gameState == GameState.MAIN_MENU || gameState == GameState.IN_PLAY)	{
			for (int i = 0; i < fm.getFilesNamesList().size(); i ++)	{
				output.printGameText(fm.getFilesNamesList().get(i));
			}
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void loadExistingGameFromFile(String fileName)	{
		if (gameState == GameState.MAIN_MENU)	{
			try	{
				gameBoard = fm.loadGame(fileName);
				output.printGameText(GameText.LOADSUCCESS.getText());
				output.renderGameBoard(gameBoard);
				gamePlayMode();
			}
			catch (Exception ex)	{
				output.printGameText(GameText.LOADERROR.getText());
				goToMainMenu();
			}
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void saveExistingGameToFile(String fileName)	{
		if (gameState == GameState.IN_PLAY)	{
			try	{
				fm.saveGame(fileName, gameBoard);
				output.printGameText(GameText.SAVESUCCESS.getText());
				gamePlayMode();
			}
			catch (Exception ex)	{
				ex.printStackTrace();
				output.printGameText(GameText.SAVEERROR.getText());
			}
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void makeMove(String shotInput)	{
		if (gameState == GameState.IN_PLAY)	{
			if (isShotValidGridLocation(shotInput))	{
				ShotResult shotResult = gameBoard.takeShotAndGetResult(shotInput);
				output.printGameText(shotResult.getMessage());
				output.printGameText(GameText.SHIPSREMAINING.getText() + gameBoard.getNumberOfRemainingShips());
				output.renderGameBoard(gameBoard);
				if (gameBoard.areAllShipsSunk())	{
					gameState = GameState.GAME_ENDED;
					output.renderGameBoard(gameBoard);
				}
				else	{
					gamePlayMode();
				}
			}
			else	{
				output.printGameText(GameText.INVALIDSHOT.getText());
			}
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void helpMenu()	{
		output.printGameText(GameText.HELPMENU.getText());
		listAllCommands();
	}
	
	
	private void informInvalidCommand(String invalidInput) {
		output.printGameText(GameText.INVALIDCOMMAND.getText() + invalidInput);
	}

	private void exitGame()	{
		if (gameState == GameState.MAIN_MENU)	{
			gameState = GameState.EXIT;
		}
		else	{
			output.printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void closeGameAndExit() {
		input.close();
		System.exit(0);
	}
	
	//HELPERS
	
	private boolean isShotValidGridLocation(String shotInput)	{
		char column = shotInput.charAt(0);		
		String row = shotInput.substring(1);
		return columnExistOnGrid(column) && rowExistOnGrid(row);
	}

	private boolean rowExistOnGrid(String row) {
		int rowNum;
		try	{
			rowNum = Integer.parseInt(row);
			if (rowNum >= 1 && rowNum <= boardHeight)	{
				return true;
			}
			else	{
				return false;
			}
		}
		catch (NumberFormatException ex)	{
			return false;
		}
	}

	private boolean columnExistOnGrid(char column) {
		if (column >= 65 && column < 65 + boardWidth)	{
			return true;
		}
		return false;
	}

	
	private void gamePlayMode()	{
		gameState = GameState.IN_PLAY;
		output.printGameText(GameText.SHOTPROMPT.getText());
	}
	
	private boolean isCorrectShotFormat(String input) {
		if (input.length() == 2 || input.length() == 3)	{
			return isValidRowFormat(input.substring(1)) && isValidColumnFormat(input.charAt(0));
		}
		return false;		
	}

	private boolean isValidRowFormat(String rowString) {
		try	{
			Integer.parseInt(rowString);
		}
		catch (NumberFormatException ex)	{
			return false;
		}
		return true;
	}

	private boolean isValidColumnFormat(char columnString) {
		if (Character.isUpperCase(columnString))	{
			return true;
		}
		return false;
	}
	

	private void listAllCommands() {
		for (CommandManager command : CommandManager.values())	{
			output.printGameText(command.getCommand() + "\t\t-\t" + command.getMenuOption());
		}
	}
	
}
