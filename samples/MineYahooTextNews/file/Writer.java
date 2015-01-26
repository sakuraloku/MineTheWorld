package file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {
	public static void write(byte[] content, String filePath){
		FileOutputStream fos = null;
		try {
			 fos = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]: \"" + filePath + "\" is a directory");
			return;
		}
		
		try {
			fos.write(content);
		} catch (IOException e1) {
			System.out.println("[ERROR]: Write data to file \"" + filePath + "\" failed");
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("[ERROR]: Close FileOutputStream failed");
				return;
			}
			return;
		}
		
		try {
			if(fos != null){
				fos.close();
			}
		} catch (IOException e) {
			System.out.println("[ERROR]: Close FileOutputStream failed");
			return;
		}
	}
}
