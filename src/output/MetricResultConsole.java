package output;

import java.util.Set;

import structures.metrics.MethodMetric;
import structures.metrics.MetricDefinition;
import structures.metrics.MetricThreshold;
import structures.metrics.NamespaceMetric;
import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;
import utils.StatisticalAnalysis;
import utils.StringFormat;

public class MetricResultConsole implements MetricOutput {
	private NamespaceMetricResult nmr;
	private TypeMetricResult tmr;
	private MethodMetricResult mmr;
	private StatisticalAnalysis sa;

	@Override
	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr) {
		this.nmr = nmr;
		this.tmr = tmr;
		this.mmr = mmr;
		this.sa  = new StatisticalAnalysis();
	}

	@Override
	public void showNamespaces() {
		System.out.println("\n\n----------------------------------------------------------------------");
		System.out.println("NAMESPACES\t\t\t\t\t\tNOC\tNAC");
		System.out.println("----------------------------------------------------------------------");

		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			System.out.printf("%50s\t%d\t%d\n", StringFormat.withLimit(namespace.getName(), 50),
					namespace.getNumOfTypes(), tmr.getTotalOfAbstractTypesIn(namespace.getName()));
		}
	}

	@Override
	public void showTypes() {
		System.out.println(
				"\n\n---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("TYPES\t\t\t\t\t\t\t\tSLOC\tNOM\tNPM\tWMC\tDEP\tI-DEP\tFAN-IN\tFAN-OUT\tNOA\tLCOM3");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");

		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			System.out.printf("%60s\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%.2f\n",
					StringFormat.withLimit(type.getFullName(), 60), type.getSloc(), type.getNumOfMethods(),
					type.getNumOfPublicMethods(), tmr.getTotalCycloBy(type.getFullName()),
					type.getNumberOfDependencies(), type.getNumberOfInternalDependencies(), tmr.getFanInOf(name),
					type.getFanOut(), type.getNumOfVariables(), tmr.getLackCohesionMethods(type.getFullName()));
		}
	}

	@Override
	public void showMethods() {
		System.out.println(
				"\n\n---------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("METHODS\t\t\t\t\t\t\t\t\t\t\t\t\tMLOC\tCYCLO\tCALLS\tNBD\tPARAM");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------");

		for (String name : mmr.getNamesResult()) {
			MethodMetric method = mmr.getMethod(name);
			System.out.printf("%100s\t%d\t%d\t%d\t%d\t%d\n", StringFormat.withLimit(method.getName(), 100),
					method.getLoc(), method.getCyclo(), method.getCalls(), method.getNestedBlockDepth(),
					method.getNumOfParameters());
		}
	}

	@Override
	public void showSummary() {
		loadCollectionsToStatisticalComputation();
		
		sa.setElements(nmr.getTypesPerNamespace());
		System.out.println("------------------");
		System.out.println("SUMMARY OF METRICS");
		System.out.println("------------------");
		System.out.printf("            Total of Namespaces: %d", nmr.getTotalNumberOfNamespaces());
		System.out.printf("\n                 Total of Types: %d", tmr.getTotalNumberOfTypes());
		System.out.printf(" - %.2f (number of types/namespaces - median: %.2f - std dev: %.2f)",
			(float) tmr.getTotalNumberOfTypes() / nmr.getTotalNumberOfNamespaces(), sa.getMedian(),
			sa.getStandardDeviation());
		
		sa.setElements(tmr.getSLOCPerType());
		System.out.printf("\n                  Total of SLOC: %d", tmr.getTotalSLOC());
		System.out.printf(" - %.2f (number of SLOC/types - median: %.2f - std dev: %.2f)",
				(float) tmr.getTotalSLOC() / tmr.getTotalNumberOfTypes(), sa.getMedian(),
				sa.getStandardDeviation());
		
		sa.setElements(mmr.getMethodsPerType());
		System.out.printf("\n               Total of Methods: %d", mmr.getTotalNumberOfMethods());
		System.out.printf(" - %.2f (number of methods/types - median: %.2f - std dev: %.2f)",
				(float) mmr.getTotalNumberOfMethods() / tmr.getTotalNumberOfTypes(), sa.getMedian(),
				sa.getStandardDeviation());
		
		System.out.printf("\n                 Total of CYCLO: %d", mmr.getTotalCyclo());
		System.out.printf(" - %.2f (number of CYCLO/types)", (float) mmr.getTotalCyclo() / tmr.getTotalNumberOfTypes());
	}

	@Override
	public void loadCollectionsToStatisticalComputation() {
		nmr.defineNumberOfTypesPerNamespace();
		tmr.defineNumberOfSLOCPerTypes();
		mmr.defineNumberOfMethodsPerType();
	}

	@Override
	public void show() {
		showSummary();
		showNamespaces();
		showTypes();
		showMethods();
	}

	@Override
	public void showDependencies() {
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			System.out.println(
					"\n--------------------------------------------------------------------------------------------------------");
			System.out.println("Type: " + StringFormat.withLimit(type.getFullName(), 60) + "\tSLOC: " + type.getSloc()
					+ "\tNumber of Dependencies: " + type.getNumberOfDependencies());
			Set<String> dependencies = type.getImports();
			if (type.getNumberOfDependencies() > 0)
				System.out.println("DEPENDENCIES:");
			for (String dependency : dependencies) {
				System.out.println("\t" + dependency);
			}
		}
	}

	@Override
	public void showCyclicDependencies() {
		Set<String> names = tmr.getCyclicDependencies();

		if (names.isEmpty()) {
			InfoConsole.printHeader("This project hasn't types with cyclic dependencies...");
			return;
		}

		System.out.println("\n--------------------------------");
		System.out.println("TYPES WITH CYCLIC DEPENDENCIES");
		System.out.println("--------------------------------");
		for (String name : names)
			System.out.println(name);
	}

	@Override
	public void showInternalDependencies() {
		Set<String> names = tmr.getTypeMetrics().keySet();

		if (names.isEmpty()) {
			InfoConsole.printHeader("This project hasn't types with internal dependencies...");
			return;
		}

		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);

			System.out.println(
					"\n--------------------------------------------------------------------------------------------------------");
			System.out.println("Type: " + StringFormat.withLimit(type.getFullName(), 60) + "\tSLOC: " + type.getSloc()
					+ "\tNumber of Internal Dependencies: " + type.getNumberOfInternalDependencies());
			Set<String> dependencies = type.getInternalImports();

			System.out.println("INTERNAL DEPENDENCIES:");
			for (String dependency : dependencies) {
				System.out.println("\t" + dependency);
			}
		}
	}

	@Override
	public void showNamespaceCoupling() {
		System.out.println(
				"\n\n---------------------------------------------------------------------------------------------");
		System.out.println("NAMESPACES\t\t\t\t\t\tCA\tCE\tI\tA\tD");
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			int ca = tmr.getAfferentCoupling(namespace.getName());
			int ce = tmr.getEfferentCoupling(namespace.getName());
			double abstractness = nmr.getAbstractness(tmr.getTotalOfAbstractTypesIn(namespace.getName()),
					namespace.getNumOfTypes());
			double instability = nmr.getInstability(ca, ce);
			double distance = nmr.getDistance(abstractness, instability);
			System.out.printf("%50s\t%d\t%d\t%.3f\t%.3f\t%.3f\n", StringFormat.withLimit(namespace.getName(), 50), ca,
					ce, instability, abstractness, distance);
		}
	}

	@Override
	public void showAllCoupling() {
		showNamespaceCoupling();
		showDependencies();
	}

	@Override
	public void showThresholds() {
		System.out.println("-----------------------------------");
		System.out.println("INFORMATION ABOUT METRIC THRESHOLDS");
		System.out.println("-----------------------------------");
		MetricThreshold mt = new MetricThreshold();
		for (MetricDefinition metric : mt.getThresholds()) {
			System.out.printf("%-60s",
					StringFormat.withLimit(metric.getName() + " (" + metric.getAcronym() + ") ", 60));
			System.out.print(metric.getDescription() + "\n");
		}
	}

	public TypeMetricResult getTypeMetricResult() {
		return tmr;
	}

	@Override
	public void showTypeCoupling() {
		System.out.println(
				"\n\n-----------------------------------------------------------------------------------------------");
		System.out.println("TYPES\t\t\t\t\t\t\t\tDEP\tI-DEP\tFAN-IN\tFAN-OUT");
		System.out.println(
				"-----------------------------------------------------------------------------------------------");
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			System.out.printf("%60s\t%d\t%d\t%d\t%d\n", StringFormat.withLimit(type.getFullName(), 60),
					type.getNumberOfDependencies(), type.getNumberOfInternalDependencies(), tmr.getFanInOf(name),
					type.getFanOut());
		}
	}
}
