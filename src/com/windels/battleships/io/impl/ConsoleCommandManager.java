package com.windels.battleships;

public enum ConsoleCommandManager {
	
	NEWGAME("!n"),
	MAINMENU("!m"),
	EXITGAME("!q"),
	LISTGAMES("!g"),
	HELPMENU("!h"),
	LOADGAME("!l"),
	SAVEGAME("!s"),
	SHOT("");
	
	private final String command;
	
	ConsoleCommandManager(String aCommand)	{
		command = aCommand;
	}
	
	String getCommand()	{
		return command;
	}
	
	
	public static ConsoleCommandManager getCommand(String command) {
	    for (ConsoleCommandManager commandManager : values()) {
	        if (commandManager.getCommand().equals(command)) {
	            return commandManager;
	        }
	    }
	    return null;
	}
}