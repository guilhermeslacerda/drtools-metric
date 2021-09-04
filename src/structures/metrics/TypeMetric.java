package structures.metrics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.builder.CompareToBuilder;

import structures.results.MethodMetricResult;
import utils.files.SourceCodeLineCounter;

public class TypeMetric implements Comparable<TypeMetric> {
	private static final String ELEMENT_SEPARATOR = ".";
	private String namespace;
	private String name;
	private int sloc;
	private int numOfMethods;
	private int numOfPublicMethods;
	private int numOfVariables;
	private boolean isAbstract;
	private boolean isInterface;
	private Set<String> imports;
	private Set<String> internalImports;
	private Set<String> internalTypes;
	private Set<String> variables;
	private Set<String> variablesUsedInMethods;
	private int fanOut;
	
	public TypeMetric() {
		setImports(new TreeSet<>());
		setInternalImports(new TreeSet<>());
		setInternalTypes(new TreeSet<>());
	}
	
	public int getNumOfMethods() {
		return numOfMethods;
	}

	public void setNumOfMethods(int numOfMethods) {
		this.numOfMethods = numOfMethods;
	}

	public int getNumOfPublicMethods() {
		return numOfPublicMethods;
	}

	public void setNumOfPublicMethods(int numOfPublicMethods) {
		this.numOfPublicMethods = numOfPublicMethods;
	}

	public int getNumOfVariables() {
		return numOfVariables;
	}

	public void setNumOfVariables(int numOfVariables) {
		this.numOfVariables = numOfVariables;
	}

	public int getSloc() {
		return sloc;
	}

	public void setSloc(String sourceCode) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceCode)));
			this.sloc = new SourceCodeLineCounter().getNumberOfLines(reader);
		} catch (IOException e) {
			this.sloc = 0;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getFullName() {
		return (getNamespace() != null ? getNamespace() + ELEMENT_SEPARATOR : "") + getName();
	}
	
	@Override
	public int compareTo(TypeMetric other) {
		MethodMetricResult mmr = new MethodMetricResult();
		int aCyclo = mmr.getTotalCycloBy(this.getFullName());
		int otherCyclo = mmr.getTotalCycloBy(other.getFullName());
		CompareToBuilder buider = new CompareToBuilder();
	 
	    return buider
	    		.append(this.getSloc(), other.getSloc())
	    		.append(aCyclo, otherCyclo)
                .append(this.getNumOfMethods(), other.getNumOfMethods())
                .toComparison();
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isInterface() {
		return isInterface;
	}

	public void setInterface(boolean isInterface) {
		this.isInterface = isInterface;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}
	
	public void addImport(String dependency) {
		imports.add(dependency);
	}
	
	public int getNumberOfDependencies() {
		return imports.size();
	}

	public void setSloc(int number) {
		sloc = number;
	}

	public Set<String> getInternalImports() {
		return internalImports;
	}

	public void setInternalImports(Set<String> internalImports) {
		this.internalImports = internalImports;
	}

	public int getNumberOfInternalDependencies() {
		return internalImports.size();
	}

	public Set<String> getInternalTypes() {
		return internalTypes;
	}

	public void setInternalTypes(Set<String> internalTypes) {
		this.internalTypes = internalTypes;
	}

	public int getNumberOfInternalTypes() {
		return internalTypes.size();
	}

	public void setFanOut(int fanOut) {
		this.fanOut = fanOut;
	}
	
	public int getFanOut() {
		return fanOut - (getNumberOfDependencies() - getNumberOfInternalDependencies());
	}
	
	public Set<String> getVariables() {
		return variables;
	}

	public void setVariables(Set<String> variables) {
		this.variables = variables;
	}

	public void setVariablesUsedInMethods(Set<String> variablesUsedInMethods) {
		this.variablesUsedInMethods = variablesUsedInMethods;
	}
	
	public Set<String> getVariablesUsedInMethods() {
		return variablesUsedInMethods;
	}
}
