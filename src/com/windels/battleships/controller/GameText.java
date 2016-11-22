package com.windels.battleships.controller;

public enum GameText {
	
	INTRO(new StringBuilder("")
		.append("\n")
		.append("           ****************************************************************" + "\n")
		.append("           *              Welcome to Battleshite v3, Captain.             *" + "\n")
		.append("           *     Your objective is to shoot all 4 of the enemy ships.     *" + "\n")
		.append("           *       When prompted, enter a coordinate from the grid        *" + "\n")
		.append("           *        Try to use as few shots as you can. Good Luck!        *" + "\n")
		.append("           ****************************************************************" + "\n")
		.toString()),
			
	MAINMENU(new StringBuilder("")
		.append("\n")
		.append("           ****************************************************************" + "\n")
		.append("           *                   You are at the main menu.                  *" + "\n")
		.append("           ****************************************************************" + "\n")
		.toString()),
	
	SAVEERROR("There was a problem when saving the game. Game not saved"),
	
	UNAVAILABLEINPUT("That command is not available at this point"),
	
	INVALIDSHOT("That wasn't a valid shot, you fool!"),
	
	SHOTPROMPT("Where would you like to shoot next captain? (ie. A1, D4)"),
	
	SAVESUCCESS("Game saved successfully"),
	
	SHIPSREMAINING("Number of ships remaining: "),
	
	LOADSUCCESS("Game loaded successfully"),
	
	LOADERROR("There was a problem when attempting to load the game. Game not loaded"),
	
	INVALIDCOMMAND("Command not valid"),
	
	GAMEENDING(new StringBuilder()
		.append("           ****************************************************************" + "\n")
		.append("           *                  Congratulations, Captain!                   *" + "\n")
		.append("           *     You have located and destroyed all of the enemy ships    *" + "\n")
		.append("           ****************************************************************" + "\n")
		.toString());

	private final String text;
	
	GameText(String someText)	{
		text = someText;
	}
	
	String getText()	{
		return text;
	}
}
