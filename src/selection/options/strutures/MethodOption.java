package selection.options.strutures;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class MethodOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showMethods();
	}
}
