package datacenter;

import java.util.Vector;
import charactor.Buff;
import charactor.Bullet;
import charactor.EmenyTankCharactor;
import charactor.MyTankCharactor;
import database.MapData;
import ui.BeganFrame;
import ui.GameControlPanel;
import ui.GameMapPanel;
import ui.LoaddingPanel;
import util.DrawMap;
import util.KeyMonitor;
/*
 * 保存一些实时性信息
 */
public class RealTimeProcess {
	//获取地图配置信息并设置全局可变的地图数据信息
	public static int[][] readTimeMapData = MapData.map;
	public static MyTankCharactor mtc =  null;
	public static GameMapPanel gmp = null;
	public static DrawMap dm = new DrawMap();
	public static KeyMonitor km = new KeyMonitor();
	public static Vector<EmenyTankCharactor> etc = new Vector<EmenyTankCharactor>();
	public static GameControlPanel gcp = null;
	public static Vector<Bullet> RealTimeProcess = new Vector<Bullet>(); 
	public static Buff buff = null;
	public static Vector<Bullet> bullet = new Vector<Bullet>();
	public static LoaddingPanel loadding = null;
	public static BeganFrame bf = null;
			
}
