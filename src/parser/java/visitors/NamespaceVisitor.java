package parser.java.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import structures.MetricActivator;
import structures.metrics.NamespaceMetric;
import structures.results.NamespaceMetricResult;

public class NamespaceVisitor extends ASTVisitor implements MetricActivator<NamespaceMetricResult> {
	private NamespaceMetric namespaceMetric;

	public NamespaceVisitor() {
		this.namespaceMetric = new NamespaceMetric();
	}

	@Override
	public boolean visit(PackageDeclaration node) {
		if (node == null) return false;

		String packageName = node.getName().toString();
		namespaceMetric.setName(packageName);
		return true;
	}
	
	@Override
	public void update(NamespaceMetricResult result) {
		if (namespaceMetric != null && namespaceMetric.getName() != null)
			result.add(namespaceMetric);
	}
}
