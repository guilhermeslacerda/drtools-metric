package structures.statistics.namespaces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.NamespaceMetricFixture;
import structures.metrics.NamespaceMetric;
import structures.results.NamespaceMetricResult;
import structures.statistics.StatisticOfNamespace;

public class StatisticOfNamespaceTest {
	private NamespaceMetricResult nmr;
	private StatisticOfNamespace sn;
	
	@Before
	public void setUp() {
		nmr = new NamespaceMetricResult();
		sn  = new StatisticOfNamespace();
		createNamespaces();
		loadData();
	}
	
	@Test
	public void testGetMedian() {
		assertEquals(15.0, sn.getMedian(), 0.01);
	}

	@Test
	public void testGetAverage() {
		assertEquals(13.8, sn.getAverage(), 0.01);
	}

	@Test
	public void testGetAmplitude() {
		assertEquals(20.0, sn.getAmplitude(), 0.01);
	}
	
	@Test
	public void testGetFirstQuartile() {
		assertEquals(5.5, sn.getFirstQuartile(), 0.01);
	}

	@Test
	public void testGetThirdQuartile() {
		assertEquals(21.5, sn.getThirdQuartile(), 0.01);
	}

	@Test
	public void testGetMinValue() {
		assertEquals(3.0, sn.getMinValue(), 0.01);
	}

	@Test
	public void testGetMaxValue() {
		assertEquals(23.0, sn.getMaxValue(), 0.01);
	}
	
	@Test
	public void testGetLowerFence() {
		assertEquals(-18.5, sn.getLowerFence(), 0.01);
	}

	@Test
	public void testGetUpperFence() {
		assertEquals(45.5, sn.getUpperFence(), 0.01);
	}
	
	@Test
	public void testGetStandardDeviation() {
		assertEquals(7.41, sn.getStandardDeviation(), 0.01);
	}

	@Test
	public void testGetInterQuartileRange() {
		assertEquals(16.0, sn.getInterQuartileRange(), 0.01);
	}

	private void createNamespaces() {
		NamespaceMetric ns1 = new NamespaceMetricFixture().withName("test.model").withAbstractTypes(2).withTypes(3).create();
		NamespaceMetric ns2 = new NamespaceMetricFixture().withName("test.controller").withAbstractTypes(2).withTypes(8).create();
		NamespaceMetric ns3 = new NamespaceMetricFixture().withName("test.services").withAbstractTypes(3).withTypes(23).create();
		NamespaceMetric ns4 = new NamespaceMetricFixture().withName("test.utils").withAbstractTypes(0).withTypes(15).create();
		NamespaceMetric ns5 = new NamespaceMetricFixture().withName("test.ui").withAbstractTypes(5).withTypes(20).create();
		
		nmr.add(ns1);
		nmr.add(ns2);
		nmr.add(ns3);
		nmr.add(ns4);
		nmr.add(ns5);
	}

	private void loadData() {
		sn.defineResults(nmr);
		sn.useNOC();
	}
}
