package selection.options.statistics;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class StatisticMethodOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.showStatisticalMethod();
	}
}
