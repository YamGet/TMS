package org.fidel.tms.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacAddressGenerator {
	
	public static List<String> getMacAddressesList(){
		
		List<String> macList = new ArrayList<String>();
		
		String command = "ipconfig /all";
		
		try{
			Process p = Runtime.getRuntime().exec(command);
			
			BufferedReader inn = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			Pattern pattern = Pattern.compile(".*Physical Address.*: (.*)");
			
			while(true){
				
				String line = inn.readLine();
				
				if(line == null){
					break;
				}
				
				Matcher mm = pattern.matcher(line);
				
				if(mm.matches()){
					
					macList.add(mm.group(1));
				}
			}
		} catch(IOException e){
			
			e.printStackTrace();
		}
		
		return macList;
	}
	
	public static String getMacAddress(){
		String mac = "";
		String os = System.getProperty("os.name").toLowerCase();
		
		try{
			if(os.startsWith("windows")){
				mac = windowParseMacAddress(windowsRunIpConfigCommand());
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return mac;
	}
	
	private static String windowParseMacAddress(String ipConfigResponse) throws ParseException{
		
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse);
		
		String lastMacAddress = null;
		
		while(tokenizer.hasMoreTokens()){
			
			String line = tokenizer.nextToken().trim();
			if(line.equalsIgnoreCase(":")){
				
				String macAddressCandidate = tokenizer.nextToken();
				
				if(windowIsMacAddress(macAddressCandidate)){
					lastMacAddress = macAddressCandidate;
					continue;
				}
			}
		}
		return lastMacAddress;
	
	}
	
	private static boolean windowIsMacAddress(String macAddressCandidate){
		Pattern macPattern = Pattern.compile("[0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}[-:][0-9a-fA-F]{2}");
		Matcher m = macPattern.matcher(macAddressCandidate);
		return m.matches();
	}
	
	private static String windowsRunIpConfigCommand() throws IOException {
		
		Process p = Runtime.getRuntime().exec("ipconfig /all");
		InputStream stdoutStream = new BufferedInputStream(p.getInputStream());		
		StringBuilder buffer = new StringBuilder();
		
		for(;;){
			int c = stdoutStream.read();
			if(c == -1){
				break;
			}
			buffer.append((char)c);
		}
		
		String outputText = buffer.toString();
		stdoutStream.close();
		
		return outputText;
		
	}
	
	/*
	 * not used just for back up -- if it might be needed.
	 */
	private static String windowParseMacAddressOriginal(String ipConfigResponse) throws ParseException{
		
		String localhost = null;
		
		try{
			localhost = InetAddress.getLocalHost().getHostAddress();
		} catch(java.net.UnknownHostException ex){
			ex.printStackTrace();
			throw new ParseException(ex.getMessage(), 0);
		}
		
		StringTokenizer tokenizer = new StringTokenizer(ipConfigResponse);
		String lastMacAddress = null;
		
		while(tokenizer.hasMoreTokens()){
			
			String line = tokenizer.nextToken().trim();
			
			if(line.endsWith(localhost) && lastMacAddress != null){
				return lastMacAddress;
			}
			
			int macAddressPosition = line.indexOf(':');
			if(macAddressPosition <= 0){
				continue;
			}
			
			String macAddressCandidate = line.substring(macAddressPosition);
			if(windowIsMacAddress(macAddressCandidate)){
				lastMacAddress = macAddressCandidate;
				continue;
			}
			//System.out.println(macAddressCandidate + " macAddressCandidate");			
			//System.out.println(lastMacAddress + " last madAddress");			
		}
		
		ParseException ex = new ParseException("Cannot read mac address", 0);
		ex.printStackTrace();
		throw ex;		
	}
	
	/*
	 * not used just for back up -- if it might be needed.
	 */
	private String getMacValueOfComputer(){
		
		String macAddress = "";
		
		InetAddress ip; 
		
		try {
              ip = InetAddress.getLocalHost();
              
              NetworkInterface network = NetworkInterface.getByInetAddress(ip);
              
              byte[] mac = network.getHardwareAddress();
              
              String macAdd = mac.toString();
              
              StringBuilder sb = new StringBuilder();
              
              byte[] m = new byte[macAdd.length()];
              
              for (int i = 0; i < macAdd.length(); i++) {
              		m[i] = (byte) macAdd.charAt(i);
              		sb.append(String.format("%02X%s", m[i], (i < macAdd.length() - 1) ? "-" : ""));
                      //sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));            
              }
              
              macAddress = sb.toString();
              
		} catch (UnknownHostException e) {
              e.printStackTrace();
		} catch (SocketException e){
              e.printStackTrace();
		}
      
		return macAddress;
	}

}
