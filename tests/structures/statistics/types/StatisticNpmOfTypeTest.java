package structures.statistics.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticOfTypeFixture;

public class StatisticNpmOfTypeTest extends StatisticOfTypeFixture {

	@Before
	public void setUp() {
		createStructureToTest();
		st.useNPM();
	}

	@Test
	public void testGetMedian() {
		assertEquals(0.0, st.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(0.2, st.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(1.0, st.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(0.0, st.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(0.5, st.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(0.0, st.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(1.0, st.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-0.75, st.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(1.25, st.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(0.4, st.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(0.5, st.getInterQuartileRange(), 0.01);
	}
}
