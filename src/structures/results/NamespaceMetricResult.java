package structures.results;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import structures.MetricResultNotifier;
import structures.metrics.NamespaceMetric;

public class NamespaceMetricResult implements MetricResultNotifier<NamespaceMetric> {
	private Map<String, NamespaceMetric> namespaceMetrics;
	private int typesPerNamespace[];
	private int number = 0;

	public NamespaceMetricResult() {
		namespaceMetrics = new LinkedHashMap<>();
	}

	@Override
	public void setTop(int number) {
		this.number = number;
	}

	@Override
	public void add(NamespaceMetric metric) {
		if (!namespaceMetrics.containsKey(metric.getName()))
			namespaceMetrics.put(metric.getName(), metric);
		else {
			NamespaceMetric nmm = namespaceMetrics.get(metric.getName());
			nmm.addNumOfTypes();
			namespaceMetrics.replace(metric.getName(), nmm);
		}
	}

	public Map<String, NamespaceMetric> getNamespaceMetrics() {
		return namespaceMetrics;
	}

	public LinkedHashMap<String, NamespaceMetric> getSortedNamespaceMetrics() {
		return namespaceMetrics.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public Set<String> getNamespacesName() {
		return namespaceMetrics.keySet();
	}

	public NamespaceMetric getNamespace(String name) {
		return namespaceMetrics.get(name);
	}

	public int getTotalNumberOfTypes() {
		int totalTypes = 0;
		for (String name : this.getNamespacesName()) {
			NamespaceMetric namespace = this.getNamespace(name);
			totalTypes += namespace.getNumOfTypes();
		}
		return totalTypes;
	}

	public int getTotalNumberOfNamespaces() {
		return this.getNamespacesName().size();
	}

	public Set<String> getNamesResult() {
		Set<String> names = (number > 0) ? getSortedNamespaceMetricsWithLimit() : getSortedNamespaceMetrics().keySet();
		return names;
	}

	private Set<String> getSortedNamespaceMetricsWithLimit() {
		List<String> namespaceList = new ArrayList<>(getSortedNamespaceMetrics().keySet());
		return new LinkedHashSet<>(
				namespaceList.subList(0, number > namespaceList.size() ? namespaceList.size() : number));
	}

	public int getTotalNumberOfAbstractTypes() {
		int totalAbstractTypes = 0;
		for (String name : this.getNamespacesName()) {
			NamespaceMetric namespace = this.getNamespace(name);
			totalAbstractTypes += namespace.getNumOfAbstractTypes();
		}
		return totalAbstractTypes;
	}

	public double getInstability(double ca, double ce) {
		return (ca + ce > 0) ? ce / (ca + ce) : 0;
	}

	public double getAbstractness(double nac, double noc) {
		return nac / noc;
	}

	public double getDistance(double abstractness, double instability) {
		double distance = abstractness + instability - 1;
		return distance >= 0 ? distance : distance * (-1);
	}

	public void defineNumberOfTypesPerNamespace() {
		typesPerNamespace = new int[getTotalNumberOfNamespaces()];
		int position = 0;
		for (String name : this.getNamespacesName()) {
			NamespaceMetric namespace = this.getNamespace(name);
			typesPerNamespace[position++] = namespace.getNumOfTypes();
		}
	}

	public double getMedianOfTypes() {
		defineNumberOfTypesPerNamespace();
		Arrays.sort(typesPerNamespace);

		int odd = typesPerNamespace.length % 2;
		if (odd == 1)
			return typesPerNamespace[((typesPerNamespace.length + 1) / 2) - 1];

		int middle = typesPerNamespace.length / 2;
		return (typesPerNamespace[middle - 1] + typesPerNamespace[middle]) / 2;
	}

	private double getSumOfSquareOfTypePerNamespace() {
		double sum = 0;
		for (int indx = 0; indx < typesPerNamespace.length; indx++)
			sum += Math.pow(typesPerNamespace[indx], 2);
		return sum;
	}

	private double getTypesVariance() {
		double p1 = 1 / Double.valueOf(typesPerNamespace.length - 1);
		double p2 = (getSumOfSquareOfTypePerNamespace()
				- (Math.pow(getTotalNumberOfTypes(), 2) ) / Double.valueOf(typesPerNamespace.length));
		return p1 * p2;
	}
	
	public double getStandardDeviationTypes() {
		return Math.sqrt(getTypesVariance());
	}
}
