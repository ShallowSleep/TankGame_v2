package com.hsp.tankGame3_0;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

class MyPanel extends JPanel implements Runnable, KeyListener{
	//declare Tanks
	MyTank mt=null;
	EnemyTank et1=null;
	EnemyTank et2=null;
	EnemyTank et3=null;
	
	public MyPanel(){
		mt=new MyTank(180,130);
		et1=new EnemyTank(20,0);
		et2=new EnemyTank(70,0);
		et3=new EnemyTank(120,0);
		et1.setDirect(2);
		et2.setDirect(2);
		et3.setDirect(2);
	}	
	//paint Tank（can be encapsulate later）
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, MyTankGame3.width, MyTankGame3.length);
		//myTank
		this.drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), mt.getSpeed(), mt.getType());
		//EnemyTank
		this.drawTank(et1.getX(), et1.getY(), g, et1.getDirect(), et1.getSpeed(), et1.getType());
		this.drawTank(et2.getX(), et2.getY(), g, et2.getDirect(), et2.getSpeed(), et2.getType());
		this.drawTank(et3.getX(), et3.getY(), g, et3.getDirect(), et3.getSpeed(), et3.getType());
		//bullet
		if(mt.bt!=null){
			g.setColor(Color.yellow);
			g.fill3DRect(mt.bt.getX(), mt.bt.getY(), 2, 2, false);
		}
	}
	
	//draw-tank-method
	public void drawTank(int x, int y, Graphics g, int direct, int speed, int type){
		//1.tank type
		switch(type){
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
			break;
		}
		//2. tank direction
		//direct: 0-up,1-right,2-down,3-left
		switch(direct){
		case 0:
			//->up
			g.fillRect(x, y, 5, 30);
			g.fillRect(x+15, y, 5, 30);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			g.fillOval(x+4, y+9, 10, 10);
			g.drawLine(x+9, y+1, x+9, y+15);		
			break;
		case 1:
			//->right
			g.fillRect(x-5, y+5, 30, 5);
			g.fillRect(x-5, y+20, 30, 5);
			g.fill3DRect(x, y+10, 20, 10,false);
			g.fillOval(x+4, y+9, 10, 10);
			g.drawLine(x+9, y+14, x+23, y+14);
			break;
		case 2:
			//->down
			g.fillRect(x, y, 5, 30);
			g.fillRect(x+15, y, 5, 30);
			g.fill3DRect(x+5, y+5, 10, 20,false);
			g.fillOval(x+4, y+9, 10, 10);
			g.drawLine(x+9, y+15, x+9, y+29);
			break;
		case 3:
			//->left
			g.fillRect(x-5, y+5, 30, 5);
			g.fillRect(x-5, y+20, 30, 5);
			g.fill3DRect(x, y+10, 20, 10,false);
			g.fillOval(x+4, y+9, 10, 10);
			g.drawLine(x-4, y+14, x+10, y+14);
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP){
			this.mt.setDirect(0);
			this.mt.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
			this.mt.setDirect(2);
			this.mt.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			this.mt.setDirect(3);
			this.mt.moveLeft();
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			this.mt.setDirect(1);
			this.mt.moveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			this.mt.fire();
		}
		this.repaint();		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
		}
	}
}
