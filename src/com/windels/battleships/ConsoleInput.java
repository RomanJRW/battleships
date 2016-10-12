package com.windels.battleships;

import java.util.Scanner;

public class ConsoleInput implements Input {

	private Scanner sc;
	
	public ConsoleInput()	{
		sc = new Scanner(System.in);
		sc.useDelimiter(",");
	}
	
	@Override
	public Command getInput()	{
		String input = sc.nextLine();
		if (input.charAt(0) == '!' && input.length() >= 2)	{
			ConsoleCommandManager cmType = ConsoleCommandManager.getCommand(input.substring(0, 2));
			if (cmType != null && ((input.length() == 2 && cmType != ConsoleCommandManager.LOADGAME && cmType != ConsoleCommandManager.SAVEGAME)))	{
				return new Command(cmType);
			}
			else if (input.length() > 3 && (cmType == ConsoleCommandManager.LOADGAME || cmType == ConsoleCommandManager.SAVEGAME))	{
				return new Command(cmType, input.substring(3));
			}
		}
		else	{
			return new Command(input);
		}
		return null;
	}

	@Override
	public void close()	{
		sc.close();
	}

	@Override
	public String getHelpText() {
		StringBuilder helpText = new StringBuilder();
		helpText.append("Here are the available commands. Please note, not all commands may be available\n");
		helpText.append("at any given time");
		helpText.append(ConsoleCommandManager.NEWGAME.getCommand() + "\t\t-\t" + "New game");
		helpText.append(ConsoleCommandManager.MAINMENU.getCommand() + "\t\t-\t" + "Go to main menu");
		helpText.append(ConsoleCommandManager.EXITGAME.getCommand() + "\t\t-\t" + "Exit Battleships");
		helpText.append(ConsoleCommandManager.LISTGAMES.getCommand() + "\t\t-\t" + "List saved games");
		helpText.append(ConsoleCommandManager.HELPMENU.getCommand() + "\t\t-\t" + "Open help menu");
		helpText.append(ConsoleCommandManager.LOADGAME.getCommand() + " + (file name)" + "\t\t-\t" + "Load an existing game");
		helpText.append(ConsoleCommandManager.SAVEGAME.getCommand() + " + (file name)" + "\t\t-\t" + "Save the current game");
		return helpText.toString();
	}
}
