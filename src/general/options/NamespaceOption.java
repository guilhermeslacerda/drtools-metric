package general.options;

import output.MetricOutput;

public class NamespaceOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showNamespaces();
	}
}
