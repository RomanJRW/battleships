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
		output.displayIntro();
		goToMainMenu();
		input.runInput(this);
	}
	
	
	void obtainInputAndPerformAction()	{
		String command = input.getInput();
		while (sc.hasNextLine())	{
			
			if (command.charAt(0) == '!')	{
				if (command.length() == 2)	{
					switch (command) {
					case mainMenuCommand: goToMainMenu(); 
							break;
					case newGameCommand: startNewGame();
							break;
					case listGamesCommand: listSavedGames();
							break;
					case exitGameCommand: exitGame();
							break;
					case helpMenuCommand: listAllCommands();
							break;
					}
				}
				else if (command.length() > 3)	{
					String fileName = command.substring(3);
					if (command.substring(0, 2).equals(saveGameCommand))	{
						saveExistingGameToFile(fileName);
					}
					else if (command.substring(0, 2).equals(loadGameCommand))	{
						loadExistingGameFromFile(fileName);
					}
				}
				else	{
					invalidInput(command);
				}
			}
			else if (isCorrectShotFormat(command))	{
				makeMove(command);
			}
			else	{
				invalidInput(command);
			}
		}
		
		while (sc.hasNextLine())	{
			String input = sc.nextLine();
			if (input.charAt(0) == '!')	{
				if (input.length() == 2)	{
					switch (input) {
					case mainMenuCommand: bs.goToMainMenu(); 
							break;
					case newGameCommand: bs.startNewGame();
							break;
					case listGamesCommand: bs.listSavedGames();
							break;
					case exitGameCommand: bs.exitGame();
							break;
					case helpMenuCommand: bs.listAllCommands();
							break;
					}
				}
				else if (input.length() > 3)	{
					String fileName = input.substring(3);
					if (input.substring(0, 2).equals(saveGameCommand))	{
						bs.saveExistingGameToFile(fileName);
					}
					else if (input.substring(0, 2).equals(loadGameCommand))	{
						bs.loadExistingGameFromFile(fileName);
					}
				}
				else	{
					invalidInput(input);
				}
			}
			else if (isCorrectShotFormat(input))	{
				bs.makeMove(input);
			}
			else	{
				invalidInput(input);
			}
		}
	}
	
	
	
	//NOT SURE IF I WANT THIS. IF I'M USING IT, IT MIGHT IMPLY COUPLING THAT I DON'T WANT
	public GameState getGameState()	{
		return this.gameState;
	}
	
	public void goToMainMenu()	{
		gameState = GameState.MAIN_MENU;
		output.displayMainMenu();
		listAllCommands();
	}
	
	public void startNewGame()	{
		if (gameState == GameState.MAIN_MENU)	{			
			gameBoard = new GameBoard(boardHeight, boardWidth, new Ship[4]); //DEFAULT STANDARD GRID, CAN ADD VARIATIONS LATER
			output.displayGameBoard(gameBoard);
			gamePlayMode();
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	public void listSavedGames()	{
		if (gameState == GameState.MAIN_MENU || gameState == GameState.IN_PLAY)	{
			output.displaySavedGames(fm.getFilesNamesList());
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	public void loadExistingGameFromFile(String fileName)	{
		if (gameState == GameState.MAIN_MENU)	{
			try	{
				gameBoard = fm.loadGame(fileName);
				output.displayLoadSuccessText(fileName);
				output.displayGameBoard(gameBoard);
				gamePlayMode();
			}
			catch (Exception ex)	{
				output.displayLoadErrorText(fileName);
				goToMainMenu();
			}
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	public void saveExistingGameToFile(String fileName)	{
		if (gameState == GameState.IN_PLAY)	{
			try	{
				fm.saveGame(fileName, gameBoard);
				output.displaySaveSuccessText();
				gamePlayMode();
			}
			catch (Exception ex)	{
				ex.printStackTrace();
				output.displaySaveErrorText();
			}
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	public void makeMove(String shotInput)	{
		if (gameState == GameState.IN_PLAY)	{
			if (isShotValidGridLocation(shotInput))	{
				ShotResult shotResult = gameBoard.takeShotAndGetResult(shotInput);
				output.displayShotResult(shotResult);
				output.displayNumberOfShipsRemaining(gameBoard);
				output.displayGameBoard(gameBoard);
				if (gameBoard.areAllShipsSunk())	{
					gameState = GameState.GAME_ENDED;
					output.displayGameEnding(gameBoard);
				}
				else	{
					gamePlayMode();
				}
			}
			else	{
				output.displayInvalidShotText();
			}
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	public void listAllCommands() {
		output.displayCommands(input.getInputInstructions());
	}
	
	public void sendInvalidCommand(String invalidInput) {
		output.displayInvalidCommandText(invalidInput);
	}

	public void exitGame()	{
		if (gameState == GameState.MAIN_MENU)	{
			//LATER ON, THERE MAY BE SOME OTHER TEARING DOWN CODE TO ADD IN HERE
			input.close();
			System.exit(0);
		}
		else	{
			output.displayUnavailableInputText(); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
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
		output.displayPromptForShot();
	}
	
}
