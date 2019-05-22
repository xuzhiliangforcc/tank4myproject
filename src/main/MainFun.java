package main;

import datacenter.RealTimeProcess;
import ui.BeganFrame;
import util.SoundProcessUtil;

/*
 * 主入口方法：
 * 功能如下：
 * 创建界面类，其中界面类中集成了一系列得创建和构造方法
 * 
 */
public class MainFun {
	public static void main(String[] args) {
		//游戏线程
		RealTimeProcess.bf = BeganFrame.getBeganFrame();
		
	}
}
