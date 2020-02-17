package general.options;

import output.MetricOutput;

public class CyclicDependencyOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showCyclicDependencies();
	}
}
