package fixtures;

import java.util.Set;

import structures.metrics.TypeMetric;

public class TypeMetricFixture {
	private TypeMetric typeMetric;

	public TypeMetricFixture() {
		typeMetric = new TypeMetric();
	}
	
	public TypeMetricFixture withName(String name) {
		typeMetric.setName(name);
		return this;
	}
	
	public TypeMetricFixture withNamespace(String name) {
		typeMetric.setNamespace(name);
		return this;
	}
	
	public TypeMetricFixture isAbstract(boolean flag) {
		typeMetric.setAbstract(flag);
		return this;
	}

	public TypeMetricFixture isInterface(boolean flag) {
		typeMetric.setInterface(flag);
		return this;
	}

	public TypeMetricFixture withVariables(int number) {
		typeMetric.setNumOfVariables(number);
		return this;
	}

	public TypeMetricFixture withSloc(int number) {
		typeMetric.setSloc(number);
		return this;
	}

	public TypeMetricFixture withImports(Set<String> imports) {
		typeMetric.setImports(imports);
		return this;
	}

	public TypeMetricFixture withMethods(int number) {
		typeMetric.setNumOfMethods(number);
		return this;
	}

	public TypeMetricFixture withPublicMethods(int number) {
		typeMetric.setNumOfPublicMethods(number);
		return this;
	}

	public TypeMetric create() {
		return typeMetric;
	}
}
