package structures.statistics;

import java.util.List;

import structures.metrics.MetricDefinition;
import structures.metrics.MetricThreshold;
import structures.results.StatisticalMetricResult;
import utils.calc.StatisticalAnalysis;

public abstract class StatisticalOperations {
	protected StatisticalAnalysis sa;
	protected List<StatisticalMetricResult> statisticList;
	protected MetricThreshold mt;

	public StatisticalOperations() {
		sa = new StatisticalAnalysis();
		mt = new MetricThreshold();
	}
	
	public abstract void compute();

	public double getMedian() {
		return sa.getMedian();
	}
	
	public double getAverage() {
		return sa.getAverage();
	}

	public double getFirstQuartile() {
		return sa.getFirstQuartile();
	}

	public double getThirdQuartile() {
		return sa.getThirdQuartile();
	}

	public double getInterQuartileRange() {
		return sa.getInterQuartileRange();
	}

	public double getLowerFence() {
		return sa.getLowerFence();
	}

	public double getUpperFence() {
		return sa.getUpperFence();
	}

	public double getMinValue() {
		return sa.getMinValue();
	}

	public double getMaxValue() {
		return sa.getMaxValue();
	}

	public double getAmplitude() {
		return sa.getAmplitude();
	}

	public double getStandardDeviation() {
		return sa.getStandardDeviation();
	}
	
	protected abstract void setInfo();
	
	public List<StatisticalMetricResult> getList() {
		setInfo();
		return statisticList;
	}
	
	public MetricDefinition getMetric(String acronym) {
		return mt.getMetricDefinition(acronym);
	}
}
