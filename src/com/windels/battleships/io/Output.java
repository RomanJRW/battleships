package com.windels.battleships.io;

import com.windels.battleships.controller.GameBoard;
import java.util.List;

public interface Output {

	void renderGameBoard(GameBoard gameBoard);

	void displayInGameText(String text);
	
        void displayGameControlText(String introText);
        
        void displayHelpMenu(String helpText);
        
        void displaySavedGames(List<String> savedGames);
}
