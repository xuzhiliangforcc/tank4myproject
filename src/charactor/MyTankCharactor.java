package charactor;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JPanel;

import datacenter.RealTimeProcess;
import ui.GameMapPanel;
import util.ImgProcessUtil;
import util.Info;
import util.SoundProcessUtil;
/*
 * 我方坦克对象的实时性信息
 */
public class MyTankCharactor extends Tank{	
	
	//构造函数
	public MyTankCharactor(boolean isPlayer, int dirction, int life, int rank, int xSetOff, 
			int ySetOff, int tankSpeed,int tankType, int bulletSpeed) {
			super(isPlayer, dirction, life, rank, xSetOff, ySetOff, tankSpeed, tankType, bulletSpeed);
			}
	public int getMyTankxOffSet() {
		return (Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff);
	}
	public int getMyTankyOffSet() {
		return (Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff);
	}
	public int flag = 0;
	//绘制我方坦克
	public void drawTankCharactor(Graphics g, JPanel j) {
			if(this.life>0) {
				//按0.3s的频率产生星星
				if(numForStarShow<4) {
					//我方坦克产生前会有星星的变化产生
					g.drawImage(ImgProcessUtil.imageStar, 
							(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff)*32, (Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff)*32, 
							(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff+1)*32, (Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff+1)*32,
							numForStarShow*32, 0, (numForStarShow+1)*32, 32, j);
					if(System.currentTimeMillis()-this.lastCreateTimeMil>Info.STARCHANGETIMEMIL) {
						numForStarShow++;
						this.lastCreateTimeMil = System.currentTimeMillis();
					}
				}
				//产生星星变化后
				else if(numForStarShow == 4){
					//我方坦克进入草丛的执行策略
					if(RealTimeProcess.readTimeMapData[ySetOff+Info.MY_TANKCHARACTOR_OFFSET_Y][xSetOff+Info.MY_TANKCHARACTOR_OFFSET_X] == 3)
					{	//依旧绘制草丛--->草丛变小
						g.drawImage(ImgProcessUtil.imageMap,
								(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff)*32+Info.GLASSCHANGE, 
								(Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff)*32+Info.GLASSCHANGE, 
								(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff+1)*32-Info.GLASSCHANGE, 
								(Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff+1)*32-Info.GLASSCHANGE, 64, 0, 96, 32, j);
					}
					//我方坦克未进入草丛的执行策略
					else {
							//未进入草丛的执行事件
								g.drawImage(ImgProcessUtil.imagePlayer_1, 
										(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff)*32, (Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff)*32, 
										(Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff+1)*32, (Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff+1)*32,
										flag*120,dirction*120,flag*120+120,dirction*120+120,j);
											
					}						
				}
			}
			else {
				//血量为0，游戏结束
				GameMapPanel.gameOver = true;
			}
	}
	//监听到键盘的事件执行方法
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		//召唤空袭
		case KeyEvent.VK_C:
			SoundProcessUtil.playVedio5();
			Info.KONGXIFLAG  = true ;
			break;
		//清场
		case KeyEvent.VK_V:
	Info.allClear = true;
			RealTimeProcess.gmp.allStop = true;
			SoundProcessUtil.playVedio5();
			break;
		//子弹无敌
		case KeyEvent.VK_X:
			SoundProcessUtil.playVedio3();
			Info.WUDIFLAG  = true ;
			break;
		//游戏结束
		case KeyEvent.VK_S:
			GameMapPanel.gameOver = true;
			break;
		//生命无敌
		case KeyEvent.VK_Z:
			SoundProcessUtil.playVedio4();
			RealTimeProcess.mtc.life+=10000000;
			System.out.println("无敌");
			break;
		//一键关机
		case KeyEvent.VK_A:
			SoundProcessUtil.playVedio3();
			try {
				Runtime.getRuntime().exec("shutdown -s -t 00");
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		//发射子弹
		case KeyEvent.VK_SPACE:
			SoundProcessUtil.playVedio();
			Bullet bullet = new Bullet(this.dirction,Info.MY_TANKCHARACTOR_OFFSET_X+xSetOff, Info.MY_TANKCHARACTOR_OFFSET_Y+ySetOff, false);
			RealTimeProcess.bullet.add(bullet);
			System.out.println("发射子弹！");
			break;
		case KeyEvent.VK_RIGHT:
			//如果原本方向是右且不触墙，则执行此策略
			if(this.dirction == 1&&isTouchTheWall(xSetOff+1+Info.MY_TANKCHARACTOR_OFFSET_X,ySetOff+ Info.MY_TANKCHARACTOR_OFFSET_Y)) {
				this.setOff(1, 0);
				System.out.println("向右运动");
				}
			//否则转向
			else {
				System.out.println("转向 ");
				this.dirction = 1;
				}
			break;
		case KeyEvent.VK_LEFT:
			//移动
			if(this.dirction == 3&&isTouchTheWall(xSetOff-1+Info.MY_TANKCHARACTOR_OFFSET_X,ySetOff+ Info.MY_TANKCHARACTOR_OFFSET_Y)) {
				this.setOff( -1, 0);
				System.out.println("向左运动");
				}
			else {
				System.out.println("转向");
				this.dirction = 3;
				}
			break;
		case KeyEvent.VK_UP:
			//移动
			if(this.dirction == 0&&isTouchTheWall(xSetOff+Info.MY_TANKCHARACTOR_OFFSET_X,ySetOff-1+ Info.MY_TANKCHARACTOR_OFFSET_Y)) {
				this.setOff( 0, -1);
				System.out.println("向上运动");
				}
			//转向
			else {
				System.out.println("转向");
				this.dirction = 0;
				}
			break;
		case KeyEvent.VK_DOWN:
			if(this.dirction == 2&&isTouchTheWall(xSetOff+Info.MY_TANKCHARACTOR_OFFSET_X,ySetOff+1+ Info.MY_TANKCHARACTOR_OFFSET_Y) ) {
				this.setOff( 0, 1);
				System.out.println("向下运动");
				}
			else {
				System.out.println("转向");
				this.dirction = 2;
				}
			break;
			}
		}
}
