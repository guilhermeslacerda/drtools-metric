package parser.java.visitors;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.WhileStatement;

import structures.MetricActivator;
import structures.metrics.MethodMetric;
import structures.results.MethodMetricResult;

public class MethodVisitor extends ASTVisitor implements MetricActivator<MethodMetricResult> {
	private static final String ELEMENT_SEPARATOR = ".";
	private int numOfParameters;
	private int cyclo;
	private int calls;
	private int nbd;
	private int maxNbd;
	private MethodMetric methodMetric;
	private String namespace;
	private CompilationUnit unit;
	private MethodDeclaration method, anotherMethod;
	private String parameters;

	public MethodVisitor(CompilationUnit unit, MethodDeclaration method) {
		methodMetric = new MethodMetric();
		numOfParameters = 0;
		cyclo = 1;
		calls = 0;
		nbd = 0;
		maxNbd = 0;
		parameters = "";
		this.unit = unit;
		this.method = method;
	}
	
	@Override
	public boolean visit(PackageDeclaration node) {
		namespace = node.getName().getFullyQualifiedName();
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		if (node == null) return false;

		anotherMethod = node;
		if (method.equals(anotherMethod)) {
			defineNumberOfParameters(node);
			defineParameters();	
			defineNumberOfLines();
			defineMethodSignature(node);
		}

		return true;
	}

	private void defineMethodSignature(MethodDeclaration node) {
		IMethodBinding bindMethod = node.resolveBinding();
		if (bindMethod != null) {
			ITypeBinding bindType = bindMethod.getDeclaringClass();
			if (bindType != null) {
				String fullTypeName = namespace + ELEMENT_SEPARATOR + bindType.getName();
				String methodSignature = bindType.getBinaryName() + ELEMENT_SEPARATOR + node.getName().toString() + parameters;
				methodMetric.setTypeName(fullTypeName);
				methodMetric.setName(methodSignature);
			}
		}
	}

	private void defineNumberOfLines() {
		methodMetric.setStartLine(unit.getLineNumber(method.getStartPosition()));
		methodMetric.setEndLine(unit.getLineNumber(method.getStartPosition() + method.getLength()));
	}

	private void defineNumberOfParameters(MethodDeclaration node) {
		numOfParameters = node.parameters() == null ? 0 : node.parameters().size();
		methodMetric.setNumOfParameters(numOfParameters);
	}

	private void defineParameters() {
		StringBuilder allParameters = new StringBuilder();
		allParameters.append("(");

        @SuppressWarnings("unchecked")
		Iterator<SingleVariableDeclaration> parameters = method.parameters().iterator();
		while (parameters.hasNext()) {
			SingleVariableDeclaration parameter = parameters.next();
			allParameters.append(((SingleVariableDeclaration) parameter).toString());
			if (parameters.hasNext()) {
				allParameters.append(", ");
			}
		}
		allParameters.append(')');
		this.parameters = allParameters.toString();
	}

	@Override
    public boolean visit(ForStatement node) {
		if (method.equals(anotherMethod)) 
			increaseCyclo();
    	return true;
    }

    @Override
    public boolean visit(EnhancedForStatement node) {
		if (method.equals(anotherMethod)) 
			increaseCyclo();
    	return true;
    }
    
    @Override
    public boolean visit(ConditionalExpression node) {
		if (method.equals(anotherMethod)) 
			increaseCyclo();
    	return true;
    }
    
    @Override
    public boolean visit(DoStatement node) {
		if (method.equals(anotherMethod)) 
			increaseCyclo();
    	return true;
    }
    
    @Override
    public boolean visit(WhileStatement node) {
		if (method.equals(anotherMethod)) 
			increaseCyclo();
    	return true;
    }
    
    @Override
    public boolean visit(SwitchCase node) {
    	if (method.equals(anotherMethod)) {
    		if(!node.isDefault())
    			increaseCyclo();
    	}
    	return true;
    }
    
    @Override
    public boolean visit(IfStatement node) {
    	if (method.equals(anotherMethod)) {
	    	String expression = node.getExpression().toString().replace("&&", "&").replace("||", "|");
			int ands = StringUtils.countMatches(expression, "&");
			int ors = StringUtils.countMatches(expression, "|");
			
			increaseCyclo(ands + ors);
	    	increaseCyclo();
    	}
	    return true;
    }
    
    private void increaseCyclo() {
    	increaseCyclo(1);
    }

    protected void increaseCyclo(int count) {
    	cyclo += count;
    }
    
    @Override
    public boolean visit(AnonymousClassDeclaration node) {
        return false;
    }
    
    @Override
    public boolean visit(MethodInvocation node) {
    	if (method.equals(anotherMethod)) 
			calls++;
		return true;
	}

    @Override
    public boolean visit(SuperMethodInvocation node) {
    	if (method.equals(anotherMethod)) 
    		calls++;
		return true;
	}

    @Override
	public boolean visit(Block node) {
		nbd++;
		return true;
	}

	@Override
	public void endVisit(Block node) {
		maxNbd = Math.max(nbd,  maxNbd);
		nbd--;
	}
	
	@Override
	public void update(MethodMetricResult result) {
		methodMetric.setCyclo(cyclo);
		methodMetric.setCalls(calls);
		methodMetric.setNestedBlockDepth(maxNbd - 1);
		result.add(methodMetric);
	}
}
