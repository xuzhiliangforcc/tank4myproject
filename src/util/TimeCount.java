package util;
import ui.GameMapPanel;
/*
 * 计时线程
 */
public class TimeCount implements Runnable{
	private static TimeCount tc = null;
	public static TimeCount getTimeCount() {
		if(tc==null)
			tc = new TimeCount();
		return tc;
	}
	public static long now = 0l;
	public long history = 0l;
	private TimeCount(){
		history = System.currentTimeMillis();
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		while(!GameMapPanel.gameOver) {
			now = System.currentTimeMillis()-history;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
