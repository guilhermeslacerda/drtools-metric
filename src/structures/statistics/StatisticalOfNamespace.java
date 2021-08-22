package structures.statistics;

import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.NamespaceMetric;
import structures.results.NamespaceMetricResult;

public class StatisticalOfNamespace extends StatisticalOperations {
	private List<NamespaceMetric> namespaceMetrics;
	private double[] noc;
	
	public StatisticalOfNamespace() {
		super();
	}
	
	public void defineResults(NamespaceMetricResult nmr) {
		namespaceMetrics = nmr.getNamespaceMetrics().values().stream().collect(Collectors.toList());
	}
	
	@Override
	public void compute() {
		noc = new double[namespaceMetrics.size()];
		int indx = 0;
		for (NamespaceMetric namespaceMetric : namespaceMetrics) 
			noc[indx++] = namespaceMetric.getNumOfTypes();
	}
	
	public void useNOC() {
		sa.setElements(noc);
	}
}
