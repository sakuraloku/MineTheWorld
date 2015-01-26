package test;


import page.NewsPage;

public class NewsPageTest {
	public static void main(String[] args) {
		
		String url = "http://headlines.yahoo.co.jp/videonews/fnn?a=20150115-00000418-fnn-int";
		String text_save_path = "d:\\text.txt";
		NewsPage newsPage = new NewsPage();
		newsPage.setUrl(url);
		NewsPage.TEXT_SAVE_PATH = text_save_path;
		System.out.println(newsPage.getVideoId());
	}
}
