package cookie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cookie {
	Map<String,String> cookieItems;
	
	public Cookie() {
		cookieItems = new HashMap<String,String>();
	}
	
	public void update(List<String> cookieList){
		if(cookieList != null){
			for(String cookie : cookieList){
				String[] items = cookie.split(";");
				updateCookie(items[0]);
			}
			
		}
	}
	
	private void updateCookie(String cookieStr) {
		if(cookieStr != null){
			String[] keyValue = cookieStr.split("=");
			if(keyValue.length == 2){
				cookieItems.put(keyValue[0], keyValue[1]);
			}
		}
	}

	public String toString(){
		String cookieStr = "";
		if(cookieItems.size() > 0){
			Iterator<String> iterator = cookieItems.keySet().iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				cookieStr += ";" + key + "=" + cookieItems.get(key);
			}
			cookieStr = cookieStr.substring(1);
		}
		System.out.println("[NOTE]: " + cookieStr);
		return cookieStr;
	}
}
