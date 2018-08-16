package org.fidel.tms.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenFile {
	
	public static void openExistingPDFFile(String fileName){
		
		try {
			////*** Method One ***////
//			if(fileName.toString().endsWith(".pdf")){
//				Process p = Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler" + fileName);
//				p.waitFor();				
//			}
			
			////*** Method Two ***////
			File certificateFile = new File(fileName);
			if(certificateFile.exists()){
				
				if(Desktop.isDesktopSupported()){
					Desktop.getDesktop().open(certificateFile);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	public static void openExistingExcelFile(String fileName){
		
		try {
			
			////*** Method Two ***////
			File excelFile = new File(fileName);
			
			if(excelFile.exists()){
				
				if(Desktop.isDesktopSupported()){
					Desktop.getDesktop().open(excelFile);	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
