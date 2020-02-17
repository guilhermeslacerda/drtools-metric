package general.options;

import output.MetricOutput;

public class MethodOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showMethods();
	}
}
