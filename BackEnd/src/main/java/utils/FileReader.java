package utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileReader {
	public static String readStringFromFile(String inputFile) {
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(inputFile));
			byte[] bytes = new byte[(int) new File(inputFile).length()];
			in.read(bytes);
			in.close();
			return new String(bytes);
		}
		catch (Exception e) {
			return null;
		}
	}
}
