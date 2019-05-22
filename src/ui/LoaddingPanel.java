package ui;
/*
 * 打开游戏界面的缓冲动画效果
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import javax.swing.JPanel;
import datacenter.RealTimeProcess;
import util.ImgProcessUtil;
public class LoaddingPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	public boolean isEnd = false;
	Iterator<Image> it  = null;
	public LoaddingPanel() {
		new Thread(this).start();
		it  = ImgProcessUtil.loadding.iterator();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//基本设置
		this.setLayout(null);
		this.setBounds(0, 0, 900, 702);
		//设置背景色
		this.setBackground(Color.black);
		//设置窗口大小
		//g.fillRect(Info.GAMEMAPPANEL_X, Info.GAMEMAPPANEL_Y, Info.GAMEMAPPANEL_X_1, Info.GAMEMAPPANEL_Y_1);
		if(it.hasNext()) {
			Image image = it.next();
			g.drawImage(image, 0, 0, 900, 702, 0,0,400,300, this);
		}
		else
			isEnd = true;
	}
	@Override
	public void run() {
		while(!isEnd) {
			this.repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		RealTimeProcess.bf.remove(this);
		BeganFrame.isStart = true;
		RealTimeProcess.bf.init();
	}
}

	



