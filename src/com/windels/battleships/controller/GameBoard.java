package com.windels.battleships.controller;
import java.util.Arrays;
import java.util.Random;

public class GameBoard implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int totalNumberOfShotsTaken;
	private int gridWidth;
	private int gridHeight;
	
	private Ship[][] shipGrid;
	private Ship[] ships;
	private boolean[][] shotGrid;
	private int numberOfShips;
	
	private Random rand;
	
	public GameBoard(int aGridHeight, int aGridWidth, Ship[] someShips)	{
		gridWidth = aGridWidth;
		gridHeight = aGridHeight;
		shipGrid = new Ship[gridHeight][gridWidth];
		shotGrid = new boolean[gridHeight][gridWidth];
		totalNumberOfShotsTaken = 0;
		ships = someShips;
		numberOfShips = ships.length;
		rand = new Random();
	}
	
	GameBoard(int aGridHeight, int aGridWidth, Ship[] someShips, long seed)	{
		this(aGridHeight, aGridWidth, someShips);
		this.rand = new Random(seed);
		generateAndPlaceShipsOnGrid();
	}
	
	
	public int getGridWidth()	{
		return gridWidth;
	}
	
	public int getGridHeight()	{
		return gridHeight;
	}
	
	public int getStartingNumberOfShips()	{
		return numberOfShips;
	}
	
	public int getNumberOfRemainingShips()	{
		int shipsRemaining = 0;
		for (int i = 0; i < numberOfShips; i ++)	{
			if (!ships[i].isSunk())	{
				shipsRemaining ++;
			}
		}
		return shipsRemaining;
	}
	
	public int getTotalShotsTaken()	{
		return totalNumberOfShotsTaken;
	}
	
	public boolean isCoordinateAlreadyShot(Coordinate coordinate)	{
		return shotGrid[coordinate.getRow()][coordinate.getColumn()];
	}
	
	public boolean isThereShipInGivenCoordinate(Coordinate coordinate)	{
		if (shipGrid[coordinate.getRow()][coordinate.getColumn()] != null)	{
			return true;
		}
		return false;
	}
	
	public boolean areAllShipsSunk()	{
		return getNumberOfRemainingShips() == 0;
	}	
	
	ShotResult takeShotAndGetResult(String shot) {
		try	{
			Coordinate shotCoord = convertInputToCoordinate(shot);
			return takeGridCompatibleShotAndGetResult(shotCoord);
		}
		catch (NumberFormatException ex)	{
			return ShotResult.INVALID;
		}
		catch (ClassCastException ex)	{
			return ShotResult.INVALID;
		}
		catch (ArrayIndexOutOfBoundsException ex)	{
			return ShotResult.INVALID;
		}
	}
	
	void generateAndPlaceShipsOnGrid()	{
		generateShips();
		placeGeneratedShipsOnGrid();
	}
	
	Ship[] getShips()	{
		Ship[] shipsCopy = Arrays.copyOf(ships, ships.length);
		return shipsCopy;
	}
	
	//HELPER METHODS
	
	private void placeGeneratedShipsOnGrid() {
		for (int i = 0; i < numberOfShips; i ++)	{
			Coordinate shipStartCoordinates = getStartCoordinatesForAvailableShipPlacement(ships[i]);
			placeShipOnGrid(ships[i], shipStartCoordinates);
		}
	}
	
	private Ship getShipAtCoordinate(Coordinate coordinate)	{
		return shipGrid[coordinate.getRow()][coordinate.getColumn()];
	}

	private void setGridCoordinateAsShot(Coordinate coordinate)	{
		shotGrid[coordinate.getRow()][coordinate.getColumn()] = true;
	}
	
	private void placeShipOnGivenGridCoordinate(Coordinate coordinate, Ship ship)	{
		shipGrid[coordinate.getRow()][coordinate.getColumn()] = ship;
	}
	
	private ShotResult takeGridCompatibleShotAndGetResult(Coordinate shotCoord) {
		if (!isCoordinateAlreadyShot(shotCoord))	{
			registerShotTaken(shotCoord);
			return fireShotAtGridAndGetResult(shotCoord);
		}
		else	{
			return ShotResult.DUPLICATE;
		}
	}

	private Coordinate convertInputToCoordinate(String shot) {
		int column = extractGameBoardCompatibleColumnFromInput(shot);
		int row = extractGameBoardCompatibleRowFromInput(shot);
		Coordinate shotCoord = new Coordinate(row, column);
		return shotCoord;
	}
	
	private int extractGameBoardCompatibleColumnFromInput(String shot)	{
		int column = convertColumnInputToInteger(shot);
		return makeColumnSelectionCompatibleWithGameBoard(column);
	}
	
	private int extractGameBoardCompatibleRowFromInput(String shot)	{
		int row = convertRowInputToInteger(shot);
		return makeRowSelectionCompatibleWithGameBoard(row);
	}
	
	private int convertColumnInputToInteger(String shot) throws ClassCastException	{
		char column = shot.charAt(0);
		int columnNumber = (int) column;
		return columnNumber;
	}
	
	private int convertRowInputToInteger(String shot) throws NumberFormatException	{
		String row =shot.substring(1);
		int rowNumber = Integer.parseInt(row);
		return rowNumber;
	}
	
	private int makeRowSelectionCompatibleWithGameBoard(int row) {
		return row - 1;
	}
	
	private int makeColumnSelectionCompatibleWithGameBoard(int column) {
		return column - 65;
	}
	
	private void registerShotTaken(Coordinate coordinate) {
		setGridCoordinateAsShot(coordinate);
		totalNumberOfShotsTaken ++;
	}

	private ShotResult fireShotAtGridAndGetResult(Coordinate coordinate)	{
		if (!isThereShipInGivenCoordinate(coordinate))	{
			return ShotResult.MISS;
		}
		else {
			return shootShipAtGivenCoordinates(coordinate);
		}
	}
	
	private ShotResult shootShipAtGivenCoordinates(Coordinate coordinate)	{
		getShipAtCoordinate(coordinate).damageShip();
		if (getShipAtCoordinate(coordinate).isSunk())	{
			return ShotResult.SUNK;
		}
		else	{
			return ShotResult.HIT;
		}
	}
	
	private void generateShips()	{
		for (int i = 0; i < numberOfShips; i ++)	{
			ships[i] = new Ship(i + 2);
		}
	}
	
	private void placeShipOnGrid(Ship ship, Coordinate startCoordinates) {
		if (ship.isVertical())	{
			for (int k = 0; k < ship.getSize(); k ++)	{
				placeShipOnGivenGridCoordinate(new Coordinate(startCoordinates.getRow() + k, startCoordinates.getColumn()), ship);
			}
		}
		else	{
			for (int l = 0; l < ship.getSize(); l ++)	{
				placeShipOnGivenGridCoordinate(new Coordinate(startCoordinates.getRow(), startCoordinates.getColumn() + l), ship);
			}
		}
	}
	
	private Coordinate getStartCoordinatesForAvailableShipPlacement(Ship ship)	{
		Coordinate coordinate = null;
		boolean validPlacement = false;
		do	{
			setRandomShipOrientation(ship);
			coordinate = getPotentialStartCoordinates(ship);
			validPlacement = isShipPlacementAvailable(ship, coordinate);
		}
		while(!validPlacement);
		return coordinate;
	}
	
	private boolean isShipPlacementAvailable(Ship ship, Coordinate coordinate)	{
		for (int j = 0; j < ship.getSize(); j ++)	{
			if (ship.isVertical() && isThereShipInGivenCoordinate(new Coordinate(coordinate.getRow() + j, coordinate.getColumn())))	{
				return false;
			}
			else if (!ship.isVertical() && isThereShipInGivenCoordinate(new Coordinate(coordinate.getRow(), coordinate.getColumn() + j)))	{
				return false;
			}
		}
		return true;
	}
	
	private Coordinate getPotentialStartCoordinates(Ship ship)	{
		int row, column;
		if (ship.isVertical())	{
			row = rand.nextInt(gridHeight - ship.getSize());
			column = rand.nextInt(gridWidth);
		}
		else	{
			row = rand.nextInt(gridHeight);
			column = rand.nextInt(gridWidth - ship.getSize());
		}
		return new Coordinate(row, column);
	}

	private void setRandomShipOrientation(Ship ship) {
		ship.setVertical(rand.nextBoolean());
	}
}
