package selection.options.statistics;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class StatisticTypeOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.getTypeMetricResult().defineFanIn();
		output.showStatisticalType();
	}
}
