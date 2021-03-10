package fixtures.output;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fixtures.output.data.CyclicDependencyData;
import fixtures.output.data.MethodData;
import fixtures.output.data.MetricThresholdData;
import fixtures.output.data.NamespaceCouplingData;
import fixtures.output.data.NamespaceData;
import fixtures.output.data.NamespaceDependencyData;
import fixtures.output.data.SummaryData;
import fixtures.output.data.TypeData;
import fixtures.output.data.TypeResonanceData;
import utils.JSONBuilder;

public class JSONDataFixture extends DataFixture {
	private StringBuilder sb;
	private Gson gson;
	private JsonObject rootList;
	
	public JSONDataFixture() {
		super();
		this.gson = new GsonBuilder().disableHtmlEscaping().create();
	}

	@Override
	public String generateNamespaces() {
		JsonArray namespaceList = new JsonArray();

		for (NamespaceData namespace : getNamespaceData()) {
			namespaceList.add(
					new JSONBuilder()
					.add("namespace", namespace.getName())
					.add("noc", namespace.getNoc())
					.add("nac", namespace.getNac()).create()
					);
		}
		return gson.toJson(namespaceList);
	}

	@Override
	public String generateTypes() {
		JsonArray typeList = new JsonArray();

		for (TypeData type : getTypeData()) {
			typeList.add(
					new JSONBuilder()
					.add("type", type.getType())
					.add("sloc", type.getSloc())
					.add("nom", type.getNom())
					.add("npm", type.getNpm())
					.add("wmc", type.getWmc())
					.add("dep", type.getDep())
					.add("i-dep", type.getiDep())
					.add("fan-in", type.getFanIn())
					.add("fan-out", type.getFanOut())
					.add("noa", type.getNoa())
					.add("lcom3", Double.parseDouble(type.getLcom3())).create());
		}
		return gson.toJson(typeList);
	}

	@Override
	public String generateMethods() {
		JsonArray methodList = new JsonArray();

		for (MethodData method : getMethodData()) {
			methodList.add(
					new JSONBuilder()
					.add("method", method.getMethod())
					.add("loc", method.getLoc())
					.add("cyclo", method.getCyclo())
					.add("calls", method.getCalls())
					.add("nbd", method.getNbd())
					.add("parameters", method.getParam()).create()
					);
		}
		return gson.toJson(methodList);
	}

	@Override
	public String generateSummary() {
		JsonArray summaryList = new JsonArray();

		for (SummaryData summary : getSummaryData()) {
			summaryList.add(
					new JSONBuilder()
					.add("description", summary.getDescription())
					.add("value", summary.getValue())
					.add("percent", summary.getPercent())
					.add("median", summary.getMedian())
					.add("std_dev", summary.getStdDev()).create()
					);
		}
		return gson.toJson(summaryList);
	}

	public String getDependencies() {
		sb = new StringBuilder();
		sb.append("[{\"type\":\"javaProject.com.controller.Type\",\"size\":245,\"dependencies\":[\"java.util.HashMap\",\"java.util.HashSet\",\"java.util.LinkedList\",\"java.util.Set\",\"javaProject.others.ClassDescriptor\",\"javaProject.others.ClassVertex\",\"javax.lang.model.type.ReferenceType\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Man\",\"size\":29,\"dependencies\":[\"java.util.ArrayList\"]},");
		sb.append("{\"type\":\"javaProject.com.controller.Dispatcher\",\"size\":27,\"dependencies\":[\"javaProject.com.model.Child\",\"javaProject.com.model.Man\",\"javaProject.com.model.Person\",\"javaProject.com.model.Woman\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Woman\",\"size\":16,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.model.Human\",\"size\":10,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.view.QueueViewer\",\"size\":10,\"dependencies\":[\"javaProject.com.controller.Dispatcher\"]},");
		sb.append("{\"type\":\"javaProject.com.controller.ClassWithComments\",\"size\":8,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.model.Child\",\"size\":7,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.controller.XClass\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.AnalysisContext\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ClassVertex\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.one.A\",\"size\":5,\"dependencies\":[\"javaProject.two.B\"]},");
		sb.append("{\"type\":\"javaProject.two.B\",\"size\":5,\"dependencies\":[\"javaProject.one.A\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Person\",\"size\":4,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.controller.XMethod\",\"size\":3,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ClassDescriptor\",\"size\":3,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ObjectType\",\"size\":3,\"dependencies\":[]}]");
		return sb.toString();
	}
	
	public String getInternalDependencies() {
		sb = new StringBuilder();
		sb.append("[{\"type\":\"javaProject.com.controller.Type\",\"size\":245,\"dependencies\":[\"javaProject.others.ClassDescriptor\",\"javaProject.others.ClassVertex\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Man\",\"size\":29,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.controller.Dispatcher\",\"size\":27,\"dependencies\":[\"javaProject.com.model.Child\",\"javaProject.com.model.Man\",\"javaProject.com.model.Person\",\"javaProject.com.model.Woman\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Woman\",\"size\":16,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.model.Human\",\"size\":10,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.view.QueueViewer\",\"size\":10,\"dependencies\":[\"javaProject.com.controller.Dispatcher\"]},");
		sb.append("{\"type\":\"javaProject.com.controller.ClassWithComments\",\"size\":8,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.model.Child\",\"size\":7,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.controller.XClass\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.AnalysisContext\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ClassVertex\",\"size\":6,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.one.A\",\"size\":5,\"dependencies\":[\"javaProject.two.B\"]},");
		sb.append("{\"type\":\"javaProject.two.B\",\"size\":5,\"dependencies\":[\"javaProject.one.A\"]},");
		sb.append("{\"type\":\"javaProject.com.model.Person\",\"size\":4,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.com.controller.XMethod\",\"size\":3,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ClassDescriptor\",\"size\":3,\"dependencies\":[]},");
		sb.append("{\"type\":\"javaProject.others.ObjectType\",\"size\":3,\"dependencies\":[]}]");		
		return sb.toString();
	}
	
	@Override
	public String generateNamespaceCoupling() {
		JsonArray namespaceList = new JsonArray();

		for (NamespaceCouplingData namespace : getNamespaceCouplingData()) {
			namespaceList.add(
					new JSONBuilder()
					.add("namespace", namespace.getNamespace())
					.add("ca", namespace.getCa())
					.add("ce", namespace.getCe())
					.add("instability", namespace.getInstability())
					.add("abstractness", namespace.getAbstractness())
					.add("distance", namespace.getDistance()).create()
					);
		}
		return gson.toJson(namespaceList);
	}

	@Override
	public String generateThresholds() {
		JsonArray thresholdsList = new JsonArray();

		for (MetricThresholdData description : getThresholdData()) {
			thresholdsList.add(
					new JSONBuilder()
					.add("acronym", description.getAcronym())
					.add("name", description.getName())
					.add("description", description.getDescription())
					.add("min", description.getMin())
					.add("max", description.getMax()).create()
					);
		}
		
		return gson.toJson(thresholdsList);
	}

	@Override
	public String getNamespacesWithLimit() {
		sb = new StringBuilder();
		sb.append("[");
		sb.append("{\"namespace\":\"javaProject.com.controller\",\"noc\":5,\"nac\":0}");
		sb.append("]");
		return sb.toString();
	}

	@Override
	public String getTypesWithLimit() {
		sb = new StringBuilder();
		sb.append("[{\"type\":\"javaProject.com.controller.Type\",\"sloc\":245,\"nom\":35,\"npm\":25,\"wmc\":58,\"dep\":7,\"i-dep\":2,\"fan-in\":0,\"fan-out\":9,\"noa\":13,\"lcom3\":0.8484162895927602}]");
		return sb.toString();
	}

	@Override
	public String getMethodsWithLimit() {
		sb = new StringBuilder();
		sb.append("[{\"method\":\"javaProject.com.controller.Type.isSubtype(String dottedSubtype, String collectionType)\",\"loc\":45,\"cyclo\":7,\"calls\":3,\"nbd\":2,\"parameters\":2}]");
		return sb.toString();
	}

	@Override
	public String generateTypeCoupling() {
		JsonArray typeList = new JsonArray();

		for (TypeData type : getTypeData()) {
			typeList.add(
					new JSONBuilder()
					.add("type", type.getType())
					.add("dep", type.getDep())
					.add("i-dep", type.getiDep())
					.add("fan-in", type.getFanIn())
					.add("fan-out", type.getFanOut()).create());
		}
		return gson.toJson(typeList);
	}

	@Override
	public String generateCyclicDependencies() {
		JsonArray dependenciesList = new JsonArray();

		for (CyclicDependencyData dependency : getCyclicDependencyData()) {
			dependenciesList.add(
					new JSONBuilder()
					.add("from", dependency.getFrom())
					.add("to", dependency.getTo())
					.add("number", dependency.getNumber()).create());
		}
		return gson.toJson(dependenciesList);
	}
	
	public String generateTypesResonance() {
		rootList = new JsonObject();
		rootList.addProperty("name", "variants");
		Map<String, LinkedHashSet<TypeResonanceData>> typesResonance = getTypesResonance();
		JsonArray namespaceList = addTypesToNamespace(typesResonance);
		rootList.add("children", namespaceList);
		return gson.toJson(rootList);
	}

	private JsonArray addTypesToNamespace(Map<String, LinkedHashSet<TypeResonanceData>> typesResonance) {
		JsonArray namespaceList = new JsonArray();
		for (String name : typesResonance.keySet()) {
			HashSet<TypeResonanceData> types = typesResonance.get(name);
			JsonArray typeList = addTypes(types);
			namespaceList.add(
				new JSONBuilder()
					.add("name", name)
					.add("children", typeList.getAsJsonArray()).create());
		}
		return namespaceList;
	}

	private JsonArray addTypes(HashSet<TypeResonanceData> types) {
		JsonArray typeList = new JsonArray();
		for (TypeResonanceData typeData : types) {
			typeList.add(
			new JSONBuilder()
				.add("name", typeData.getType())
				.add("sloc", typeData.getSloc())
				.add("wmc", typeData.getWmc()).create());
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

		for (NamespaceData namespace : getNamespaceData()) {
			nodesList.add(
					new JSONBuilder()
					.add("name", namespace.getName())
					.add("label", namespace.getName())
					.add("id", namespace.getName())
					.add("size", namespace.getNoc()).create()
					);
		}

		return nodesList;
	}

	private JsonArray generateLinksBetweenNamespaces() {
		JsonArray linksList = new JsonArray();
		
		for (NamespaceDependencyData namespace : getNamespaceDependencyData()) {
				linksList.add(new JSONBuilder()
				.add("source", namespace.getSource())
				.add("target", namespace.getTarget()).create()
				);
		}
		
		return linksList;
	}
}
