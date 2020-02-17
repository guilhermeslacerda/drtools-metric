package fixtures.output.data;

public class SummaryData {
	private String description;
	private int value;
	private int percent;
	
	public SummaryData(String description, int value, int percent) {
		this.description = description;
		this.value = value;
		this.percent = percent;
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
}