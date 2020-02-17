package general.options;

import output.MetricOutput;

public class ThresholdsOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showThresholds();
	}
}
