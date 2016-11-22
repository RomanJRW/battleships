package com.windels.battleships.io;

import com.windels.battleships.ConsoleCommandManager;

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
	
	ConsoleCommandManager getType()	{
		return type;
	}
	
	String getFileName()	{
		return fileName;
	}
	
	String getShotLocation()	{
		return shotLocation;
	}

}
