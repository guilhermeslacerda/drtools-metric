package structures.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import structures.metrics.NamespaceMetric;
import structures.results.NamespaceMetricResult;
import structures.results.StatisticalMetricResult;

public class StatisticOfNamespace extends StatisticalOperations {
	private List<NamespaceMetric> namespaceMetrics;
	private static final String NOC = "NOC";
	private double[] noc;
	
	public StatisticOfNamespace() { 
		super();
	}
	
	public void defineResults(NamespaceMetricResult nmr) {
		namespaceMetrics = nmr.getNamespaceMetrics().values().stream().collect(Collectors.toList());
		compute();
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
	
	@Override
	protected void setInfo() {
		statisticList = new ArrayList<>();
		statisticList.add(new StatisticalMetricResult(mt.getMetricDefinition(NOC).getAcronym(),
				sa.getAverage(), sa.getMedian(), sa.getAmplitude(), sa.getFirstQuartile(),
				sa.getThirdQuartile(), sa.getStandardDeviation(), sa.getLowerFence(),
				sa.getUpperFence(), sa.getInterQuartileRange(), sa.getMinValue(), sa.getMaxValue(),
				mt.getMetricDefinition(NOC).getMax()));
	}
}
