package output;

import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;
import utils.SystemUtils;

public class MetricResultFile implements MetricOutput {
	private static final String SUMMARY_INFO = "drtools-metric-summary.csv";
	private static final String NAMESPACES_INFO = "drtools-metric-namespaces.csv";
	private static final String TYPES_INFO = "drtools-metric-types.csv";
	private static final String METHODS_INFO = "drtools-metric-methods.csv";
	private static final String NAMESPACE_COUPLING_INFO = "drtools-metric-namespace-coupling.csv";
	private static final String INTERNAL_DEPENDENCIES_INFO = "drtools-metric-internal-dependencies.json";
	private static final String CYCLIC_DEPENDENCIES_INFO = "drtools-metric-cyclic-dependencies.csv";
	private static final String METRIC_THRESHOLDS_INFO = "drtools-metric-thresholds.csv";
	private static final String TYPE_COUPLING_INFO = "drtools-metric-type-coupling.csv";
	private static final String TYPE_RESONANCE_INFO = "drtools-metric-resonance.json";
	private TypeMetricResult tmr;
	private MetricResultCSV csv;
	private MetricResultJSON json;

	public MetricResultFile() {
		csv = new MetricResultCSV();
		json = new MetricResultJSON();
	}
	
	@Override
	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr) {
		this.tmr = tmr;
		csv.setResults(nmr, tmr, mmr);
		json.setResults(nmr, tmr, mmr);
	}

	@Override
	public void showNamespaces() {
		InfoConsole.print("\nNamespaces info (CSV)...............");
		InfoConsole.print(generateNamespacesFile(NAMESPACES_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateNamespacesFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateNamespaces());
	}

	@Override
	public void showTypes() {
		InfoConsole.print("\nTypes info (CSV)....................");
		InfoConsole.print(generateTypesFile(TYPES_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateTypesFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateTypes());
	}

	@Override
	public void showMethods() {
		InfoConsole.print("\nMethods info (CSV)..................");
		InfoConsole.print(generateMethodsFile(METHODS_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateMethodsFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateMethods());
	}

	@Override
	public void show() {
		showSummary();
		showTypesResonance();
		showNamespaces();
		showTypes();
		showMethods();
		showNamespaceCoupling();
		showInternalDependencies();
		showCyclicDependencies();
		showThresholds();
		showTypeCoupling();
		InfoConsole.printMetricVisualizationUsage();
	}

	@Override
	public void showSummary() {
		InfoConsole.print("\nSummary info (CSV)..................");
		InfoConsole.print(generateSummaryFile(SUMMARY_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateSummaryFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateSummary());
	}

	@Override
	public void showDependencies() {
	}

	@Override
	public TypeMetricResult getTypeMetricResult() {
		return tmr;
	}

	@Override
	public void showCyclicDependencies() {
		InfoConsole.print("\nCyclic dependencies info (CSV)......");
		InfoConsole.print(generateCyclicDependenciesFile(CYCLIC_DEPENDENCIES_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateCyclicDependenciesFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateCyclicDependencies());
	}

	@Override
	public void showInternalDependencies() {
		InfoConsole.print("\nInternal dependencies info (JSON)...");
		InfoConsole.print(generateInternalDependenciesFile(INTERNAL_DEPENDENCIES_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateInternalDependenciesFile(String fileName) {
		return SystemUtils.writeFile(fileName, json.generateInternalDependencies());
	}

	@Override
	public void showNamespaceCoupling() {
		InfoConsole.print("\nNamespace coupling info (CSV).......");
		InfoConsole.print(generateNamespaceCouplingFile(NAMESPACE_COUPLING_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateNamespaceCouplingFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateNamespaceCoupling());
	}

	@Override
	public void showAllCoupling() {
	}

	@Override
	public void showThresholds() {
		InfoConsole.print("\nMetric thresholds info (CSV)........");
		InfoConsole.print(generateMetricThresholdsFile(METRIC_THRESHOLDS_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateMetricThresholdsFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateThresholds());
	}

	@Override
	public void showTypeCoupling() {
		InfoConsole.print("\nType coupling info (CSV)............");
		InfoConsole.print(generateTypeCouplingFile(TYPE_COUPLING_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateTypeCouplingFile(String fileName) {
		return SystemUtils.writeFile(fileName, csv.generateTypeCoupling());
	}

	public void showTypesResonance() {
		InfoConsole.print("\nCode resonance info (JSON)..........");
		InfoConsole.print(generateTypesResonanceFile(TYPE_RESONANCE_INFO) ? "[DONE]" : "[FAIL]");
	}

	public boolean generateTypesResonanceFile(String fileName) {
		return SystemUtils.writeFile(fileName, json.generateTypesResonance());
	}
}
