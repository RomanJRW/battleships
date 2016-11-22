package com.windels.battleships.io.impl;

import com.windels.battleships.controller.Coordinate;
import com.windels.battleships.controller.GameBoard;
import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;

public class ConsoleGridGeneratorTest {
	
	private ConsoleGridGenerator testGridGen;
	private GameBoard mockedGameBoard;

	@Before
	public void setUp() throws Exception {
		testGridGen = new ConsoleGridGenerator();
		mockedGameBoard = mock(GameBoard.class);
	}

	@Test
	public void testGenerateGrid_ShouldGenerateMidGameTallGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(14);
		when(mockedGameBoard.getGridWidth()).thenReturn(8);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true, true, true, true, true, true, true, false, true, true, false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(true, true, true, true, false, false, false, false, true);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - -\n"
				+ "1\t|\tO\tO\tO\tO\tX\tX\tX\t-\n\n\n"
				+ "2\t|\tX\tO\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "11\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "12\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "13\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "14\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateMidGameSquareGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(10);
		when(mockedGameBoard.getGridWidth()).thenReturn(10);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true, true, true, true, true, true, true, false, true, true, false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(true, true, true, true, false, false, false, false, true);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - -\n"
				+ "1\t|\tO\tO\tO\tO\tX\tX\tX\t-\tX\tO\n\n\n"
				+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
		
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateMidGameWideGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(8);
		when(mockedGameBoard.getGridWidth()).thenReturn(14);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true, true, true, true, true, true, true, false, true, true, false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(true, true, true, true, false, false, false, false, true);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\tK\tL\tM\tN\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
				+ "1\t|\tO\tO\tO\tO\tX\tX\tX\t-\tX\tO\t-\t-\t-\t-\n\n\n"
				+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";

		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateEmptyTallGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(14);
		when(mockedGameBoard.getGridWidth()).thenReturn(8);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - -\n"
				+ "1\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "11\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "12\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "13\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "14\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateEmptySquareGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(10);
		when(mockedGameBoard.getGridWidth()).thenReturn(10);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - -\n"
				+ "1\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
		
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateEmptyWideGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(8);
		when(mockedGameBoard.getGridWidth()).thenReturn(14);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(false);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\tK\tL\tM\tN\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
				+ "1\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
				+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";

		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateFullyShotEmptyTallGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(14);
		when(mockedGameBoard.getGridWidth()).thenReturn(8);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - -\n"
				+ "1\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "2\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "3\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "4\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "5\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "6\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "7\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "8\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "9\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "10\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "11\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "12\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "13\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "14\t|\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n";
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateFullyShotEmptySquareGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(10);
		when(mockedGameBoard.getGridWidth()).thenReturn(10);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - -\n"
				+ "1\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "2\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "3\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "4\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "5\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "6\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "7\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "8\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "9\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "10\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n";
		
		
		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
	
	@Test
	public void testGenerateGrid_ShouldGenerateFullyShotEmptyWideGridAsExpected() {
		when(mockedGameBoard.getGridHeight()).thenReturn(8);
		when(mockedGameBoard.getGridWidth()).thenReturn(14);
		when(mockedGameBoard.isCoordinateAlreadyShot(any(Coordinate.class))).thenReturn(true);
		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
		String expectedGrid = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\tK\tL\tM\tN\n"
				+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - - -"
				+ " - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
				+ "1\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "2\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "3\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "4\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "5\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "6\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "7\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
				+ "8\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n";

		assertTrue(testGridGen.generateGrid(mockedGameBoard).equals(expectedGrid));
	}
}
