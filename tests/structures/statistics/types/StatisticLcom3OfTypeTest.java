package structures.statistics.types;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticOfTypeFixture;

public class StatisticLcom3OfTypeTest extends StatisticOfTypeFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		st.useLCOM();
	}

	@Test
	public void testGetMedian() {
		assertEquals(1.16, st.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(0.88, st.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(2.0, st.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(0.0, st.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(1.625, st.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(0.0, st.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(2.0, st.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-2.43, st.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(4.06, st.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(0.77, st.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(1.625, st.getInterQuartileRange(), 0.01);
	}
}
