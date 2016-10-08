package com.windels.battleships;

public enum GameState {
		
	INTRO, //nothing allowed, but used so commands aren't set before Intro has finished running.
	IN_PLAY, //allows save, exit to main, make a move
	MAIN_MENU, //allows new, load, exit game
	GAME_ENDED, //allows main menu, exit game
	EXIT;
}
