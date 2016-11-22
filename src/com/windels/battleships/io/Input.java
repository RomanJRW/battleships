package com.windels.battleships.io;

public interface Input {
	
	void close();
	Command getInput();
	String getHelpText();

}
