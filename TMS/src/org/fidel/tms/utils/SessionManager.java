package org.fidel.tms.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fidel.tms.model.SystemURL;
import org.springframework.context.annotation.Scope;
import org.springframework.test.web.client.ResponseCreator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

//import com.sun.xml.internal.ws.client.ResponseContext;
//import com.sun.xml.internal.ws.client.ResponseContextReceiver;

import nl.bitwalker.useragentutils.UserAgent;

//import com.sun.tools.internal.ws.processor.model.Request;


public class SessionManager extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//private static Map<String, Object> sessionMap = new HashMap<String, Object>();
	//static HttpServletResponse response;
	//static InetAddress IP;
	
	public static boolean isSessionOn(){
		
		boolean rslt = false;
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		
		UserAgent useragent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		
		//IP = InetAddress.getLocalHost();
		
		if(session.getAttribute("browserType") != null && (useragent+"-"+session.getAttribute("loggedInUser")).equals(session.getAttribute("browserType"))){
		
			rslt = true;			
		}
		
		return rslt;
	}
	
	public static boolean sessionExpire(){
		
		return !isSessionOn();
	}
	
	public static ModelAndView redirectToLogin(){
		
		ModelAndView modelandview = new ModelAndView("redirect:../user/session_expire");
		
		return modelandview;
	}
	
	public static void addingUserOnSession(String username, int user_id, List<SystemURL> urlList){
		//if(isSessionOn()){
			SessionManager.clearSession();
		//}
		setSession(username, user_id, urlList);
	}
		
	public static void setSession(String username, int user_id, List<SystemURL> urlList){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();		
		HttpSession session = request.getSession();
		
		UserAgent useragent_in = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		
		session.setAttribute("loggedInUser", username);
		session.setAttribute("user_id", user_id);
		session.setAttribute("browserType", useragent_in + "-" + username);
		session.setAttribute("urlList", urlList);
				
//		sessionMap.put("loggedInUser", username);
//		sessionMap.put("user_id", user_id);
//		sessionMap.put("urlList", urlList);		
//		sessionMap.put("browserType", useragent_in + "-" + username);
	}
	
	

	public static boolean checkAuthenticatedURL(String url){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();		
		HttpSession session = request.getSession();
		
		boolean rslt = false;
		
		List<SystemURL> urlList = (List<SystemURL>)session.getAttribute("urlList");
		
//		if(urlList == null){
//			
//			return rslt;
//		}
		
		for(int i = 0; i < urlList.size(); i++){
			
			if(url.equalsIgnoreCase(urlList.get(i).getUrl()+".html")){
				
				rslt = true;
			}
		}
		
		return rslt;
	}
	
	public static ModelAndView authenticationDenied(){
		
		ModelAndView model = new ModelAndView("/authentication_denied");
		
		return model;
	}
	
	public static int getUserIdOnSession(){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();		
		HttpSession session = request.getSession();
		
		return (Integer)session.getAttribute("user_id");
		
		//return (Integer)sessionMap.get("user_id");
	}
	
	public static void clearSession(){
		
//		sessionMap.remove("loggedInUser");
//		sessionMap.remove("user_id");
//		sessionMap.remove("urlList");
//		sessionMap.remove("browserType");
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();		
		HttpSession session = request.getSession();
		
		session.removeAttribute("loggedInUser");
		session.removeAttribute("user_id");
		session.removeAttribute("urlList");
		session.removeAttribute("browserType");
	}

//	public static Map<String, Object> getSessionMap() {
//		return sessionMap;
//	}
//
//	public static void setSessionMap(Map<String, Object> sessionMap) {
//		SessionManager.sessionMap = sessionMap;
//	}
}
