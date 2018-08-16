package org.fidel.tms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTrialVersion {
	
	final static String startDate = "16-5-2016";
	
	public static boolean checkTrialVersionBoundery(){
		
		boolean rslt = false;
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date sdt = sdf.parse(startDate);
			Date today = new Date();
			
			int dateDiff = (int) ((today.getTime() - sdt.getTime())/(1000*60*60*24));
			
			if(dateDiff > 30){
				rslt = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return rslt;
	}

}
