package fixtures.output.data;

public class StatisticData {
	private String acronym;
	private double average, median, amplitude, firstQuartile, thirdQuartile, standardDeviation,  
		upperFence, minValue, maxValue, threshold; 
	
	public StatisticData(String acronym, double firstQuartile, double thirdQuartile, 
			double average, double median, double minValue, double maxValue, double amplitude, 
			double standardDeviation, double upperFence, double threshold) {
		this.acronym            = acronym;
		this.average            = average;  
		this.median             = median;
		this.amplitude          = amplitude;
		this.firstQuartile      = firstQuartile;
		this.thirdQuartile      = thirdQuartile;
		this.standardDeviation  = standardDeviation;
		this.upperFence         = upperFence;
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

	public double getUpperFence() {
		return upperFence;
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
