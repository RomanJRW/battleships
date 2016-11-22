package com.windels.battleships.io.impl;

import com.windels.battleships.controller.GameBoard;
import com.windels.battleships.io.Output;

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
