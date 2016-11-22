package com.windels.battleships.io;

import com.windels.battleships.GameBoard;

public interface Output {

	void renderGameBoard(GameBoard gameBoard);

	void printGameText(String text);
	
}
