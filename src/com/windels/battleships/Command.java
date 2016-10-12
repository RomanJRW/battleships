package com.windels.battleships;

class Command {
	
	private CommandManager type;
	private String fileName;
	
	Command(CommandManager commandType)	{
		type = commandType;
		fileName = null;
	}
	
	Command(CommandManager commandType, String aFileName)	{
		this(commandType);
		fileName = aFileName;
	}
	
	CommandManager getType()	{
		return type;
	}
	
	String getFileName()	{
		return fileName;
	}

}
