package utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

public class SystemUtilsTest {
	private static final String RESOURCES_JAVA_PROJECT = "./resources/javaProject";
	private static final String RESOURCES_UNKNOWN_PROJECT = "./resources/fooProject";

	@Test
	public void testGetJavaFiles() throws IOException {
		SystemUtils su = new SystemUtils();
		Collection<File> javaFiles = su.getJavaFiles(RESOURCES_JAVA_PROJECT);
		assertTrue(javaFiles.size() > 0);
	}

	@Test(expected=RuntimeException.class)
	public void testGetJavaFilesInUnknownProject() throws IOException {
		SystemUtils su = new SystemUtils();
		Collection<File> javaFiles = su.getJavaFiles(RESOURCES_UNKNOWN_PROJECT);
		assertTrue(javaFiles.size() == 0);
	}
}
