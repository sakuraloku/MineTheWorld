package program;

import java.io.File;
import java.util.List;

import code.Code;

import file.Writer;

import page.HomePage;
import page.NewsPage;
import structure.News;

public class Program {
	
	public static void main(String[] args) {
		
		String rootDir = "D:/YahooNewsView";
		
		if(args.length > 0){
			rootDir = args[0];
		}
		
		File root = new File(rootDir);
		if(!root.exists()){
			root.mkdirs();
		}
		
		HomePage homePage = new HomePage();
		List<String> newsPageList = homePage.getNewsPageList();
		
		for(String newsPageUrl : newsPageList){
			int startIndex = newsPageUrl.indexOf('=') + 1;
			String newsName = newsPageUrl.substring(startIndex);
			
			//create directory
			File dir = new File(rootDir + "/" + newsName);
			if(!dir.exists()){
				dir.mkdir();
			}else{
				continue;
			}
			
			NewsPage newsPage = new NewsPage();
			News news = newsPage.getNews(newsPageUrl);
			
			Writer.write(Code.encode(news.getTitle().getBytes()), rootDir + "/" + newsName + "/title.txt");
			Writer.write(Code.encode(news.getText().getBytes()), rootDir + "/" + newsName + "/text.txt");
			
		}
	}
}
