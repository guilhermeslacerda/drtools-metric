package output;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fixtures.output.CSVDataFixture;
import selection.ProjectInfo;

public class MetricResultCSVTest {
	private static final String PROJECT_DIRECTORY = "./resources/javaProject/";
	private ProjectInfo projectInfo;
	private CSVDataFixture cdf;
	private MetricResultCSV mr;
	
	@Before
	public void setUp() {
		cdf = new CSVDataFixture();
		mr = new MetricResultCSV();
		projectInfo = new ProjectInfo(PROJECT_DIRECTORY, mr);
		projectInfo.analyze();
	}

	@Test
	public void testForNamespacesCSV() {
		projectInfo.show("-n");
		assertEquals(cdf.generateNamespaces(), mr.generateNamespaces());
	}

	@Test
	public void testForSummaryCSV() {
		projectInfo.show("-s");
		assertEquals(cdf.generateSummary(), mr.generateSummary());
	}

	@Test
	public void testForTypesCSV() {
		projectInfo.show("-t");
		assertEquals(cdf.generateTypes(), mr.generateTypes());
	}

	@Test
	public void testForMethodsCSV() {
		projectInfo.show("-m");
		assertEquals(cdf.generateMethods(), mr.generateMethods());
	}

	@Test
	public void testForNamespaceCouplingCSV() {
		projectInfo.show("-nc");
		assertEquals(cdf.generateNamespaceCoupling(), mr.generateNamespaceCoupling());
	}
	
	@Test
	public void testForMetricThresholdsCSV() {
		projectInfo.show("-mt");
		assertEquals(cdf.generateThresholds(), mr.generateThresholds().toString());
	}
	
	@Test
	public void testGetNamespacesWithLimitCSV() {
		projectInfo.defineTop(1);
		projectInfo.show("-n");
		assertEquals(cdf.getNamespacesWithLimit(), mr.generateNamespaces());
	}

	@Test
	public void testGetNamespacesWithLimitOutOfBoundsCSV() {
		projectInfo.defineTop(6);
		projectInfo.show("-n");
		assertEquals(cdf.generateNamespaces(), mr.generateNamespaces());
	}

	@Test
	public void testGetTypesWithLimitCSV() {
		projectInfo.defineTop(1);
		projectInfo.show("-t");
		assertEquals(cdf.getTypesWithLimit(), mr.generateTypes());
	}

	@Test
	public void testGetTypesWithLimitOutOfBoundsCSV() {
		projectInfo.defineTop(30);
		projectInfo.show("-t");
		assertEquals(cdf.generateTypes(), mr.generateTypes());
	}

	@Test
	public void testGetMethodsWithLimitCSV() {
		projectInfo.defineTop(1);
		projectInfo.show("-m");
		assertEquals(cdf.getMethodsWithLimit(), mr.generateMethods());
	}

	@Test
	public void testGetMethodsWithLimitOutOfBoundsCSV() {
		projectInfo.defineTop(80);
		projectInfo.show("-m");
		assertEquals(cdf.generateMethods(), mr.generateMethods());
	}

	@Test
	public void testGetTypeCouplingCSV() {
		projectInfo.show("-tc");
		assertEquals(cdf.generateTypeCoupling(), mr.generateTypeCoupling());
	}

	@Test
	public void testGetCyclicDependencyCSV() {
		projectInfo.getTypeMetricResult().defineInternalDependencies();
		projectInfo.show("-nc");
		assertEquals(cdf.generateCyclicDependencies(), mr.generateCyclicDependencies());
	}
}
