package structures.statistics.types;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticOfTypeFixture;

public class StatisticSlocOfTypeTest extends StatisticOfTypeFixture {
	
	@Before
	public void setUp() {
		createStructureToTest();
		st.useSLOC();
	}

	@Test
	public void testGetMedian() {
		assertEquals(50.0, st.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(69.0, st.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(140.0, st.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(12.5, st.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(135.0, st.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(10.0, st.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(150.0, st.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-171.25, st.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(318.75, st.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(56.42, st.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(122.5, st.getInterQuartileRange(), 0.01);
	}
}
