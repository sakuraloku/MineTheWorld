package test;

import java.util.List;

import page.HomePage;

public class HomePageTest {
	public static void main(String[] args) {
		
		HomePage homePage = new HomePage();
		List<String> newsPageList = homePage.getNewsPageList();
		
		for(String newsPage : newsPageList){
			System.out.println(newsPage);
		}
	}
}
