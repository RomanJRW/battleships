package main;



import static org.junit.Assert.*;




//import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	
	private Ship testShip;
	private int testShipSize = 5;

	@Before
	public void setUp() throws Exception {
		testShip = new Ship(testShipSize);
	}

	@Test
	public void testDamageShip_ShouldIncreaseHitsTaken() {
		testShip.damageShip();
		assertEquals(testShip.getHitsTaken(), 1);
	}
	
	@Test
	public void testIsSunk_ShouldReturnSunkIfFullyDamaged() {
		testShip.setHitsTaken(testShipSize);
		boolean result = testShip.isSunk();
		assertTrue(result);
	}
	
	@Test
	public void testIsSunk_ShouldNotReturnSunkIfPartiallyDamaged() {
		testShip.damageShip();
		boolean result = testShip.isSunk();
		assertFalse(result);
	}
	
	@Test
	public void testDamageShip_ShouldNotReturnSunkIfNotDamaged() {
		boolean result = testShip.isSunk();
		assertFalse(result);
	}
}
