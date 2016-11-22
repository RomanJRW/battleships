package com.windels.battleships;

public class ConsoleGridGenerator {

	ConsoleGridGenerator()	{
	}
	
	String generateGrid(GameBoard aGameBoard)	{
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
		return grid.toString();
	}
	
}
