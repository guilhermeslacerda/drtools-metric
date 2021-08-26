package selection.options.general;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class SummaryOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showSummary();
	}
}
