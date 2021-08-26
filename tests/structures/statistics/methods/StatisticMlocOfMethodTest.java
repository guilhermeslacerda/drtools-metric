package structures.statistics.methods;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticOfMethodFixture;

public class StatisticMlocOfMethodTest extends StatisticOfMethodFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		sm.useMLOC();
	}

	@Test
	public void testGetMedian() {
		assertEquals(27.5, sm.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(27.5, sm.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(15.0, sm.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(20.0, sm.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(35.0, sm.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(20.0, sm.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(35.0, sm.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-2.5, sm.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(57.5, sm.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(7.5, sm.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(15.0, sm.getInterQuartileRange(), 0.01);
	}
}
