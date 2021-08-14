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
import utils.calc.StatisticalAnalysis;
import utils.files.JSONBuilder;
import utils.files.StringFormat;

public class MetricResultJSON implements MetricOutput, MetricFile {
	private NamespaceMetricResult nmr;
	private TypeMetricResult tmr;
	private MethodMetricResult mmr;
	private StatisticalAnalysis sa;
	private Gson gson;

	@Override
	public void setResults(NamespaceMetricResult nmr, TypeMetricResult tmr, MethodMetricResult mmr) {
		this.nmr = nmr;
		this.tmr = tmr;
		this.mmr = mmr;
		this.sa  = new StatisticalAnalysis();
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
			namespaceList
					.add(new JSONBuilder().add("namespace", namespace.getName()).add("noc", namespace.getNumOfTypes())
							.add("nac", tmr.getTotalOfAbstractTypesIn(namespace.getName())).create());
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
			typeList.add(new JSONBuilder().add("type", type.getFullName()).add("sloc", type.getSloc())
					.add("nom", type.getNumOfMethods()).add("npm", type.getNumOfPublicMethods())
					.add("wmc", tmr.getTotalCycloBy(type.getFullName())).add("dep", type.getNumberOfDependencies())
					.add("i-dep", type.getNumberOfInternalDependencies()).add("fan-in", tmr.getFanInOf(name))
					.add("fan-out", type.getFanOut()).add("noa", type.getNumOfVariables())
					.add("lcom3", tmr.getLackCohesionMethods(name)).create());
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
			if (name == null)
				continue;
			methodList.add(new JSONBuilder().add("method", StringFormat.convertQuotation(method.getName()))
					.add("loc", method.getLoc()).add("cyclo", method.getCyclo()).add("calls", method.getCalls())
					.add("nbd", method.getNestedBlockDepth()).add("parameters", method.getNumOfParameters()).create());
		}
		return gson.toJson(methodList);
	}

	@Override
	public void show() {
	}

	@Override
	public void showSummary() {
		System.out.println(generateSummary());
	}

	@Override
	public String generateSummary() {
		JsonArray summaryList = new JsonArray();
		loadCollectionsToStatisticalComputation();

		JsonObject totalNamespaces = new JsonObject();
		totalNamespaces.addProperty("description", "total_namespaces");
		totalNamespaces.addProperty("value", nmr.getTotalNumberOfNamespaces());
		totalNamespaces.addProperty("percent", 100);
		totalNamespaces.addProperty("median", 0.0);
		totalNamespaces.addProperty("std_dev", 0.0);

		JsonObject totalTypes = new JsonObject();
		sa.setElements(nmr.getTypesPerNamespace());
		totalTypes.addProperty("description", "total_types");
		totalTypes.addProperty("value", tmr.getTotalNumberOfTypes());
		totalTypes.addProperty("percent", tmr.getTotalNumberOfTypes() / nmr.getTotalNumberOfNamespaces());
		totalTypes.addProperty("median", sa.getMedian());
		totalTypes.addProperty("std_dev", sa.getStandardDeviation());

		JsonObject totalSloc = new JsonObject();
		sa.setElements(tmr.getSLOCPerType());
		totalSloc.addProperty("description", "total_sloc");
		totalSloc.addProperty("value", tmr.getTotalSLOC());
		totalSloc.addProperty("percent", tmr.getTotalSLOC() / tmr.getTotalNumberOfTypes());
		totalSloc.addProperty("median", sa.getMedian());
		totalSloc.addProperty("std_dev", sa.getStandardDeviation());

		JsonObject totalMethods = new JsonObject();
		sa.setElements(mmr.getMethodsPerType());
		totalMethods.addProperty("description", "total_methods");
		totalMethods.addProperty("value", mmr.getTotalNumberOfMethods());
		totalMethods.addProperty("percent", mmr.getTotalNumberOfMethods() / tmr.getTotalNumberOfTypes());
		totalMethods.addProperty("median", sa.getMedian());
		totalMethods.addProperty("std_dev", sa.getStandardDeviation());

		JsonObject totalCyclo = new JsonObject();
		totalCyclo.addProperty("description", "total_cyclo");
		totalCyclo.addProperty("value", mmr.getTotalCyclo());
		totalCyclo.addProperty("percent", mmr.getTotalCyclo() / tmr.getTotalNumberOfTypes());
		totalCyclo.addProperty("median", 0.0);
		totalCyclo.addProperty("std_dev", 0.0);

		summaryList.add(totalNamespaces);
		summaryList.add(totalTypes);
		summaryList.add(totalSloc);
		summaryList.add(totalMethods);
		summaryList.add(totalCyclo);
		return gson.toJson(summaryList);
	}

	@Override
	public void loadCollectionsToStatisticalComputation() {
		nmr.defineNumberOfTypesPerNamespace();
		tmr.defineNumberOfSLOCPerTypes();
		mmr.defineNumberOfMethodsPerType();
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
			typeList.add(new JSONBuilder().add("type", type.getFullName()).add("size", type.getSloc())
					.add("dependencies", dependenciesList).create());
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
		if (names.isEmpty())
			return "[]";

		for (String name : names) {
			String[] types = name.split(" - ");
			typeList.add(new JSONBuilder().add("from", types[0]).add("to", types[1]).add("number", 1).create());
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
			typeList.add(new JSONBuilder().add("type", type.getFullName()).add("size", type.getSloc())
					.add("dependencies", dependenciesList).create());
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
			double abstractness = nmr.getAbstractness(tmr.getTotalOfAbstractTypesIn(namespace.getName()),
					namespace.getNumOfTypes());
			double instability = nmr.getInstability(ca, ce);
			double distance = nmr.getDistance(abstractness, instability);

			namespaceList.add(new JSONBuilder().add("namespace", namespace.getName()).add("ca", ca).add("ce", ce)
					.add("instability", instability).add("abstractness", abstractness).add("distance", distance)
					.create());
		}
		return gson.toJson(namespaceList);
	}

	@Override
	public void showAllCoupling() {
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
			thresholdsList.add(new JSONBuilder().add("acronym", metric.getAcronym()).add("name", metric.getName())
					.add("description", metric.getDescription()).add("min", metric.getMin()).add("max", metric.getMax())
					.create());
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
			typeList.add(new JSONBuilder().add("type", type.getFullName()).add("dep", type.getNumberOfDependencies())
					.add("i-dep", type.getNumberOfInternalDependencies()).add("fan-in", tmr.getFanInOf(name))
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
			namespaceList.add(new JSONBuilder().add("name", name).add("children", typeList.getAsJsonArray()).create());

		}
	}

	private JsonArray addTypesResonance(HashSet<TypeMetric> types) {
		JsonArray typeList = new JsonArray();
		for (TypeMetric typeMetric : types) {
			typeList.add(new JSONBuilder().add("name", typeMetric.getName()).add("sloc", typeMetric.getSloc())
					.add("wmc", tmr.getTotalCycloBy(typeMetric.getFullName())).create());
		}
		return typeList;
	}

	public String generateNamespacesDependencies() {
		JsonObject namespaceList = new JsonObject();

		namespaceList.add("nodes", generateNamespacesFromProject());
		namespaceList.add("links", generateLinksBetweenNamespaces());

		return gson.toJson(namespaceList);
	}

	private JsonArray generateNamespacesFromProject() {
		JsonArray nodesList = new JsonArray();

		for (String name : nmr.getNamesResult()) {
			NamespaceMetric namespace = nmr.getNamespace(name);
			nodesList.add(new JSONBuilder().add("name", name).add("label", name).add("id", name)
					.add("size", namespace.getNumOfTypes()).create());
		}

		return nodesList;
	}

	private JsonArray generateLinksBetweenNamespaces() {
		JsonArray linksList = new JsonArray();

		for (String name : nmr.getNamesResult()) {
			if (name == null)
				continue;
			Set<String> links = tmr.getInternalImportsBy(name);
			for (String link : links)
				linksList.add(new JSONBuilder().add("source", name).add("target", link).create());
		}

		return linksList;
	}
}
