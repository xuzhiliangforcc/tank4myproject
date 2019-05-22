package charactor;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import datacenter.RealTimeProcess;
import util.ImgProcessUtil;
import util.Info;
/*
 * 某些特殊buff
 */
public class Buff extends JPanel{
	private static final long serialVersionUID = 1L;
	public int type = 0;
	public int xOffSet = 0;
	public int yOffSet = 0;
	public long createTime = System.currentTimeMillis();
	public Buff( int xOffSet, int yOffSet, int type) {
		this.type = type;
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
	}
	public void drawBuff(Graphics g,JPanel j) {
		if(System.currentTimeMillis()-createTime < Info.BUFFTIME) {
			//buff显示时长
			g.drawImage(ImgProcessUtil.imageBuff, xOffSet*32, yOffSet*32, 32*(xOffSet+1), 32*(yOffSet+1), type*32, 0, (type+1)*32, 32, j);
			eatBuff();
			}else{
			//自动消失
		}
	}
	//吃到buff的执行策略
	public void eatBuff() {
		if(this.xOffSet == RealTimeProcess.mtc.getMyTankxOffSet()&&this.yOffSet == RealTimeProcess.mtc.getMyTankyOffSet()) {
			//让buff消失
			createTime-=Info.BUFFTIME;
			//我方坦克吃到了buff
			switch(type) {
			//level up
			case 0:
				System.out.println("吃到了level up的buff！！！");
				RealTimeProcess.mtc.life++;
				RealTimeProcess.mtc.rank++;
				break;
			case 1:
				//stop
				System.out.println("吃到了stop的buff！！！");
				//让所有坦克停止，8s后恢复移动
				RealTimeProcess.gmp.allStop = true;
				Timer t = new Timer();
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						RealTimeProcess.gmp.allStop = false;
					}
				}, Info.STOPTIME);
				break;
			case 2:
				break;
			case 3:
				//敌方目前场上坦克全部爆炸
				//迭代消除集合中的坦克
				Iterator<EmenyTankCharactor> it = RealTimeProcess.etc.iterator();
				while(it.hasNext()) {
					EmenyTankCharactor et = it.next();
					et.life = 0;
					}
				break;
			case 4:
				break;
			case 5:
				//无敌状态
				int tmp = RealTimeProcess.mtc.life;
				RealTimeProcess.mtc.life+=10000000;
				t = new Timer();
				t.schedule(new TimerTask() {
					@Override
					public void run() {
						RealTimeProcess.mtc.life = tmp;
					}
				}, Info.NOONEELSECANKILLME);
				break;
				
			}
		}
	}

	
	
	
	
}
