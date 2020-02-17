package output;

public interface MetricFile {
	String generateNamespaces();
	String generateTypes();
	String generateMethods();
	String generateSummary();
	String generateCyclicDependencies();
	String generateNamespaceCoupling();
	String generateThresholds();
	String generateTypeCoupling();
}
