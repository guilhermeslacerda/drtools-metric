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
import structures.metrics.MethodMetric;

public class MethodMetricResult implements MetricResultNotifier<MethodMetric> {
	private Map<String, MethodMetric> methodMetrics;
	private int methodsPerType[];
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
			if (method == null) continue;
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
		methodsPerType = new int[ getTotalNumberOfMethods() ];
		int position = 0;
		for (String name : this.getMethodsName()) {
			MethodMetric method = this.getMethod(name);
			methodsPerType[position++] = method.getLoc();
		}
	}
	
	public double getMedianOfMethods() {
		defineNumberOfMethodsPerType();
		Arrays.sort(methodsPerType);
		
		int odd = methodsPerType.length % 2;
		if (odd == 1)	return methodsPerType[((methodsPerType.length + 1) / 2) - 1];
		
		int middle = methodsPerType.length / 2;
		return (methodsPerType[middle - 1] + methodsPerType[middle]) / 2;
	}
	
	private double getSumOfSquareOfMethodsPerType() {
		double sum = 0;
		for (int indx = 0; indx < methodsPerType.length; indx++)
			sum += Math.pow(methodsPerType[indx], 2);
		return sum;
	}

	private double getMethodsVariance() {
		double p1 = 1 / Double.valueOf(methodsPerType.length - 1);
		double p2 = (getSumOfSquareOfMethodsPerType()
				- (Math.pow(getTotalNumberOfMethods(), 2) ) / Double.valueOf(methodsPerType.length));
		return p1 * p2;
	}
	
	public double getStandardDeviationSLOC() {
		return Math.sqrt(getMethodsVariance());
	}

}
