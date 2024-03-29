package structures.metrics;

import java.util.ArrayList;
import java.util.List;

public class MetricThreshold {
	private List<MetricDefinition> thresholds;
 
	public MetricThreshold() {
		thresholds = new ArrayList<>();
		thresholds.add(new MetricDefinition("SMALL", "Small Project", "small project with < 50 KLOC or 200 < classes",
				0, 200));
		thresholds.add(new MetricDefinition("MEDIUM", "Medium Project",
				"medium project with (50 KLOC <= project <= 250 KLOC) or (200 <= classes <= 1000)", 200, 1000));
		thresholds.add(new MetricDefinition("LARGE", "Large Project", "large project with > 250 KLOC or > 1000 classes",
				1000, Double.MAX_VALUE));

		thresholds.add(new MetricDefinition("NOC", "Number of Types/Classes",
				"Good: <= 11; Regular: between 11 and 28; Bad: > 28", 11, 28));
		thresholds.add(new MetricDefinition("NAC", "Number of Abstract Types/Classes", "without references", 0, 0));

		thresholds.add(new MetricDefinition("SLOC", "Type Lines of Code", "Bad: > 500", 0, 500));
		thresholds.add(new MetricDefinition("NOM", "Number of Methods",
				"Good: <= 6; Regular: between 6 and 14; Bad: > 14", 6, 14));
		thresholds.add(new MetricDefinition("WMC", "Weighted Methods per Class",
				"Good: <= 20; Regular: between 20 and 100; Bad: > 100", 20, 100));
		thresholds.add(new MetricDefinition("DEP", "Number of external types dependencies", "Bad: > 20", 0, 20));
		thresholds.add(new MetricDefinition("I-DEP", "Number of internal types dependencies", "Bad: > 15", 0, 15));
		thresholds.add(new MetricDefinition("FAN-IN", "Number of other types that depend on a given type", "Bad: > 10", 0, 10));
		thresholds.add(new MetricDefinition("FAN-OUT", "Number of other types referenced by a type", "Bad: > 15", 0, 15));
		thresholds.add(new MetricDefinition("NPM", "Number of Public Methods",
				"Good: <= 10; Regular: between 11 and 40; Bad: > 40", 10, 40));
		thresholds.add(new MetricDefinition("NOA", "Number of Attributes/Fields",
				"Good: <= 3; Regular: between 3 and 8; Bad: > 8", 3, 8));
		thresholds.add(new MetricDefinition("LCOM3", "Lack of Cohesion in Methods",
				"Good: = 0; Regular: between 0 and 1; Bad: > 1", 0, 1));

		thresholds.add(new MetricDefinition("MLOC", "Method Lines of Code",
				"Good: <= 10; Regular: between 10 and 30; Bad: > 30", 10, 30));
		thresholds.add(new MetricDefinition("CYCLO", "Cyclomatic Complexity",
				"Good: <= 2; Regular: between 2 and 10; Bad: > 10", 2, 10));
		thresholds.add(new MetricDefinition("CALLS", "Number of Invocations", "Bad: > 5", 0, 5));
		thresholds.add(new MetricDefinition("NBD", "Nested Block Depth","Good: <= 1; Regular: between 1 and 3; Bad: > 3",
				1, 3));
		thresholds.add(new MetricDefinition("PARAM", "Number of Parameters", "Good: <= 2; Regular: between 2 and 4; Bad: > 4",
				2, 4));

		thresholds.add(new MetricDefinition("CA", "Afferent Coupling", "Good: <= 7; Regular: between 7 and 39; Bad: > 39",
				7, 39));
		thresholds.add(new MetricDefinition("CE", "Efferent Coupling", "Good: <= 6; Regular: between 6 and 16; Bad: > 16",
				6, 16));
		thresholds.add(new MetricDefinition("I", "Package Instability", "range between 0=Maximally stability and 1=Maximally instability",
				0, 1));
		thresholds.add(new MetricDefinition("A", "Abstractness Degree", "range between 0=Minimally abstractness and 1=Maximally abstractness",
				0, 1));
		thresholds.add(new MetricDefinition("D", "Normalized Distance", "range between 0=exactly located in the main sequence and 1=far from the main sequence",
				0, 1));
	}

	public List<MetricDefinition> getThresholds() {
		return thresholds;
	}
	
	public MetricDefinition getMetricDefinition(String acronym) {
		for (MetricDefinition metricDefinition : thresholds) {
			if (acronym.equals(metricDefinition.getAcronym()))
					return metricDefinition;
		}
		return null;
	}
}

