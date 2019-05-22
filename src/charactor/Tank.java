package charactor;

import java.awt.Graphics;
import javax.swing.JPanel;
import datacenter.RealTimeProcess;
public abstract class Tank {
		//是否已经创建过star图标
		public int numForStarShow = 0;
		//产生星星的判断时间
		public long lastCreateTimeMil = 0l;
		//是否我是我方坦克
		public boolean isPlayerFlag = false;
		//方向
		public  int dirction = 0;//0123上右下左
		//血量
		public  int life = 0;
		//等级
		public  int rank = 0;//012
		//x方向偏移量
		public  int xSetOff = 0;
		//y方向偏移量
		public  int ySetOff = 0;
		//坦克速度
		public int tankSpeed = 0;
		//坦克种类
		public int tankType = 0;
		//子弹速度
		public int bulletSpeed = 5;
		//履带变化的标志
		public boolean dynamicFlag = false;
		public Tank(boolean isPlayerFlag, int dirction, int life, int rank, int xSetOff, int ySetOff, int tankSpeed,
				int tankType, int bulletSpeed) {
			super();
			this.isPlayerFlag = isPlayerFlag;
			this.dirction = dirction;
			this.life = life;
			this.rank = rank;
			this.xSetOff = xSetOff;
			this.ySetOff = ySetOff;
			this.tankSpeed = tankSpeed;
			this.tankType = tankType;
			this.bulletSpeed = bulletSpeed;
			}
		//坦克移动调用的方法
		public void setOff(int xSetOff,int ySetOff) {
			this.xSetOff += xSetOff;
			this.ySetOff += ySetOff;
		};
		//判断是否触墙
		public static boolean isTouchTheWall(int x,int y) {
			if(RealTimeProcess.readTimeMapData[y][x]==0) {
				return true;
				}
			//坦克进入草丛
			else if(RealTimeProcess.readTimeMapData[y][x]==3){
				return true;
			}
			System.out.println("天啦噜--------->撞墙啦！");
			return false;
		}
		//显示坦克方法
		public abstract void drawTankCharactor(Graphics g, JPanel j);
}
