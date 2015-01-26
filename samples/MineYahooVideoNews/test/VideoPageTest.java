package test;


import page.VideoPage;

public class VideoPageTest {
	public static void main(String[] args) {
		
		String videoId = "1373507,1373508";
		String video_save_path = "D:\\video.mp4";
		VideoPage videoPage = new VideoPage();
		VideoPage.VIDEO_SAVE_PATH = video_save_path;
		videoPage.getVideoUrl(videoId);
		
	}
}
