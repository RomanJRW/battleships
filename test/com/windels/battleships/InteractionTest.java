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
//	private Interaction interact;
//	private GameBoard mockedGameBoard;
//	
//	@Before
//	public void setUp() throws Exception {
//		interact = new Interaction();
//		mockedGameBoard = mock(GameBoard.class);
//	}
//
//	@Test
//	public void testGetGameIntroductionText_ShouldReturnStringWithIntroText() {
//		String expectedText = "\n"
//							+ "           ****************************************************************\n"
//							+ "           *              Welcome to Battleshite v3, Captain.             *\n"
//							+ "           *     Your objective is to shoot all 4 of the enemy ships.     *\n"
//							+ "           *       When prompted, enter a coordinate from the grid        *\n"
//							+ "           *        Try to use as few shots as you can. Good Luck!        *\n"
//							+ "           ****************************************************************\n\n"; 
//		String resultText = interact.getGameIntroductionText();
//		assertTrue(expectedText.equals(resultText));
//	}
//	
//	@Test
//	public void testGetGameEndgingText_ShouldReturnStringWithEndingTextAndCorrectTotalShots() {
//		when(mockedGameBoard.getTotalShotsTaken()).thenReturn(65);
//		String expectedText = "\n"
//							+ "           ****************************************************************\n"
//							+ "           *                  Congratulations, Captain!                   *\n"
//							+ "           *     You have located and destroyed all of the enemy ships    *\n"
//							+ "           *                  Total number of shots: " + 65 + "                   *\n"
//							+ "           ****************************************************************\n\n"; 	
//		String resultText = interact.getGameEndingText(mockedGameBoard);
//		
//		assertTrue(expectedText.equals(resultText));
//	}
//	
//	@Test
//	public void testGetNumberOfRemainingShipsText_ShouldReturnStringWithNumberOfRemainingShipsAndText()	{
//		when(mockedGameBoard.getNumberOfRemainingShips()).thenReturn(2);
//		String expectedText = "There are still 2 left to find!\n";
//		String resultText = interact.getNumberOfRemainingShipsText(mockedGameBoard);
//		assertTrue(expectedText.equals(resultText));
//	}
//	
//	@Test
//	public void testGetGridToDisplayText_ShouldPrintEmptyGrid()	{
//		when(mockedGameBoard.getGridHeight()).thenReturn(10);
//		when(mockedGameBoard.getGridWidth()).thenReturn(10);
//		when(mockedGameBoard.hasCoordinateOnGridAlreadyBeenShot(any(Coordinate.class))).thenReturn(false);
//		
//		String expectedSquareGridText = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\n"
//								+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
//								+ "1\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "2\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
//		
//		String resultSquareGridText = interact.getGridToDisplayText(mockedGameBoard);
//		assertTrue(expectedSquareGridText.equals(resultSquareGridText));
//		
//		//AS WITH OTHER TEST CASES, I'VE LEFT THIS ONE FOR NOW AS JUST TESTING A SQUAE GRID. WOULD BE EASY TO DUPLICATE
//		//FOR TALL AND WIDE GRIDS IF THE APPROACH IS OK. BRING UP WITH LUKE
//	}
//	
//	@Test
//	public void testGetGridToDisplayText_ShouldPrintFullyShotAndEmptyGrid()	{
//		when(mockedGameBoard.getGridHeight()).thenReturn(8);
//		when(mockedGameBoard.getGridWidth()).thenReturn(14);
//		when(mockedGameBoard.hasCoordinateOnGridAlreadyBeenShot(any(Coordinate.class))).thenReturn(true);
//		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(false);
//		
//		String expectedWideGridText = "\t\tA\tB\tC\tD\tE\tF\tG\tH\tI\tJ\tK\tL\tM\tN\n"
//								+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
//								+ " - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n"
//								+ "1\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "2\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "3\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "4\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "5\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "6\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "7\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n"
//								+ "8\t|\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\tX\n\n\n";
//		
//		String resultWideGridText = interact.getGridToDisplayText(mockedGameBoard);
//		assertTrue(expectedWideGridText.equals(resultWideGridText));
//		
//		//JUST AS A CHANGE TO THE ABOVE METHOD AND FOR PROOF OF HOW EASY IT IS TO USE DIFFERENT GRID SIZES, THIS
//		//ONE IS DONE WITH A WIDE GRID (14 X 8)
//	}
//
//	@Test
//	public void testGetGridToDisplayText_ShouldPrintMidGameStateGrid()	{
//		when(mockedGameBoard.getGridHeight()).thenReturn(14);
//		when(mockedGameBoard.getGridWidth()).thenReturn(8);
//		when(mockedGameBoard.hasCoordinateOnGridAlreadyBeenShot(any(Coordinate.class))).thenReturn(true, true, true, true, true, true, true, false, true, true, false);
//		when(mockedGameBoard.isThereShipInGivenCoordinate(any(Coordinate.class))).thenReturn(true, true, true, true, false, false, false, false, true);
//		
//		String expectedTallGridText = "\t\tA\tB\tC\tD\tE\tF\tG\tH\n"
//								+ "\t+- - - - - - - - - - - - - - - - - - - - - - - - - - -"
//								+ " - - - - - - -\n"
//								+ "1\t|\tO\tO\tO\tO\tX\tX\tX\t-\n\n\n"
//								+ "2\t|\tX\tO\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "3\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "4\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "5\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "6\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "7\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "8\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "9\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "10\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "11\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "12\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "13\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n"
//								+ "14\t|\t-\t-\t-\t-\t-\t-\t-\t-\n\n\n";
//		
//		String resultTallGridText = interact.getGridToDisplayText(mockedGameBoard);		
//		assertTrue(expectedTallGridText.equals(resultTallGridText));
//		
//		//JUST AS A CHANGE TO THE ABOVE METHOD AND FOR PROOF OF HOW EASY IT IS TO USE DIFFERENT GRID SIZES, THIS
//		//ONE IS DONE WITH A TALL GRID (8 X 14)
//	}
//	
//	@Test
//	public void testRequestUserGridHeightSelection_ShouldReturnGivenValidUserInput()	{
//		String givenInput = "9";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 9;
//		int actualResult = mockedScanInteract.requestUserGridHeightSelection();
//		
//		assertTrue(expectedResult == actualResult);
//		
//	}
//	
//	@Test
//	public void testRequestUserGridHeightSelection_ShouldReturnGivenValidUserInputAfterMultipleInvalidAttemptsForHeight()	{
//		String givenInput = "15 7 ; [ 8";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 8;
//		int actualResult = mockedScanInteract.requestUserGridHeightSelection();
//		
//		assertTrue(expectedResult == actualResult);
//	}
//	
//	@Test
//	public void testRequestUserGridWidthSelection_ShouldReturnGivenUserInput()	{
//		String givenInput = "12";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 12;
//		int actualResult = mockedScanInteract.requestUserGridWidthSelection();
//		
//		assertTrue(expectedResult == actualResult);
//	}
//	
//	@Test
//	public void testRequestUserGridWidthSelection_ShouldReturnGivenValidUserInputAfterMultipleInvalidAttemptsForWidth()	{
//		String givenInput = "15 7 b * 14";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 14;
//		int actualResult = mockedScanInteract.requestUserGridWidthSelection();
//		
//		assertTrue(expectedResult == actualResult);
//	}
//
//	@Test
//	public void testRequestUserColumnShot_ShouldReturnGivenUserInputWhenFirstInputIsValidColumn() {
//		when(mockedGameBoard.getGridWidth()).thenReturn(13);
//		String givenInput = "A";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 0; //Compatibility check changes move from actual input
//		int actualResult = mockedScanInteract.requestUserColumnShot(mockedGameBoard);
//		
//		assertTrue(expectedResult == actualResult);
//	}
//	
//	@Test
//	public void testRequestUserColumnShot_ShouldReturnGivenValidUserInputAfterCheckingMultipleInvalidAttemptsForColumn() {
//		when(mockedGameBoard.getGridWidth()).thenReturn(14);
//		String givenInput = "O 6 C";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 2; //Compatibility check changes move from actual input
//		int actualResult = mockedScanInteract.requestUserColumnShot(mockedGameBoard);
//		
//		assertTrue(expectedResult == actualResult);
//	}
//
//	@Test
//	public void testRequestUserRowShot_ShouldReturnGivenUserInputWhenFirstInputIsValidRow()	{
//		when(mockedGameBoard.getGridHeight()).thenReturn(10);
//		String givenInput = "6";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 5; //Compatibility check changes move from actual input
//		int actualResult = mockedScanInteract.requestUserRowShot(mockedGameBoard);
//		
//		assertTrue(expectedResult == actualResult);
//	}
//	
//	@Test
//	public void testRequestUserRowShot_ShouldReturnGivenValidUserInputAfterCheckingMultipleInvalidAttemptsForRow() {
//		when(mockedGameBoard.getGridHeight()).thenReturn(14);
//		String givenInput = "O 15 14";
//		Interaction mockedScanInteract = new Interaction(givenInput);
//		
//		int expectedResult = 13; //Compatibility check changes move from actual input
//		int actualResult = mockedScanInteract.requestUserRowShot(mockedGameBoard);
//		
//		assertTrue(expectedResult == actualResult);
//	}
//}
