package charactor;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import datacenter.RealTimeProcess;
import util.ImgProcessUtil;
import util.Info;
/*
 * 敌方坦克对象
 */
public class EmenyTankCharactor extends Tank implements Runnable{
	private long nowMoveTime = 0l;
	public EmenyTankCharactor(boolean isPlayerFlag, int dirction, int life, int rank, int xSetOff, int ySetOff,
			int tankSpeed, int tankType, int bulletSpeed) {
		super(isPlayerFlag, dirction, life, rank, xSetOff, ySetOff, tankSpeed, tankType, bulletSpeed);
		new Thread(this).start();
	}
	//敌方坦克的绘制
	@Override
	public void drawTankCharactor(Graphics g, JPanel j) {
		if(this.life == 0) {
			//坦克被击中血量为0，产生爆炸效果,,并移除此坦克
			g.drawImage(ImgProcessUtil.imageBoom_1,
					(xSetOff)*32+8, 
					(ySetOff)*32+8, 
					(xSetOff+1)*32-8, 
					(ySetOff+1)*32-8, 0, 0, 28, 28, j);
			if(this.tankType > 2) {
				RealTimeProcess.buff = new Buff(this.xSetOff, this.ySetOff, (int)Math.random()*6);
			}
			Timer t = new Timer();
			RealTimeProcess.etc.remove(this);
			RealTimeProcess.gmp.maxTankNum--;
			t.schedule(new TimerTask() {
				@Override
				public void run() {
					g.drawImage(ImgProcessUtil.imageBoom_2,
							(xSetOff)*32+8, 
							(ySetOff)*32+8, 
							(xSetOff+1)*32-8, 
							(ySetOff+1)*32-8, 0, 0, 64, 64, j);
				}
			}, 500);
			
		}
		else {
			//敌方坦克进入草丛
			if(RealTimeProcess.readTimeMapData[ySetOff][xSetOff] == 3) {
				g.drawImage(ImgProcessUtil.imageMap,
						(xSetOff)*32+Info.GLASSCHANGE, 
						(ySetOff)*32+Info.GLASSCHANGE, 
						(xSetOff+1)*32-Info.GLASSCHANGE, 
						(ySetOff+1)*32-Info.GLASSCHANGE, 64, 0, 96, 32, j);
						}
			else{
				//星星展示
				if(numForStarShow<4) {
					g.drawImage(ImgProcessUtil.imageStar, 
							(xSetOff)*32, (ySetOff)*32, 
							(xSetOff+1)*32, (ySetOff+1)*32,
							numForStarShow*32, 0, (numForStarShow+1)*32, 32, j);
					if(System.currentTimeMillis()-this.lastCreateTimeMil>Info.STARCHANGETIMEMIL) {
						numForStarShow++;
						this.lastCreateTimeMil = System.currentTimeMillis();
						}
				}
				//切换履带
				else{
					if(!dynamicFlag) {
					g.drawImage(ImgProcessUtil.imageEnemy, (xSetOff)*32, (ySetOff)*32, 
							(xSetOff+1)*32, (ySetOff+1)*32,tankType*64,dirction*32,(tankType)*64+32,(dirction+1)*32,j);
							this.dynamicFlag = true;
							}
					else {
					g.drawImage(ImgProcessUtil.imageEnemy, (xSetOff)*32, (ySetOff)*32, 
							(xSetOff+1)*32, (ySetOff+1)*32,tankType*64+32,dirction*32,(tankType)*64+32+32,(dirction+1)*32,j);
							this.dynamicFlag = false;
							}
					}
				}
			//敌方坦克血量为0时的执行策略
			if(this.life == 0) {
				//类型大于的0坦克死亡会有buff掉落
				if(this.tankType > 0) 
					RealTimeProcess.buff = new Buff(this.xSetOff,this.ySetOff,(int)(Math.random()*6));
				RealTimeProcess.etc.remove(this);
				
			}
		}
	}
	//坦克的随机移动和随机开火
	public void autoMoveAndAutoMove(){
		if(System.currentTimeMillis()-nowMoveTime>1000&&numForStarShow == 4) {
			//随机坐标变化
			randomMove();
			//开火
			fire();
			nowMoveTime = System.currentTimeMillis();
		}
	}
	//敌方坦克的自动运行和发射子弹
	@Override
	public void run() {
		while(this.life>0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!RealTimeProcess.gmp.allStop)
				autoMoveAndAutoMove();
		}
		RealTimeProcess.etc.remove(this);
	}
	//敌方坦克发射子弹
	public void fire() {
		//以50%的策略发射子弹
		if(Math.random()>0.5) {
			Bullet bullet = new Bullet(this.dirction,xSetOff, ySetOff, true);
			RealTimeProcess.bullet.add(bullet);
		}
	}
	public void randomMove() {
		if(this.xSetOff>0&&this.xSetOff<20&&this.ySetOff>0&&this.ySetOff<20) {
			double move = Math.random();
			if(move<0.25&&(RealTimeProcess.readTimeMapData[this.ySetOff][this.xSetOff+1] == 0||RealTimeProcess.readTimeMapData[this.ySetOff][this.xSetOff+1]  == 3)) {
				xSetOff++;
				this.dirction = 1;
			}
			else if(move>0.25&&move<0.5&&(RealTimeProcess.readTimeMapData[this.ySetOff][this.xSetOff-1] == 0|| RealTimeProcess.readTimeMapData[this.ySetOff][this.xSetOff-1] == 3)) {
				xSetOff--;
				this.dirction = 3;
			}
			else if(move>0.5&&move<0.75&&(RealTimeProcess.readTimeMapData[this.ySetOff+1][this.xSetOff] == 0|| RealTimeProcess.readTimeMapData[this.ySetOff+1][this.xSetOff] == 3)) {
				ySetOff++;
				this.dirction = 2;
			}
			else if(move>0.75&&(RealTimeProcess.readTimeMapData[this.ySetOff-1][this.xSetOff] == 0 ||RealTimeProcess.readTimeMapData[this.ySetOff-1][this.xSetOff] == 3)) {
				ySetOff--;
				this.dirction = 0;
			}
		}
		else if(this.xSetOff == 0) {
			xSetOff++;
			this.dirction = 1;
		}
		else if(this.ySetOff ==0) {
			ySetOff++;
			this.dirction = 2;
		}
		else if(this.xSetOff == 20) {
			xSetOff--;
			this.dirction = 3;
		}
		else if(this.ySetOff == 20) {
			ySetOff--;
			this.dirction = 0;
		}
	}
}
