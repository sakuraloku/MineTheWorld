package proxys;
/*
 * author: sakuraloku
 * 
 * fetch html page such as http://www.proxy360.net/guonei/1
 * and parse to Proxy Information,
 * at last return as list
 */
import java.util.ArrayList;
import java.util.List;

import net.HttpPage;
import reg.Reg;

public class Proxy360dotNet implements IProxys {
	private List<String> proxyList;
	private int maxParsePageCount;
	
	public Proxy360dotNet() {
		proxyList = new ArrayList<String>();
		maxParsePageCount = 20;
	}

	public List<String> getProxys() {
		// TODO Auto-generated method stub
		return proxyList;
	}

	public void loadProxysFromInternet() {
		// TODO Auto-generated method stub
		proxyList = new ArrayList<String>();
		_load_proxys();
	}
	
	private void _load_proxys(){
		String httpPage;//
		String ip_field_start_str = "<td><span>";
		String ip_field_end_str   = "</span></td>";
		String port_field_start_str = "<td>";
		String port_field_end_str   = "</td>";
		
		String ip , port;
		int page = 0;

		while(true){
			page++;
			if(page > maxParsePageCount)break;
			
			httpPage = HttpPage.getPageSource("http://www.proxy360.net/guonei/" + page);
			System.out.println("[NOTE] Fetching proxy information from page " + page);
			
			//fetch proxy from one page
			int ip_field_start_index , ip_field_end_index , port_field_start_index , port_field_end_index = 0;
			while(true){
				ip_field_start_index = httpPage.indexOf(ip_field_start_str , port_field_end_index);
				if(-1 == ip_field_start_index)break;
				
				ip_field_end_index = httpPage.indexOf(ip_field_end_str, ip_field_start_index);
				ip = parseIPField(httpPage.substring(ip_field_start_index, ip_field_end_index));
				
				port_field_start_index = httpPage.indexOf(port_field_start_str , ip_field_end_index);
				port_field_end_index = httpPage.indexOf(port_field_end_str , port_field_start_index);
				
				port = parsePortField(httpPage.substring(port_field_start_index,port_field_end_index));
				
				proxyList.add(ip + ":" + port);
			}
			
			//if no proxy is fetched from this page, then break
			if(0 == port_field_end_index){
				break;
			}
		}
//		log("proxys:",proxyList);
	}
	
	private String parsePortField(String PortField){
		int len = PortField.length();
		String port = "";
		for(int i = 0; i < len; i++){
			char ch = PortField.charAt(i);
			if(ch >= '0' && ch <= '9'){
				port += ch;
			}
		}
		return port;
	}
	
	private String parseIPField(String IPField){
		String ip = "";
		
		//deal style section
		int start_of_style = IPField.indexOf("style");
		int end_of_style   = IPField.indexOf("style",start_of_style+1);
		String CLASS_REG_STR = "\\.[^\\{]+\\{[^\\}]+\\}";
		List<String> style_list = Reg.matches(CLASS_REG_STR, IPField.subSequence(start_of_style, end_of_style));
		//log("styles:",style_list);
		
		//replace class name
		for(int i = 0; i < style_list.size(); i++){
			String style = style_list.get(i);
			IPField = IPField.replace(style.substring(1, 5), style.substring(6, style.length()-1));
		}

		//replace none
		IPField = IPField.replaceAll("none\">", "");
		//System.out.println(IPField);
		
		//get IP information
		String IP_INFO_REG_STR = ">[0-9\\.]+<";
		List<String> list_ip_info = Reg.matches(IP_INFO_REG_STR, IPField);
		
		//produce IP
		for(int i = 0; i < list_ip_info.size(); i++){
			String ip_info = list_ip_info.get(i);
			ip += ip_info.substring(1, ip_info.length()-1);
		}
//		System.out.println(ip);
		return ip;
	}
	
//	private void log(String text,List<String> list){
//		System.out.println(text);
//		int count = list.size();
//		for(int i = 0; i < count; i++){
//			System.out.println(list.get(i));
//		}
//	}
	
	public static void main(String[] args) {
		/*
		 * parseIPField test
		 */
		/*
		Proxy360dotNet proxy = new Proxy360dotNet();
		String IPField = "<td><span><style> .lpr8{display:none} .QWRt{display:inline} .bHJi{display:none} .BwLs{display:inline} .Q23O{display:none} .p6_G{display:inline} </style><span class=\"QWRt\">183</span>.<span style=\"display:none\">6</span><span class=\"bHJi\">6</span><span></span><span style=\"display:none\">12</span><span class=\"bHJi\">12</span><div style=\"display:none\">12</div>207.228<span style=\"display: inline\">.</span><span class=\"88\">9</span><span style=\"display:none\">15</span><span class=\"bHJi\">15</span><span></span><span style=\"display:none\">27</span><span class=\"bHJi\">27</span><div style=\"display:none\">27</div><span style=\"display:none\">75</span><span class=\"Q23O\">75</span><span class=\"lpr8\">87</span><div style=\"display:none\">87</div><span style=\"display:none\">103</span><span class=\"lpr8\">103</span><span></span><span style=\"display:none\">141</span><span class=\"lpr8\">141</span><div style=\"display:none\">141</div><span style=\"display:none\">170</span><span class=\"bHJi\">170</span><div style=\"display:none\">170</div><span style=\"display:none\">172</span><span></span><span></span><span class=\"bHJi\">189</span><span style=\"display:none\">204</span><span class=\"bHJi\">204</span><span></span></span></td>";
		proxy.parseIPField(IPField);
		*/
		System.setProperty("http.proxyHost","10.10.10.78");
		System.setProperty("http.proxyPort","8080");
		Proxy360dotNet proxy = new Proxy360dotNet();
		proxy.setMaxParsePageCount(2);
		proxy.loadProxysFromInternet();
	}

	public void setMaxParsePageCount(int page_count) {
		// TODO Auto-generated method stub
		maxParsePageCount = page_count;
	}
}
