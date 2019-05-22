package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import datacenter.RealTimeProcess;
import util.ImgProcessUtil;
import util.Info;
/*
 * 1.初始展示界面
 * 2.创建GameMapPanel
 * 3.展示GameMapPanel控件
 */
public class BeganFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static boolean isStart = false;
	private static BeganFrame bf = null;
	public static BeganFrame getBeganFrame() {
		if(bf == null) {
			bf = new BeganFrame();
		}
		return bf;
	}
	private BeganFrame(){
		init();
	}
	//窗口界面的初始化
	public  void init() {
		//加入键盘监听
		this.addKeyListener(RealTimeProcess.km);
	
		//获取屏幕参数并调整界面居于界面中心
		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = defaultToolkit.getScreenSize();
		this.setBounds((int)(screenSize.getWidth()-Info.WIDTH)/2, 
				(int)(screenSize.getHeight()-Info.HEIGHT)/2, 
				Info.WIDTH, Info.HEIGHT);
		//设置成网格布局
		this.setLayout(new GridLayout(2,1));
		if(isStart) {
			//创建GameMapPanel控件和GameControlPanel控件
			RealTimeProcess.gmp = new GameMapPanel();
			RealTimeProcess.gcp = new GameControlPanel();
			//添加GameMapPanel控件
			this.getContentPane().add(RealTimeProcess.gmp);
			//添加GameControlPanel控件
			this.getContentPane().add(RealTimeProcess.gcp);
		}else {
			//添加loadding控件
			RealTimeProcess.loadding = new LoaddingPanel();
			this.getContentPane().add(RealTimeProcess.loadding);
		}
		//设置用户是否可调整窗口
		this.setResizable(Info.ISRESIZEABLE);
		this.setBackground(Color.black);
		//用户点击退出按钮的执行策略
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置图标
		this.setIconImage(ImgProcessUtil.imageTitle);
		//设置标题
		this.setTitle(Info.title);
		//设置窗口可见性
		this.setVisible(true);
		this.addMouseListener(new MouseAdapter()//鼠标监听
		        {
		             public void mouseEntered(MouseEvent e)
		             {
		                 System.out.println(e.getXOnScreen()+" "+e.getYOnScreen());
		             }
		             public void mouseClicked(MouseEvent e)
		             {
		                 if(e.getXOnScreen()>1310&&e.getXOnScreen()<1410&&e.getYOnScreen()>215&&e.getYOnScreen()<265)
		                	 System.out.println("点击了新游戏按钮");
		             }
		         });
		//获取焦点
		this.setFocusable(true);
	}
}
