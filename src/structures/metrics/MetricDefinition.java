package structures.metrics;

public class MetricDefinition {
	private String acronym;
	private String name;
	private String description;
	private double min;
	private double max;
	
	public MetricDefinition(String acronym, String name, String description, double min, double max) {
		this.acronym = acronym;
		this.name = name;
		this.description = description;
		this.min = min;
		this.max = max;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getMax() {
		return max;
	}
	
	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}
}
