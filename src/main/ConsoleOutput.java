package main;

import java.util.List;

public class ConsoleOutput implements Output {
	
	@Override
	public void displayIntro() {
		StringBuilder introText = new StringBuilder();
		introText.append("\n");
		introText.append("           ****************************************************************" + "\n");
		introText.append("           *              Welcome to Battleshite v3, Captain.             *" + "\n");
		introText.append("           *     Your objective is to shoot all 4 of the enemy ships.     *" + "\n");
		introText.append("           *       When prompted, enter a coordinate from the grid        *" + "\n");
		introText.append("           *        Try to use as few shots as you can. Good Luck!        *" + "\n");
		introText.append("           ****************************************************************" + "\n");
		System.out.println(introText.toString());
	}

	@Override
	public void displayMainMenu() {
		StringBuilder mainMenuText = new StringBuilder();
		mainMenuText.append("\n");
		mainMenuText.append("           ****************************************************************" + "\n");
		mainMenuText.append("           *                   You are at the main menu.                  *" + "\n");
		mainMenuText.append("           ****************************************************************" + "\n");
		System.out.println(mainMenuText.toString());
	}

	@Override
	public void displayGameBoard(GameBoard aGameBoard) {
		StringBuilder grid = new StringBuilder();
		grid.append("\t");
		for (int i = 0; i < aGameBoard.getGridWidth(); i++) {
			int columnHeader = i + 65;
			grid.append("\t" + (char)columnHeader);
		}
		grid.append('\n' + "\t" + "+- -");
		for (int j = 0; j < aGameBoard.getGridWidth(); j++) {
			grid.append(" - - - -");
		}
		grid.append("\n");
		for (int k = 0; k < aGameBoard.getGridHeight(); k++) {
			grid.append(k + 1 + "\t" + "|");
			for (int l = 0; l < aGameBoard.getGridWidth(); l++) {
				Coordinate aCoordinate = new Coordinate(k,l);	
				
				if (aGameBoard.isCoordinateAlreadyShot(aCoordinate))	{
					if (aGameBoard.isThereShipInGivenCoordinate(aCoordinate))	{
						grid.append("\t" + "O");
					}
					else	{
						grid.append("\t" + "X");
					}
				}
				else {
				grid.append("\t" + "-");
				}	
			}
			grid.append("\n\n\n");
		}
		System.out.println(grid);
	}

	@Override
	public void displayShotResult(ShotResult shotResult) {
		String shotResultText = shotResult.getMessage() + "\n";
		System.out.println(shotResultText);
	}

	@Override
	public void displayGameEnding(GameBoard gameBoard) {
		StringBuilder gameEndText = new StringBuilder();
		gameEndText.append("           ****************************************************************" + "\n");
		gameEndText.append("           *                  Congratulations, Captain!                   *" + "\n");
		gameEndText.append("           *     You have located and destroyed all of the enemy ships    *" + "\n");
		gameEndText.append("           *                  Total number of shots: " + gameBoard.getTotalShotsTaken() + "                   *" + "\n");
		gameEndText.append("           ****************************************************************" + "\n");
		System.out.println(gameEndText.toString());
	}

	@Override
	public void displayLoadErrorText(String fileName) {
		System.out.println("Unable to load " + fileName);
	}

	@Override
	public void displaySaveErrorText() {
		System.out.println("There was a problem when saving the game. Game not saved");
	}

	@Override
	public void displayUnavailableInputText() {
		System.out.println("That command is not available at this point");
	}

	@Override
	public void displayInvalidCommandText(String invalidInput) {
		System.out.println("Command not recognised");
	}

	@Override
	public void displayInvalidShotText() {
		System.out.println("That wasn't a valid shot, you fool!");
	}

	@Override
	public void displayPromptForShot() {
		System.out.println("Where would you like to shoot next captain? (ie. A1, D4)");
	}

	@Override
	public void displaySavedGames(List<String> fileNamesList) {
		fileNamesList.sort(null);
		for (int i = 0; i < fileNamesList.size(); i ++)	{
			System.out.println(fileNamesList.get(i));
		}	
	}

	@Override
	public void displayCommands(String inputInstructions) {
		System.out.println(inputInstructions);
	}

	@Override
	public void displayNumberOfShipsRemaining(GameBoard gameBoard) {
		System.out.println("There are still " + gameBoard.getNumberOfRemainingShips() + " left to find!");
	}

	@Override
	public void displayLoadSuccessText(String fileName) {
		System.out.println(fileName + " loaded successfully");
	}

	@Override
	public void displaySaveSuccessText() {
		System.out.println("Game saved successfully");
	}

}
