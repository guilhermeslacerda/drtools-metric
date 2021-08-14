package structures.results;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import fixtures.NamespaceMetricFixture;
import structures.metrics.NamespaceMetric;
import utils.StatisticalAnalysis;

public class NamespaceMetricResultTest {
	private NamespaceMetricResult nmr;
	private StatisticalAnalysis sa;
	
	@Before
	public void setUp() {
		nmr = new NamespaceMetricResult();
		sa  = new StatisticalAnalysis();
		createNamespaces();
	}
	
	@Test
	public void testNumberOfTypes() {
		assertEquals(13, nmr.getTotalNumberOfTypes());
	}

	@Test
	public void testNumberOfNamespaces() {
		assertEquals(2, nmr.getTotalNumberOfNamespaces());
	}

	@Test
	public void testGetMedianOfTypes() {
		loadCollectionsToStatisticalComputation();		
		assertEquals(6, sa.getMedian(), 0.001);
	}

	@Test
	public void testGetStandardDeviationTypes() {
		loadCollectionsToStatisticalComputation();
		assertEquals(3.5, sa.getStandardDeviation(), 0.001);
	}

	@Test
	public void testGetMethodMetrics() {
		assertEquals(2, nmr.getNamespaceMetrics().size());
	}
	
	@Test
	public void testSortedNamespaces() {
		LinkedHashMap<String, NamespaceMetric> namespaces = nmr.getSortedNamespaceMetrics();
		NamespaceMetric ns = null;
		for (String namespace : namespaces.keySet()) {
			ns = nmr.getNamespace(namespace);
			break;
		}
		
		assertEquals("test.controller", ns.getName());
	}
	
	@Test
	public void testInstability() {
		assertEquals(0.772, nmr.getInstability(0.01, 0.034), 0.01);
	}

	@Test
	public void testTotalOfAbstractTypes() {
		assertEquals(6, nmr.getTotalNumberOfAbstractTypes());
	}

	@Test
	public void testInstabilityWithZeros() {
		assertEquals(0.0, nmr.getInstability(0.0, 0.0), 0.01);
	}

	private void createNamespaces() {
		NamespaceMetric ns1 = new NamespaceMetricFixture().withName("test.model").withAbstractTypes(2).withTypes(3).create();
		NamespaceMetric ns2 = new NamespaceMetricFixture().withName("test.controller").withAbstractTypes(4).withTypes(10).create();
		
		nmr.add(ns1);
		nmr.add(ns2);
	}

	private void loadCollectionsToStatisticalComputation() {
		nmr.defineNumberOfTypesPerNamespace();
		sa.setElements(nmr.getTypesPerNamespace());
	}
}
