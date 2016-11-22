package com.windels.battleships.controller;

import static org.junit.Assert.*;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	
	private GameBoard testGameBoardSquare;
	private GameBoard testGameBoardTall;
	private GameBoard testGameBoardWide;
	
	private Ship mockedShip1 = mock(Ship.class);
	private Ship mockedShip2 = mock(Ship.class);
	private Ship mockedShip3 = mock(Ship.class);
	private Ship mockedShip4 = mock(Ship.class);
	private Ship[] mockedShips = {mockedShip1, mockedShip2, mockedShip3, mockedShip4};
	private Coordinate mockedCoord = mock(Coordinate.class);
	
	@Before
	public void setUp() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips);
		testGameBoardTall = new GameBoard(14, 8, mockedShips);
		testGameBoardWide = new GameBoard(8, 14, mockedShips);
	}

	@Test
	public void testIsThereShipInGivenCoordinate_ShouldReturnTrueIfShipExistsOnSquareGrid() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		when(mockedCoord.getRow()).thenReturn(8);
		when(mockedCoord.getColumn()).thenReturn(3);
		
		assertTrue(testGameBoardSquare.isThereShipInGivenCoordinate(mockedCoord));
	}
	
	@Test
	public void testIsThereShipInGivenCoordinate_ShouldReturnTrueIfShipExistsOnTallGrid() {
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		when(mockedCoord.getRow()).thenReturn(13);
		when(mockedCoord.getColumn()).thenReturn(0);
		
		assertTrue(testGameBoardTall.isThereShipInGivenCoordinate(mockedCoord));
	}
	
	@Test
	public void testIsThereShipInGivenCoordinate_ShouldReturnTrueIfShipExistsOnWideGrid() {
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		when(mockedCoord.getRow()).thenReturn(6);
		when(mockedCoord.getColumn()).thenReturn(2);
		
		assertTrue(testGameBoardWide.isThereShipInGivenCoordinate(mockedCoord));
	}

	@Test
	public void testAreAllShipsSunk_ShouldReturnTrueIfAllSunk() {
		when(mockedShip1.isSunk()).thenReturn(true);
		when(mockedShip2.isSunk()).thenReturn(true);
		when(mockedShip3.isSunk()).thenReturn(true);
		when(mockedShip4.isSunk()).thenReturn(true);
		
		assertTrue(testGameBoardSquare.areAllShipsSunk());
		assertTrue(testGameBoardTall.areAllShipsSunk());
		assertTrue(testGameBoardWide.areAllShipsSunk());
	}
	
	@Test
	public void testAreAllShipsSunk_ShouldReturnFalseIfNoneSunk() {
		when(mockedShip1.isSunk()).thenReturn(false);
		when(mockedShip2.isSunk()).thenReturn(false);
		when(mockedShip3.isSunk()).thenReturn(false);
		when(mockedShip4.isSunk()).thenReturn(false);
		
		assertFalse(testGameBoardSquare.areAllShipsSunk());
		assertFalse(testGameBoardTall.areAllShipsSunk());
		assertFalse(testGameBoardWide.areAllShipsSunk());
	}
	
	@Test
	public void testAreAllShipsSunk_ShouldReturnFalseIfSomeAreNotSunk() {
		when(mockedShip1.isSunk()).thenReturn(false);
		when(mockedShip2.isSunk()).thenReturn(true);
		when(mockedShip3.isSunk()).thenReturn(true);
		when(mockedShip4.isSunk()).thenReturn(false);
		
		assertFalse(testGameBoardSquare.areAllShipsSunk());
		assertFalse(testGameBoardTall.areAllShipsSunk());
		assertFalse(testGameBoardWide.areAllShipsSunk());
	}

	@Test
	public void testTakeShotAndGetResult_ShouldReturnHitTypeForSuccessfulShot() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		assertTrue(testGameBoardSquare.takeShotAndGetResult("D4") == ShotResult.HIT);
		assertTrue(testGameBoardTall.takeShotAndGetResult("B5") == ShotResult.HIT);
		assertTrue(testGameBoardWide.takeShotAndGetResult("N1") == ShotResult.HIT);
	}
	
	@Test
	public void testTakeShotAndGetResult_ShouldReturnMissTypeForUnsuccessfulShot() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		assertTrue(testGameBoardSquare.takeShotAndGetResult("A1") == ShotResult.MISS);
		assertTrue(testGameBoardTall.takeShotAndGetResult("A1") == ShotResult.MISS);
		assertTrue(testGameBoardWide.takeShotAndGetResult("A1") == ShotResult.MISS);
	}
	
	@Test
	public void testTakeShotAndGetResult_ShouldReturnDuplicateTypeForAlreadyShotLocation() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		testGameBoardSquare.takeShotAndGetResult("D4");
		testGameBoardTall.takeShotAndGetResult("B5");
		testGameBoardWide.takeShotAndGetResult("N1");
		
		assertTrue(testGameBoardSquare.takeShotAndGetResult("D4") == ShotResult.DUPLICATE);
		assertTrue(testGameBoardTall.takeShotAndGetResult("B5") == ShotResult.DUPLICATE);
		assertTrue(testGameBoardWide.takeShotAndGetResult("N1") == ShotResult.DUPLICATE);
	}
	
	@Test
	public void testTakeShotAndGetResult_ShouldReturnSunkTypeForFinalShotOnShip() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		testGameBoardSquare.takeShotAndGetResult("D4");
		assertTrue(testGameBoardSquare.takeShotAndGetResult("D5") == ShotResult.SUNK);

		testGameBoardTall.takeShotAndGetResult("B5");
		assertTrue(testGameBoardTall.takeShotAndGetResult("B6") == ShotResult.SUNK);

		testGameBoardWide.takeShotAndGetResult("N1");
		assertTrue(testGameBoardWide.takeShotAndGetResult("N2") == ShotResult.SUNK);		
	}
	
	@Test
	public void testTakeShotAndGetResult_ShouldReturnInvalidTypeForOutOfBoundsCoordinates() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		assertTrue(testGameBoardSquare.takeShotAndGetResult("B35") == ShotResult.INVALID);
		assertTrue(testGameBoardTall.takeShotAndGetResult("P5") == ShotResult.INVALID);
		assertTrue(testGameBoardWide.takeShotAndGetResult("Z50") == ShotResult.INVALID);
	}
	
	@Test
	public void testTakeShotAndGetResult_ShouldReturnInvalidTypeForIncorrectFormatCoordinates() {
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10);
		testGameBoardTall = new GameBoard(14, 8, mockedShips, 20);
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30);
		
		assertTrue(testGameBoardSquare.takeShotAndGetResult("Bwsfifw") == ShotResult.INVALID);
		assertTrue(testGameBoardTall.takeShotAndGetResult("&*@^*") == ShotResult.INVALID);
		assertTrue(testGameBoardWide.takeShotAndGetResult(";sq'dlq") == ShotResult.INVALID);
	}
	
	@Test
	public void testGetNumberOfRemainingShips_ShouldReturnCorrectNumberIfSomeAreSunk()	{
		when(mockedShip1.isSunk()).thenReturn(true);
		when(mockedShip2.isSunk()).thenReturn(false);
		when(mockedShip3.isSunk()).thenReturn(false);
		when(mockedShip4.isSunk()).thenReturn(true);
		
		assertTrue(testGameBoardSquare.getNumberOfRemainingShips() == 2);
		assertTrue(testGameBoardTall.getNumberOfRemainingShips() == 2);
		assertTrue(testGameBoardWide.getNumberOfRemainingShips() == 2);
	}

	@Test
	public void testGetNumberOfRemainingShips_ShouldReturnZeroIfAllAreSunk()	{
		when(mockedShip1.isSunk()).thenReturn(true);
		when(mockedShip2.isSunk()).thenReturn(true);
		when(mockedShip3.isSunk()).thenReturn(true);
		when(mockedShip4.isSunk()).thenReturn(true);
		
		assertTrue(testGameBoardSquare.getNumberOfRemainingShips() == 0);
		assertTrue(testGameBoardTall.getNumberOfRemainingShips() == 0);
		assertTrue(testGameBoardWide.getNumberOfRemainingShips() == 0);
	}
	
	@Test
	public void testGenerateAndPlaceShipsOnGrid_ShouldSuccessfullyPlaceShipsAsExpectedOnWideGrid()	{
		testGameBoardWide = new GameBoard(8, 14, mockedShips, 30); //method under test is called from within the constructor
		when(mockedCoord.getRow()).thenReturn(0, 1, 1, 2, 3, 3, 4, 5, 6, 2, 3, 4, 5, 6);
		when(mockedCoord.getColumn()).thenReturn(13, 13, 10, 10, 10, 2, 2, 2, 2, 9, 9, 9, 9, 9);
		int shipParts = 0;
		for (Ship aShip : testGameBoardWide.getShips())	{
			shipParts += aShip.getSize();
		}
		for (int i = 0; i < shipParts; i ++)	{
			assertTrue(testGameBoardWide.isThereShipInGivenCoordinate(mockedCoord));			
		}
	}
	
	@Test
	public void testGenerateAndPlaceShipsOnGrid_ShouldSuccessfullyPlaceShipsAsExpectedOnTallGrid()	{
		testGameBoardTall = new GameBoard(8, 14, mockedShips, 20); //method under test is called from within the constructor
		when(mockedCoord.getRow()).thenReturn(4, 5, 0, 1, 2, 4, 4, 4, 4, 0, 0, 0, 0, 0);
		when(mockedCoord.getColumn()).thenReturn(1, 1, 11, 11, 11, 3, 4, 5, 6, 1, 2, 3, 4, 5);
		int shipParts = 0;
		for (Ship aShip : testGameBoardTall.getShips())	{
			shipParts += aShip.getSize();
		}
		for (int i = 0; i < shipParts; i ++)	{
			assertTrue(testGameBoardTall.isThereShipInGivenCoordinate(mockedCoord));			
		}
	}
	
	@Test
	public void testGenerateAndPlaceShipsOnGrid_ShouldSuccessfullyPlaceShipsAsExpectedOnSquareGrid()	{
		testGameBoardSquare = new GameBoard(10, 10, mockedShips, 10); //method under test is called from within the constructor
		when(mockedCoord.getRow()).thenReturn(3, 4, 6, 6, 6, 8, 8, 8, 8, 3, 3, 3, 3, 3);
		when(mockedCoord.getColumn()).thenReturn(3, 3, 6, 7, 8, 3, 4, 5, 6, 4, 5, 6, 7, 8);
		int shipParts = 0;
		for (Ship aShip : testGameBoardSquare.getShips())	{
			shipParts += aShip.getSize();
		}
		for (int i = 0; i < shipParts; i ++)	{
			assertTrue(testGameBoardSquare.isThereShipInGivenCoordinate(mockedCoord));			
		}
	}
}
