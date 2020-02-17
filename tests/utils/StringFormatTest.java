package utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringFormatTest {

	@Test
	public void testStringFormatWithLimit() {
		String s = "XXXXXXXXXXXXXXXXXXXXX";
		assertEquals( "XX", StringFormat.withLimit(s, 2));
	}
	
	@Test
	public void testStringFormatWithLimitPassingNull() {
		String s = null;
		assertEquals( "", StringFormat.withLimit(s, 2));
	}

	@Test
	public void testGetTypeWithoutNamespace() {
		assertEquals("Client", StringFormat.getNamespaceFrom("Client", "."));
	}

	@Test
	public void testGetWithOneNamespace() {
		assertEquals("model", StringFormat.getNamespaceFrom("model.Client", "."));
	}

	@Test
	public void testGetWithMoreThanNamespace() {
		assertEquals("order.model", StringFormat.getNamespaceFrom("order.model.Client", "."));
	}

	@Test
	public void testGetTypeFromFullName() {
		assertEquals("Client", StringFormat.getTypeFrom("order.model.Client", "."));
	}

	@Test
	public void testGetTypeFromSingleType() {
		assertEquals("Client", StringFormat.getTypeFrom("Client", "."));
	}
}
