package selection.options.general;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class ThresholdsOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showThresholds();
	}
}
