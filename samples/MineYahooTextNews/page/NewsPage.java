package page;

import structure.News;
import net.HttpPage;


public class NewsPage {
	
	private final String CODE_MODE = "euc-jp";

	public News getNews(String url){
		News news = new News();
		
		HttpPage.CODE_MODE = CODE_MODE;
		String page_content = HttpPage.getPageSource(url);
		
		//get title
		String titleStartString = "yjXL\">";
		int titleStartIndex = page_content.indexOf(titleStartString) + titleStartString.length();
		int titleEndIndex = page_content.indexOf('<', titleStartIndex);
		String title = page_content.substring(titleStartIndex, titleEndIndex);
		
		//get text
		String text = "";
		String startString = "ynDetailText\">";
		String endString   = "</div>";
		int startIndex ,endIndex = titleEndIndex;
		
		while(true){
			startIndex = page_content.indexOf(startString,endIndex);
			if(startIndex == -1){
				break;
			}
			
			startIndex += startString.length();
			endIndex = page_content.indexOf(endString,startIndex);
			text += page_content.substring(startIndex,endIndex);
		}
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
		
		news.setTitle(title);
		news.setText(text);
		return news;
	}
	
}
