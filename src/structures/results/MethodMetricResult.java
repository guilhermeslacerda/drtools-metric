package structures.results;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import structures.MetricResultNotifier;
import structures.metrics.MethodMetric;

public class MethodMetricResult implements MetricResultNotifier<MethodMetric> {
	private Map<String, MethodMetric> methodMetrics;
	private double methodsPerType[];
	private int number = 0;

	public MethodMetricResult() {
		methodMetrics = new LinkedHashMap<>();
	}

	@Override
	public void setTop(int number) {
		this.number = number;
	}

	@Override
	public void add(MethodMetric metric) {
		methodMetrics.put(metric.getName(), metric);
	}

	public Map<String, MethodMetric> getMethodMetrics() {
		return methodMetrics;
	}
	
	public LinkedHashMap<String, MethodMetric> getSortedMethodMetrics() {
		return methodMetrics.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	public Set<String> getMethodsName() {
		return methodMetrics.keySet();
	}
	
	public MethodMetric getMethod(String name) {
		return methodMetrics.get(name);
	}
	
	public int getTotalCyclo() {
		int totalCyclo = 0;
		for (String name : this.getMethodsName()) {
			MethodMetric method = this.getMethod(name);
			totalCyclo += method.getCyclo();
		}
		return totalCyclo;
	}
 
	public int getTotalMLOC() {
		int totalNumberMLOC = 0;
		for (String name : this.getMethodsName()) {
			MethodMetric method = this.getMethod(name);
			totalNumberMLOC += method.getLoc();
		}
		return totalNumberMLOC;
	}
	
	public int getTotalNumberOfMethods() {
		return this.getMethodsName().size();
	}
	
	public int getTotalCycloBy(String type) {
		int totalCyclo = 0;
		String name = "";
		for (String method : methodMetrics.keySet()) {
			if (method == null) 	continue;
			MethodMetric mm = methodMetrics.get(method);
			if (!name.equals(mm.getTypeName())) {
				name = mm.getTypeName();
			}
			if (mm.getTypeName().equals(type)) {
				totalCyclo += mm.getCyclo();
			}
		}
		return totalCyclo == 0 ? 1: totalCyclo;
	}

	@Override
	public Set<String> getNamesResult() {
		Set<String>names = (number > 0) ? 
				getSortedMethodMetricsWithLimit() :
				getSortedMethodMetrics().keySet();	
		return names;
	}	
	
	private Set<String> getSortedMethodMetricsWithLimit() {
		List<String> methodList = new ArrayList<>(getSortedMethodMetrics().keySet());
		return new LinkedHashSet<>(methodList.subList(0, 
				number > methodList.size() ? methodList.size() : number));
	}
	
	public void defineNumberOfMethodsPerType() {
		methodsPerType = new double[getTotalNumberOfMethods()];
		int position = 0;
		for (String name : this.getMethodsName()) {
			MethodMetric method = this.getMethod(name);
			methodsPerType[position++] = method.getLoc();
		}
	}
	
	public double[] getMethodsPerType() {
		return methodsPerType;
	}
}
