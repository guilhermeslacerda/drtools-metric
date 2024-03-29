package selection.options.statistics;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class StatisticAndMethodOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showStatisticAndMethod();
	}
}
