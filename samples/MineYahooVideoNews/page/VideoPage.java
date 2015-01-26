package page;

import net.HttpPage;

public class VideoPage {
	public static String VIDEO_SAVE_PATH = "";
	
	private final String CODE_MODE = "utf8";
	private final String url_str_pre = "http://player.glair.yahooapis.jp/GlairWebService/V1/video?vid=";
	
	public String getVideoUrl(String videoId){
		HttpPage.CODE_MODE = CODE_MODE;
		String content = HttpPage.getPageSource(url_str_pre + videoId);
//		System.out.println(content);
		
		String videoUrl = getBestVideoUrl(content);
		System.out.println(videoUrl);
		
		return videoUrl;
//		Writer.write(HttpPage.getPageRawSource(videoUrl),VIDEO_SAVE_PATH); 
	}

	private String getBestVideoUrl(String content) {
		String bestVideoUrl = "";
		
		String urlStartString = "<FileURL>";
		String urlEndString = "<";
		String rateStartString = "<Bitrate>";
		String rateEndString = "<";
		
		int urlStartIndex,urlEndIndex,rateStartIndex,rateEndIndex = 0;
		int maxRate = 0 , rate;
		while(true){
			urlStartIndex = content.indexOf(urlStartString, rateEndIndex);
			if(-1 == urlStartIndex)break;
			
			urlStartIndex += urlStartString.length();
			urlEndIndex = content.indexOf(urlEndString, urlStartIndex);
			
			rateStartIndex = content.indexOf(rateStartString, urlEndIndex) + rateStartString.length();
			rateEndIndex = content.indexOf(rateEndString, rateStartIndex);
			
			rate = Integer.parseInt(content.substring(rateStartIndex, rateEndIndex));
//			System.out.println(rate);
			if(rate > maxRate){
				bestVideoUrl = content.substring(urlStartIndex, urlEndIndex);
				maxRate = rate;
			}
		}
		return bestVideoUrl;
	}
}
