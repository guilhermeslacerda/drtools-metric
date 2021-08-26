package structures.statistics.types;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticOfTypeFixture;

public class StatisticNoaOfTypeTest extends StatisticOfTypeFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		st.useNOA();
	}

	@Test
	public void testGetMedian() {
		assertEquals(1.0, st.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(1.4, st.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(3.0, st.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(0.0, st.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(3.0, st.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(0.0, st.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(3.0, st.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-4.5, st.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(7.5, st.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(1.35, st.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(3.0, st.getInterQuartileRange(), 0.01);
	}
}
