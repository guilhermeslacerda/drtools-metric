package output;

import java.util.List;
import java.util.Set;

import output.utils.InfoConsole;
import structures.metrics.MethodMetric;
import structures.metrics.MetricDefinition;
import structures.metrics.MetricThreshold;
import structures.metrics.NamespaceMetric;
import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.StatisticMetricResult;
import structures.results.TypeMetricResult;
import structures.statistics.StatisticOfMethod;
import structures.statistics.StatisticOfNamespace;
import structures.statistics.StatisticOfType;
import utils.calc.StatisticalAnalysis;
import utils.files.StringFormat;

public class MetricResultCSV implements MetricOutput, MetricFile {
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
		String lines = generateNamespaces();
		System.out.print(lines);
	}

	@Override
	public String generateNamespaces() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"namespace\",\"noc\",\"nac\"\n");
		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			sb.append(String.format("\"%s\",%d,%d\n", namespace.getName(), namespace.getNumOfTypes(),
					tmr.getTotalOfAbstractTypesIn(namespace.getName())));
		}
		return sb.toString();
	}

	@Override
	public void showTypes() {
		String lines = generateTypes();
		System.out.print(lines);
	}

	@Override
	public String generateTypes() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\"type\",\"sloc\",\"nom\",\"npm\",\"wmc\",\"dep\",\"i-dep\",\"fan-in\",\"fan-out\",\"noa\",\"lcom3\"\n");
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			sb.append(String.format("\"%s\",%d,%d,%d,%d,%d,%d,%d,%d,%d,%s\n", type.getFullName(), type.getSloc(),
					type.getNumOfMethods(), type.getNumOfPublicMethods(), tmr.getTotalCycloBy(type.getFullName()),
					type.getNumberOfDependencies(), type.getNumberOfInternalDependencies(), tmr.getFanInOf(name),
					type.getFanOut(), type.getNumOfVariables(),
					String.valueOf(tmr.getLackCohesionMethods(name)).replace(',', '.')));
		}
		return sb.toString();
	}

	@Override
	public void showMethods() {
		String lines = generateMethods();
		System.out.print(lines);
	}

	@Override
	public String generateMethods() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"method\",\"loc\",\"cyclo\",\"calls\",\"nbd\",\"param\"\n");
		for (String name : mmr.getNamesResult()) {
			if (name == null)
				continue;
			MethodMetric method = mmr.getMethod(name);
			sb.append(String.format("\"%s\",%d,%d,%d,%d,%d\n", StringFormat.convertQuotation(method.getName()),
					method.getLoc(), method.getCyclo(), method.getCalls(), method.getNestedBlockDepth(),
					method.getNumOfParameters()));
		}
		return sb.toString();
	}

	@Override
	public void show() {
	}

	@Override
	public void showSummary() {
		String lines = generateSummary();
		System.out.print(lines);

	}

	@Override
	public String generateSummary() {
		StringBuilder sb = new StringBuilder();
		loadCollectionsToStatisticalComputation();
		
		sa.setElements(nmr.getTypesPerNamespace());
		sb.append("\"description\",value,percent,median,std_dev\n");
		sb.append("\"total_namespaces\"," + nmr.getTotalNumberOfNamespaces() + ",100,0.0,0.0\n");
		sb.append(
				"\"total_types\"," + tmr.getTotalNumberOfTypes() + ","
						+ String.valueOf(tmr.getTotalNumberOfTypes() / nmr.getTotalNumberOfNamespaces())
								.replace(',', '.')
						+ "," + String.valueOf(sa.getMedian()).replace(',', '.') + ","
						+ String.valueOf(sa.getStandardDeviation()).replace(',', '.') + "\n");

		sa.setElements(tmr.getSLOCPerType());
		sb.append("\"total_sloc\"," + tmr.getTotalSLOC() + ","
				+ String.valueOf(tmr.getTotalSLOC() / tmr.getTotalNumberOfTypes()).replace(',', '.') + ","
				+ String.valueOf(sa.getMedian()).replace(',', '.') + ","
				+ String.valueOf(sa.getStandardDeviation()).replace(',', '.') + "\n");
		
		sa.setElements(mmr.getMethodsPerType());
		sb.append("\"total_methods\"," + mmr.getTotalNumberOfMethods() + ","
				+ String.valueOf(mmr.getTotalNumberOfMethods() / tmr.getTotalNumberOfTypes()).replace(',', '.') + ","
				+ String.valueOf(sa.getMedian()).replace(',', '.') + ","
				+ String.valueOf(sa.getStandardDeviation()).replace(',', '.') + "\n");
		
		sb.append("\"total_cyclo\"," + mmr.getTotalCyclo() + ","
				+ String.valueOf(mmr.getTotalCyclo() / tmr.getTotalNumberOfTypes()).replace(',', '.') + ",0.0,0.0\n");

		return sb.toString();
	}
	
	@Override
	public void loadCollectionsToStatisticalComputation() {
		nmr.defineNumberOfTypesPerNamespace();
		tmr.defineNumberOfSLOCPerTypes();
		mmr.defineNumberOfMethodsPerType();
	}
	
	@Override
	public void showDependencies() {
	}

	@Override
	public void showCyclicDependencies() {
		String lines = generateCyclicDependencies();
		if (lines.isEmpty()) {
			InfoConsole.printHeader("This project hasn't types with cyclic dependencies...");
			return;
		}
		System.out.print(lines);
	}

	@Override
	public String generateCyclicDependencies() {
		Set<String> names = tmr.getCyclicDependencies();
		if (names.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append("\"from\",\"to\",\"number\"\n");
		for (String name : names) {
			String[] types = name.split(" - ");
			sb.append(String.format("\"%s\",\"%s\",%d\n", types[0], types[1], 1));
		}

		return sb.toString();
	}

	@Override
	public void showInternalDependencies() {
	}

	@Override
	public void showNamespaceCoupling() {
		String lines = generateNamespaceCoupling();
		System.out.print(lines);
	}

	@Override
	public String generateNamespaceCoupling() {
		StringBuilder sb = new StringBuilder();

		sb.append("\"namespace\",\"ca\",\"ce\",\"instability\",\"abstractness\",\"distance\"\n");
		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			int ca = tmr.getAfferentCoupling(namespace.getName());
			int ce = tmr.getEfferentCoupling(namespace.getName());
			double abstractness = nmr.getAbstractness(tmr.getTotalOfAbstractTypesIn(namespace.getName()),
					namespace.getNumOfTypes());
			double instability = nmr.getInstability(ca, ce);
			double distance = nmr.getDistance(abstractness, instability);
			sb.append(String.format("\"%s\",%d,%d,%s,%s,%s\n", namespace.getName(), ca, ce, instability, abstractness,
					distance));
		}

		return sb.toString();
	}

	@Override
	public void showAllCoupling() {
	}

	@Override
	public void showThresholds() {
		String lines = generateThresholds();
		System.out.print(lines);
	}

	@Override
	public String generateThresholds() {
		StringBuilder sb = new StringBuilder();

		sb.append("\"acronym\",\"name\",\"description\",\"min\",\"max\"\n");
		MetricThreshold mt = new MetricThreshold();
		for (MetricDefinition metric : mt.getThresholds())
			sb.append(String.format("\"%s\",\"%s\",\"%s\",%s,%s\n", metric.getAcronym(), metric.getName(),
					metric.getDescription(), String.valueOf(metric.getMin()).replace(',', '.'),
					String.valueOf(metric.getMax()).replace(',', '.')));

		return sb.toString();
	}

	@Override
	public TypeMetricResult getTypeMetricResult() {
		return tmr;
	}

	@Override
	public void showTypeCoupling() {
		String lines = generateTypeCoupling();
		System.out.print(lines);
	}

	@Override
	public String generateTypeCoupling() {
		StringBuilder sb = new StringBuilder();

		sb.append("\"type\",\"dep\",\"i-dep\",\"fan-in\",\"fan-out\"\n");
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			sb.append(String.format("\"%s\",%d,%d,%d,%d\n", type.getFullName(), type.getNumberOfDependencies(),
					type.getNumberOfInternalDependencies(), tmr.getFanInOf(name), type.getFanOut()));
		}

		return sb.toString();
	}

	@Override
	public void showStatisticalNamespace() {
		String lines = generateStatisticalNamespace();
		System.out.print(lines);
	}

	@Override
	public String generateStatisticalNamespace() {
		StatisticOfNamespace sn = new StatisticOfNamespace();
		sn.defineResults(nmr);
		List<StatisticMetricResult> list = sn.getList();
		return getStatisticalMetrics(list);
	}

	@Override
	public void showStatisticalType() {
		String lines = generateStatisticalType();
		System.out.print(lines);
	}

	@Override
	public String generateStatisticalType() {
		StatisticOfType st = new StatisticOfType();
		st.defineResults(tmr, mmr);
		List<StatisticMetricResult> list = st.getList();
		return getStatisticalMetrics(list);
	}

	@Override
	public void showStatisticalMethod() {
		String lines = generateStatisticalType();
		System.out.print(lines);
	}

	@Override
	public String generateStatisticalMethod() {
		StatisticOfMethod st = new StatisticOfMethod();
		st.defineResults(mmr);
		List<StatisticMetricResult> list = st.getList();
		return getStatisticalMetrics(list);
	}

	public String getStatisticalMetrics(List<StatisticMetricResult> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"metric\",\"1stQ\",\"3rdQ\",\"avg\",\"median\",\"min\",\"max\",\"max-min\",\"stddev\",\"u-fence\",\"threshold\"\n");
		for (StatisticMetricResult metric : list) {
			sb.append("\"" + metric.getAcronym() + "\"," 
					+ String.valueOf(metric.getFirstQuartile()).replace(',', '.') + ","
					+ String.valueOf(metric.getThirdQuartile()).replace(',', '.') + ","
					+ String.valueOf(metric.getAverage()).replace(',', '.') + ","
					+ String.valueOf(metric.getMedian()).replace(',', '.') + ","
					+ String.valueOf(metric.getMinValue()).replace(',', '.') + ","
					+ String.valueOf(metric.getMaxValue()).replace(',', '.') + ","
					+ String.valueOf(metric.getAmplitude()).replace(',', '.') + ","
					+ String.valueOf(metric.getStandardDeviation()).replace(',', '.') + ","
					+ String.valueOf(metric.getUpperFence()).replace(',', '.') + ","
					+ String.valueOf(metric.getThreshold()).replace(',', '.') + "\n");
		}
		return sb.toString();
	}
}
