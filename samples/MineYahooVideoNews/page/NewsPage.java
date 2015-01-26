package page;

import java.util.List;

import file.Writer;

import net.HttpPage;

import reg.Reg;

public class NewsPage {
	public static String TEXT_SAVE_PATH = "";
	
	private final String VIDEO_REG_STR = "data-vid=\"[^\"]+";
	private final String CODE_MODE = "euc-jp";
	private String url_str;
	
	private String page_content;
	
	public void setUrl(String url){
		url_str = url;
	}
	
	/*
	 * save text
	 */
	private void loadText(){
		HttpPage.CODE_MODE = CODE_MODE;
		page_content = HttpPage.getPageSource(url_str);
		
		//get text
		String startString = "yjS ymuiDate\">";
		String endString   = "</div>";
		int startIndex = page_content.indexOf(startString) + startString.length();
		int endIndex = page_content.indexOf(endString,startIndex);
		String text = page_content.substring(startIndex,endIndex);
		
		//replace </p>
		text = text.replace("</p>", "[ENTER]");
		
		//replace <br>
		text = text.replaceAll("<br\\s*[/]?\\s*>", "[ENTER]");
		
		//remove &1234;
		text = text.replaceAll("&.{4};","");
		
		//remove <*>
		text = text.replaceAll("<[^>]+>", "");
		
		//remove enter key
		text = text.replace("\n","");
		
		System.out.println(text);
		
		Writer.write(text.getBytes(), TEXT_SAVE_PATH);
	}
	
	public String getVideoId(){
		loadText();
		
		String videoId = "";
		List<String> matchList = Reg.matches(VIDEO_REG_STR, page_content);
		videoId = matchList.get(0).substring(10);
		return videoId;
	}
}
