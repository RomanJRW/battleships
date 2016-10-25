package com.windels.battleships;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {

	private Ship testShipLong;
	private Ship testShipShort;
	private Ship testShipMedium;

	@Before
	public void setUp() throws Exception {
		testShipLong = new Ship(5);
		testShipShort = new Ship(2);
		testShipMedium = new Ship(4);
	}

	@Test
	public void testDamageShip_ShouldIncreaseHitsTaken() {
		testShipLong.damageShip();
		testShipShort.damageShip();
		testShipMedium.damageShip();
		assertEquals(testShipLong.getHitsTaken(), 1);
		assertEquals(testShipShort.getHitsTaken(), 1);
		assertEquals(testShipMedium.getHitsTaken(), 1);
	}
	
	@Test
	public void testIsSunk_ShouldReturnSunkIfFullyDamaged() {
		testShipLong.setHitsTaken(5);
		testShipShort.setHitsTaken(2);
		testShipMedium.setHitsTaken(4);
		assertTrue(testShipLong.isSunk());
		assertTrue(testShipShort.isSunk());
		assertTrue(testShipMedium.isSunk());
	}
	
	@Test
	public void testIsSunk_ShouldNotReturnSunkIfPartiallyDamaged() {
		testShipLong.damageShip();
		testShipShort.damageShip();
		testShipMedium.damageShip();
		assertFalse(testShipLong.isSunk());
		assertFalse(testShipShort.isSunk());
		assertFalse(testShipMedium.isSunk());
	}
	
	@Test
	public void testDamageShip_ShouldNotReturnSunkIfNotDamaged() {
		assertFalse(testShipLong.isSunk());
		assertFalse(testShipShort.isSunk());
		assertFalse(testShipMedium.isSunk());
	}

}
