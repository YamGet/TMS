package org.fidel.tms.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckFileExistance {
	
	public static boolean checkAndCreateFile(String path){
		
		boolean rslt = false;
		
		File myDir = new File(path);
		//myDir.getParentFile().mkdir();		
		
		File file = new File(myDir, "initialPage.txt");
		
		if(!file.exists()){
			
			myDir.mkdir();
			
			try {
				
				FileWriter fw = new FileWriter(file);
				PrintWriter pw = new PrintWriter(fw);
				pw.println("initialPage"); 
				pw.flush(); 
				pw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		return rslt;		
	}

}
