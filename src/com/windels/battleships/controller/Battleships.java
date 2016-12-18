package com.windels.battleships.controller;

import com.windels.battleships.io.impl.Command;
import com.windels.battleships.io.Input;
import com.windels.battleships.io.Output;

public class Battleships {
	
	private Input input;
	private Output output;
	private GameBoard gameBoard;
	private GameState gameState;
	private FileManager fm;
	
	private final int BOARD_HEIGHT = 10;
	private final int BOARD_WIDTH = 10;
	
	public Battleships(Input anInput, Output anOutput)	{
		this.input = anInput;
		this.output = anOutput;
		this.fm = new FileManager();
		gameState = GameState.INTRO;
	}

	public void run() {
		output.displayIntro(GameText.INTRO.getText());
		goToMainMenu();
		while (gameState != GameState.EXIT)	{
                    Command userCommand = input.getUserInput();
                    performInputAction(userCommand);	
		}
                
		closeGameAndExit();
	}
        
	private void performInputAction(Command command)	{
                if (command != null)    {
                    switch (command.getType()) {
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
			case LOADGAME:	String loadFileName = command.getFileName();
					loadExistingGameFromFile(loadFileName);
					break;				
                        case SAVEGAME:	String saveFileName = command.getFileName();
					saveExistingGameToFile(saveFileName);
					break;
			case SHOT:	String shotInput = command.getShotLocation();
					makeMove(shotInput);
					break;
                        default:        break;
			}
               }
		
		
	}
	
	//NOT SURE IF I WANT THIS. IF I'M USING IT, IT MIGHT IMPLY COUPLING THAT I DON'T WANT
	GameState getGameState()	{
		return this.gameState;
	}
	
	private void goToMainMenu()	{
		gameState = GameState.MAIN_MENU;
		output.displayGameText(GameText.MAINMENU.getText());
		helpMenu();
	}
	
	private void startNewGame()	{		
            gameBoard = new GameBoard(BOARD_HEIGHT, BOARD_WIDTH, new Ship[4]); //DEFAULT STANDARD GRID, CAN ADD VARIATIONS LATER
            gameBoard.generateAndPlaceShipsOnGrid();
            output.displayGameText(GameText.NEWGAME.getText());
            output.renderGameBoard(gameBoard);
            gamePlayMode();
	}

	private void listSavedGames()	{
		if (gameState == GameState.MAIN_MENU || gameState == GameState.IN_PLAY)	{
			for (int i = 0; i < fm.getFilesNamesList().size(); i ++)	{
				output.displayGameText(fm.getFilesNamesList().get(i));
			}
		}
		else	{
			output.displayGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void loadExistingGameFromFile(String fileName)	{
		if (gameState == GameState.MAIN_MENU)	{
			try	{
				gameBoard = fm.loadGame(fileName);
				output.displayGameText(GameText.LOADSUCCESS.getText());
				output.renderGameBoard(gameBoard);
				gamePlayMode();
			}
			catch (Exception ex)	{
				output.displayGameText(GameText.LOADERROR.getText());
				goToMainMenu();
			}
		}
		else	{
			output.displayGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void saveExistingGameToFile(String fileName)	{
		if (gameState == GameState.IN_PLAY)	{
			try	{
				fm.saveGame(fileName, gameBoard);
				output.displayGameText(GameText.SAVESUCCESS.getText());
				gamePlayMode();
			}
			catch (Exception ex)	{
				ex.printStackTrace();
				output.displayGameText(GameText.SAVEERROR.getText());
			}
		}
		else	{
			output.displayGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void makeMove(String shotInput)	{
		if (gameState == GameState.IN_PLAY && isCorrectShotFormat(shotInput))	{
			if (isShotValidGridLocation(shotInput))	{
				ShotResult shotResult = gameBoard.takeShotAndGetResult(shotInput);
				output.displayGameText(shotResult.getMessage());
				output.displayGameText(GameText.SHIPSREMAINING.getText() + gameBoard.getNumberOfRemainingShips());
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
				output.displayGameText(GameText.INVALIDSHOT.getText());
			}
		}
		else	{
			output.displayGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void helpMenu()	{
		output.displayHelpMenu(input.getHelpText());
	}

	private void exitGame()	{
            gameState = GameState.EXIT;
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
			if (rowNum >= 1 && rowNum <= BOARD_HEIGHT)	{
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
		if (column >= 65 && column < 65 + BOARD_WIDTH)	{
			return true;
		}
		return false;
	}

	
	private void gamePlayMode()	{
		gameState = GameState.IN_PLAY;
		output.displayGameText(GameText.SHOTPROMPT.getText());
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
}
