package stringTie;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTranscript {

	static String fileName="C:\\Users\\David\\Desktop\\算法导论实验\\splicing_graphs\\out.txt";
	public static void write(String content) {
		File file = new File(fileName);
		
		try {
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
