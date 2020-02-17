package output;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import structures.metrics.MethodMetric;
import structures.metrics.MetricDefinition;
import structures.metrics.MetricThreshold;
import structures.metrics.NamespaceMetric;
import structures.metrics.TypeMetric;
import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;
import utils.JSONBuilder;

public class MetricResultJSON implements MetricOutput, MetricFile {
	private NamespaceMetricResult nmr;
	private TypeMetricResult tmr;
	private MethodMetricResult mmr;
	private Gson gson;

	@Override
	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr) {
		this.nmr = nmr;
		this.tmr = tmr;
		this.mmr = mmr;
		this.gson = new GsonBuilder().disableHtmlEscaping().create();
	}

	@Override
	public void showNamespaces() {
		System.out.println(generateNamespaces());
	}

	@Override
	public String generateNamespaces() {
		JsonArray namespaceList = new JsonArray();

		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			namespaceList.add(
					new JSONBuilder()
					.add("namespace", namespace.getName())
					.add("noc", namespace.getNumOfTypes())
					.add("nac", tmr.getTotalOfAbstractTypesIn(namespace.getName())).create()
					);
		}
		return gson.toJson(namespaceList);
	}

	@Override
	public void showTypes() {
		System.out.println(generateTypes());
	}

	@Override
	public String generateTypes() {
		JsonArray typeList = new JsonArray();
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			typeList.add(
					new JSONBuilder()
					.add("type", type.getFullName())
					.add("sloc", type.getSloc())
					.add("nom", type.getNumOfMethods())
					.add("npm", type.getNumOfPublicMethods())
					.add("wmc", tmr.getTotalCycloBy(type.getFullName()))
					.add("dep", type.getNumberOfDependencies())
					.add("i-dep", type.getNumberOfInternalDependencies())
					.add("fan-in", tmr.getFanInOf(name))
					.add("fan-out", type.getFanOut())
					.add("noa", type.getNumOfVariables()).create());
		}
		return gson.toJson(typeList);
	}

	@Override
	public void showMethods() {
		System.out.println(generateMethods());
	}

	@Override
	public String generateMethods() {
		JsonArray methodList = new JsonArray();
		
		for (String name : mmr.getNamesResult()) {
			MethodMetric method = mmr.getMethod(name);
			methodList.add(
					new JSONBuilder()
					.add("method", method.getName())
					.add("loc", method.getLoc())
					.add("cyclo", method.getCyclo())
					.add("calls", method.getCalls())
					.add("nbd", method.getNestedBlockDepth())
					.add("parameters", method.getNumOfParameters()).create()
					);
		}
		return gson.toJson(methodList);
	}

	@Override
	public void show() {
		InfoConsole.printHeader("This option is only available in console format...");
	}

	@Override
	public void showSummary() {
		System.out.println(generateSummary());
	}

	@Override
	public String generateSummary() {
		JsonArray summaryList = new JsonArray();

		JsonObject totalTypes = new JsonObject();
		totalTypes.addProperty("description", "total_types");
		totalTypes.addProperty("value", nmr.getTotalNumberOfTypes());
		totalTypes.addProperty("percent", nmr.getTotalNumberOfTypes() / nmr.getTotalNumberOfNamespaces());

		JsonObject totalSloc = new JsonObject();
		totalSloc.addProperty("description", "total_sloc");
		totalSloc.addProperty("value", tmr.getTotalSLOC());
		totalSloc.addProperty("percent", tmr.getTotalSLOC() / nmr.getTotalNumberOfTypes());

		JsonObject totalMethods = new JsonObject();
		totalMethods.addProperty("description", "total_methods");
		totalMethods.addProperty("value", mmr.getTotalNumberOfMethods());
		totalMethods.addProperty("percent", mmr.getTotalNumberOfMethods() / nmr.getTotalNumberOfTypes());
		
		JsonObject totalCyclo = new JsonObject();
		totalCyclo.addProperty("description", "total_cyclo");
		totalCyclo.addProperty("value", mmr.getTotalCyclo());
		totalCyclo.addProperty("percent", mmr.getTotalCyclo() / nmr.getTotalNumberOfTypes());
		
		summaryList.add(totalTypes);
		summaryList.add(totalSloc);
		summaryList.add(totalMethods);
		summaryList.add(totalCyclo);
		return gson.toJson(summaryList);
	}

	@Override
	public void showDependencies() {
		System.out.println(generateDependencies());
	}

	public String generateDependencies() {
		JsonArray typeList = new JsonArray();
		
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			JsonArray dependenciesList = new JsonArray();
			Set<String> dependencies = type.getImports();
			for (String dependency : dependencies) {
				dependenciesList.add(dependency);
			}
			typeList.add(
					new JSONBuilder()
					.add("type", type.getFullName())
					.add("size", type.getSloc())
					.add("dependencies", dependenciesList).create()
					);
		}
		return gson.toJson(typeList);
	}

	@Override
	public void showCyclicDependencies() {
		String lines = generateCyclicDependencies();
		if (lines.equals("[]")) {
			InfoConsole.printHeader("This project hasn't types with cyclic dependencies...");
			return;
		}
		System.out.println(lines);
	}

	@Override
	public String generateCyclicDependencies() {
		JsonArray typeList = new JsonArray();
		Set<String> names = tmr.getCyclicDependencies();
		if (names.isEmpty()) return "[]";
		
		for (String name : names) {
			String[] types = name.split(" - ");
			typeList.add(
					new JSONBuilder()
					.add("from", types[0])
					.add("to", types[1])
					.add("number", 1).create()
					);
		}
		return gson.toJson(typeList);
	}

	@Override
	public void showInternalDependencies() {
		System.out.println(generateInternalDependencies());
	}

	public String generateInternalDependencies() {
		JsonArray typeList = new JsonArray();

		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			JsonArray dependenciesList = new JsonArray();
			Set<String> dependencies = type.getInternalImports();
			for (String dependency : dependencies) {
				dependenciesList.add(dependency);
			}
			typeList.add(
					new JSONBuilder()
					.add("type", type.getFullName())
					.add("size", type.getSloc())
					.add("dependencies", dependenciesList).create()
					);
		}
		return gson.toJson(typeList);
	}

	@Override
	public void showNamespaceCoupling() {
		System.out.println(generateNamespaceCoupling());
	}

	@Override
	public String generateNamespaceCoupling() {
		JsonArray namespaceList = new JsonArray();

		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			int ca = tmr.getAfferentCoupling(namespace.getName());
			int ce = tmr.getEfferentCoupling(namespace.getName());
			double abstractness = nmr.getAbstractness(tmr.getTotalOfAbstractTypesIn(namespace.getName()), namespace.getNumOfTypes());
			double instability = nmr.getInstability(ca, ce);
			double distance = nmr.getDistance(abstractness, instability);

			namespaceList.add(
					new JSONBuilder()
					.add("namespace", namespace.getName())
					.add("ca", ca)
					.add("ce", ce)
					.add("instability", instability)
					.add("abstractness", abstractness)
					.add("distance", distance).create()
					);
		}
		return gson.toJson(namespaceList);
	}

	@Override
	public void showAllCoupling() {
		InfoConsole.printHeader("This option is only available in console format...");
	}

	@Override
	public void showThresholds() {
		System.out.println(generateThresholds());
	}

	@Override
	public String generateThresholds() {
		JsonArray thresholdsList = new JsonArray();

		MetricThreshold mt = new MetricThreshold();
		for (MetricDefinition metric : mt.getThresholds()) {
			thresholdsList.add(
					new JSONBuilder()
					.add("acronym", metric.getAcronym())
					.add("name", metric.getName())
					.add("description", metric.getDescription())
					.add("min", metric.getMin())
					.add("max", metric.getMax()).create()
					);
		}
		
		return gson.toJson(thresholdsList);
	}

	@Override
	public TypeMetricResult getTypeMetricResult() {
		return tmr;
	}

	@Override
	public void showTypeCoupling() {
		System.out.println(generateTypeCoupling());
	}

	@Override
	public String generateTypeCoupling() {
		JsonArray typeList = new JsonArray();
		for (String name : tmr.getNamesResult()) {
			TypeMetric type = tmr.getType(name);
			typeList.add(
					new JSONBuilder()
					.add("type", type.getFullName())
					.add("dep", type.getNumberOfDependencies())
					.add("i-dep", type.getNumberOfInternalDependencies())
					.add("fan-in", tmr.getFanInOf(name))
					.add("fan-out", type.getFanOut()).create());
		}
		
		return gson.toJson(typeList);
	}
	
	public String generateTypesResonance() {
		JsonObject rootList = new JsonObject();
		rootList.addProperty("name", "variants");
		JsonArray namespaceList = new JsonArray();
		
		addTypesToNamespace(namespaceList);
		rootList.add("children", namespaceList);
		
		return gson.toJson(rootList);
	}

	private void addTypesToNamespace(JsonArray namespaceList) {
		Map<String, LinkedHashSet<TypeMetric>> typesResonance = tmr.getTypesResonance();
		for (String name : typesResonance.keySet()) {
			HashSet<TypeMetric> types = typesResonance.get(name);

			JsonArray typeList = addTypesResonance(types);
			namespaceList.add(
					new JSONBuilder()
					.add("name", name)
					.add("children", typeList.getAsJsonArray()).create());

		}
	}

	private JsonArray addTypesResonance(HashSet<TypeMetric> types) {
		JsonArray typeList = new JsonArray();
		for (TypeMetric typeMetric : types) {
			typeList.add(
			new JSONBuilder()
			.add("name", typeMetric.getName())
			.add("sloc", typeMetric.getSloc())
			.add("wmc", tmr.getTotalCycloBy(typeMetric.getFullName())).create());
		}
		return typeList;
	}
}
