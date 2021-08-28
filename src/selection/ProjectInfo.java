package selection;

import java.io.File;
import java.util.List;

import output.MetricOutput;
import output.utils.Gauge;
import parser.TypeParser;
import parser.java.JavaParser;
import selection.options.Options;
import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;
import utils.files.SystemUtils;

public class ProjectInfo {
	private List<File> javaFiles;
	private String path;
	private NamespaceMetricResult namespaceMetricResult;
	private TypeMetricResult typeMetricResult;
	private MethodMetricResult methodMetricResult;
	private MetricOutput output;
	
	public ProjectInfo(String path, MetricOutput output) {
		this.path = path;
		this.namespaceMetricResult = new NamespaceMetricResult();
		this.typeMetricResult = new TypeMetricResult();
		this.methodMetricResult = new MethodMetricResult();
		this.output = output;
	}
	
	public void loadProject() {
		SystemUtils su = new SystemUtils();
		javaFiles = su.getJavaFiles(path);
	}

	public void analyze() {
		loadProject();
		parsingTypes();
	}

	private void parsingTypes() {
		Gauge.start(output);
		int number = 0;
		for (File file : javaFiles) { 
			parse(file);
			Gauge.progress(number++);
		}
		Gauge.end();
	}

	private void parse(File file) {
		TypeParser typeParse = new JavaParser(file, getNamespaceMetricResult(), getTypeMetricResult(), getMethodMetricResult());
		typeParse.parsing();
	}
	
	public void show(String argument) {
		output.setResults(getNamespaceMetricResult(), getTypeMetricResult(), getMethodMetricResult());
		new Options().selectBy(argument).execute(output);
	}

	public NamespaceMetricResult getNamespaceMetricResult() {
		return namespaceMetricResult;
	}

	public TypeMetricResult getTypeMetricResult() {
		return typeMetricResult;
	}

	public MethodMetricResult getMethodMetricResult() {
		return methodMetricResult;
	}

	public MetricOutput getOutput() {
		return output;
	}

	public void defineTop(int number) {
		namespaceMetricResult.setTop(number);
		typeMetricResult.setTop(number);
		methodMetricResult.setTop(number);
	}
}
