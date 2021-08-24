package structures.statistics.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fixtures.statistics.StatisticalOfTypeFixture;

public class StatisticalNomOfTypeTestTest extends StatisticalOfTypeFixture {
	
	@Before
	public void setUp() {
		createStructureToTest();
		st.useNOM();
	}

	@Test
	public void testGetMedian() {
		assertEquals(2.0, st.getMedian(), 0.01);
	}
	
	@Test 
	public void testGetAverage() {
		assertEquals(3.6, st.getAverage(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(5.0, st.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(2.0, st.getFirstQuartile(), 0.01);
	}
	
	@Test
	public void testGetThirdQuartile() {
		assertEquals(6.0, st.getThirdQuartile(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(2.0, st.getMinValue(), 0.01);
	}
	
	@Test
	public void testGetMaxValue() {
		assertEquals(7.0, st.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-4.0, st.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(12.0, st.getUpperFence(), 0.01);
	}
	
	@Test 
	public void testGetStandardDeviation() {
		assertEquals(2.05, st.getStandardDeviation(), 0.01);
	}
	
	@Test
	public void testGetInterQuartileRange() {
		assertEquals(4.0, st.getInterQuartileRange(), 0.01);
	}
}
