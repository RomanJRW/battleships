package com.windels.battleships.io.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class ConsoleInputTest {
	
	private ConsoleInput testConIn;
	
	@After
	public void tearDown()	{
		testConIn.close();
	}

	@Test
	public void testGetInput_ShouldReturnMainMenuCommand() {
		testConIn = new ConsoleInput("!m");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.MAINMENU);
	}
	
	@Test
	public void testGetInput_ShouldReturnExitGameCommand() {
		testConIn = new ConsoleInput("!q");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.EXITGAME);
	}
	
	@Test
	public void testGetInput_ShouldReturnNewGameCommand() {
		testConIn = new ConsoleInput("!n");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.NEWGAME);
	}
	
	@Test
	public void testGetInput_ShouldReturnListGamesCommand() {
		testConIn = new ConsoleInput("!g");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.LISTGAMES);
	}
	
	@Test
	public void testGetInput_ShouldReturnHelpMenuCommand() {
		testConIn = new ConsoleInput("!h");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.HELPMENU);
	}
	
	@Test
	public void testGetInput_ShouldReturnLoadGameCommand() {
		testConIn = new ConsoleInput("!l (test file name)");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.LOADGAME);
	}
	
	@Test
	public void testGetInput_ShouldReturnSaveGameCommand() {
		testConIn = new ConsoleInput("!s (test file name)");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.SAVEGAME);
	}
	
	@Test
	public void testGetInput_ShouldReturnShotCommandForNonOptionInputUsingValidCoordinates() {
		testConIn = new ConsoleInput("B2");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.SHOT);
	}
	
	@Test
	public void testGetInput_ShouldReturnShotCommandForNonOptionInputUsingInvalidCoordinates() {
		testConIn = new ConsoleInput("testTESTinvalidCoords");
		assertTrue(testConIn.getUserInput().getType() == ConsoleCommandManager.SHOT);
	}

	@Test
	public void testGetHelpText_ShouldDisplayAsExpected() {
		testConIn = new ConsoleInput();
		String expectedText = "Here are the available commands. Please note, "
				+ "not all commands may be available\nat any given time\n"
				+ "!n\t\t\t-\t" + "New game\n"
				+"!m\t\t\t-\t" + "Go to main menu\n"
				+"!q\t\t\t-\t" + "Exit Battleships\n"
				+"!g\t\t\t-\t" + "List saved games\n"
				+"!h\t\t\t-\t" + "Open help menu\n"
				+"!l + (file name)\t-\t" + "Load an existing game\n"
				+"!s + (file name)\t-\t" + "Save the current game\n";

		assertTrue(expectedText.equals(testConIn.getHelpText()));
	}

}
