package structures.results;

public class StatisticMetricResult {
	private String acronym;
	private double average, median, amplitude, firstQuartile, thirdQuartile, standardDeviation, lowerFence, 
		upperFence, interQuartileRange, minValue, maxValue, threshold; 
	
	public StatisticMetricResult(String acronym, double average, double median, double amplitude, double firstQuartile,
				double thirdQuartile, double standardDeviation, double lowerFence, double upperFence, double interQuartileRange, 
				double minValue, double maxValue, double threshold) {
		this.acronym            = acronym;
		this.average            = average;  
		this.median             = median;
		this.amplitude          = amplitude;
		this.firstQuartile      = firstQuartile;
		this.thirdQuartile      = thirdQuartile;
		this.standardDeviation  = standardDeviation;
		this.lowerFence         = lowerFence;
		this.upperFence         = upperFence;
		this.interQuartileRange = interQuartileRange;
		this.minValue           = minValue;
		this.maxValue           = maxValue;
		this.threshold          = threshold;
	}

	public String getAcronym() {
		return acronym;
	}

	public double getAverage() {
		return average;
	}

	public double getMedian() {
		return median;
	}

	public double getAmplitude() {
		return amplitude;
	}

	public double getFirstQuartile() {
		return firstQuartile;
	}

	public double getThirdQuartile() {
		return thirdQuartile;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public double getLowerFence() {
		return lowerFence;
	}

	public double getUpperFence() {
		return upperFence;
	}

	public double getInterQuartileRange() {
		return interQuartileRange;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public double getThreshold() {
		return threshold;
	}
}
