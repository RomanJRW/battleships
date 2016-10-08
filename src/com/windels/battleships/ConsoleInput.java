package com.windels.battleships;

import java.util.Scanner;

public class ConsoleInput implements Input {

	private Scanner sc;
	
	public ConsoleInput()	{
		sc = new Scanner(System.in);
		sc.useDelimiter(",");
	}
	
	public String getInput()	{
		return sc.nextLine();
	}

	public void close()	{
		sc.close();
	}
}
