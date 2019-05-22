package util;

/*
 * 工具类，将图片读取到内存中供其他方法使用
 */
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
public class ImgProcessUtil {
	//图标
	public static Image imageTitle = null;
	//地图
	public static Image imageMap = null;
	public static Image imageMyTankCharactor_1 = null;
	public static Image imageMyTankCharactor_2 = null;
	public static Vector<Image> loadding = new Vector<Image>();
	public static Vector<Image> timgU0JZ7HGP = new Vector<Image>();
	public static Vector<Image> timgA23U2IJV = new Vector<Image>();
	public static Vector<Image> uaVlUPbjlokrzFC = new Vector<Image>();
	public static Vector<Image> boom = new Vector<Image>();
	public static Image imagePlayer_1 = null;
	public static Image imageBullet = null;
	public static Image imageEnemy = null;
	public static Image imageControl = null;
	public static Image imageStar = null;
	public static Image imageBoom_1 = null;
	public static Image imageBoom_2 = null;
	public static Image imageGameOver = null;
	public static Image imageBackground = null;
	public static Image imageStringBackground = null;
	public static Image imageSuccsee = null;
	public static Image imageBuff = null;
	public static Image imageStone = null;
	public static Image imagePlane = null;
	public static Image imageTree = null;
	static {
		try {
			imageTree= ImageIO.read(new File("image\\tree.png"));
			imageStone= ImageIO.read(new File("image\\stone.png"));
			imagePlane = ImageIO.read(new File("image\\boom.jpg"));
			//buff
			imageBuff = ImageIO.read(new File("image\\buff.bmp"));
			//过关图片
			imageSuccsee= ImageIO.read(new File("image\\success.gif"));
			//载入背景图片
			imageBackground = ImageIO.read(new File("image\\background.jpg"));
			//载入gameover图片
			imageGameOver = ImageIO.read(new File("image\\gameover.bmp"));
			//载入标题图片
			imageTitle = ImageIO.read(new File("image\\icon.png"));
			//载入地图配置文件
			imageMap = ImageIO.read(new File("image\\mapDraw.bmp"));
			//载入砖墙的爆炸效果
			imageBoom_1 = ImageIO.read(new File("image\\boom1.bmp"));
			imageBoom_2 = ImageIO.read(new File("image\\boom2.bmp"));
			//载入我方坦克
			imagePlayer_1 = ImageIO.read(new File("image\\mycharactor.png"));
			imageBullet = ImageIO.read(new File("image\\bullet.bmp"));
			//载入敌方坦克
			imageEnemy = ImageIO.read(new File("image\\enemy.bmp"));
			//载入imageStringBackground图片
			imageStringBackground = ImageIO.read(new File("image\\stingbackground.jpg"));
			//载入控制图标
			imageControl = ImageIO.read(new File("image\\title.png"));
			//载入星星图标
			imageStar = ImageIO.read(new File("image\\star.bmp"));
			//循环载入开始动画
			for(int i = 1;i<=250;i++) {
				loadding.add(ImageIO.read(new File("image\\img\\frame"+i+".png")));
			}
			//载入我也不知道的东西
			for(int i = 0;i<=11;i++) {
				timgU0JZ7HGP.add(ImageIO.read(new File("image\\timgU0JZ7HGP.gif.ifl\\IMG0000"+i+".bmp")));
			}
			//载入我也不知道的东西
			for(int i = 0;i<=10;i++) {
				timgA23U2IJV.add(ImageIO.read(new File("image\\timgA23U2IJV.gif.ifl\\IMG0000"+i+".bmp")));
			}
			//载入我也不知道的东西
			for(int i = 0;i<=10;i++) {
				uaVlUPbjlokrzFC.add(ImageIO.read(new File("image\\uaVlUPbjlokrzFC.gif.ifl\\IMG0000"+i+".bmp")));
			}
			//载入我也不知道的东西
			for(int i = 0;i<=15;i++) {
				boom.add(ImageIO.read(new File("image\\boom.gif.ifl\\IMG0000"+i+".bmp")));
			}
		} catch (IOException e) {
			System.out.println("天啦噜，图片读取失败！");
		}
	}
}
