package utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StatisticalAnalysisTest {
	private StatisticalAnalysis sa;
	private int[] values;
	
	@Before
	public void setUp() {
		sa  = new StatisticalAnalysis();
		values = new int[]{10, 20, 30, 20, 50, 40, 10, 50, 5, 60};
		sa.setElements(values);
	}
	
	@Test
	public void testGetMedian() {
		assertEquals(25.0, sa.getMedian(), 0.01);
	}

	@Test
	public void testGetVariance() {
		assertEquals(342.25, sa.getVariance(), 0.01);
	}
	
	@Test
	public void testGetStandardDeviation() {
		assertEquals(18.5, sa.getStandardDeviation(), 0.01);
	}
}
