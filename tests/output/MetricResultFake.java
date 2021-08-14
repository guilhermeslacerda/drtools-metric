package output;

import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;

public class MetricResultFake implements MetricOutput {

	@Override
	public void showNamespaces() {
	}

	@Override
	public void showTypes() {
	}

	@Override
	public void showMethods() {
	}

	@Override
	public void show() {
	}

	@Override
	public void showSummary() {
	}

	@Override
	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr) {
	}

	@Override
	public void showDependencies() {
	}

	@Override
	public void showCyclicDependencies() {
	}

	@Override
	public void showInternalDependencies() {
	}

	@Override
	public void showNamespaceCoupling() {
	}

	@Override
	public void showAllCoupling() {
	}

	@Override
	public void showThresholds() {
	}

	@Override
	public TypeMetricResult getTypeMetricResult() {
		return null;
	}

	@Override
	public void showTypeCoupling() {
	}

	@Override
	public void loadCollectionsToStatisticalComputation() {
	}
}
