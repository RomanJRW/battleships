/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.windels.battleships;

/**
 *
 * @author joshw
 */
public class Application {
    
    public static void main(String[] args) {
		Input in = null;
		Output out = null;
		if (args.length == 1 && args[0] == "-gui")	{
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
