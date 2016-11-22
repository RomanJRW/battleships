/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windels.battleships;

import com.windels.battleships.controller.Battleships;
import com.windels.battleships.io.impl.ConsoleInput;
import com.windels.battleships.io.impl.ConsoleOutput;
//import com.windels.battleships.io.impl.guiInput;
//import com.windels.battleships.io.impl.guiOutput;
import com.windels.battleships.io.Input;
import com.windels.battleships.io.Output;

/**
 *
 * @author joshw
 */
public class Application {
    
    public static void main(String[] args) {
		Input in = null;
		Output out = null;
		if (args.length == 1 && args[0].equals("-gui"))	{
			//in = new GuiInput();
			//out = new GuiOutput();
			System.out.println("This is where I would initialize in and out to gui objects");
		}
		else if (args.length > 0)	{
			System.out.println("Invalid arguments provided");
			System.exit(1);
		}
		else	{
			in = new ConsoleInput();
			out = new ConsoleOutput();
		}
		Battleships bs = new Battleships(in, out);
		bs.run();
	}
}