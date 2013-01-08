package org.unitsofmeasurement.test.quantity;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.unitsofmeasurement.test.unit.BitUnit;
import org.unitsofmeasurement.test.unit.TimeUnit;

public class BitQuantityTest {
	BitQuantity bq;
	BitUnit bu;

	@Before
	public void setUp() {
		bu = BitUnit.bit;
		bq = new BitQuantity(100, bu);
	}

	@Test
	public void testAddBitQuantity() {
		BitQuantity bq1 = (BitQuantity) bq.add(new BitQuantity(100, bu));
		assertEquals(Double.valueOf(200), Double.valueOf(bq1.scalar));
	}

	@Test
	public void testDivideTimeNumericQuantity() {
		BitRateQuantity brq = bq.divideTime(new TimeQuantity(20, TimeUnit.s));
		assertEquals(Double.valueOf(5), Double.valueOf(brq.scalar));
		assertEquals("5.0 bps", brq.toString());
	}

	@Test
	public void testConvertBitQuantity() {
		@SuppressWarnings("unchecked")
		BitQuantity bq1 = bq.to(BitUnit.kb);
		assertEquals(Double.valueOf(0.1d), bq1.value());
		assertEquals("0.1 kb", bq1.toString());
	}
}