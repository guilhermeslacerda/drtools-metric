package general.options;

import output.MetricOutput;

public class SummaryOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showSummary();
	}
}
