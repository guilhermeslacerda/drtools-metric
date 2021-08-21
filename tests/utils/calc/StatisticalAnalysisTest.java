package utils.calc;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.calc.StatisticalAnalysis;

public class StatisticalAnalysisTest {
	private StatisticalAnalysis sa;
	private double[] values;
	
	@Before
	public void setUp() {
		sa  = new StatisticalAnalysis();
		values = new double[]{10, 20, 30, 20, 50, 40, 10, 50, 5, 60};
		sa.setElements(values);
	}
	
	@Test
	public void testGetMedian() {
		assertEquals(25.0, sa.getMedian(), 0.01);
	}

	@Test
	public void testGetAverage() {
		assertEquals(29.5, sa.getAverage(), 0.01);
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
