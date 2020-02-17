package general.options;

import output.MetricOutput;

public class AllCouplingOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showAllCoupling();
	}
}
