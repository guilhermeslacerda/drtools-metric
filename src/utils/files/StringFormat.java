package utils.files;

public class StringFormat {

	public static String withLimit(String string, int value) {
		if (string == null) return ""; 
		int lengthString = string.length();
		return (lengthString < value) ? string : string.substring(string.length() - value, string.length());
	}

	public static String getTypeFrom(String fullName, String separator) {
		int position = fullName.lastIndexOf(separator);
		return (position == -1) ? fullName : fullName.substring(position + 1, fullName.length());
	}

	public static String getNamespaceFrom(String fullName, String separator) {
		int position = fullName.lastIndexOf(separator);
		return (position == -1) ? fullName : fullName.substring(0, position);
	}
	
	public static String convertQuotation(String string) {
		int position = string.lastIndexOf("\"");
		return (position == -1) ? string : string.replace('\"', '\'');
	}
}
