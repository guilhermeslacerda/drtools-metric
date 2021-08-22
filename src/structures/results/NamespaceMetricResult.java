package structures.results;

import java.util.ArrayList;
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
	private double typesPerNamespace[];
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
		return this.getNamespacesName().size() > 0? this.getNamespacesName().size() : 1;
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
		typesPerNamespace = new double[getTotalNumberOfNamespaces()];
		int position = 0;
		for (String name : this.getNamespacesName()) {
			NamespaceMetric namespace = this.getNamespace(name);
			typesPerNamespace[position++] = namespace.getNumOfTypes();
		}
	}

	public double[] getTypesPerNamespace() {
		return typesPerNamespace;
	}	
}
