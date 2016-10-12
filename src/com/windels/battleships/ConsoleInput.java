package com.windels.battleships;

import java.util.Scanner;

public class ConsoleInput implements Input {

	private Scanner sc;
	
	public ConsoleInput()	{
		sc = new Scanner(System.in);
		sc.useDelimiter(",");
	}
	
	public Command getInput()	{
		String input = sc.nextLine();
		if (input.charAt(0) == '!' && input.length() >= 2)	{
			CommandManager cmType = CommandManager.getCommand(input.substring(0, 2));
			if (cmType != null && ((input.length() == 2 && cmType != CommandManager.LOADGAME && cmType != CommandManager.SAVEGAME)))	{
				return new Command(cmType);
			}
			else if (input.length() > 3 && (cmType == CommandManager.LOADGAME || cmType == CommandManager.SAVEGAME))	{
				return new Command(cmType, input.substring(3));
			}
		}
		else	{
			return new Command(input);
		}
		return null;
	}

	public void close()	{
		sc.close();
	}
}
