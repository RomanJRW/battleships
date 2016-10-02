package main;

public class Ship implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private int size;
	private int hitsTaken;
	private boolean vertical;
	
	
	public Ship(int aSize)	{
		size = aSize;
		hitsTaken = 0;
		vertical = true;
	}
	
	public boolean isSunk()	{
		return hitsTaken == size;
	}
	
	public int getSize()	{
		return size;
	}
	
	public int getHitsTaken()	{
		return hitsTaken;
	}
	
	public boolean isVertical()	{
		return vertical;
	}
	
	void setVertical(boolean isVertical)	{
		vertical = isVertical;
	}
	
	void setHitsTaken(int numberOfHitsTaken)	{
		hitsTaken = numberOfHitsTaken;
	}
	
	void damageShip()	{
		hitsTaken ++;
	}
	
}
