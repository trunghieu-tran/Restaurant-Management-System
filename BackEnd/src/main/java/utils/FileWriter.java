package utils;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter {
	public static void writeStringToFile(String data, String outputFile) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new java.io.FileWriter(outputFile));
			writer.write(data);
		}
		catch (IOException e) {
			System.out.println("Writing error <<<");
		}
		finally {
			try {
				if (writer != null)
					writer.close();
			}
			catch (IOException e) {
				System.out.println("Flie Closing error <<<");
			}
		}
	}
}
