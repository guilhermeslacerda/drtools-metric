package general.options;

import output.MetricOutput;

public class TypeOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.showTypes();
	}
}
