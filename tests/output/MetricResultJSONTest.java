package output;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fixtures.output.JSONDataFixture;
import general.ProjectInfo;

public class MetricResultJSONTest {
	private static final String PROJECT_DIRECTORY = "./resources/javaProject/";
	private ProjectInfo projectInfo;
	private JSONDataFixture jdf;
	private MetricResultJSON mr;
	
	@Before
	public void setUp() {
		jdf = new JSONDataFixture();
		mr = new MetricResultJSON();
		projectInfo = new ProjectInfo(PROJECT_DIRECTORY, mr);
		projectInfo.analyze();
	}

	@Test
	public void testForNamespacesJSON() {
		projectInfo.show("-n");
		assertEquals(jdf.generateNamespaces(), mr.generateNamespaces());
	}
	
	@Test
	public void testForTypesJSON() {
		projectInfo.show("-t");
		assertEquals(jdf.generateTypes(), mr.generateTypes());
	}

	@Test
	public void testForMethodsJSON() {
		projectInfo.show("-m");
		assertEquals(jdf.generateMethods(), mr.generateMethods());
	}

	@Test
	public void testForSummaryJSON() {
		projectInfo.show("-s");
		assertEquals(jdf.generateSummary(), mr.generateSummary());
	}

	@Test
	public void testForDependenciesJSON() {
		projectInfo.show("-d");
		assertEquals(jdf.getDependencies(), mr.generateDependencies());
	}

	@Test
	public void testForInternalDependenciesJSON() {
		projectInfo.show("-id");
		assertEquals(jdf.getInternalDependencies(), mr.generateInternalDependencies());
	}

	@Test
	public void testForNamespaceCouplingJSON() {
		projectInfo.show("-nc");
		assertEquals(jdf.generateNamespaceCoupling(), mr.generateNamespaceCoupling());
	}

	@Test
	public void testForMetricThresholdsJSON() {
		projectInfo.show("-mt");
		assertEquals(jdf.generateThresholds(), mr.generateThresholds());
	}

	@Test
	public void testForNamespacesWithLimitJSON() {
		projectInfo.defineTop(1);
		projectInfo.show("-n");
		assertEquals(jdf.getNamespacesWithLimit(), mr.generateNamespaces());
	}
	
	@Test
	public void testGetNamespacesWithLimitOutOfBoundsJSON() {
		projectInfo.defineTop(6);
		projectInfo.show("-n");
		assertEquals(jdf.generateNamespaces(), mr.generateNamespaces());
	}

	@Test
	public void testForTypesWithLimitJSON() {
		projectInfo.defineTop(1);
		projectInfo.show("-t");
		assertEquals(jdf.getTypesWithLimit(), mr.generateTypes());
	}

	@Test
	public void testGetTypesWithLimitOutOfBoundsJSON() {
		projectInfo.defineTop(30);
		projectInfo.show("-t");
		assertEquals(jdf.generateTypes(), mr.generateTypes());
	}
	
	@Test
	public void testForMethodWithLimitJSON() {
		projectInfo.defineTop(1);
		projectInfo.show("-m");
		assertEquals(jdf.getMethodsWithLimit(), mr.generateMethods());
	}
	
	@Test
	public void testGetMethodsWithLimitOutOfBoundsJSON() {
		projectInfo.defineTop(80);
		projectInfo.show("-m");
		assertEquals(jdf.generateMethods(), mr.generateMethods());
	}

	@Test
	public void testForTypeCouplingJSON() {
		projectInfo.show("-tc");
		assertEquals(jdf.generateTypeCoupling(), mr.generateTypeCoupling());
	}

	@Test
	public void testGetCyclicDependencyJSON() {
		projectInfo.getTypeMetricResult().defineInternalDependencies();
		projectInfo.show("-nc");
		assertEquals(jdf.generateCyclicDependencies(), mr.generateCyclicDependencies());
	}
	
	@Test
	public void testForTypesResonanceJSON() {
		projectInfo.show("-t");
		assertEquals(jdf.generateTypesResonance(), mr.generateTypesResonance());
	}

	@Test
	public void testForNamespacesDependenciesJSON() {
		projectInfo.show("-n");
		assertEquals(jdf.generateNamespacesDependencies(), mr.generateNamespacesDependencies());
	}
}
