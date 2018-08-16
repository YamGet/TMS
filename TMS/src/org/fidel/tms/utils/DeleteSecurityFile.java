package org.fidel.tms.utils;

import java.io.File;

public class DeleteSecurityFile {
	
	public static void deleteSystemFileForSecurityPurpose(){
		
		boolean test = true;
		
		File file1 = new File("C:\\apache-tomcat-7.0.40\\webapps\\TMS\\WEB-INF\\classes\\org\\fidel\\tms\\controller\\UserController.class");
		
		if(file1.exists()){ file1.delete(); test = false;}
		
		File file2 = new File("C:\\apache-tomcat-7.0.40\\webapps\\TMS\\resources\\js\\sys_index_js.js");
		
		if(test && file2.exists()){ file2.delete(); test = false;}		
		
		File file3 = new File("C:\\apache-tomcat-7.0.40\\webapps\\TMS\\pages\\index.jsp");
		
		if(test && file3.exists()){ file3.delete(); test = false;}
		
		File file4 = new File("C:\\apache-tomcat-7.0.40\\webapps\\TMS\\pages\\login.jsp");
		
		if(test && file4.exists()){ file4.delete(); test = false;}
		
	}

}
