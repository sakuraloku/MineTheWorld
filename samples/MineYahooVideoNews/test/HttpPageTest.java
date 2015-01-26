package test;

import net.HttpPage;

public class HttpPageTest {
	public static void main(String[] args) {
		
		//utf-8
//		String urlStr = "http://news.yahoo.co.jp/pickup/6145985";
//		System.out.println(HttpPage.getPageSource(urlStr));
		
		//euc-jp
		String urlStr = "http://headlines.yahoo.co.jp/videonews/";
		HttpPage.CODE_MODE = "euc-jp";
		System.out.println(HttpPage.getPageSource(urlStr));
	}
}
