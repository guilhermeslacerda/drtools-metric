package fixtures;

import structures.metrics.MethodMetric;

public class MethodMetricFixture {
	private MethodMetric methodMetric;

	public MethodMetricFixture() {
		methodMetric = new MethodMetric();
	}
	
	public MethodMetricFixture withName(String name) {
		methodMetric.setName(name);
		return this;
	}
	
	public MethodMetricFixture withType(String name) {
		methodMetric.setTypeName(name);
		return this;
	}
	
	public MethodMetricFixture startLine(int start) {
		methodMetric.setStartLine(start);
		return this;
	}

	public MethodMetricFixture endLine(int end) {
		methodMetric.setEndLine(end);
		return this;
	}

	public MethodMetricFixture parameters(int number) {
		methodMetric.setNumOfParameters(number);
		return this;
	}

	public MethodMetricFixture withCyclo(int cyclo) {
		methodMetric.setCyclo(cyclo);
		return this;
	}

	public MethodMetricFixture withCalls(int calls) {
		methodMetric.setCalls(calls);
		return this;
	}

	public MethodMetricFixture withNBD(int nbd) {
		methodMetric.setNestedBlockDepth(nbd);
		return this;
	}

	public MethodMetric create() {
		return methodMetric;
	}
}
