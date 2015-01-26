package program;

import java.io.File;
import java.util.List;

import file.Writer;

import page.HomePage;
import page.NewsPage;
import page.VideoPage;

public class Program {
	
	public static void main(String[] args) {
		
		String batPath = "click_yahoo.bat";
		String nextJava = "java program.NewsPageProgram";
		String rootDir = "D:/YahooNews";
		
		if(args.length > 0){
			rootDir = args[0];
		}
		
		File root = new File(rootDir);
		if(!root.exists()){
			root.mkdirs();
		}
		
		HomePage homePage = new HomePage();
		List<String> newsPageList = homePage.getNewsPageList();
		
		String batContent = "";
		
		for(String newsPageUrl : newsPageList){
			int startIndex = newsPageUrl.indexOf('=') + 1;
			String newsName = newsPageUrl.substring(startIndex);
			String dirName = newsPageUrl.substring(startIndex, newsPageUrl.indexOf("-",startIndex));
			
			//create directory
			File dir = new File(rootDir + "/" + dirName);
			if(!dir.exists()){
				dir.mkdir();
			}
			File subDir = new File(rootDir + "/" + dirName + '/' + newsName);
			if(!subDir.exists()){
				subDir.mkdir();
			}
			
			File file = new File(rootDir + "/" + dirName + '/' + newsName + "/text.txt");
			if(file.exists()){
				continue;
			}
			
			NewsPage newsPage = new NewsPage();
			NewsPage.TEXT_SAVE_PATH = rootDir + "/" + dirName + '/' + newsName + "/text.txt";
			newsPage.setUrl(newsPageUrl);
			String videoId = newsPage.getVideoId();
			
			VideoPage videoPage = new VideoPage();
			String videoUrl = videoPage.getVideoUrl(videoId);
			int videoEndIndex = videoUrl.indexOf('?');
			int videoStartIndex = videoUrl.lastIndexOf('/',videoEndIndex) + 1;
			videoUrl = videoUrl.substring(videoStartIndex , videoEndIndex);
			
			batContent += nextJava + " \"" + videoId + "\" " + newsName + System.lineSeparator();
			batContent += "FOR /F %%i in (c:/" + newsName + ") do (" + System.lineSeparator();
			batContent += "\twget %%i" + System.lineSeparator();
			batContent += ")" + System.lineSeparator();
			batContent += "move " + videoUrl + "* " + rootDir + "/" + dirName + '/' + newsName + "/video.mp4" + System.lineSeparator();
		}
		
		Writer.write(batContent.getBytes(), batPath);
	}
}
