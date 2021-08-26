package fixtures.statistics;

import fixtures.MethodMetricFixture;
import structures.metrics.MethodMetric;
import structures.results.MethodMetricResult;
import structures.statistics.StatisticOfMethod;

public abstract class StatisticOfMethodFixture {
	protected MethodMetricResult mmr;
	protected StatisticOfMethod sm;

	protected void createMethods() {
		MethodMetric m1 = new MethodMetricFixture().withType("test.model.Client").withName("getClient").withCyclo(5).parameters(1)
				.startLine(10).endLine(30).withCalls(15).create();
		MethodMetric m2 = new MethodMetricFixture().withType("test.services.CustomerService").withName("getOrder").withCyclo(10).parameters(3)
				.startLine(40).endLine(75).withCalls(12).create();
		MethodMetric m3 = new MethodMetricFixture().withType("test.services.InvoiceService").withName("getOrder").withCyclo(15).parameters(3)
				.startLine(40).endLine(75).withCalls(13).withNBD(3).create();
		
		mmr.add(m1);
		mmr.add(m2);
		mmr.add(m3);
	}

	protected void createStructureToTest() {
		mmr = new MethodMetricResult();
		sm  = new StatisticOfMethod();
		createMethods();
		loadData();
	}

	protected void loadData() {
		sm.defineResults(mmr);
		sm.compute();
	}
}
