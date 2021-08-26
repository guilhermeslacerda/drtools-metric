package selection.options.dependencies;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class DependencyOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showDependencies();
	}
}
