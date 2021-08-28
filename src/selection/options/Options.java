package selection.options;

import java.util.HashMap;
import java.util.Map;

import selection.options.dependencies.AllCouplingOption;
import selection.options.dependencies.CyclicDependencyOption;
import selection.options.dependencies.DependencyOption;
import selection.options.dependencies.InternalDependencyOption;
import selection.options.dependencies.NamespaceCouplingOption;
import selection.options.dependencies.TypeCouplingOption;
import selection.options.general.AllMetricsOption;
import selection.options.general.MetricVisualizationOption;
import selection.options.general.SummaryOption;
import selection.options.general.ThresholdsOption;
import selection.options.statistics.StatisticMethodOption;
import selection.options.statistics.StatisticNamespaceOption;
import selection.options.statistics.StatisticTypeOption;
import selection.options.strutures.MethodOption;
import selection.options.strutures.NamespaceOption;
import selection.options.strutures.TypeOption;

public class Options {
	protected Map<String, OptionDefinition> options;
	
	public Options() {
		options = new HashMap<>();
		options.put("-a", new AllMetricsOption());
		options.put("-ac", new AllCouplingOption());
		options.put("-s", new SummaryOption());
		options.put("-n", new NamespaceOption());
		options.put("-t", new TypeOption());
		options.put("-m", new MethodOption());
		options.put("-d", new DependencyOption());
		options.put("-cd", new CyclicDependencyOption());
		options.put("-id", new InternalDependencyOption());
		options.put("-nc", new NamespaceCouplingOption());
		options.put("-mt", new ThresholdsOption());
		options.put("-tc", new TypeCouplingOption());
		options.put("-mv", new MetricVisualizationOption());
		options.put("-sn", new StatisticNamespaceOption());
		options.put("-st", new StatisticTypeOption());
		options.put("-sm", new StatisticMethodOption());
	}

	public OptionDefinition selectBy(String argument) {
		return options.get(argument);
	}
}
