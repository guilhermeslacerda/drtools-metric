package structures.statistics.methods;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticalOfMethodFixture;

public class StatisticalNbdOfMethodTest extends StatisticalOfMethodFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		sm.useNBD();
	}

	@Test
	public void testGetMedian() {
		assertEquals(1.5, sm.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(1.5, sm.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(3.0, sm.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(0.0, sm.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(3.0, sm.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(0.0, sm.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(3.0, sm.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-4.5, sm.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(7.5, sm.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(1.5, sm.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(3.0, sm.getInterQuartileRange(), 0.01);
	}
}
