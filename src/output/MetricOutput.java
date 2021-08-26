package output;

import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;

public interface MetricOutput {
	void showNamespaces();
	void showTypes();
	void showMethods();
	void show();
	void showSummary();
	void showDependencies();
	void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr);
	TypeMetricResult getTypeMetricResult();
	void showCyclicDependencies();
	void showInternalDependencies();
	void showNamespaceCoupling();
	void showAllCoupling();
	void showThresholds();
	void showTypeCoupling();
	void loadCollectionsToStatisticalComputation();
	void showStatisticalNamespace();
}
