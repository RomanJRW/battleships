package com.windels.battleships;

public class ConsoleOutput implements Output {
	
	private ConsoleGridGenerator gridGenerator;
	
	public ConsoleOutput()	{
		gridGenerator = new ConsoleGridGenerator();
	}
		
	@Override
	public void renderGameBoard(GameBoard aGameBoard)	{
		String grid = gridGenerator.generateGrid(aGameBoard);
		System.out.println(grid);
	}
	
	@Override
	public void printGameText(String text)	{
		System.out.println(text);
	}
}
