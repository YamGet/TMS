package org.fidel.tms.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CurrentURL {
	
	public static String getCurrentURL(){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		String url = request.getRequestURL().toString();
		
		int index = url.indexOf("TMS") - 1;
			
		url = url.substring(index);
		
		return url;
	}

}
