package fixtures.output;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fixtures.output.data.CyclicDependencyData;
import fixtures.output.data.MethodData;
import fixtures.output.data.MetricThresholdData;
import fixtures.output.data.NamespaceCouplingData;
import fixtures.output.data.NamespaceData;
import fixtures.output.data.NamespaceDependencyData;
import fixtures.output.data.SummaryData;
import fixtures.output.data.TypeData;
import fixtures.output.data.TypeResonanceData;
import output.MetricFile;
import structures.metrics.MetricDefinition;

public abstract class DataFixture implements MetricFile {
	private Map<String, LinkedHashSet<TypeResonanceData>> typesResonanceData;
	private List<SummaryData> summary;
	private List<NamespaceData> namespaces;
	private List<TypeData> types;
	private List<MethodData> methods;
	private List<NamespaceCouplingData> namespaceCoupling;
	private List<MetricThresholdData> thresholds;
	private List<CyclicDependencyData> dependencies;
	private Set<NamespaceDependencyData> namespacesDependencies;
	
	public DataFixture() {
		typesResonanceData = new LinkedHashMap<>(); 
		summary = new ArrayList<>();
		namespaces = new ArrayList<>();
		types = new ArrayList<>();
		methods = new ArrayList<>();
		namespaceCoupling = new ArrayList<>();
		thresholds = new ArrayList<>();
		dependencies = new ArrayList<>();
		namespacesDependencies = new LinkedHashSet<>(); 
	}

	public abstract String getNamespacesWithLimit();
	public abstract String getTypesWithLimit();
	public abstract String getMethodsWithLimit();
	
	public List<NamespaceData> getNamespaceData() {
		namespaces.add(new NamespaceData("javaProject.com.controller", 5, 0));
		namespaces.add(new NamespaceData("javaProject.com.model", 5, 2));
		namespaces.add(new NamespaceData("javaProject.others", 4, 0));
		namespaces.add(new NamespaceData("javaProject.com.view", 1, 0));
		namespaces.add(new NamespaceData("javaProject.one",1,0));
		namespaces.add(new NamespaceData("javaProject.two",1,0));

		return namespaces;
	}

	public List<SummaryData> getSummaryData() {
		summary.add(new SummaryData("total_namespaces", 6, 100, 0.0, 0.0));
		summary.add(new SummaryData("total_types", 17, 2, 2.0, 2.041241452319315));
		summary.add(new SummaryData("total_sloc", 393, 23, 6.0, 62.42395373572552));
		summary.add(new SummaryData("total_methods", 56, 3, 3.0, 10.428108867165443));
		summary.add(new SummaryData("total_cyclo", 86, 5, 0.0, 0.0));

		return summary;
	}

	public List<TypeData> getTypeData() {
		types.add(new TypeData("javaProject.com.controller.Type",245,35,25,58,7,2,0,9,13,0.8484162895927602));
		types.add(new TypeData("javaProject.com.model.Man",29,5,5,9,1,0,1,2,0,0.0));
		types.add(new TypeData("javaProject.com.controller.Dispatcher",27,4,4,4,4,4,1,4,3,0.6666666666666666));
		types.add(new TypeData("javaProject.com.model.Woman",16,3,3,5,0,0,1,1,0,0.0));
		types.add(new TypeData("javaProject.com.model.Human",10,2,2,2,0,0,2,1,1,1.0));
		types.add(new TypeData("javaProject.com.view.QueueViewer",10,0,1,2,1,1,0,2,0,0.0));
		types.add(new TypeData("javaProject.com.controller.ClassWithComments",8,1,1,1,0,0,0,0,0,0.0));
		types.add(new TypeData("javaProject.com.model.Child",7,1,1,1,0,0,1,1,1,0.0));
		types.add(new TypeData("javaProject.com.controller.XClass",6,1,1,1,0,0,0,0,0,0.0));
		types.add(new TypeData("javaProject.others.AnalysisContext",6,1,1,1,0,0,0,0,0,0.0));
		types.add(new TypeData("javaProject.others.ClassVertex",6,1,1,1,0,0,1,0,0,0.0));
		types.add(new TypeData("javaProject.one.A",5,0,0,1,1,1,1,1,1,0.0));
		types.add(new TypeData("javaProject.two.B",5,0,0,1,1,1,1,1,1,0.0));
		types.add(new TypeData("javaProject.com.model.Person",4,1,1,1,0,0,3,0,0,0.0));
		types.add(new TypeData("javaProject.com.controller.XMethod",3,0,0,1,0,0,0,0,0,0.0));
		types.add(new TypeData("javaProject.others.ClassDescriptor",3,0,0,1,0,0,1,0,0,0.0));
		types.add(new TypeData("javaProject.others.ObjectType",3,0,0,1,0,0,0,0,0,0.0));
		
		return types;
	}
	
	public List<MethodData> getMethodData() {
		methods.add(new MethodData("javaProject.com.controller.Type.isSubtype(String dottedSubtype, String collectionType)",45,7,3,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.addClassAndGetClassVertex(XClass xclass)",32,6,5,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isSubtype(ClassDescriptor subDesc, ClassDescriptor... superDesc)",13,5,2,2,2));
		methods.add(new MethodData("javaProject.com.model.Man.foo()",15,5,1,1,0));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeQueryResults.containsType(ClassDescriptor possibleSupertypeClassDescriptor)",10,3,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isSubtype(ClassDescriptor subDesc, ClassDescriptor superDesc)",8,3,1,2,2));
		methods.add(new MethodData("javaProject.com.model.Woman.foo()",8,3,0,1,0));
		methods.add(new MethodData("javaProject.com.controller.Type.traverseSupertypes(ClassDescriptor start)",24,2,5,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.computeKnownSubtypes(ClassDescriptor classDescriptor)",21,2,3,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.computeKnownSupertypes(ClassDescriptor classDescriptor)",14,2,3,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.traverseSupertypesDepthFirstHelper(ClassDescriptor cur, String visitor, Set<ClassDescriptor> seen)",10,2,3,2,3));
		methods.add(new MethodData("javaProject.com.view.QueueViewer.main(String[] args)",10,2,3,1,1));
		methods.add(new MethodData("javaProject.com.controller.Type.traverseEdge(ClassVertex vertex, ClassDescriptor supertypeDescriptor, boolean isInterfaceEdge)",13,1,0,2,3));
		methods.add(new MethodData("javaProject.com.controller.Type.hasKnownSubclasses(ClassDescriptor classDescriptor)",12,1,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isSubtype0(ClassDescriptor subDesc, ClassDescriptor superDesc)",11,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.addClass(XClass xclass)",9,1,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.addToWorkList(LinkedList<SupertypeTraversalPath> workList, SupertypeTraversalPath curPath, ClassDescriptor supertypeDescriptor)",9,1,0,2,3));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.fork(ClassVertex next)",5,1,2,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.Type()",5,1,0,2,0));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.toString()",3,1,1,2,0));
		methods.add(new MethodData("javaProject.com.controller.Type.addClassVertexForMissingClass(ClassDescriptor supertypeDescriptor, boolean isInterfaceEdge)",3,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.addSupertypeEdges(ClassVertex vertex, LinkedList<XClass> workList)",3,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.SupertypeTraversalPath(ClassVertex next)",3,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isApplicationClass(ClassDescriptor descriptor)",3,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isContainer(ReferenceType target)",3,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.resolveClassVertex(ClassDescriptor supertypeDescriptor)",3,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.instanceOf(ClassDescriptor subDescriptor, Class<?> c)",2,1,2,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.traverseSupertypesDepthFirst(ClassDescriptor start, String visitor)",2,1,1,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeQueryResults.addSupertype(ClassDescriptor classDescriptor)",2,1,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.hasBeenSeen(ClassDescriptor classDescriptor)",2,1,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.markSeen(ClassDescriptor classDescriptor)",2,1,1,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.addVertexToGraph(ClassDescriptor classDescriptor, ClassVertex vertex)",2,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.instanceOf(ClassDescriptor subDescriptor, String dottedSupertype)",2,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type.instanceOf(ClassVertex subtype, String dottedSupertype)",2,1,0,2,2));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeQueryResults.setEncounteredMissingClasses(boolean encounteredMissingClasses)",2,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.setNext(ClassVertex next)",2,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type.isCollection(ReferenceType target)",2,1,0,2,1));
		methods.add(new MethodData("javaProject.com.controller.Type$SupertypeTraversalPath.getNext()",2,1,0,2,0));
		methods.add(new MethodData("javaProject.com.model.Man.getName()",2,1,1,1,0));
		methods.add(new MethodData("javaProject.com.model.Woman.getName()",2,1,1,1,0));
		methods.add(new MethodData("javaProject.com.model.Man.Man(String name)",2,1,0,1,1));
		methods.add(new MethodData("javaProject.com.model.Man.searchFor(String name)",2,1,0,1,1));
		methods.add(new MethodData("javaProject.com.model.Man.searchFor(int code)",2,1,0,1,1));
		methods.add(new MethodData("javaProject.com.model.Woman.Woman(String name)",2,1,0,1,1));
		methods.add(new MethodData("javaProject.com.controller.ClassWithComments.foo()",8,1,0,0,0));
		methods.add(new MethodData("javaProject.com.controller.Dispatcher.Dispatcher()",6,1,0,0,0));
		methods.add(new MethodData("javaProject.com.controller.Dispatcher.next()",5,1,1,0,0));
		methods.add(new MethodData("javaProject.com.controller.XClass.getXMethods()",3,1,0,0,0));
		methods.add(new MethodData("javaProject.others.AnalysisContext.currentAnalysisContext()",3,1,0,0,0));
		methods.add(new MethodData("javaProject.others.ClassVertex.isFinished()",3,1,0,0,0));
		methods.add(new MethodData("javaProject.com.controller.Dispatcher.foo(@parameterId('id') int id)",2,1,0,0,1));
		methods.add(new MethodData("javaProject.com.model.Human.Human(String name)",2,1,0,0,1));
		methods.add(new MethodData("javaProject.com.controller.Dispatcher.hasNext()",2,1,0,0,0));
		methods.add(new MethodData("javaProject.com.model.Child.getName()",2,1,0,0,0));
		methods.add(new MethodData("javaProject.com.model.Human.getName()",2,1,0,0,0));
		methods.add(new MethodData("javaProject.com.model.Person.getName()",0,1,0,0,0));

		return methods;
	}
	
	public List<NamespaceCouplingData> getNamespaceCouplingData() {
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.com.controller",1,2,0.6666666666666666,0.0,0.33333333333333337));
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.com.model",1,0,0.0,0.4,0.6));
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.others",1,0,0.0,0.0,1.0));
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.com.view",0,1,1.0,0.0,0.0));
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.one",1,1,0.5,0.0,0.5));
		namespaceCoupling.add(new NamespaceCouplingData("javaProject.two",1,1,0.5,0.0,0.5));
		
		return namespaceCoupling;
	}
	
	public List<MetricThresholdData> getThresholdData() {
		thresholds.add(new MetricThresholdData("SMALL", "Small Project", "small project with < 50 KLOC or 200 < classes",
				0, 200));
		thresholds.add(new MetricThresholdData("MEDIUM", "Medium Project",
				"medium project with (50 KLOC <= project <= 250 KLOC) or (200 <= classes <= 1000)", 200, 1000));
		thresholds.add(new MetricThresholdData("LARGE", "Large Project", "large project with > 250 KLOC or > 1000 classes",
				1000, Double.MAX_VALUE));

		thresholds.add(new MetricThresholdData("NOC", "Number of Types/Classes",
				"Good: <= 11; Regular: between 11 and 28; Bad: > 28", 11, 28));
		thresholds.add(new MetricThresholdData("NAC", "Number of Abstract Types/Classes", "without references", 0, 0));

		thresholds.add(new MetricThresholdData("SLOC", "Type Lines of Code", "Bad: > 500", 0, 500));
		thresholds.add(new MetricThresholdData("NOM", "Number of Methods",
				"Good: <= 6; Regular: between 6 and 14; Bad: > 14", 6, 14));
		thresholds.add(new MetricThresholdData("WMC", "Weighted Methods per Class",
				"Good: <= 20; Regular: between 20 and 100; Bad: > 100", 20, 100));
		thresholds.add(new MetricThresholdData("DEP", "Number of external types dependencies", "Bad: > 20", 0, 20));
		thresholds.add(new MetricThresholdData("I-DEP", "Number of internal types dependencies", "Bad: > 15", 0, 15));
		thresholds.add(new MetricThresholdData("FAN-IN", "Number of other types that depend on a given type", "Bad: > 10", 0, 10));
		thresholds.add(new MetricThresholdData("FAN-OUT", "Number of other types referenced by a type", "Bad: > 15", 0, 15));
		thresholds.add(new MetricThresholdData("NPM", "Number of Public Methods",
				"Good: <= 10; Regular: between 11 and 40; Bad: > 40", 10, 40));
		thresholds.add(new MetricThresholdData("NOA", "Number of Attributes/Fields",
				"Good: <= 3; Regular: between 3 and 8; Bad: > 8", 3, 8));	
		thresholds.add(new MetricThresholdData("LCOM3", "Lack of Cohesion in Methods",
				"Good: = 0; Regular: between 0 and 1; Bad: > 1", 0, 1));


		thresholds.add(new MetricThresholdData("MLOC", "Method Lines of Code",
				"Good: <= 10; Regular: between 10 and 30; Bad: > 30", 10, 30));
		thresholds.add(new MetricThresholdData("CYCLO", "Cyclomatic Complexity",
				"Good: <= 2; Regular: between 2 and 4; Bad: > 4", 2, 4));
		thresholds.add(new MetricThresholdData("CALLS", "Number of Invocations", "Bad: > 5", 0, 5));
		thresholds.add(new MetricThresholdData("NBD", "Nested Block Depth","Good: <= 1; Regular: between 1 and 3; Bad: > 3",
				1, 3));
		thresholds.add(new MetricThresholdData("PARAM", "Number of Parameters", "Good: <= 2; Regular: between 2 and 4; Bad: > 4",
				2, 4));

		thresholds.add(new MetricThresholdData("CA", "Afferent Coupling", "Good: <= 7; Regular: between 7 and 39; Bad: > 39",
				7, 39));
		thresholds.add(new MetricThresholdData("CE", "Efferent Coupling", "Good: <= 6; Regular: between 6 and 16; Bad: > 16",
				6, 16));
		thresholds.add(new MetricThresholdData("I", "Package Instability", "range between 0=Maximally stability and 1=Maximally instability",
				0, 1));
		thresholds.add(new MetricThresholdData("A", "Abstractness Degree", "range between 0=Minimally abstractness and 1=Maximally abstractness",
				0, 1));
		thresholds.add(new MetricThresholdData("D", "Normalized Distance", "range between 0=exactly located in the main sequence and 1=far from the main sequence",
				0, 1));
	
		return thresholds;
	}

	public Set<NamespaceDependencyData> getNamespaceDependencyData() {
		namespacesDependencies.add(new NamespaceDependencyData("javaProject.com.controller", "javaProject.com.model"));
		namespacesDependencies.add(new NamespaceDependencyData("javaProject.com.controller", "javaProject.others"));
		namespacesDependencies.add(new NamespaceDependencyData("javaProject.com.view", "javaProject.com.controller"));
		namespacesDependencies.add(new NamespaceDependencyData("javaProject.one", "javaProject.two"));
		namespacesDependencies.add(new NamespaceDependencyData("javaProject.two", "javaProject.one"));
		
		return namespacesDependencies;
	}

	public List<TypeData> getTypeCouplingData() {
		return types;
	}
	
	public List<CyclicDependencyData> getCyclicDependencyData() {
		dependencies.add(new CyclicDependencyData("javaProject.one.A","javaProject.two.B",1));
				
		return dependencies;
	}
	
	public Map<String, LinkedHashSet<TypeResonanceData>> getTypesResonance() {
		LinkedHashSet<TypeResonanceData> typesToController = new LinkedHashSet<TypeResonanceData>();
		typesToController.add(new TypeResonanceData("ClassWithComments", 8, 1));
		typesToController.add(new TypeResonanceData("Dispatcher", 27, 4));
		typesToController.add(new TypeResonanceData("Type", 245, 58));
		typesToController.add(new TypeResonanceData("XClass", 6, 1));
		typesToController.add(new TypeResonanceData("XMethod", 3, 1));
		typesResonanceData.put("javaProject.com.controller", typesToController);

		LinkedHashSet<TypeResonanceData> typesToModel = new LinkedHashSet<TypeResonanceData>();
		typesToModel.add(new TypeResonanceData("Child", 7, 1));
		typesToModel.add(new TypeResonanceData("Human", 10, 2));
		typesToModel.add(new TypeResonanceData("Man", 29, 9));
		typesToModel.add(new TypeResonanceData("Person", 4, 1));
		typesToModel.add(new TypeResonanceData("Woman", 16, 5));
		typesResonanceData.put("javaProject.com.model", typesToModel);

		LinkedHashSet<TypeResonanceData> typesToView = new LinkedHashSet<TypeResonanceData>();
		typesToView.add(new TypeResonanceData("QueueViewer", 10, 2));
		typesResonanceData.put("javaProject.com.view", typesToView);

		LinkedHashSet<TypeResonanceData> typesToOne = new LinkedHashSet<TypeResonanceData>();
		typesToOne.add(new TypeResonanceData("A", 5, 1));
		typesResonanceData.put("javaProject.one", typesToOne);
		
		LinkedHashSet<TypeResonanceData> typesToOthers = new LinkedHashSet<TypeResonanceData>();
		typesToOthers.add(new TypeResonanceData("AnalysisContext", 6, 1));
		typesToOthers.add(new TypeResonanceData("ClassDescriptor", 3, 1));
		typesToOthers.add(new TypeResonanceData("ClassVertex", 6, 1));
		typesToOthers.add(new TypeResonanceData("ObjectType", 3, 1));
		typesResonanceData.put("javaProject.others", typesToOthers);

		LinkedHashSet<TypeResonanceData> typesToTwo = new LinkedHashSet<TypeResonanceData>();
		typesToTwo.add(new TypeResonanceData("B", 5, 1));
		typesResonanceData.put("javaProject.two", typesToTwo);

		return typesResonanceData;
	}	
}
