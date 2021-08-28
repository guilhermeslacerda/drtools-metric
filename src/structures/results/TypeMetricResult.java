package structures.results;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import structures.MetricResultNotifier;
import structures.metrics.TypeMetric;
import utils.files.StringFormat;

public class TypeMetricResult implements MetricResultNotifier<TypeMetric> {
	private static final String VARIABLE_SEPARATOR = " ";
	private static final String NAMESPACE_SEPARATOR = ".";
	private Map<String, TypeMetric> typeMetrics;
	private Map<String, LinkedHashSet<TypeMetric>> typesResonance;
	private Map<String, Integer> typeFanIn;
	private double slocPerType[];
	private int number = 0;
	private MethodMetricResult mmr;

	public TypeMetricResult() {
		typeMetrics = new LinkedHashMap<>();
		typesResonance = new LinkedHashMap<>();
		typeFanIn = new LinkedHashMap<>();
	}

	public void setMethodMetricResult(MethodMetricResult mmr) {
		this.mmr = mmr;
	}

	@Override
	public void setTop(int number) {
		this.number = number;
	}

	@Override
	public void add(TypeMetric metric) {
		typeMetrics.put(metric.getFullName(), metric);
		addToResonance(metric);
	}

	private void addToResonance(TypeMetric metric) {
		LinkedHashSet<TypeMetric> typeMetricResonance = !typesResonance.containsKey(metric.getNamespace())
				? new LinkedHashSet<TypeMetric>()
				: typesResonance.get(metric.getNamespace());
		typeMetricResonance.add(metric);
		typesResonance.put(metric.getNamespace(), typeMetricResonance);
	}

	public Map<String, LinkedHashSet<TypeMetric>> getTypesResonance() {
		return typesResonance;
	}

	public Map<String, TypeMetric> getTypeMetrics() {
		return typeMetrics;
	}

	public LinkedHashMap<String, TypeMetric> getSortedTypeMetrics() {
		return typeMetrics.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public Set<String> getTypesName() {
		return typeMetrics.keySet();
	}

	public TypeMetric getType(String name) {
		return typeMetrics.get(name);
	}

	public int getTotalNumberOfMethods() {
		int totalNumberMethods = 0;
		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			totalNumberMethods += type.getNumOfMethods();
		}
		return totalNumberMethods;
	}

	public int getTotalNumberOfMethods(String typeName) {
		TypeMetric type = this.getType(typeName);
		return type.getNumOfMethods();
	}

	public int getTotalNumberOfPublicMethods(String typeName) {
		TypeMetric type = this.getType(typeName);
		return type.getNumOfPublicMethods();
	}

	public int getTotalSLOC() {
		int totalSLOC = 0;
		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			totalSLOC += type.getSloc();
		}
		return totalSLOC;
	}

	public int getTotalOfVariables() {
		int totalVariables = 0;
		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			totalVariables += type.getNumOfVariables();
		}
		return totalVariables;
	}

	public int getTotalCycloBy(String name) {
		int totalCyclo = 0;
		TypeMetric type = this.getType(name);
		totalCyclo = mmr.getTotalCycloBy(name);
		for (String internalTypeName : type.getInternalTypes()) {
			totalCyclo += mmr.getTotalCycloBy(internalTypeName);
		}
		return totalCyclo;
	}

	public int getTotalOfAbstractTypesIn(String namespace) {
		int totalAbstractTypes = 0;

		if (namespace == null)
			return totalAbstractTypes;

		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			if (isNull(namespace, type))
				continue;
			if (type.getNamespace().equals(namespace))
				if (isAbstractType(type))
					totalAbstractTypes++;
		}
		return totalAbstractTypes;
	}

	private boolean isAbstractType(TypeMetric type) {
		return type.isAbstract() || type.isInterface();
	}

	public int getTotalNumberOfTypes() {
		return this.getTypesName().size();
	}

	@Override
	public Set<String> getNamesResult() {
		Set<String> names = (number > 0) ? getSortedTypeMetricsWithLimit() : getSortedTypeMetrics().keySet();
		return names;
	}

	private Set<String> getSortedTypeMetricsWithLimit() {
		List<String> typeList = new ArrayList<>(getSortedTypeMetrics().keySet());
		return new LinkedHashSet<>(typeList.subList(0, number > typeList.size() ? typeList.size() : number));
	}

	public Set<String> getCyclicDependencies() {
		Set<String> typesWithCyclos = new TreeSet<>();
		Set<String> names = getNamesResult();
		TypeMetric type = new TypeMetric();
		TypeMetric otherType = new TypeMetric();
		boolean isCyclic = false;

		for (String name : names) {
			type = getType(name);
			Set<String> dependencies = type.getInternalImports();
			for (String dependency : dependencies) {
				otherType = getType(dependency);
				isCyclic = hasCyclicDependency(type, otherType, isCyclic);
				isCyclic = addTypeWithCyclicDependency(typesWithCyclos, type, otherType, isCyclic);
			}
		}

		return cleanListOf(typesWithCyclos);
	}

	private boolean hasCyclicDependency(TypeMetric type, TypeMetric otherType, boolean isCyclic) {
		if (otherType != null)
			if (otherType.getInternalImports().contains(type.getFullName()))
				isCyclic = true;
		return isCyclic;
	}

	private boolean addTypeWithCyclicDependency(Set<String> typesWithCyclos, TypeMetric type, TypeMetric otherType,
			boolean isCyclic) {
		if (isCyclic) {
			typesWithCyclos.add(type.getFullName() + " - " + otherType.getFullName());
			isCyclic = false;
		}
		return isCyclic;
	}

	private Set<String> cleanListOf(Set<String> typesWithCyclos) {
		if (isEmptyOrOneElement(typesWithCyclos))
			return typesWithCyclos;

		Set<String> namesCycloReturn = new TreeSet<>();
		for (String typeWithCyclos : typesWithCyclos) {
			String[] typeFromCyclo = typeWithCyclos.split(" - ");
			boolean isExist = false;
			for (String otherTypeCyclo : namesCycloReturn) {
				String[] otherTypeFromCyclo = otherTypeCyclo.split(" - ");
				if (isEquals(typeFromCyclo, otherTypeFromCyclo))
					isExist = true;
			}
			if (!isExist)
				namesCycloReturn.add(typeWithCyclos);
		}

		return namesCycloReturn;
	}

	private boolean isEmptyOrOneElement(Set<String> typesWithCyclos) {
		return typesWithCyclos.isEmpty() || typesWithCyclos.contains("[]");
	}

	private boolean isEquals(String[] typeFromCyclo, String[] otherTypeFromCyclo) {
		return otherTypeFromCyclo[0].equals(typeFromCyclo[1]) && otherTypeFromCyclo[1].equals(typeFromCyclo[0]);
	}

	public void defineInternalDependencies() {
		Set<String> names = getTypesName();
		TypeMetric type = new TypeMetric();
		TypeMetric otherType = new TypeMetric();

		for (String name : names) {
			type = getType(name);
			Set<String> dependencies = type.getImports();
			Set<String> otherDependencies = new TreeSet<>();
			getInternalDependenciesByType(dependencies, otherDependencies);
			otherType = type;
			otherType.setInternalImports(otherDependencies);
		}
	}

	private void getInternalDependenciesByType(Set<String> dependencies, Set<String> otherDependencies) {
		TypeMetric otherType;
		for (String dependency : dependencies) {
			otherType = getType(dependency);
			if (otherType != null)
				otherDependencies.add(otherType.getFullName());
		}
	}

	public Set<String> getInternalNamesResult() {
		Set<String> names = (number > 0) ? getTypeMetrics().keySet().stream().limit(number).collect(Collectors.toSet())
				: getTypeMetrics().keySet();
		return names;
	}

	public int getEfferentCoupling(String namespace) {
		HashSet<String> efferents = new HashSet<>();

		for (String name : getInternalNamesResult()) {
			TypeMetric type = getType(name);
			if (isNull(namespace, type))
				continue;
			if (!type.getNamespace().equals(namespace))
				continue;
			identifyEfferentCoupling(namespace, efferents, type);
		}

		return efferents.size();
	}

	private void identifyEfferentCoupling(String namespace, HashSet<String> efferents, TypeMetric type) {
		for (String dependency : type.getInternalImports()) {
			String otherNamespace = StringFormat.getNamespaceFrom(dependency, NAMESPACE_SEPARATOR);
			if (!namespace.equals(otherNamespace))
				efferents.add(otherNamespace);
		}
	}

	public int getAfferentCoupling(String namespace) {
		HashSet<String> afferents = new HashSet<>();

		for (String name : getInternalNamesResult()) {
			TypeMetric type = getType(name);
			if (isNull(namespace, type))
				continue;
			if (type.getNamespace().equals(namespace))
				continue;
			identifyAfferentCoupling(namespace, afferents, type);
		}

		return afferents.size();
	}

	private void identifyAfferentCoupling(String namespace, HashSet<String> afferents, TypeMetric type) {
		for (String dependency : type.getInternalImports()) {
			String otherNamespace = StringFormat.getNamespaceFrom(dependency, NAMESPACE_SEPARATOR);
			if (namespace.equals(otherNamespace))
				afferents.add(type.getFullName());
		}
	}

	private boolean isNull(String namespace, TypeMetric type) {
		return type == null || type.getNamespace() == null || namespace == null;
	}

	public void defineFanIn() {
		for (String name : getTypeMetrics().keySet()) {
			addOrUpdateFanInOf(name);
			for (String importName : getType(name).getInternalImports())
				addOrUpdateFanInOf(importName);
		}
	}

	public void addOrUpdateFanInOf(String name) {
		if (!typeFanIn.containsKey(name)) {
			typeFanIn.put(name, 0);
		} else {
			int numberFanIn = typeFanIn.get(name);
			typeFanIn.replace(name, ++numberFanIn);
		}
	}

	public int getFanInOf(String typeName) {
		if (typeName == null || !typeFanIn.containsKey(typeName))
			return 0;
		return typeFanIn.get(typeName);
	}

	public Set<String> getFanIn() {
		return typeFanIn.keySet();
	}

	public Set<String> getInternalImportsBy(String namespace) {
		Set<String> internalImportsOnlyNamespaces = new TreeSet<>();

		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			if (type == null || type.getNamespace() == null)
				continue;
			if (type.getNamespace().equals(namespace)) {
				Set<String> internalImports = type.getInternalImports();
				for (String fullName : internalImports)
					internalImportsOnlyNamespaces.add(StringFormat.getNamespaceFrom(fullName, NAMESPACE_SEPARATOR));
			}
		}

		return internalImportsOnlyNamespaces;
	}

	public void defineNumberOfSLOCPerTypes() {
		slocPerType = new double[getTotalNumberOfTypes()];
		int position = 0;
		for (String name : this.getTypesName()) {
			TypeMetric type = this.getType(name);
			slocPerType[position++] = type.getSloc();
		}
	}

	public double[] getSLOCPerType() {
		return slocPerType;
	}

	public double getLackCohesionMethods(String name) {
		TypeMetric type = getType(name);
		int variables = type.getNumOfVariables();
		int methods = type.getNumOfMethods();
		int variablesUsedInMethods = getTotalOfVariablesUsedInMethods(type);
		return computeLCOM3(variables, methods, variablesUsedInMethods);
	}

	private int getTotalOfVariablesUsedInMethods(TypeMetric type) {
		if (type.getVariables() == null) 	return 0;

		int totalVariables = 0;
		int totalVariablesUsed = 0;
		for (String variable : type.getVariables()) {
			String v[] = variable.split(VARIABLE_SEPARATOR);
			for (String argument : type.getVariablesUsedInMethods()) {
				if (v[v.length - 1].contains(argument)) {
					totalVariables++;
					break;
				}
			}
			totalVariablesUsed += totalVariables;
		}

		return totalVariablesUsed;
	}

	private double computeLCOM3(int variables, int methods, int variablesUsedInMethods) {
		if (methods < 2 || variables == 0)		return 0.0;
		double lcom = ((double) methods - ((double) variablesUsedInMethods / (double) variables))
				/ ((double) methods - 1.0);
		return (lcom < 0.0) ? 0.0 : lcom;
	}
}
