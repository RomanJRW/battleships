package com.windels.battleships.io;

import com.windels.battleships.controller.GameBoard;

public interface Output {

	void renderGameBoard(GameBoard gameBoard);

	void displayInGameText(String text);
	
        void displayGameControl(String introText);
        
        void displayHelpMenu(String helpText);
}
