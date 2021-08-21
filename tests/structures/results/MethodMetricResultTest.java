package structures.results;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import fixtures.MethodMetricFixture;
import structures.metrics.MethodMetric;
import utils.calc.StatisticalAnalysis;

public class MethodMetricResultTest {
	private MethodMetricResult mmr;
	private StatisticalAnalysis sa;
	
	@Before
	public void setUp() {
		mmr = new MethodMetricResult();
		sa  = new StatisticalAnalysis();
		createTypes();
	}
	
	@Test
	public void testTotalCyclo() {
		assertEquals(20, mmr.getTotalCyclo());
	}

	@Test
	public void testMLOC() {
		assertEquals(55, mmr.getTotalMLOC());
	}

	@Test
	public void testNumberOfMethods() {
		assertEquals(2, mmr.getTotalNumberOfMethods());
	}

	@Test
	public void testGetMethodMetrics() {
		assertEquals(2, mmr.getMethodMetrics().size());
	}

	@Test
	public void testGetMedianOfMethods() {
		loadCollectionsToStatisticalComputation();
		assertEquals(27.5, sa.getMedian(), 0.001);
	}

	@Test
	public void testGetStandardDeviationSLOC() {
		loadCollectionsToStatisticalComputation();
		assertEquals(7.5, sa.getStandardDeviation(), 0.001);
	}

	@Test
	public void testNestedBlockDepth() {
		LinkedHashMap<String, MethodMetric> methods = mmr.getSortedMethodMetrics();
		MethodMetric m = null;
		for (String method : methods.keySet()) {
			m = mmr.getMethod(method);
			break;
		}
		assertEquals(3, m.getNestedBlockDepth());
	}

	@Test
	public void testSortedMethods() {
		LinkedHashMap<String, MethodMetric> methods = mmr.getSortedMethodMetrics();
		MethodMetric m = null;
		for (String method : methods.keySet()) {
			m = mmr.getMethod(method);
			break;
		}
		
		assertEquals("getOrder", m.getName());
	}

	@Test
	public void testGetCalls() {
		LinkedHashMap<String, MethodMetric> methods = mmr.getSortedMethodMetrics();
		MethodMetric m = null;
		for (String method : methods.keySet()) {
			m = mmr.getMethod(method);
			break;
		}
		
		assertEquals(13, m.getCalls());
	}

	private void createTypes() {
		MethodMetric m1 = new MethodMetricFixture().withType("test.model.Client").withName("getClient").withCyclo(5).parameters(1)
				.startLine(10).endLine(30).withCalls(15).create();
		MethodMetric m2 = new MethodMetricFixture().withType("test.services.CustomerService").withName("getOrder").withCyclo(10).parameters(3)
				.startLine(40).endLine(75).withCalls(12).create();
		MethodMetric m3 = new MethodMetricFixture().withType("test.services.InvoiceService").withName("getOrder").withCyclo(15).parameters(3)
				.startLine(40).endLine(75).withCalls(13).withNBD(3).create();
		
		mmr.add(m1);
		mmr.add(m2);
		mmr.add(m3);
	}

	private void loadCollectionsToStatisticalComputation() {
		mmr.defineNumberOfMethodsPerType();
		sa.setElements(mmr.getMethodsPerType());
	}
}
