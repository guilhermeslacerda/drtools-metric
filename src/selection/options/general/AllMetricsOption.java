package selection.options.general;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class AllMetricsOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.show();
	}
}
