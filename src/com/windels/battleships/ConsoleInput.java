package com.windels.battleships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

	private Scanner sc;
	private Battleships bs;
	
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
		String input = null;
		while (sc.hasNextLine())	{
			input = sc.nextLine();
		}
		return input;
	}

	public void runInput(Battleships bsGame)	{
		bs = bsGame;
		while (sc.hasNextLine())	{
			String input = sc.nextLine();
			if (input.charAt(0) == '!')	{
				if (input.length() == 2)	{
					switch (input) {
					case mainMenuCommand: bs.goToMainMenu(); 
							break;
					case newGameCommand: bs.startNewGame();
							break;
					case listGamesCommand: bs.listSavedGames();
							break;
					case exitGameCommand: bs.exitGame();
							break;
					case helpMenuCommand: bs.listAllCommands();
							break;
					}
				}
				else if (input.length() > 3)	{
					String fileName = input.substring(3);
					if (input.substring(0, 2).equals(saveGameCommand))	{
						bs.saveExistingGameToFile(fileName);
					}
					else if (input.substring(0, 2).equals(loadGameCommand))	{
						bs.loadExistingGameFromFile(fileName);
					}
				}
				else	{
					invalidInput(input);
				}
			}
			else if (isCorrectShotFormat(input))	{
				bs.makeMove(input);
			}
			else	{
				invalidInput(input);
			}
		}
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
	
	//HELPER METHODS
	
	private void invalidInput(String invalidInput)	{
		bs.sendInvalidCommand(invalidInput);
	}
	
	private boolean isCorrectShotFormat(String input) {
		if (input.length() == 2 || input.length() == 3)	{
			return isValidRowFormat(input.substring(1)) && isValidColumnFormat(input.charAt(0));
		}
		return false;		
	}

	private boolean isValidRowFormat(String rowString) {
		try	{
			Integer.parseInt(rowString);
		}
		catch (NumberFormatException ex)	{
			return false;
		}
		return true;
	}

	private boolean isValidColumnFormat(char columnString) {
		if (Character.isUpperCase(columnString))	{
			return true;
		}
		return false;
	}
	
}
