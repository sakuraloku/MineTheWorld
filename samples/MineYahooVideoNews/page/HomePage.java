package page;

import java.util.ArrayList;
import java.util.List;

import reg.Reg;

import net.HttpPage;

public class HomePage {
	private final String REG_STR = "padTB4\">[^>]+";
	private final String CODE_MODE = "euc-jp";
	private final String URL_STR = "http://headlines.yahoo.co.jp/videonews/";
	
	public List<String> getNewsPageList(){
		List<String> newsPageList = new ArrayList<String>();
		
		HttpPage.CODE_MODE = CODE_MODE;
		String content = HttpPage.getPageSource(URL_STR);
		
		List<String> matchList = Reg.matches(REG_STR, content);
		
		for(String matchStr : matchList){
			int startIndex = matchStr.indexOf('"', 9);
			newsPageList.add(matchStr.substring(startIndex+1, matchStr.length()-1));
		}
		
		return newsPageList;
	}
}
