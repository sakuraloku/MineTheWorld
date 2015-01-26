package page;

import java.util.ArrayList;
import java.util.List;

import reg.Reg;

import net.HttpPage;

public class HomePage {
	private final String REG_STR = "hl\\?a[^\"]+";
	private final String CODE_MODE = "utf8";
	private final String URL_STR = "http://news.yahoo.co.jp/";
	
	private final String URL_PRE = "http://headlines.yahoo.co.jp/";
	
	public List<String> getNewsPageList(){
		List<String> newsPageList = new ArrayList<String>();
		
		HttpPage.CODE_MODE = CODE_MODE;
		String content = HttpPage.getPageSource(URL_STR);
		
//		System.out.println(content);
		
		List<String> matchList = Reg.matches(REG_STR, content);
		
		for(String matchStr : matchList){
			if(!matchStr.endsWith("'")){
				newsPageList.add(URL_PRE + matchStr);	
			}
		}
		
		return newsPageList;
	}
}
