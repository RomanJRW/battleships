package com.windels.battleships;

public enum CommandManager {
	
	NEWGAME("!n", "New game"),
	MAINMENU("!m", "Go to main menu"),
	EXITGAME("!q", "Exit Battleships"),
	LISTGAMES("!g", "List saved games"),
	HELPMENU("!h", "Open help menu"),
	LOADGAME("!l", "+ (file name) Load a new game"),
	SAVEGAME("!s", "+ (file name) Save current game");
	
	private final String command;
	private final String menuOption;
	
	CommandManager(String aCommand, String anOption)	{
		command = aCommand;
		menuOption = anOption;
	}
	
	String getCommand()	{
		return command;
	}
	
	String getMenuOption()	{
		return menuOption;
	}
	
	public static CommandManager getCommand(String command) {
	    for (CommandManager commandManager : values()) {
	        if (commandManager.getCommand().equals(command)) {
	            return commandManager;
	        }
	    }
	    return null;
	}
}