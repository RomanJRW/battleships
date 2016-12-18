package com.windels.battleships.io.impl;

import com.windels.battleships.controller.GameBoard;
import com.windels.battleships.io.Output;

public class ConsoleOutput implements Output {
		
    @Override
    public void renderGameBoard(GameBoard aGameBoard) {
        String grid = ConsoleGridGenerator.generateLargeGrid(aGameBoard);
        System.out.println(grid);
    }

    @Override
    public void displayInGameText(String text) {
        System.out.println(text);
    }

    @Override
    public void displayGameControl(String gameControlText) {
        System.out.println("****************************************************************" + "\n"
        + gameControlText
        + "****************************************************************" + "\n");
    }

    @Override
    public void displayHelpMenu(String helpText) {
        System.out.println(helpText);
    }
}
