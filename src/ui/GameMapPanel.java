package ui;
/*
 * 1.绘制游戏窗口
 * 2.地图的构建
 * 3.我方坦克的放置
 * 4.敌方坦克的放置
 */
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import charactor.EmenyTankCharactor;
import charactor.MyTankCharactor;
import datacenter.RealTimeProcess;
import util.ImgProcessUtil;
import util.Info;
import util.SoundProcessUtil;
import util.TimeCount;
public class GameMapPanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	//游戏结束的标记
	public static boolean gameOver = false;
	//记录当前已创建过的敌方坦克数量
	public int tankCreadted = 0;
	//停止所有敌方坦克的随机移动
	public boolean allStop = false;
	//设置敌方坦克出生点
	//共三个
	public int [][]locationSite = {
			{1,1},
			{9,1},
			{17,1}
			};
	//坦克出生点切换的标识符
	public int site = 0;
	public int gameOverflag = 0;
	//记录创建敌方坦克的时间，用于定时产生敌方坦克对象
	public long lastCreateTime = 0l;
	//当前窗口已经正在运行的最大坦克的数量
	public int maxTankNum = 0;
	public GameMapPanel() {
		//清除默认布局
		this.setLayout(null);
		//加声音
		SoundProcessUtil.playVedio1();
		RealTimeProcess.mtc = new MyTankCharactor(true, 0, Info.MY_TANK_LIFE, 0, 0, 0, 0, 0, 0);
		//开启此线程
		new Thread(this).start();
		//时间线程
		TimeCount.getTimeCount();
		}
	//绘制控件
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);	
		//对此控件的大小和位置做出限制
		this.setSize(800, 702);
		this.setLocation(0, 0);
		//绘制地图
		if(!Info.allClear) {
		//设置游戏背景图片
			arg0.drawImage(ImgProcessUtil.imageBackground,
				0, 0, Info.GAMEMAPPANEL_X_1, Info.GAMEMAPPANEL_Y_1, 
				0, 0, 1500, 1200, this);
			RealTimeProcess.dm.drawMap(arg0);
		}else {
				
			RealTimeProcess.dm.drawMapBoomBoomBoom(arg0);
		}
		//设置文字背景图片
		arg0.drawImage(ImgProcessUtil.imageStringBackground,
				640, 60, 800, 600, 
				0, 0, 655, 433, this);
		//如果存在buff对象就绘制出来
		if(RealTimeProcess.buff != null) {
			RealTimeProcess.buff.drawBuff(arg0, this);
			}
		//绘制我方坦克
		RealTimeProcess.mtc.drawTankCharactor(arg0,this);
		//创建子弹------>在我方坦克类中已经创建
		//绘制已产生的子弹对象
		for(int index = 0;index<RealTimeProcess.bullet.size();index++) {
			RealTimeProcess.bullet.get(index).drawBullet(arg0, this);
			}
		//在当前桌面坦克小于设定值时且还有为创建的敌方坦克时创建敌方坦克--------------->间隔2秒！！！
		if(RealTimeProcess.etc.size()<5&&tankCreadted<Info.TANKNUM) {
			if(System.currentTimeMillis()-lastCreateTime>Info.GENERATORTIMEFOREMENYTANK) {
				//随机等级------>8个等级
				int rank = (int)(Math.random()*Info.RANKFOREMENYTANK);
				//出生位置切换
				site = site%3;
			    //敌方坦克类型
				int type = (int)(Math.random()*Info.KINDFOREMENYTANK);
				//showInfo
				System.out.println("制造一名敌人------>等级："+rank+" 类型："+type);
				//创建并加入到集合
				RealTimeProcess.etc.add(new EmenyTankCharactor(false, 2, 1, rank, locationSite[site][0], locationSite[site][1], 0, type,  0));
				site++;
				this.maxTankNum++;
				this.tankCreadted++;
				//记录当前产生时间，用作后面的判断
				lastCreateTime = System.currentTimeMillis();
				}
			}  
		//for循环绘制敌方坦克
		for(int index = 0;index<RealTimeProcess.etc.size();index++) {
			RealTimeProcess.etc.get(index).drawTankCharactor(arg0, this);
			}
		//显示相关信息
		drawInfo(arg0,this);
		//空袭
		if(Info.KONGXIFLAG)
			drawPlaneFly(arg0, this);
		///判定游戏结束的的执行操作  --------->游戏结束符为true或已产生的坦克数达到最大数
		if(gameOver||(tankCreadted==Info.TANKNUM&&RealTimeProcess.etc.isEmpty())) {
			drawGameOver(arg0,this);
		}
	}
	//绘制游戏结束的画面
	public void drawGameOver(Graphics arg0,JPanel j) {
		if(gameOver) {
			SoundProcessUtil.playVedio6();
			if(this.gameOverflag<65) {
				arg0.drawImage(ImgProcessUtil.imageGameOver, gameOverflag, 171, gameOverflag+577, 531, 0, 0, 256, 160, j);
				this.gameOverflag++;
			}
		}else {
			gameOver = true;
			arg0.drawImage(ImgProcessUtil.imageSuccsee, 65, 171, 577, 531, 0, 0, 533, 404, j);
			}
		}
	public int sroll = 0;
	//绘制空袭
	public void drawPlaneFly(Graphics arg0,JPanel j) {
		arg0.drawImage(ImgProcessUtil.imagePlane, 120, 0+sroll, 520, 200+sroll, 0, 0, 512, 512, j);
		sroll += 25;
		for(int index = 0;index<RealTimeProcess.etc.size();index++)
			RealTimeProcess.etc.get(index).life = 0;
		if(200+sroll>900) {
			sroll = 0;
			Info.KONGXIFLAG = false;
		}
	}
	//显示当前坦克状态和剩余坦克数量
	public void drawInfo(Graphics arg0,JPanel j) {
		//设置字体格式
		arg0.setFont(new Font("微软雅黑",Font.BOLD,16));
		arg0.drawString("当前血量：",650, 100);
		arg0.drawString(RealTimeProcess.mtc.life>5000?"无敌状态":RealTimeProcess.mtc.life+"",730, 100);
		arg0.drawString("剩余敌方坦克：",650, 150);
		arg0.drawString(Info.TANKNUM-this.tankCreadted+"",760, 150);
		arg0.drawString("游戏时长：", 650, 200);
		arg0.drawString(TimeCount.now/1000+"  秒",730, 200);
		arg0.drawString("总积分：", 650, 250);
		arg0.drawString((tankCreadted-RealTimeProcess.etc.size())*100+"  分",730, 250);
		arg0.drawString("通关秘籍：--->",650, 400);
		arg0.drawString("Z------->一键无敌！",655, 450);
		arg0.drawString("X------->子弹升级！",655, 470);
		arg0.drawString("C------->空袭！！！",655, 490);
		arg0.drawString("V------->清场！！！",655, 510);
		arg0.drawString("S------->一键自杀！",655, 530);
		arg0.drawString("A------->一键关机！",655, 550);
	}
	
	
	
	@Override
	public void run() {
		while(!GameMapPanel.gameOver) {
			//更新控件
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
		}
	}
}



