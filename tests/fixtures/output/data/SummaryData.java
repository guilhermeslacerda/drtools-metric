package fixtures.output.data;

public class SummaryData {
	private String description;
	private int value;
	private int percent;
	private double median;
	private double stdDev;
	
	public SummaryData(String description, int value, int percent, double median, double stdDev) {
		this.description = description;
		this.value = value;
		this.percent = percent;
		this.median = median;
		this.stdDev = stdDev;
	}

	public String getDescription() {
		return description;
	}

	public int getValue() {
		return value;
	}

	public int getPercent() {
		return percent;
	}
	
	public double getMedian() {
		return median;
	}
	
	public double getStdDev() {
		return stdDev;
	}
}