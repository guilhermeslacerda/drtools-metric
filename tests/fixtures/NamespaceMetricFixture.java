package fixtures;

import structures.metrics.NamespaceMetric;

public class NamespaceMetricFixture {
	private NamespaceMetric namespaceMetric;

	public NamespaceMetricFixture() {
		namespaceMetric = new NamespaceMetric();
	}
	
	public NamespaceMetricFixture withName(String name) {
		namespaceMetric.setName(name);
		return this;
	}
	
	public NamespaceMetricFixture withAbstractTypes(int number) {
		namespaceMetric.setNumOfAbstractTypes(number);
		return this;
	}
	
	public NamespaceMetricFixture withTypes(int number) {
		namespaceMetric.setNumOfTypes(number);
		return this;
	}

	public NamespaceMetric create() {
		return namespaceMetric;
	}
}
