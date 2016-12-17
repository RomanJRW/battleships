package com.windels.battleships.io.impl;

import com.windels.battleships.controller.Coordinate;
import com.windels.battleships.controller.GameBoard;

public class ConsoleGridGenerator {
        
        public static String generateSmallGrid(GameBoard aGameBoard)    {
            return ConsoleGridGenerator.generateGridTemplate(aGameBoard, "  ", "\n", "-", "\t");
        }
        
        public static String generateLargeGrid(GameBoard aGameBoard)    {
            return ConsoleGridGenerator.generateGridTemplate(aGameBoard, "\t", "\n\n\n", "- - ", "\t");
        }
        
        private static String generateGridTemplate(GameBoard aGameBoard, String textSpacer,
                                                    String lineSpacer, String borderSpacer,
                                                    String formatSpacer)  {
		StringBuilder grid = new StringBuilder();
		grid.append(formatSpacer);
		for (int i = 0; i < aGameBoard.getGridWidth(); i++) {
			int columnHeader = i + 65;
			grid.append(textSpacer + (char)columnHeader);
		}
		grid.append('\n' + formatSpacer + "+" + borderSpacer);
		for (int j = 0; j < aGameBoard.getGridWidth(); j++) {
			grid.append(borderSpacer + borderSpacer);
		}
		grid.append("\n");
		for (int k = 0; k < aGameBoard.getGridHeight(); k++) {
			grid.append(k + 1 + formatSpacer + "|");
			for (int l = 0; l < aGameBoard.getGridWidth(); l++) {
				Coordinate aCoordinate = new Coordinate(k,l);	
				
				if (aGameBoard.isCoordinateAlreadyShot(aCoordinate))	{
					if (aGameBoard.isThereShipInGivenCoordinate(aCoordinate))	{
						grid.append(textSpacer + "O");
					}
					else	{
						grid.append(textSpacer + "X");
					}
				}
				else {
				grid.append(textSpacer + "-");
				}	
			}
			grid.append(lineSpacer);
		}
		return grid.toString();
	}
	
}
