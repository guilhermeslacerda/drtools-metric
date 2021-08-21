package structures.statistics;

import structures.results.NamespaceMetricResult;
import utils.calc.StatisticalAnalysis;

public class StatisticalNamespace {
	private StatisticalAnalysis sa;
	private NamespaceMetricResult nmr;
	
	public StatisticalNamespace() {
		sa = new StatisticalAnalysis();
	}
	
	public void defineResults(NamespaceMetricResult nmr) {
		this.nmr = nmr;
	}
}
