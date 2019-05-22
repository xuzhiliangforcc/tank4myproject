package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import sun.audio.AudioPlayer;
public class SoundProcessUtil {
		public static void playVedio(){
			//微信音效
			File file = new File("music\\eod3a-1qa1u.wav.crdownload");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				AudioPlayer.player.start(fileInputStream);
			} catch (FileNotFoundException e) {
		
				e.printStackTrace();
			}
		}
		//开场王者音效
		public static void playVedio1(){
		File file = new File("music\\1ltf2-cbpfl.wav");
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			AudioPlayer.player.start(fileInputStream);
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		}
	}
		public static void playVedio3(){
			//刀剑
			File file = new File("music\\zyzvs-s2iqf.wav");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				AudioPlayer.player.start(fileInputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		public static void playVedio4(){
			//打到敌人音效
			File file = new File("music\\zvyhm-bsfbz.wav");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				AudioPlayer.player.start(fileInputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//炸弹爆炸音效
		public static void playVedio5(){
			//打到敌人音效
			File file = new File("music\\tkt54-mh8s0.wav");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				AudioPlayer.player.start(fileInputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		//游戏结束
		public static void playVedio6(){
			File file = new File("music\\GAMEOVER.wav");
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				AudioPlayer.player.start(fileInputStream);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
}
