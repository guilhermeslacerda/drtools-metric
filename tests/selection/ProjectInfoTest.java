package general;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import output.MetricResultConsole;
import structures.metrics.MethodMetric;
import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.TypeMetricResult;

public class ProjectInfoTest {
	private static final String PROJECT_DIRECTORY = "./resources/javaProject/";
	private ProjectInfo projectInfo;
	private TypeMetric type;

	@Before
	public void setUp() {
		projectInfo = new ProjectInfo(PROJECT_DIRECTORY, new MetricResultConsole());
		projectInfo.analyze();
	}

	@Test
	public void testIfNamespaceMetricResultIsNotEmpty() {
		assertNotNull(projectInfo.getNamespaceMetricResult());
	}

	@Test
	public void testIfTypeMetricResultIsNotEmpty() {
		assertNotNull(projectInfo.getTypeMetricResult());
	}

	@Test
	public void testIfMethodMetricResultIsNotEmpty() {
		assertNotNull(projectInfo.getMethodMetricResult());
	}

	@Test
	public void testGetTotalNumberOfNamespaces() {
		assertEquals(6, projectInfo.getNamespaceMetricResult().getTotalNumberOfNamespaces());
	}

	@Test
	public void testGetTotalNumberOfTypes() {
		assertEquals(17, projectInfo.getNamespaceMetricResult().getTotalNumberOfTypes());
	}
	
	@Test
	public void testGetNumberOfMethods() {
		assertEquals(55, projectInfo.getTypeMetricResult().getTotalNumberOfMethods());
	}

	@Test
	public void testGetTotalOfVariables() {
		assertEquals(20, projectInfo.getTypeMetricResult().getTotalOfVariables());
	}

	@Test
	public void testGetTotalSLOC() {
		assertEquals(393, projectInfo.getTypeMetricResult().getTotalSLOC());
	}

	@Test
	public void testGetTotalCyclo() {
		assertEquals(86, projectInfo.getMethodMetricResult().getTotalCyclo());
	}

	@Test
	public void testGetTotalMLOC() {
		assertEquals(369, projectInfo.getMethodMetricResult().getTotalMLOC());
	}

	@Test
	public void testGetInternalTypesOfTypeClass() {
		prepareTypesforTest("javaProject.com.controller.Type");
		assertEquals(2, type.getNumberOfInternalTypes());
	}

	@Test
	public void testGetMethodsOfTypeClass() {
		prepareTypesforTest("javaProject.com.controller.Type");
		MethodMetricResult mmr = projectInfo.getMethodMetricResult();
		Set<String> methods = mmr.getMethodsName();		
		assertEquals(25, countMethods(mmr, methods));
	}

	private int countMethods(MethodMetricResult mmr, Set<String> methods) {
		int countMethods = 0;
		for (String m : methods) {
			MethodMetric method = mmr.getMethod(m);
			if (method.getTypeName().equals(type.getFullName())) 
				countMethods++;
		}
		
		return countMethods;
	}

	@Test
	public void testGetInternalNamesResultWithLimit() {
		projectInfo.defineTop(1);
		assertEquals(1, projectInfo.getTypeMetricResult().getInternalNamesResult().size());
	}

	@Test
	public void testGetInternalTypesOfDispatcherClass() {
		prepareTypesforTest("javaProject.com.controller.Dispatcher");
		assertEquals(0, type.getNumberOfInternalTypes());
	}

	private void prepareTypesforTest(String typeName) {
		TypeMetricResult tmr = projectInfo.getTypeMetricResult();
		type = tmr.getType(typeName);
	}	
}
