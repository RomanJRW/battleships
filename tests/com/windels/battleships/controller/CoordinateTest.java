package com.windels.battleships.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	private Coordinate testCoord1;
	private Coordinate testCoord2;
	
	@Before
	public void setUp()	{
		testCoord1 = new Coordinate(3, 2);
		testCoord2 = new Coordinate(6, 7);
	}
	
	@Test
	public void testEqualsObject_ShouldReturnFalseIfComparedAgainstNonCoordinate() {
		assertFalse(testCoord1.equals("FailString"));
		assertFalse(testCoord2.equals(5));
	}
	
	@Test
	public void testEqualsObject_ShouldReturnFalseIfComparedAgainstCoordinateWithDifferentLocation() {
		assertFalse(testCoord1.equals(testCoord2));
		assertFalse(testCoord2.equals(testCoord1));
	}
	
	@Test
	public void testEqualsObject_ShouldReturnTrueIfComparedAgainstCoordinateWithSameLocation() {
		testCoord2 = new Coordinate(3, 2);
		assertTrue(testCoord1.equals(testCoord2));
		assertTrue(testCoord2.equals(testCoord1));
	}
	
	@Test
	public void testEqualsObject_ShouldReturnTrueIfComparedAgainstItself() {
		assertTrue(testCoord1.equals(testCoord1));
		assertTrue(testCoord2.equals(testCoord2));
	}

	@Test
	public void testHashCode_ShouldReturnExpectedHashCode() {
		assertTrue(testCoord1.hashCode() == 2948);
		assertTrue(testCoord2.hashCode() == 3106);
	}	

	@Test
	public void testToString_ShouldReturnExpectedStringRepresentation() {
		assertTrue(testCoord1.toString().equals("(3, 2)"));
		assertTrue(testCoord2.toString().equals("(6, 7)"));
	}

}
