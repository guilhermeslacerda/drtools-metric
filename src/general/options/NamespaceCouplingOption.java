package general.options;

import output.MetricOutput;

public class NamespaceCouplingOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showNamespaceCoupling();
	}
}
