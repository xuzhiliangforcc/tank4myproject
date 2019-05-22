package util;

public class Info {
	//默认窗口宽度
	public final static int WIDTH = 900;
	//坦克进入草丛的草丛变化值
	public final static int GLASSCHANGE = 8;
	//星星图片的切换时间
	public final static long STARCHANGETIMEMIL = 300L; 
	//默认窗口高度
	public final static int HEIGHT = 702;
	//是否可以重新调整窗口
	public static boolean ISRESIZEABLE = false;
	//无敌标识
	public static boolean WUDIFLAG = false;
	//空袭标识
	public static boolean KONGXIFLAG = false;
	//清场标识
	public static boolean allClear = false;
	//GameMapPanel窗口的大小
	public final static int GAMEMAPPANEL_X = 0;
	public final static int GAMEMAPPANEL_Y = 0;
	public final static int GAMEMAPPANEL_X_1 = 800;
	public final static int GAMEMAPPANEL_Y_1 = 702;
	//敌方坦克制造单位最大值
	public final static int MAX_EMENY_TANK_NUM = 3;
	//敌方坦克总数
	public final static int TANKNUM = 20;
	//buff显示时间
	public final static long BUFFTIME = 5000L;
	//我方坦克血量
	public final static int MY_TANK_LIFE = 5;
	//我方坦克出生后的初始位置
	public final static int MY_TANKCHARACTOR_OFFSET_X = 7;
	public final static int MY_TANKCHARACTOR_OFFSET_Y = 19;
	//坦克移动速度
	public final static int SPEEDOFTANK = 32;
	//产生敌方坦克的间隔时间
	public final static long GENERATORTIMEFOREMENYTANK = 2000l;
	//无敌时长
	public final static long NOONEELSECANKILLME = 5000L;
	//敌方坦克的等级数
	public final static int RANKFOREMENYTANK = 8;
	//敌方坦克的种类数
	public final static int KINDFOREMENYTANK = 8;
	//吃到定时buff后敌方坦克的停止时长
	public final static long STOPTIME = 8000;
	//子弹移动速度
	public final static int SPEEDOFBULLET = 32;
	//title
	public final static String title = "--------------------坦克大战--------------------";

}
