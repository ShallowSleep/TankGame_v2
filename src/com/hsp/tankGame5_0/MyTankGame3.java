/*
 * 功能：
 * 1.敌方坦克移动，射击
 */
package com.hsp.tankGame5_0;
import javax.swing.*;

public class MyTankGame3 extends JFrame {
	//window wide, long
	static int width=400;
	static int length=300;
	MyPanel mp=null;
	public static void main(String[] args){
		MyTankGame3 mtg2=new MyTankGame3();
	}
	
	public MyTankGame3(){
		mp=new MyPanel();
		this.add(mp);
		Thread th1=new Thread(mp);
		th1.start();
		this.setVisible(true);
		this.setSize(width,length);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//register listener
		this.addKeyListener(mp);
	}
}
