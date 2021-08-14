package utils.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class SystemUtils {

	public List<File> getJavaFiles(String path) {
		try {
			return getFiles(path, "java");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<File> getFiles(String path, String platform) {
		try {
			File sourceDir = new File(path);
			return new ArrayList<File>(FileUtils.listFiles(sourceDir, new String[] {platform}, true));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static boolean writeFile(String fileName, String content) {
		FileWriter writer;
		try {
			writer = new FileWriter(new File(fileName));
			PrintWriter output = new PrintWriter(writer);
			output.print(content);
			output.close();  
			writer.close();
		} catch (IOException e) {
			System.out.println("\n" + e.getMessage() + "\n");
			return false;
		}  
		return true;
	}
}
