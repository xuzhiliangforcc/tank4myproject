package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import util.ImgProcessUtil;

public class GameControlPanel extends JPanel implements Runnable,MouseListener,MouseMotionListener{
	public int index = 0;
	public GameControlPanel() {
		this.addMouseListener(this);
		new Thread(this).start();
	}
	private static final long serialVersionUID = 4513772526868511441L;
	@Override
	//绘制paintComponent控件
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBounds(800, 0, 900, 702);
		g.drawImage(ImgProcessUtil.imageControl, 0, 0, 100, 50, 0, 0, 128, 86, this);
		g.drawImage(ImgProcessUtil.imageControl, 0, 50, 100, 100, 230, 0, 335, 86, this);
		g.drawImage(ImgProcessUtil.imageControl, 0, 100, 100, 150, 325, 0, 460, 86, this);
		g.drawImage(ImgProcessUtil.imageControl, 0, 150, 100, 200, 440, 0, 530, 86, this);
		g.drawImage(ImgProcessUtil.timgU0JZ7HGP.get(index), 0, 200, 100, 250, 0, 0, 990, 350, this);
	}
	//更新paintComponent控件内容
	@Override
	public void run() {
		while(true) {
			index = (index+1)%11;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	//下面都没有用上
	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("鼠标拖动");
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		System.out.println("鼠标移动");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
}
