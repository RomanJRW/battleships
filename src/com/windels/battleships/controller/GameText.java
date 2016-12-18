package com.windels.battleships.controller;

public enum GameText {
	
	INTRO(new StringBuilder("")
		.append("Welcome to Battleshite, Captain." + "\n")
		.append("Your objective is to shoot all of the enemy ships." + "\n")
		.append("Try to use as few shots as you can. Good Luck!" + "\n")
		.toString()),
	
	SAVEERROR("There was a problem when saving the game. Game not saved"),
	
	UNAVAILABLEINPUT("That command is not available at this point"),
	
	INVALIDSHOT("That wasn't a valid shot, you fool!"),
	
	SHOTPROMPT("Where would you like to shoot captain?"),
	
	SAVESUCCESS("Game saved successfully"),
	
	SHIPSREMAINING("Number of ships remaining: "),
	
	LOADSUCCESS("Game loaded successfully"),
	
	LOADERROR("There was a problem when attempting to load the game. Game not loaded"),
	
	INVALIDCOMMAND("Command not valid"),
	
	GAMEENDING(new StringBuilder()
		.append("Congratulations, Captain!" + "\n")
		.append("You have located and destroyed all of the enemy ships" + "\n")
		.toString()),
        
        NEWGAME("New game started");

	private final String text;
	
	GameText(String someText)	{
		text = someText;
	}
	
	String getText()	{
		return text;
	}
}
