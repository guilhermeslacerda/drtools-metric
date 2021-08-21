package utils.calc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OutlierAnalysisTest {
	private StatisticalAnalysis sa;
	private double[] elements;
	
	@Before
	public void setUp() {
		sa  = new StatisticalAnalysis();
		elements = new double[]{-2, 3, 5, 8, 9, 11, 13, 14, 15, 17, 35};
		sa.setElements(elements);
	}
	
	@Test
	public void testGetMedian() {
		assertEquals(11.0, sa.getMedian(), 0.01);
	}

	@Test
	public void testGetAverage() {
		assertEquals(11.63, sa.getAverage(), 0.01);
	}

	@Test
	public void testGetFirstQuartile() {
		assertEquals(5.0, sa.getFirstQuartile(), 0.01);
	}

	@Test
	public void testGetThirdQuartile() {
		assertEquals(15.0, sa.getThirdQuartile(), 0.01);
	}

	@Test
	public void testGetInterQuartileRange() {
		assertEquals(10.0, sa.getInterQuartileRange(), 0.01);
	}

	@Test
	public void testGetLowerFence() {
		assertEquals(-10.0, sa.getLowerFence(), 0.01);
	}
	
	@Test
	public void testGetUpperFence() {
		assertEquals(30.0, sa.getUpperFence(), 0.01);
	}
	
	@Test
	public void testGetMinValue() {
		assertEquals(-2.0, sa.getMinValue(), 0.01);
	}

	@Test
	public void testGetMaxValue() {
		assertEquals(35.0, sa.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetAmplitude() {
		assertEquals(37.0, sa.getAmplitude(), 0.01);
	}
}
