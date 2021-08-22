package structures.statistics;

import utils.calc.StatisticalAnalysis;

public abstract class StatisticalOperations {
	protected StatisticalAnalysis sa;

	public StatisticalOperations() {
		sa = new StatisticalAnalysis();
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
}
