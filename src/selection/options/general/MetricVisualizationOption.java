package selection.options.general;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class MetricVisualizationOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.show();
	}
}
