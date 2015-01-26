package test;


import page.NewsPage;
import structure.News;

public class NewsPageTest {
	public static void main(String[] args) {
		
		String url = "http://headlines.yahoo.co.jp/hl?a=20150120-00000080-spnannex-spo";

		NewsPage newsPage = new NewsPage();
		News news = newsPage.getNews(url);
		System.out.println(news.getTitle());
		System.out.println(news.getText());
	}
}
