package com.windels.battleships;

public class Coordinate {

	private int row;
	private int column;
	
	public Coordinate (int aRow, int aColumn)	{
		row = aRow;
		column = aColumn;
	}
	
	public int getRow()	{
		return row;
	}
	
	public int getColumn()	{
		return column;
	}
	
	@Override
	public boolean equals(Object anObject)	{
		if (anObject instanceof Coordinate)	{
			Coordinate coord = (Coordinate) anObject;
			if (coord.getRow() == this.getRow() && coord.getColumn() == this.getColumn())	{
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 3;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public String toString()	{
		String rep = "(" + this.getRow() + ", " + this.getColumn() + ")";
		return rep;
	}
}
