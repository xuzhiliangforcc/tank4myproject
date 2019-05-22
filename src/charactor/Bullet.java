package charactor;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import datacenter.RealTimeProcess;
import ui.GameMapPanel;
import util.ImgProcessUtil;
import util.Info;
import util.SoundProcessUtil;

/*
 * 子弹类的实现
 */
public class Bullet extends JPanel{
	private static final long serialVersionUID = 1L;
	//子弹方向
	public int direction = 0;
	//是否敌方子弹
	public boolean isEmeny;
	//横纵坐标补偿
	public int xOffSet = 0;
	public int yOffSet = 0;
	//判断子弹相撞
	public boolean bulletCrash = false;
	//子弹是否还存在
	public boolean isMissed = false;
	public Bullet(int direction, int xOffSet, int yOffSet, boolean isEmeny) {
		this.direction = direction;
		//因为坦克图片的方向顺序和这个子弹图片的方向不一样
		switch(direction) {
			case 0:this.direction = 1;break;
			case 1:this.direction = 0;break;
			case 2:this.direction = 3;break;
			case 3:this.direction = 2;break;
		}
		this.xOffSet = xOffSet;
		this.yOffSet = yOffSet;
		this.isMissed = true;
		this.isEmeny = isEmeny;
	}
	public void isCrash(){
		if(isEmeny){
			//敌方坦克击中我方坦克
			if(this.xOffSet == RealTimeProcess.mtc.getMyTankxOffSet()&&this.yOffSet == RealTimeProcess.mtc.getMyTankyOffSet()) {
				//敌方坦克击中我方坦克
				//一系列操作
				//子弹消失
				this.isMissed = false;
				//我方坦克血量减少
				RealTimeProcess.mtc.life--;
				SoundProcessUtil.playVedio3();
				//移除子弹对像
				RealTimeProcess.bullet.remove(this);
				}			
			}
		else {
			//我方坦克击中敌方坦克
			Iterator<EmenyTankCharactor> it = RealTimeProcess.etc.iterator();
			while(it.hasNext()) {
				EmenyTankCharactor et = it.next();
				if(this.xOffSet == et.xSetOff&&this.yOffSet == et.ySetOff) {
					//击中敌方坦克
					this.isMissed = false;
					SoundProcessUtil.playVedio3();
					//生命值减1
					et.life--;
					//移除子弹对像
					RealTimeProcess.bullet.remove(this);
					RealTimeProcess.gmp.maxTankNum--;
					break;
					}
			}
//			//判断是否会撞击到其他的子弹
//			Iterator<Bullet> bit = RealTimeProcess.bullet.iterator();
//			Bullet bullet = null;
//			while(bit.hasNext()) {
//				bullet = bit.next();
//				if(this!=bullet&&this.xOffSet == bullet.xOffSet&&this.yOffSet == bullet.yOffSet) {
//					this.isMissed = false;
//					System.out.println("子弹相撞！");
//					bulletCrash = true;
//					break;
//					}
//				}
//			RealTimeProcess.bullet.remove(this);
//			RealTimeProcess.bullet.remove(bullet);
//			}
		
		}
	}
	//执行调用步进方法
	public void nextStep() {
		switch(direction) {
			case 0:xOffSet++;break;
			case 1:yOffSet--;break;
			case 2:xOffSet--;break;
			case 3:yOffSet++;break;
			}
		if(arrayOutOfBound()){
			}
		else 
			isMissed = false;
	}
	public boolean arrayOutOfBound() {
		if(yOffSet>0&&yOffSet<20&&xOffSet>0&&xOffSet<20)
			return true;
		return false;
	}
	//绘制子弹
	public void drawBullet(Graphics g,JPanel j) {
		isCrash();
		//子弹相撞
		if(!isMissed) {
			g.drawImage(ImgProcessUtil.imageBoom_2, xOffSet*32+5, yOffSet*32+5, (xOffSet+1)*32-5, (yOffSet+1)*32-5, 0, 0, 18, 33, j);
			RealTimeProcess.bullet.remove(this);
		}
		if(isMissed&&arrayOutOfBound()){
			if(RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 0) {
				g.drawImage(ImgProcessUtil.imageBullet, xOffSet*32+10, yOffSet*32+10, (xOffSet+1)*32-10, (yOffSet+1)*32-10, direction*32, 0, (direction+1)*32, 32, j);
				nextStep();
				}
				//考虑砖墙的爆炸效果
			else if(RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 1) {
				g.drawImage(ImgProcessUtil.imageBoom_1, xOffSet*32+5, yOffSet*32+5, (xOffSet+1)*32-5, (yOffSet+1)*32-5, 0, 0, 28, 28, j);
				//使砖墙消失
				RealTimeProcess.readTimeMapData[yOffSet][xOffSet] = 0;
				isMissed = false;
			}
			//考虑到触碰到石墙
			else if(RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 2) {
				//石墙的爆炸效果
				g.drawImage(ImgProcessUtil.imageBoom_2, xOffSet*32+5, yOffSet*32+5, (xOffSet+1)*32-5, (yOffSet+1)*32-5, 0, 0, 64, 64, j);
				if(Info.WUDIFLAG)
					RealTimeProcess.readTimeMapData[yOffSet][xOffSet] = 0;
				isMissed = false;
			}
				//g.drawImage(ImgProcessUtil.imageBullet, xOffSet*32, yOffSet*32, (xOffSet+1)*32, (yOffSet+1)*32, direction*32, 0, (direction+1)*32, 32, j);
				//考虑河流和树木的效果
			else if(RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 3||RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 4||RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 5) {
				g.drawImage(ImgProcessUtil.imageBullet, xOffSet*32+10, yOffSet*32+10, (xOffSet+1)*32-10, (yOffSet+1)*32-10, direction*32, 0, (direction+1)*32, 32, j);
				if(Info.WUDIFLAG)
					RealTimeProcess.readTimeMapData[yOffSet][xOffSet] = 0;
				nextStep();
			}
			//考虑打击到老王的效果并判定游戏结束
			else if(RealTimeProcess.readTimeMapData[yOffSet][xOffSet] == 6) {
				if(Info.WUDIFLAG)
					RealTimeProcess.readTimeMapData[yOffSet][xOffSet] = 0;
				isMissed = false;
				GameMapPanel.gameOver = true;
			}
		}
		
		
	}

}
