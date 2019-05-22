package util;

import java.awt.Graphics;

import database.MapData;
import datacenter.RealTimeProcess;

/*
 * 地图绘制
 * 
 */
public class DrawMap {
	public final static int four = 4;
	public final static int five = 5;
	public int index = 0;
	public int boom = 0;
	public  void drawMap(Graphics g) {
		for(int i = 0;i<RealTimeProcess.readTimeMapData.length;i++) {
			for(int j = 0;j<RealTimeProcess.readTimeMapData[0].length;j++) {
				//共有四种地形加上老王的两种状态，共六种
				if(RealTimeProcess.readTimeMapData[i][j]<7) {
					//作用是切换水流的动态切换效果
					if(four == RealTimeProcess.readTimeMapData[i][j]) {
						RealTimeProcess.readTimeMapData[i][j] = 5 ;
						}
					else if(five == RealTimeProcess.readTimeMapData[i][j]){
						RealTimeProcess.readTimeMapData[i][j] = 4;
					}
					//绘制地图
					if(RealTimeProcess.readTimeMapData[i][j]!=6)
						g.drawImage(ImgProcessUtil.imageMap, 32*j, 32*i, 32*(j+1),32*(i+1), 32*(MapData.map[i][j]-1), 0,32*(MapData.map[i][j]), 32, null);
					//绘制stone
					if(RealTimeProcess.readTimeMapData[i][j] == 2)
						g.drawImage(ImgProcessUtil.imageStone, 32*j, 32*i, 32*(j+1),32*(i+1), 0, 0,103, 77, null);
					//绘制tree
					if(RealTimeProcess.readTimeMapData[i][j] == 1)
						g.drawImage(ImgProcessUtil.imageTree, 32*j, 32*i, 32*(j+1),32*(i+1), 0, 0,77, 100, null);
				}
				}
			}
		//绘制老王
		if(RealTimeProcess.readTimeMapData[9][19]!=0) {
		g.drawImage(ImgProcessUtil.timgA23U2IJV.get(index), 32*9, 32*18, 32*12,32*20, 0, 0,475, 264, null);
		index = (index+1)%11;
		}
	} 
	public int i = 0;
	//绘制boomboomboom的效果
	public  void drawMapBoomBoomBoom(Graphics g) {
		if(i<16) {
			g.drawImage(ImgProcessUtil.boom.get(i), 20, 100, 682, 602, 0, 0, 580, 326, null);
			i++;
		}
		else {
			for(int i = 0;i<RealTimeProcess.readTimeMapData.length;i++) {
				for(int j = 0;j<RealTimeProcess.readTimeMapData[0].length;j++) {
					RealTimeProcess.readTimeMapData[i][j] = 0;
					g.drawImage(ImgProcessUtil.uaVlUPbjlokrzFC.get(boom/2), 32*j, 32*i, 32*(j+1),32*(i+1), 0, 0, 410, 398, null);
				}
			}
			if(boom<20){
				boom++;
			}else 
				Info.allClear = false;
		}
	}
}
