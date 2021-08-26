package selection.options.dependencies;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class AllCouplingOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showAllCoupling();
	}
}
