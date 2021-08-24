package structures.statistics.methods;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticalOfMethodFixture;

public class StatisticalCycloOfMethodTest extends StatisticalOfMethodFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		sm.useCYCLO();
	}

	@Test
	public void testGetMedian() {
		assertEquals(10.0, sm.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(10.0, sm.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(10.0, sm.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(5.0, sm.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(15.0, sm.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(5.0, sm.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(15.0, sm.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-10.0, sm.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(30.0, sm.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(5.0, sm.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(10.0, sm.getInterQuartileRange(), 0.01);
	}
}
