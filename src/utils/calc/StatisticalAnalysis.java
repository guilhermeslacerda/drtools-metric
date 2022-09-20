package utils.calc;

import java.util.Arrays;

public class StatisticalAnalysis {
	private double[] elements;
	private static final double MODERATE_INTERVAL_FACTOR = 1.5;
	
	public void setElements(double[] elements) {
		this.elements = elements;
		Arrays.sort(this.elements);
	}

	public double getAverage() {
		return Arrays.stream(elements).average().orElse(0);
	}

	public double getMedian() {
		return getMedian(elements); 
	}
	
    public double getMedian(double[] data) {
    	if (data.length == 0)	return 0;
        if (data.length % 2 == 0)
            return (data[data.length / 2] + data[data.length / 2 - 1]) / 2;
        else 
        	return data[(data.length / 2)];
    }

	private double getSumOfSquareOfElements() {
		double sum = 0;
		double average = getAverage();
		for (int indx = 0; indx < elements.length; indx++)
			sum += Math.pow(elements[indx] - average, 2);
		return sum;
	}

	public double getVariance() {
		int numberOfElements = elements.length > 0 ? elements.length : 1;
		return getSumOfSquareOfElements() / Double.valueOf(numberOfElements);
	}
	
	public double getStandardDeviation() {
		return Math.sqrt(getVariance());
	}
	
	public double getFirstQuartile() {
		double[] firstHalf = Arrays.copyOfRange(elements, 0, elements.length / 2);
		return getMedian(firstHalf);
	}
	
	public double getThirdQuartile() {
		double[] secondHalf = (elements.length % 2 == 0 )?
							Arrays.copyOfRange(elements, elements.length / 2, elements.length) : 
								Arrays.copyOfRange(elements, elements.length / 2 + 1, elements.length); 
		return getMedian(secondHalf);
	}
	
	public double getInterQuartileRange() {
		return getThirdQuartile() - getFirstQuartile();
	}
	
	public double getLowerFence() {
		return getFirstQuartile() - MODERATE_INTERVAL_FACTOR * getInterQuartileRange();
	}
	
	public double getUpperFence() {
		return getThirdQuartile() + MODERATE_INTERVAL_FACTOR * getInterQuartileRange();
	}

	public double getMinValue() {
		return elements[0];
	}
	
	public double getMaxValue() {
		return elements[elements.length - 1];
	}

	public double getAmplitude() {
		return getMaxValue() - getMinValue();
	}
}
