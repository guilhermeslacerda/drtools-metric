package selection.options.statistics;

import output.MetricOutput;
import selection.options.OptionDefinition;

public class StatisticNamespaceOption implements OptionDefinition {

	@Override
	public void execute(MetricOutput output) {
		output.getTypeMetricResult().defineInternalDependencies();
		output.showStatisticalNamespace();
	}
}
