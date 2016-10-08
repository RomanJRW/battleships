package com.windels.battleships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

	private Scanner sc;
	
	private final String newGameCommand = "!n";
	private final String mainMenuCommand = "!m";
	private final String exitGameCommand = "!q";
	private final String listGamesCommand = "!g";
	private final String helpMenuCommand = "!h";
	private final String loadGameCommand = "!l";
	private final String saveGameCommand = "!s";
	
	public ConsoleInput()	{
		sc = new Scanner(System.in);
		sc.useDelimiter(",");
	}
	
	public String getInput()	{
		return sc.nextLine();
	}

	
	
	public String getInputInstructions()	{
		List<String> commandsList = new ArrayList<String>();
		commandsList.add("New Game" + "\t\t" + "-" + "\t" + newGameCommand);
		commandsList.add("List saved games" + "\t" + "-" + "\t" + listGamesCommand);
		commandsList.add("Load game" + "\t\t" + "-" + "\t" + loadGameCommand + " (enter file name here)");
		commandsList.add("Save game" + "\t\t" + "-" + "\t" + saveGameCommand + " (enter file name here)");
		commandsList.add("Help menu" + "\t\t" + "-" + "\t" + helpMenuCommand);
		commandsList.add("Go to main menu" + "\t\t" + "-" + "\t" + mainMenuCommand);
		commandsList.add("Exit game" + "\t\t" + "-" + "\t" + exitGameCommand);
		
		StringBuilder instructions = new StringBuilder();
		instructions.append("Below are the available commands. Please note that some commands are not always possible, \n"
							+ "depending on the current point of the game.\n"); //Crappy placeholder shite
		for (int i = 0; i < commandsList.size(); i ++)	{
			instructions.append(commandsList.get(i) + "\n");
		}
		return instructions.toString();
	}

	public void close()	{
		sc.close();
	}
}
