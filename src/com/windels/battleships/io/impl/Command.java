package com.windels.battleships.io.impl;

public class Command {
	
	private ConsoleCommandManager type;
	private String fileName;
	private String shotLocation;
	
	Command(ConsoleCommandManager commandType)	{
		type = commandType;
		fileName = null;
		shotLocation = null;
	}
	
	Command(ConsoleCommandManager commandType, String aFileName)	{
		this(commandType);
		fileName = aFileName;
	}
	
	Command(String aShotLocation)	{
		this(ConsoleCommandManager.SHOT);
		shotLocation = aShotLocation;
	}
        
        Command(Command existingCommand) {
            type = existingCommand.getType();
            fileName = existingCommand.getFileName();
            shotLocation = existingCommand.getShotLocation();
        }
	
	public ConsoleCommandManager getType()	{
		return type;
	}
	
	public String getFileName()	{
		return fileName;
	}
	
	public String getShotLocation()	{
		return shotLocation;
	}

}
