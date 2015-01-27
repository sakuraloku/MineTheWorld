package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cookie.Cookie;


public class HttpPage {
	public static String CODE_MODE = "utf-8";
	public static Cookie cookie = new Cookie();
	
	public static String getPageSource(String urlStr){
		return getPageSource(urlStr, null);
	}
	
	public static String getPageSource(String url_str, Map<String,String> headProperty){
		byte[] data = getPageRawSource(url_str, headProperty);
		
		if(data == null){
			return "";
		}else{
			String defaultCodeStr = "";
			try {
				defaultCodeStr = new String(data,CODE_MODE).toLowerCase();
				int charsetIndex = defaultCodeStr.indexOf("charset");
				/*
				 * sample ****charset***=["]_____"*****
				 */
				if(charsetIndex >= 0){
					int codeModeStartIndex = defaultCodeStr.indexOf('=', charsetIndex) + 1;
					int codeModeEndIndex = defaultCodeStr.indexOf('"', codeModeStartIndex + 1);// find the back "
					String codeMode = defaultCodeStr.substring(codeModeStartIndex, codeModeEndIndex).replace("\"", "");// remove front "
					if(!codeMode.equals(CODE_MODE)){
						defaultCodeStr = new String(data,codeMode);
					}
				}
				
				return defaultCodeStr;
			} catch (UnsupportedEncodingException e) {
				System.out.println("[ERROR]: Parse data error");
				return defaultCodeStr;
			}
		}
	}
	
	public static byte[] getPageRawSource(String urlStr){
		return getPageRawSource(urlStr,null);
	}
	
	public static byte[] getPageRawSource(String url_str, Map<String,String> headProperty){
		byte[] ret = null;
        
        //change string to URL
		URL url;
		try{
			url = new URL(url_str);
		}catch(MalformedURLException e){
			System.out.println("[ERROR]: Bad URL String");
			return null;
		}
		
		// connect
		HttpURLConnection connection;
		try{
			connection = (HttpURLConnection)url.openConnection();
			
			// add cookie
			connection.addRequestProperty("Cookie", cookie.toString());
			
			// add head property
			if(headProperty != null){
				Iterator<String> keyIterator = headProperty.keySet().iterator();
				while(keyIterator.hasNext()){
					String key = keyIterator.next();
					connection.addRequestProperty(key, headProperty.get(key));
				}
			}
			
			System.out.println("[NOTE]: Cookie : " + connection.getHeaderField("Cookie"));
			
			connection.connect();
		}catch(IOException e){
			System.out.println("[ERROR]: Connect to " + url_str + " failed");
			return null;
		}
			
		// get data
		try{
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				InputStream is = connection.getInputStream();
				
				List<Byte> dataList = new ArrayList<Byte>();
				byte buf;
				while((buf = (byte) is.read()) != -1){
					dataList.add(buf);
				}
				byte[] byteArr = new byte[dataList.size()];
				
				int i = 0;
				for(Byte data : dataList){
					byteArr[i++] = data;
				}
				
				ret = byteArr;
			}
			
			// update cookie
			cookie.update(connection.getHeaderFields().get("Set-Cookie"));
			
			if (connection != null){
				connection.disconnect();
			}
		}catch(IOException e){
			System.out.println("[ERROR]: Fetching data from " + url_str + " failed");
			return null;
		}
		return ret;
	}
}
