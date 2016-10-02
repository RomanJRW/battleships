package main;

import java.util.List;

public interface Output {
	void displayIntro();

	void displayMainMenu();

	void displayGameBoard(GameBoard aGameBoard);

	void displayShotResult(ShotResult shotResult);

	void displayGameEnding(GameBoard gameBoard);

	void displayLoadErrorText(String fileName);

	void displaySavedGames(List<String> fileNamesList);

	void displaySaveErrorText();

	void displayUnavailableInputText();

	void displayInvalidCommandText(String invalidInput);

	void displayInvalidShotText();

	void displayPromptForShot();

	void displayCommands(String inputInstructions);

	void displayNumberOfShipsRemaining(GameBoard gameBoard);

	void displayLoadSuccessText(String fileName);

	void displaySaveSuccessText();
	
}
