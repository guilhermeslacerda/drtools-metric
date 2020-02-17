package parser.java;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import parser.TypeParser;
import parser.java.visitors.MethodVisitor;
import parser.java.visitors.NamespaceVisitor;
import parser.java.visitors.TypeVisitor;
import structures.results.MethodMetricResult;
import structures.results.NamespaceMetricResult;
import structures.results.TypeMetricResult;

public class JavaParser implements TypeParser {
	private File file;
	private NamespaceMetricResult nmr;
	private TypeMetricResult tmr;
	private MethodMetricResult mmr;
	private ASTParser parser;
	private CompilationUnit unit;

	public JavaParser(File file, NamespaceMetricResult namespaceMetricResult, TypeMetricResult typeMetricResult,
			MethodMetricResult methodMetricResult) {
		this.file = file;
		this.nmr = namespaceMetricResult;
		this.tmr = typeMetricResult;
		this.mmr = methodMetricResult;
		
		tmr.setMethodMetricResult(mmr);
	}

	public void parsing() {
		String source = "";
		try {
			source = FileUtils.readFileToString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		configAndParse(source);
		parseNamespaces();
		parseTypesAndMethods();
	}

	private void parseTypesAndMethods() {
		TypeVisitor tv = new TypeVisitor(file.getAbsolutePath(), tmr);
		unit.accept(tv);
		tv.update(tmr);
		parseMethods(tv);
	}

	private void parseMethods(TypeVisitor tv) {
		Set<MethodDeclaration> methods = tv.getAllMethods();
		for (MethodDeclaration methodDeclaration : methods) {
			MethodVisitor mv = new MethodVisitor(unit, methodDeclaration);
			unit.accept(mv);
			mv.update(mmr);
		}
	}

	private void parseNamespaces() {
		NamespaceVisitor nsv = new NamespaceVisitor();
		unit.accept(nsv);
		nsv.update(nmr);
	}

	private void configAndParse(String source) {
		parser = ASTParser.newParser(AST.JLS11);	
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setBindingsRecovery(true);

		parser.setCompilerOptions(JavaCore.getOptions());
		parser.setUnitName(file.getName());
		parser.setEnvironment(null, null, null, true);
		parser.setSource(source.toCharArray());
		unit = (CompilationUnit) parser.createAST(null);
	}
}