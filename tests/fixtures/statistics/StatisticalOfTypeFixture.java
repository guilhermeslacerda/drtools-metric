package fixtures.statistics;

import java.util.Set;
import java.util.TreeSet;

import fixtures.TypeMetricFixture;
import structures.metrics.TypeMetric;
import structures.results.TypeMetricResult;
import structures.statistics.StatisticalOfType;

public abstract class StatisticalOfTypeFixture {
	protected TypeMetricResult tmr;
	protected StatisticalOfType st;

	protected void createTypes() {
		Set<String> imports1 = new TreeSet<>();
		Set<String> imports2 = new TreeSet<>();
		
		imports1.add("test.services.CustomerService");
		imports1.add("java.util.List");
		
		imports2.add("test.model.Client");
		
		
		Set<String> internalImports = new TreeSet<>();
		internalImports.add("test.listeners.ButtonListener");
		internalImports.add("test.model.AbstractLogin");
		internalImports.add("test.services.CustomerService");
		
		TypeMetric t1 = new TypeMetricFixture().withName("Client").withNamespace("test.model").withVariables(3)
							.withMethods(5).withImports(imports1).withSloc(50).create();
		TypeMetric t2 = new TypeMetricFixture().withName("CustomerService").withNamespace("test.services")
							.withVariables(1).withMethods(7).withImports(imports2).withSloc(150).create();
		TypeMetric t3 = new TypeMetricFixture().withName("AbstractLogin").withNamespace("test.model").withVariables(3)
							.withMethods(2).isAbstract(true).withSloc(120).create();
		TypeMetric t4 = new TypeMetricFixture().withName("ButtonListener").withNamespace("test.listeners")
				.withMethods(2).isInterface(true).withSloc(10).create();
		TypeMetric t5 = new TypeMetricFixture().withName("InvoiceService").withNamespace("services")
				.withMethods(2).withPublicMethods(1).isInterface(false).withInternalImports(internalImports).withSloc(15).create();

		tmr.add(t1);
		tmr.add(t2);
		tmr.add(t3);
		tmr.add(t4);
		tmr.add(t5);
	}

	protected void createStructureToTest() {
		tmr = new TypeMetricResult();
		st  = new StatisticalOfType();
		createTypes();
		loadData();
	}

	protected void loadData() {
		st.defineResults(tmr);
		st.compute();
	}
}
