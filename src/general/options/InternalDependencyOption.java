package general.options;

import output.MetricOutput;

public class InternalDependencyOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showInternalDependencies();
	}
}
