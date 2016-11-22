package com.windels.battleships.controller;

import com.windels.battleships.io.impl.Command;
import com.windels.battleships.io.impl.UserInterface;

public class Battleships {
	
	//private Input input;
	//private Output output;
        private UserInterface ui;
	private GameBoard gameBoard;
	private GameState gameState;
	private FileManager fm;
	
	private final int boardHeight = 10;
	private final int boardWidth = 10;
	
	public Battleships(UserInterface userInterface)	{
		//this.input = anInput;
		//this.output = anOutput;
                ui = userInterface;
		this.fm = new FileManager();
		gameState = GameState.INTRO;
	}

	public void run() {
		ui.getOutput().printGameText(GameText.INTRO.getText());
		goToMainMenu();
		while (gameState != GameState.EXIT)	{
			obtainInputAndPerformAction();			
		}
		closeGameAndExit();
	}
	
	private void obtainInputAndPerformAction()	{
		Command command = ui.getInput().getUserInput();
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
			}
                }
		
		
	}
	
	//NOT SURE IF I WANT THIS. IF I'M USING IT, IT MIGHT IMPLY COUPLING THAT I DON'T WANT
	GameState getGameState()	{
		return this.gameState;
	}
	
	private void goToMainMenu()	{
		gameState = GameState.MAIN_MENU;
		ui.getOutput().printGameText(GameText.MAINMENU.getText());
		helpMenu();
	}
	
	private void startNewGame()	{
		if (gameState == GameState.MAIN_MENU)	{			
			gameBoard = new GameBoard(boardHeight, boardWidth, new Ship[4]); //DEFAULT STANDARD GRID, CAN ADD VARIATIONS LATER
			gameBoard.generateAndPlaceShipsOnGrid();
			ui.getOutput().renderGameBoard(gameBoard);
			gamePlayMode();
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void listSavedGames()	{
		if (gameState == GameState.MAIN_MENU || gameState == GameState.IN_PLAY)	{
			for (int i = 0; i < fm.getFilesNamesList().size(); i ++)	{
				ui.getOutput().printGameText(fm.getFilesNamesList().get(i));
			}
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void loadExistingGameFromFile(String fileName)	{
		if (gameState == GameState.MAIN_MENU)	{
			try	{
				gameBoard = fm.loadGame(fileName);
				ui.getOutput().printGameText(GameText.LOADSUCCESS.getText());
				ui.getOutput().renderGameBoard(gameBoard);
				gamePlayMode();
			}
			catch (Exception ex)	{
				ui.getOutput().printGameText(GameText.LOADERROR.getText());
				goToMainMenu();
			}
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void saveExistingGameToFile(String fileName)	{
		if (gameState == GameState.IN_PLAY)	{
			try	{
				fm.saveGame(fileName, gameBoard);
				ui.getOutput().printGameText(GameText.SAVESUCCESS.getText());
				gamePlayMode();
			}
			catch (Exception ex)	{
				ex.printStackTrace();
				ui.getOutput().printGameText(GameText.SAVEERROR.getText());
			}
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void makeMove(String shotInput)	{
		if (gameState == GameState.IN_PLAY && isCorrectShotFormat(shotInput))	{
			if (isShotValidGridLocation(shotInput))	{
				ShotResult shotResult = gameBoard.takeShotAndGetResult(shotInput);
				ui.getOutput().printGameText(shotResult.getMessage());
				ui.getOutput().printGameText(GameText.SHIPSREMAINING.getText() + gameBoard.getNumberOfRemainingShips());
				ui.getOutput().renderGameBoard(gameBoard);
				if (gameBoard.areAllShipsSunk())	{
					gameState = GameState.GAME_ENDED;
					ui.getOutput().renderGameBoard(gameBoard);
				}
				else	{
					gamePlayMode();
				}
			}
			else	{
				ui.getOutput().printGameText(GameText.INVALIDSHOT.getText());
			}
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}
	
	private void helpMenu()	{
		ui.getOutput().printGameText(ui.getInput().getHelpText());
	}

	private void exitGame()	{
		if (gameState == GameState.MAIN_MENU)	{
			gameState = GameState.EXIT;
		}
		else	{
			ui.getOutput().printGameText(GameText.UNAVAILABLEINPUT.getText()); //PROVIDE MORE SPECIFIC TEXT DEPENDING ON ERROR AT SOME POINT
		}
	}

	private void closeGameAndExit() {
		ui.getInput().close();
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
		ui.getOutput().printGameText(GameText.SHOTPROMPT.getText());
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
