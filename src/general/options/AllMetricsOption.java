package general.options;

import output.MetricOutput;

public class AllMetricsOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.show();
	}
}
