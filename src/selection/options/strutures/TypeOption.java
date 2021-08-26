package selection.options.strutures;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class TypeOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.showTypes();
	}
}
