package util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import datacenter.RealTimeProcess;

public class KeyMonitor extends KeyAdapter {

	//键盘监听事件
	public void keyReleased(KeyEvent e) { 
		RealTimeProcess.mtc.keyReleased(e);
//		RealTimeProcess.gmp.repaint();
	}
}
