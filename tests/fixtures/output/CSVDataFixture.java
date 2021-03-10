package fixtures.output;

import fixtures.output.data.CyclicDependencyData;
import fixtures.output.data.MethodData;
import fixtures.output.data.MetricThresholdData;
import fixtures.output.data.NamespaceCouplingData;
import fixtures.output.data.NamespaceData;
import fixtures.output.data.SummaryData;
import fixtures.output.data.TypeData;

public class CSVDataFixture extends DataFixture {
	StringBuilder sb;
	
	public CSVDataFixture() {
		super();
	}
	
	@Override
	public String generateNamespaces() {
		sb = new StringBuilder();
		sb.append("\"namespace\",\"noc\",\"nac\"\n");
		for (NamespaceData namespace : getNamespaceData()) 
			sb.append(String.format("\"%s\",%d,%d\n", namespace.getName(), namespace.getNoc(), namespace.getNac()));
		return sb.toString();
	}

	@Override
	public String generateSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"description\",value,percent,median,std_dev\n");
		for (SummaryData summary : getSummaryData()) 
			sb.append(String.format("\"%s\",%d,%d,%s,%s\n", summary.getDescription(), summary.getValue(), 
					summary.getPercent(), String.valueOf(summary.getMedian()).replace(',', '.'), 
					String.valueOf(summary.getStdDev()).replace(',', '.')));
		return sb.toString();
	}

	@Override
	public String generateTypes() {
		sb = new StringBuilder();
		sb.append("\"type\",\"sloc\",\"nom\",\"npm\",\"wmc\",\"dep\",\"i-dep\",\"fan-in\",\"fan-out\",\"noa\",\"lcom3\"\n");
		for (TypeData type : getTypeData()) 
			sb.append(String.format("\"%s\",%d,%d,%d,%d,%d,%d,%d,%d,%d,%s\n", type.getType(), type.getSloc(), 
					type.getNom(), type.getNpm(), type.getWmc(), type.getDep(), type.getiDep(), 
					type.getFanIn(), type.getFanOut(), type.getNoa(),
					type.getLcom3()));
		return sb.toString();
	}

	@Override
	public String generateMethods() {
		sb = new StringBuilder();
		sb.append("\"method\",\"loc\",\"cyclo\",\"calls\",\"nbd\",\"param\"\n");
		for (MethodData method : getMethodData()) 
			sb.append(String.format("\"%s\",%d,%d,%d,%d,%d\n", method.getMethod(), method.getLoc(), method.getCyclo(),
					method.getCalls(), method.getNbd(), method.getParam()));
		return sb.toString();
	}

	@Override
	public String generateNamespaceCoupling() {
		sb = new StringBuilder();
		sb.append("\"namespace\",\"ca\",\"ce\",\"instability\",\"abstractness\",\"distance\"\n");
		for (NamespaceCouplingData namespace : getNamespaceCouplingData()) 
			sb.append(String.format("\"%s\",%d,%d,%s,%s,%s\n", namespace.getNamespace(), 
					namespace.getCa(), namespace.getCe(), namespace.getInstability(), namespace.getAbstractness(),
					namespace.getDistance()));
		return sb.toString();
	}

	@Override
	public String generateThresholds() {
		sb = new StringBuilder();
		
		sb.append("\"acronym\",\"name\",\"description\",\"min\",\"max\"\n");
		for (MetricThresholdData description : getThresholdData()) 
			sb.append(String.format("\"%s\",\"%s\",\"%s\",%s,%s\n", 
					description.getAcronym(), description.getName(), description.getDescription(), 
					String.valueOf(description.getMin()).replace(',', '.'), 
					String.valueOf(description.getMax()).replace(',', '.')));
		return sb.toString();
	}

	@Override
	public String getNamespacesWithLimit() {
		sb = new StringBuilder();
		sb.append("\"namespace\",\"noc\",\"nac\"\n");
		sb.append("\"javaProject.com.controller\",5,0\n");
		return sb.toString();
	}
	
	@Override
	public String getTypesWithLimit() {
		sb = new StringBuilder();
		sb.append("\"type\",\"sloc\",\"nom\",\"npm\",\"wmc\",\"dep\",\"i-dep\",\"fan-in\",\"fan-out\",\"noa\",\"lcom3\"\n");
		sb.append("\"javaProject.com.controller.Type\",245,35,25,58,7,2,0,9,13,0.8484162895927602\n");
		return sb.toString();
	}

	@Override
	public String getMethodsWithLimit() {
		sb = new StringBuilder();
		sb.append("\"method\",\"loc\",\"cyclo\",\"calls\",\"nbd\",\"param\"\n");
		sb.append("\"javaProject.com.controller.Type.isSubtype(String dottedSubtype, String collectionType)\",45,7,3,2,2\n");
		return sb.toString();
	}

	@Override
	public String generateTypeCoupling() {
		sb = new StringBuilder();
		sb.append("\"type\",\"dep\",\"i-dep\",\"fan-in\",\"fan-out\"\n");
		for (TypeData type : getTypeData()) 
			sb.append(String.format("\"%s\",%d,%d,%d,%d\n", type.getType(), type.getDep(), type.getiDep(), 
					type.getFanIn(), type.getFanOut()));
		return sb.toString();
	}

	@Override
	public String generateCyclicDependencies() {
		sb = new StringBuilder();
		sb.append("\"from\",\"to\",\"number\"\n");
		for (CyclicDependencyData dependency : getCyclicDependencyData()) 
			sb.append(String.format("\"%s\",\"%s\",%d\n", dependency.getFrom(), dependency.getTo(), dependency.getNumber()));
		return sb.toString();
	}
}
