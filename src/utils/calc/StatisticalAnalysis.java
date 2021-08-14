package utils.calc;

import java.util.Arrays;

public class StatisticalAnalysis {
	private int[] elements;
	
	public void setElements(int[] elements) {
		this.elements = elements;
	}

	private double getAverage() {
		return Arrays.stream(elements).average().orElse(0);
	}

	public double getMedian() {
		Arrays.sort(elements);

		if (elements.length == 0) 	return 1;
		
		int odd = elements.length % 2;
		if (odd == 1) 	return elements[((elements.length + 1) / 2) - 1];

		int middle = elements.length / 2;
		return (elements[middle - 1] + elements[middle]) / 2;
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
}
