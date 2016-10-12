package com.windels.battleships;

class Command {
	
	private CommandManager type;
	private String fileName;
	private String shotLocation;
	
	Command(CommandManager commandType)	{
		type = commandType;
		fileName = null;
		shotLocation = null;
	}
	
	Command(CommandManager commandType, String aFileName)	{
		this(commandType);
		fileName = aFileName;
	}
	
	Command(String aShotLocation)	{
		this(CommandManager.SHOT);
		shotLocation = aShotLocation;
	}
	
	CommandManager getType()	{
		return type;
	}
	
	String getFileName()	{
		return fileName;
	}
	
	String getShotLocation()	{
		return shotLocation;
	}

}
