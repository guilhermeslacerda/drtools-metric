package general.options;

import output.MetricOutput;

public class DependencyOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showDependencies();
	}
}
