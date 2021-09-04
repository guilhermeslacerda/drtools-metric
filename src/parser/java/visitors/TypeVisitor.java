package parser.java.visitors;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import structures.MetricActivator;
import structures.metrics.TypeMetric;
import structures.results.TypeMetricResult;

public class TypeVisitor extends ASTVisitor implements MetricActivator<TypeMetricResult> {
	private static final String OBJECT_CLASS = "Object";
	private static final String ABSTRACT_MODIFIER = "abstract";
	private static final String MAIN_METHOD = "main";
	private static final String NAMESPACE_SEPARATOR = ".";
	private String sourceCodeFile;
	private TypeMetric typeMetric;
	private int numOfMethods;
	private int numOfVariables;
	private int numOfPublicMethods;
	private Set<String> internalTypes;
	private Set<String> fanOut;
	private Set<MethodDeclaration> methods;
	private String typeName;
	private TypeMetricResult tmr;
	private Set<String> variablesUsedInMethods;
	private Set<String> variables;
	
	public TypeVisitor(String sourceCodeFile, TypeMetricResult tmr) {
		numOfMethods = 0;
		numOfVariables = 0;
		numOfPublicMethods = 0;
		this.sourceCodeFile = sourceCodeFile;
		this.tmr = tmr;
		typeMetric = new TypeMetric();
		internalTypes = new TreeSet<>();
		methods = new HashSet<>();
		fanOut = new HashSet<>();
		variablesUsedInMethods = new HashSet<String>();
		variables = new HashSet<String>();

	}

	@Override
	public boolean visit(PackageDeclaration node) {
		if (node == null) 	return false;
		typeMetric.setNamespace(node.getName().getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration node) {
		if (node == null) 	return false;

		getInternalTypes(node);
		getAllMethods(node);
		
		if (isTypeEqualsFileName(node)) 
			typeName = node.getName().toString();
	
		typeMetric.setName(node.getName().toString());
		isAbstractType(node);
		getImplementedInterfaces(node);
		getSuperTypes(node);

		return true;
	}

	private void getImplementedInterfaces(TypeDeclaration node) {
		if (node.resolveBinding() == null) 	return;	
		ITypeBinding[] interfaces = node.resolveBinding().getInterfaces();
			for (ITypeBinding superType : interfaces) {
				fanOut.add(superType.getQualifiedName());
				setFanIn(superType.getQualifiedName());
			}
	}

	private void getSuperTypes(TypeDeclaration node) {
		if (node.resolveBinding() == null) 	return;	
		ITypeBinding superType = node.resolveBinding().getSuperclass();
		if (superType != null && !superType.getQualifiedName().endsWith(OBJECT_CLASS)) { 
			fanOut.add(superType.getQualifiedName());
			setFanIn(superType.getQualifiedName());
		}
	}

	private void isAbstractType(TypeDeclaration node) {
		if (node.isInterface())  typeMetric.setInterface(true);

		@SuppressWarnings("unchecked")
		List<String> modifiers = node.modifiers();
		for (Object modifier : modifiers) {
			if(modifier.toString().equals(ABSTRACT_MODIFIER)) {
				typeMetric.setAbstract(true);
				break;
			}
		}
	}

	private void getAllMethods(TypeDeclaration node) {
		MethodDeclaration[] methods = node.getMethods();
		for (MethodDeclaration methodDeclaration : methods) 
			this.methods.add(methodDeclaration);
	}

	private void getInternalTypes(TypeDeclaration node) {
		TypeDeclaration[]  types = node.getTypes(); 
		for (TypeDeclaration typeDeclaration : types) {
			internalTypes.add(typeMetric.getNamespace() + NAMESPACE_SEPARATOR + typeDeclaration.getName().toString());
			setFanIn(typeMetric.getNamespace() + NAMESPACE_SEPARATOR + typeDeclaration.getName().toString());
		}
	}

	private boolean isTypeEqualsFileName(TypeDeclaration node) {
		return node.getName().toString().equals(FilenameUtils.removeExtension(FilenameUtils.getName(sourceCodeFile)));
	}
	
	@Override
	public boolean visit(ImportDeclaration node) {
		typeMetric.addImport(node.getName().getFullyQualifiedName());
		fanOut.add(node.getName().getFullyQualifiedName());
		return true;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		++numOfVariables;
		variables.add(node.toString());
		return true;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		if (!MAIN_METHOD.equals(node.getName().toString())) 
			++numOfMethods;
		
		if(Modifier.isPublic(node.getModifiers()) || node.getModifiers() == 0)
			++numOfPublicMethods;
		
		return true; 
	} 

	@Override
	public void update(TypeMetricResult result) {
		if (typeMetric != null && typeMetric.getName() != null) {
			typeMetric.setSloc(sourceCodeFile);
			typeMetric.setNumOfMethods(numOfMethods);
			typeMetric.setNumOfPublicMethods(numOfPublicMethods);
			typeMetric.setNumOfVariables(numOfVariables);		
			typeMetric.setInternalTypes(internalTypes);
			typeMetric.setName(typeName);
			typeMetric.setFanOut(fanOut.size());
			typeMetric.setVariables(variables);
			typeMetric.setVariablesUsedInMethods(variablesUsedInMethods);
			result.add(typeMetric);
		}	
	}
	
	@Override
	public boolean visit(ClassInstanceCreation node) {
		ITypeBinding bindType = node.resolveTypeBinding();
		if (bindType != null) {
			setFanIn(bindType.getQualifiedName());
			return true;
		}
		return false;	
	}
	
    @Override
    public boolean visit(MethodInvocation node) {
    	IMethodBinding bindMethod = node.resolveMethodBinding();
		if (bindMethod != null) {
			ITypeBinding bindType = bindMethod.getDeclaringClass();
			if (bindType != null) 
				fanOut.add(bindType.getQualifiedName());
		}
		
    	return true;
	}

    @Override
    public boolean visit(SuperMethodInvocation node) {
    	IMethodBinding bindMethod = node.resolveMethodBinding();
		if (bindMethod != null) {
			ITypeBinding bindType = bindMethod.getDeclaringClass();
			if (bindType != null) 
				fanOut.add(bindType.getQualifiedName());
		}

    	return true;
	}

	public Set<MethodDeclaration> getAllMethods() {
		return methods;
	}
	
	private void setFanIn(String name) {
		tmr.addOrUpdateFanInOf(name);
	}

	@Override
	public boolean visit(VariableDeclarationFragment node) {
		variablesUsedInMethods.add(node.getName().toString());
		return true;
	}
}