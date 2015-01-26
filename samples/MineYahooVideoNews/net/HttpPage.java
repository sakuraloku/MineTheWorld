package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HttpPage {
	public static String CODE_MODE = "utf8";
	
	public static String getPageSource(String url_str){
		byte[] data = getPageRawSource(url_str);
		
		if(data == null){
			return "";
		}else{
			try {
				return new String(data,CODE_MODE);
			} catch (UnsupportedEncodingException e) {
				System.out.println("[ERROR]: Parse data error");
				return "";
			}
		}
	}
	
	public static byte[] getPageRawSource(String url_str){
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
		HttpURLConnection httpurlconnection;
		try{
			httpurlconnection = (HttpURLConnection)url.openConnection();
			httpurlconnection.addRequestProperty("Referer", url_str);
			httpurlconnection.connect();
		}catch(IOException e){
			System.out.println("[ERROR]: Connect to " + url_str + " failed");
			return null;
		}
			
		// get data
		try{
			int responsecode = 0;
			responsecode = httpurlconnection.getResponseCode();
			if(responsecode == HttpURLConnection.HTTP_OK){
				InputStream is = httpurlconnection.getInputStream();
				
				List<Byte> dataList = new ArrayList<Byte>();
				byte buf;
				while((buf = (byte) is.read()) != -1){
					dataList.add(buf);
				}
				byte[] byteListData = new byte[dataList.size()];
				
				int i = 0;
				for(Byte data : dataList){
					byteListData[i++] = data;
				}
				
				ret = byteListData;
			}   
			if (httpurlconnection != null)
				httpurlconnection.disconnect();
		}catch(IOException e){
			System.out.println("[ERROR]: Fetch data from " + url_str + " failed");
			return null;
		}
		return ret;
	}
}
