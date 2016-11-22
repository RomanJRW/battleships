package com.windels.battleships.io;

import com.windels.battleships.io.impl.Command;

public interface Input {
	
	void close();
	Command getInput();
	String getHelpText();

}
