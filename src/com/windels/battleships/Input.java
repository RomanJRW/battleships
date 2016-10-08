package com.windels.battleships;

public interface Input {

	void runInput(Battleships bsGamae);
	void close();
	String getInputInstructions();
	String getInput();
}
