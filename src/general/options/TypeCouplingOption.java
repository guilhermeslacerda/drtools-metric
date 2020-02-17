package general.options;

import output.MetricOutput;

public class TypeCouplingOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.showTypeCoupling();
	}
}
