package utils;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import utils.SourceCodeLineCounter;
import org.junit.Test;

public class SourceCodeLineCounterTest {

	@Test
	public void testGetNumberOfLines() throws FileNotFoundException, IOException {
		SourceCodeLineCounter code = new SourceCodeLineCounter();
		assertEquals(25, code.getNumberOfLines(
			new BufferedReader(
				new InputStreamReader(
						new FileInputStream("./resources/javaProject/com/controller/Dispatcher.java")))));
	}

	@Test
	public void testGetNumberOfLinesWithCodeComments() throws FileNotFoundException, IOException {
		SourceCodeLineCounter code = new SourceCodeLineCounter();
		assertEquals(8, code.getNumberOfLines(
			new BufferedReader(
				new InputStreamReader(
						new FileInputStream("./resources/javaProject/com/controller/ClassWithComments.java")))));
	}


}
