//package com.windels.battleships;
//
//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.mockito.Mockito.*;
//
//public class GameBoardTest {
//	
//	private GameBoard testGBsquare;
//	private GameBoard testGBtall;
//	private GameBoard testGBwide;
//	
//	private Ship mockedShip1 = mock(Ship.class);
//	private Ship mockedShip2 = mock(Ship.class);
//	private Ship mockedShip3 = mock(Ship.class);
//	private Ship mockedShip4 = mock(Ship.class);
//	private Ship[] mockedShips = {mockedShip1, mockedShip2, mockedShip3, mockedShip4};
//	private Coordinate mockedCoord = mock(Coordinate.class);
//	
//	@Before
//	public void setUp() throws Exception {
//		testGBsquare = new GameBoard(10, 10, mockedShips);
//		testGBtall = new GameBoard(14, 8, mockedShips);
//		testGBwide = new GameBoard(8, 14, mockedShips);
//	}
//
//	@Test
//	public void testGetNumberOfRemainingShips_ShouldReturnPositiveIntegerOfUnsunkShips() {
//		when(mockedShip1.isSunk()).thenReturn(true);
//		when(mockedShip2.isSunk()).thenReturn(false);
//		when(mockedShip3.isSunk()).thenReturn(false);
//		when(mockedShip4.isSunk()).thenReturn(true);
//		
//		assertEquals(2, testGBsquare.getNumberOfRemainingShips());
//		assertEquals(2, testGBtall.getNumberOfRemainingShips());
//		assertEquals(2, testGBwide.getNumberOfRemainingShips());
//	}
//	
//	@Test
//	public void testGetNumberOfRemainingShips_ShouldReturnZeroIfAllSunk() {
//		when(mockedShip1.isSunk()).thenReturn(true);
//		when(mockedShip2.isSunk()).thenReturn(true);
//		when(mockedShip3.isSunk()).thenReturn(true);
//		when(mockedShip4.isSunk()).thenReturn(true);
//
//		assertEquals(0, testGBsquare.getNumberOfRemainingShips());
//		assertEquals(0, testGBtall.getNumberOfRemainingShips());
//		assertEquals(0, testGBwide.getNumberOfRemainingShips());
//	}
//
//	@Test
//	public void testIsThereAShipInGivenCoordinate_ShouldReturnTrueIfShipExists() {
//		when(mockedCoord.getColumn()).thenReturn(1);
//		when(mockedCoord.getRow()).thenReturn(5);
//		
//		testGBsquare.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBtall.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBwide.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		
//		assertTrue(testGBsquare.isThereShipInGivenCoordinate(mockedCoord));
//		assertTrue(testGBtall.isThereShipInGivenCoordinate(mockedCoord));
//		assertTrue(testGBwide.isThereShipInGivenCoordinate(mockedCoord));
//	}
//	
//	@Test
//	public void testIsThereAShipInGivenCoordinate_ShouldReturnFalseIfShipDoesNotExist() {
//		when(mockedCoord.getColumn()).thenReturn(5);
//		when(mockedCoord.getRow()).thenReturn(1);
//		
//		assertFalse(testGBsquare.isThereShipInGivenCoordinate(mockedCoord));
//		assertFalse(testGBtall.isThereShipInGivenCoordinate(mockedCoord));
//		assertFalse(testGBwide.isThereShipInGivenCoordinate(mockedCoord));
//	}
//	
//	@Test(expected = ArrayIndexOutOfBoundsException.class)
//	public void testIsThereAShipInGivenCoordinate_ShouldThrowExceptionIfCoordinateDoesNotExist() {
//		when(mockedCoord.getColumn()).thenReturn(5);
//		when(mockedCoord.getRow()).thenReturn(15);
//		
//		testGBsquare.isThereShipInGivenCoordinate(mockedCoord);
//	}
//
//	@Test(expected = ArrayIndexOutOfBoundsException.class)
//	public void testAttemptShot_ShouldThrowExceptionWithNonExistantColumn() {
//		when(mockedCoord.getColumn()).thenReturn(11);
//		when(mockedCoord.getRow()).thenReturn(4);
//		
//		testGBsquare.attemptShot(mockedCoord);
//	}
//	
//	@Test(expected = ArrayIndexOutOfBoundsException.class)
//	public void testAttemptShot_ShouldThrowExceptionWithNonExistantRow() {
//		when(mockedCoord.getColumn()).thenReturn(8);
//		when(mockedCoord.getRow()).thenReturn(15);
//		
//		testGBsquare.attemptShot(mockedCoord);
//	}
//	
//	@Test
//	public void testAttemptShot_ShouldReturnMissIfShipNotFoundAtCoordinate() {
//		when(mockedCoord.getColumn()).thenReturn(7);
//		when(mockedCoord.getRow()).thenReturn(7);
//		//THIS SHOULD MISS BY DEFAULT, AS NO SHOTS HAVE BEEN REGISTERED, NOR SHIPS PLACED
//		
//		assertEquals(ShotResult.MISS, testGBsquare.attemptShot(mockedCoord));
//		assertEquals(ShotResult.MISS, testGBtall.attemptShot(mockedCoord));
//		assertEquals(ShotResult.MISS, testGBwide.attemptShot(mockedCoord));
//	}
//	
//	@Test
//	public void testAttemptShot_ShouldReturnDuplicateIfCoordinatesShotAlreadyAttempted() {
//		when(mockedCoord.getColumn()).thenReturn(2);
//		when(mockedCoord.getRow()).thenReturn(2);
//		testGBsquare.setCoordinateOnGridAsShot(mockedCoord);
//		testGBtall.setCoordinateOnGridAsShot(mockedCoord);
//		testGBwide.setCoordinateOnGridAsShot(mockedCoord);
//		
//		assertEquals(ShotResult.DUPLICATE, testGBsquare.attemptShot(mockedCoord));
//		assertEquals(ShotResult.DUPLICATE, testGBtall.attemptShot(mockedCoord));
//		assertEquals(ShotResult.DUPLICATE, testGBwide.attemptShot(mockedCoord));
//	}
//	
//	@Test
//	public void testAttemptShot_ShouldReturnHitIfShipFoundAtValidCoordinate() {
//		when(mockedCoord.getColumn()).thenReturn(4);
//		when(mockedCoord.getRow()).thenReturn(4);
//		when(mockedShip1.isSunk()).thenReturn(false);
//		testGBsquare.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBtall.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBwide.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		
//		assertEquals(ShotResult.HIT, testGBsquare.attemptShot(mockedCoord));
//		assertEquals(ShotResult.HIT, testGBtall.attemptShot(mockedCoord));
//		assertEquals(ShotResult.HIT, testGBwide.attemptShot(mockedCoord));
//	}
//	
//	
//	@Test
//	public void testAttemptShot_ShouldReturnSunkIfCoordinatesHitsRemainingPartOfShip() {
//		when(mockedCoord.getColumn()).thenReturn(6);
//		when(mockedCoord.getRow()).thenReturn(6);
//		when(mockedShip1.isSunk()).thenReturn(true);
//		testGBsquare.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBtall.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		testGBwide.placeShipOnGivenGridCoordinate(mockedCoord, mockedShip1);
//		
//		assertEquals(ShotResult.SUNK, testGBsquare.attemptShot(mockedCoord));
//		assertEquals(ShotResult.SUNK, testGBtall.attemptShot(mockedCoord));
//		assertEquals(ShotResult.SUNK, testGBwide.attemptShot(mockedCoord));
//	}
//	
//	@Test
//	public void testGenerateAndPlaceShipsOnGrid_ShouldReturnValidArrayOfCoordinates() {
//		GameBoard seededTestGBsquare = new GameBoard(10, 10, mockedShips, 10);
//		//GameBoard seededTestGBtall = new GameBoard(14, 8, mockedShips, 20);
//		//GameBoard seededTestGBwide = new GameBoard(8, 14, mockedShips, 30);
//		
//		Coordinate coord1 = new Coordinate(6, 2);
//		Coordinate coord2 = new Coordinate(9, 6);
//		Coordinate coord3 = new Coordinate(2, 2);
//		Coordinate coord4 = new Coordinate(0, 0);
//		Coordinate[] expectedShipCoordsSquare = {coord1, coord2, coord3, coord4};
//		Coordinate[] resultsShipCoordsSquare = seededTestGBsquare.generateAndPlaceShipsOnGrid();
//		
//		for (int i = 0; i < seededTestGBsquare.getStartingNumberOfShips(); i ++)	{
//			//System.out.println(expectedShipCoordsSquare[i].toString());
//			//System.out.println(resultsShipCoordsSquare[i].toString());
//			assert(expectedShipCoordsSquare[i].equals(resultsShipCoordsSquare[i]));
//		}
//		
//		//CURRENTLY ONLY IMPLEMENTED FOR SQUARE GRID. WOULD BE EASY TO ADD TO THIS CLASS WITH TALL AND WIDE
//		//GRIDS BUT WANT TO CHECK THAT THIS APPROACH IS OK. IS IT OK TO USE A LOOP IN A TEST METHOD FOR INSTANCE?
//		//AND WOULD IT MATTER TO HAVE AN EXCEPTIONALLY LONG TEST METHOD (THREE TIMES THIS WITH DIFFERENT GRID SIZE
//		//VARIATIONS)? ALSO, THIS ONLY TESTS THAT THE METHOD WORKS. CAN'T THINK OF ANYTHING ELSE TO TEST WITH THIS
//		//METHOD
//	}





	//package com.windels.battleships;
	//
	//
	//
	//import static org.junit.Assert.*;
	//import static org.mockito.Mockito.mock;
	//import static org.mockito.Mockito.when;
	//import static org.mockito.Matchers.any;
	//
	//import org.junit.Before;
	//import org.junit.Test;
	//
	//public class InteractionTest {
	//
//		private Interaction interact;
//		private GameBoard mockedGameBoard;
	//	
//		@Before
//		public void setUp() throws Exception {
//			interact = new Interaction();
//			mockedGameBoard = mock(GameBoard.class);
//		}
	//
//		
	//	
//		@Test
//		public void testGetNumberOfRemainingShipsText_ShouldReturnStringWithNumberOfRemainingShipsAndText()	{
//			when(mockedGameBoard.getNumberOfRemainingShips()).thenReturn(2);
//			String expectedText = "There are still 2 left to find!\n";
//			String resultText = interact.getNumberOfRemainingShipsText(mockedGameBoard);
//			assertTrue(expectedText.equals(resultText));
//		}
	//	
//		
	//	
//		@Test
//		public void testRequestUserGridHeightSelection_ShouldReturnGivenValidUserInput()	{
//			String givenInput = "9";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 9;
//			int actualResult = mockedScanInteract.requestUserGridHeightSelection();
//			
//			assertTrue(expectedResult == actualResult);
//			
//		}
	//	
//		@Test
//		public void testRequestUserGridHeightSelection_ShouldReturnGivenValidUserInputAfterMultipleInvalidAttemptsForHeight()	{
//			String givenInput = "15 7 ; [ 8";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 8;
//			int actualResult = mockedScanInteract.requestUserGridHeightSelection();
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//	
//		@Test
//		public void testRequestUserGridWidthSelection_ShouldReturnGivenUserInput()	{
//			String givenInput = "12";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 12;
//			int actualResult = mockedScanInteract.requestUserGridWidthSelection();
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//	
//		@Test
//		public void testRequestUserGridWidthSelection_ShouldReturnGivenValidUserInputAfterMultipleInvalidAttemptsForWidth()	{
//			String givenInput = "15 7 b * 14";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 14;
//			int actualResult = mockedScanInteract.requestUserGridWidthSelection();
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//
//		@Test
//		public void testRequestUserColumnShot_ShouldReturnGivenUserInputWhenFirstInputIsValidColumn() {
//			when(mockedGameBoard.getGridWidth()).thenReturn(13);
//			String givenInput = "A";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 0; //Compatibility check changes move from actual input
//			int actualResult = mockedScanInteract.requestUserColumnShot(mockedGameBoard);
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//	
//		@Test
//		public void testRequestUserColumnShot_ShouldReturnGivenValidUserInputAfterCheckingMultipleInvalidAttemptsForColumn() {
//			when(mockedGameBoard.getGridWidth()).thenReturn(14);
//			String givenInput = "O 6 C";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 2; //Compatibility check changes move from actual input
//			int actualResult = mockedScanInteract.requestUserColumnShot(mockedGameBoard);
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//
//		@Test
//		public void testRequestUserRowShot_ShouldReturnGivenUserInputWhenFirstInputIsValidRow()	{
//			when(mockedGameBoard.getGridHeight()).thenReturn(10);
//			String givenInput = "6";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 5; //Compatibility check changes move from actual input
//			int actualResult = mockedScanInteract.requestUserRowShot(mockedGameBoard);
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//	
//		@Test
//		public void testRequestUserRowShot_ShouldReturnGivenValidUserInputAfterCheckingMultipleInvalidAttemptsForRow() {
//			when(mockedGameBoard.getGridHeight()).thenReturn(14);
//			String givenInput = "O 15 14";
//			Interaction mockedScanInteract = new Interaction(givenInput);
//			
//			int expectedResult = 13; //Compatibility check changes move from actual input
//			int actualResult = mockedScanInteract.requestUserRowShot(mockedGameBoard);
//			
//			assertTrue(expectedResult == actualResult);
//		}
	//}
//
//}
