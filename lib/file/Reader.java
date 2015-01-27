package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Reader {
	
	public static String read(String fileName){
		String content = "";
		
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR]: Can't find file named \"" + fileName + "\"");
			return "";
		}   
		  
		try {
			int length = fin.available();
			byte [] buffer = new byte[length];
			fin.read(buffer);       
			content = new String(buffer);
			fin.close();
		} catch (IOException e) {
			System.out.println("[ERROR]: Read data from file \"" + fileName + "\" failed");
		}   

		return content;
	}
}
