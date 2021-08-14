package structures.results;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import fixtures.TypeMetricFixture;
import structures.metrics.TypeMetric;
import utils.StatisticalAnalysis;

public class TypeMetricResultTest {
	private TypeMetricResult tmr;
	private StatisticalAnalysis sa;
	
	@Before
	public void setUp() {
		tmr = new TypeMetricResult();
		sa  = new StatisticalAnalysis();
		createTypes();
	}
	
	@Test
	public void testNumberOfMethods() {
		assertEquals(18, tmr.getTotalNumberOfMethods());
	}

	@Test
	public void testNumberOfMethodsOfType() {
		assertEquals(7, tmr.getTotalNumberOfMethods("test.services.CustomerService"));
	}

	@Test
	public void testNumberOfNamespaces() {
		assertEquals(5, tmr.getTotalNumberOfTypes());
	}

	@Test
	public void testNumberOfVariables() {
		assertEquals(7, tmr.getTotalOfVariables());
	}

	@Test
	public void testNumberOfAbstractTypes() {
		assertEquals(1, tmr.getTotalOfAbstractTypesIn("test.model"));
	}

	@Test
	public void testNumberOfAbstractTypesWithNull() {
		assertEquals(0, tmr.getTotalOfAbstractTypesIn(null));
	}

	@Test
	public void testSortedTypes() {
		TypeMetric t = null;
		for (String type : tmr.getNamesResult()) {
			t = tmr.getType(type);
			break;
		}
		
		assertEquals("CustomerService", t.getName());
	}

	@Test
	public void testFullName() {
		TypeMetric t = null;
		for (String type : tmr.getNamesResult()) {
			t = tmr.getType(type);
			break;
		}
		
		assertEquals("test.services.CustomerService", t.getFullName());
	}

	@Test
	public void testGetDependencies() {
		TypeMetric t = null;
		for (String type : tmr.getNamesResult()) {
			t = tmr.getType(type);
			break;
		}
		assertEquals(1, t.getNumberOfDependencies());
	}
	
	@Test
	public void testGetCyclicDependencies() {
		assertEquals(0, tmr.getCyclicDependencies().size());
	}

	@Test
	public void testGetFanInOfNoExistClassOrNull() {
		assertEquals(0, tmr.getFanInOf("xxxx"));
		assertEquals(0, tmr.getFanInOf(null));
	}

	@Test
	public void testGetMedianOfSLOC() {
		loadCollectionsToStatisticalComputation();
		assertEquals(50.0, sa.getMedian(), 0.001);
	}

	@Test
	public void testGetStandardDeviationSLOC() {
		loadCollectionsToStatisticalComputation();
		assertEquals(56.426, sa.getStandardDeviation(), 0.001);
	}	
	
	@Test
	public void testGetPublicMethods() {
		assertEquals(1, tmr.getTotalNumberOfPublicMethods("services.InvoiceService"));
	}

	@Test
	public void testgetInternalImportsByNamespaces() {
		assertEquals(3, tmr.getInternalImportsBy("services").size());
	}

	private void createTypes() {
		Set<String> imports1 = new TreeSet<>();
		Set<String> imports2 = new TreeSet<>();
		
		imports1.add("test.services.CustomerService");
		imports1.add("java.util.List");
		
		imports2.add("test.model.Client");
		
		
		Set<String> internalImports = new TreeSet<>();
		internalImports.add("test.listeners.ButtonListener");
		internalImports.add("test.model.AbstractLogin");
		internalImports.add("test.services.CustomerService");
		
		TypeMetric t1 = new TypeMetricFixture().withName("Client").withNamespace("test.model").withVariables(3)
							.withMethods(5).withImports(imports1).withSloc(50).create();
		TypeMetric t2 = new TypeMetricFixture().withName("CustomerService").withNamespace("test.services")
							.withVariables(1).withMethods(7).withImports(imports2).withSloc(150).create();
		TypeMetric t3 = new TypeMetricFixture().withName("AbstractLogin").withNamespace("test.model").withVariables(3)
							.withMethods(2).isAbstract(true).withSloc(120).create();
		TypeMetric t4 = new TypeMetricFixture().withName("ButtonListener").withNamespace("test.listeners")
				.withMethods(2).isInterface(true).withSloc(10).create();
		TypeMetric t5 = new TypeMetricFixture().withName("InvoiceService").withNamespace("services")
				.withMethods(2).withPublicMethods(1).isInterface(false).withInternalImports(internalImports).withSloc(15).create();

		tmr.add(t1);
		tmr.add(t2);
		tmr.add(t3);
		tmr.add(t4);
		tmr.add(t5);
	}

	private void loadCollectionsToStatisticalComputation() {
		tmr.defineNumberOfSLOCPerTypes();
		sa.setElements(tmr.getSLOCPerType());
	}		
}
