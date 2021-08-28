package output;

public class InfoConsole {
	private static final String VERSION = "1.5.4";

	private InfoConsole() {
	}

	public static void printUsage() {
		printCommands();
		printMetrics();
		printUsageExamples();
	}

	public static void printHeader() {
		System.out.println(
				"drtools-metric :: helping you to improve the health of your source code and reduce technical debt!");
	}

	public static void printHeader(String message) {
		printHeader();
		System.out.println(message);
	}

	public static void printDevelopmentInformation() {
		printHeader("\nDeveloped by Guilherme Lacerda (guilhermeslacerda@gmail.com)\nVersion " + 
					VERSION + "\nOfficial site: http://drtools.site");
	}
	
	private static void printCommands() {
		System.out.println("\nUsage: drtools-metric <project-directory> <OPTIONS> <OUTPUT> [--top <number>]");
		System.out.println("OPTIONS = <-a|-ac|-s|-n|-t|-m|-d|-cd|-id|-nc|-tc|-mt|-i|-mv> OUTPUT = <--console|--csv|--json|--save>");

		System.out.println("\n\tWhere");
		System.out.print("-a\tlist ALL metrics (namespaces/types/methods)");
		System.out.println("\t\t--console\tshow the results to console");
		System.out.print("-ac\tlist ALL metrics about COUPLING/DEPENDENCIES");
		System.out.println("\t\t--csv\t\tgenerate results in CSV format");
		System.out.print("-s\tlist SUMMARY of project");
		System.out.println("\t\t\t\t\t--json\t\tgenerate results in JSON format");
		System.out.print("-n\tlist information about NAMESPACES (packages)");
		System.out.println("\t\t--save\t\tgenerate file results to drtools-metric-visualization tool");
		System.out.println("-t\tlist information about TYPES (classes)");
		System.out.print("-m\tlist information about METHODS (functions)");
		System.out.println("\t\t--top\t\tlist top 'number' records, based on used format");
		System.out.println("-d\tlist information about DEPENDENCIES of types");
		System.out.println("-cd\tlist information about CYCLIC DEPENDENCIES of types");
		System.out.println("-id\tlist information about INTERNAL DEPENDENCIES of types");
		System.out.println("-nc\tlist information about NAMESPACE COUPLING");
		System.out.println("-tc\tlist information about TYPE COUPLING");
		System.out.println("-mt\tlist information about METRIC THRESHOLDS");
		System.out.println("-i\tlist INFORMATION about tool development team");
		System.out.println("-mv\tgenerate files to drtools-metric-visualization tool (use only with --save output option)");
		System.out.println("-sn\tlist the STATISTICS of NAMESPACES metrics");
	}

	private static void printMetrics() {
		System.out.println("\n\tMetrics");
		System.out.println("CA    - Number of types outside this component that depends on types inside this component (Afferent Coupling)");
		System.out.println("CE    - Number of types inside this component that depends on types outside this component (Efferent Coupling)");
		System.out.println("I     - Instability of namespace (range between 0=Maximally stability and 1=Maximally instability)");
		System.out.println("A     - Abstractness degree of namespace (range between 0=Minimally abstractness and 1=Maximally abstractness)");
		System.out.print("D     - Normalized distance of namespace");
		System.out.println("\t\t\tNOM    - Number of methods of a type");
		System.out.print("NAC   - Number of abstract types inside namespaces");
		System.out.println("\t\tWMC    - Weighted methods per types (sum the CYCLO of each method)");
		System.out.print("NOC   - Number of types inside namespaces");
		System.out.println("\t\t\tSLOC   - Number of lines of source code ");
		System.out.print("DEP   - Number of external types dependencies");
		System.out.println("\t\t\tI-DEP  - Number of internal types dependencies");
		System.out.print("FAN-IN- Number of other types that depend on a given type");
		System.out.println("\tFAN-OUT- Number of other types referenced by a type");
		System.out.print("NOA   - Number of attributes/variables");
		System.out.println("\t\t\t\tNPM    - Number of public methods of a type");
		System.out.print("NBD   - Number of nested block depth of a method");
		System.out.println("\t\tMLOC   - Number of lines of a method");
		System.out.print("PARAM - Number of parameters of a method");
		System.out.println("\t\t\tCYCLO  - Cyclomatic complexity (McCabe) of a method");
		System.out.print("CALLS - Number of invocations made from within a method");
		System.out.println("\t\tLCOM3  - Lack of cohesion in methods");
	}

	private static void printUsageExamples() {
		System.out.println("\n Usage examples:");
		System.out.println("\tExample 1 : # drtools-metric \\Project\\Java\\src -a --console");
		System.out.println("\tExample 2 : # drtools-metric \\Project\\Java\\src -t --csv");
		System.out.println("\tExample 3 : # drtools-metric \\Project\\Java\\src -m --console --top 10");
	}

	public static void printElapsedTime(long startTime) {
		if (startTime == 0) return;
		long elapsedTime = (System.currentTimeMillis() - startTime);
		System.out.println("\n\tProcessing time: " + (elapsedTime <= 1000 ? elapsedTime + 
								" milliseconds" : elapsedTime/1000 + " seconds") );
	}
	
	public static void print(String message) {
		System.out.print(message);
	}

	public static void printMetricVisualizationUsage() {
		System.out.println("\n\nTo use the data with drtools-metric visualization, you need:");
		System.out.println("1 - create a folder of your project within the datasets folder");
		System.out.println("2 - copy the generated files (CSV and JSON) to the created folder"); 	
		System.out.println("3 - do the setup on dr-tools-properties.js and you're done!");	
	}
	
	public static void printStatisticalLabels() {
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("METRIC\t1stQ\t3rdQ\tAvg\tMedian\tMin\tMax\tMax-Min\tStdDev\tU-Fnc\tThreshold");
		System.out.println("-----------------------------------------------------------------------------------------");
	}
	
	public static void printStatisticalLegend() {
		System.out.println("\n\nLegend:");
		System.out.println("\t1stQ=First Quartile | 3rdQ=Third Quartile | Avg=Average | Median=Median | Min=Min value | Max=Max value");
		System.out.println("\tMax-Min=Amplitude | StdDev=Standard Deviation | U-Fnc=Upper Fence | Threshold=Metric Threshold");
	}
}
