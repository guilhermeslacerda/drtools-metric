package general.options;

import java.util.HashMap;
import java.util.Map;

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
	}

	public OptionDefinition selectBy(String argument) {
		return options.get(argument);
	}
}
