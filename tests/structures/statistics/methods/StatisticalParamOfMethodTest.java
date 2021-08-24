package structures.statistics.methods;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticalOfMethodFixture;

public class StatisticalParamOfMethodTest extends StatisticalOfMethodFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		sm.usePARAM();
	}

	@Test
	public void testGetMedian() {
		assertEquals(2.0, sm.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(2.0, sm.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(2.0, sm.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(1.0, sm.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(3.0, sm.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(1.0, sm.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(3.0, sm.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-2.0, sm.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(6.0, sm.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(1.0, sm.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(2.0, sm.getInterQuartileRange(), 0.01);
	}
}
