package proxys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProxyManager {
	
	private List<String> proxyList;
	private int index;//index point to next proxy in proxyList
	
	private final String NOTE = "[NOTE]";
	private final String OWNER = "ProxyManager";
	
	public ProxyManager() {
		proxyList = new ArrayList<String>();
		index = 0;
	}
	
	public void setProxyList(List<String> proxys){
		proxyList = proxys;
	}
	
	// give index a random number
	public void random(){
		if(proxyList.size() > 0){
			Random random = new Random();
			index = random.nextInt(proxyList.size());
		}
	}
	
	public void setSystemProxy(){
		if(proxyList.size() <= 0){
			//clear proxy
			System.setProperty("http.proxyHost", "");
			System.setProperty("http.proxyPort", "");
			
			System.out.println(NOTE+OWNER+" no proxy");
		}else{
			//get proxy
			String proxy = proxyList.get(index++);
			String ip = proxy.substring(0, proxy.indexOf(":"));
			String port = proxy.substring(proxy.indexOf(":")+1);
			
			//set proxy
			System.setProperty("http.proxyHost", ip);
			System.setProperty("http.proxyPort", port);
			
			System.out.println(NOTE+OWNER+" set proxy '" + proxy + "'");
			
			if(index >= proxyList.size()){
				index -= proxyList.size();
			}
		}
	}
}
