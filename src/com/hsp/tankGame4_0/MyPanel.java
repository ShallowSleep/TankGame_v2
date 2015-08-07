package com.hsp.tankGame4_0;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

import java.util.*;
class MyPanel extends JPanel implements Runnable, KeyListener{
	//declare Tanks
	MyTank mt=null;
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	int etSize=3;
	
	//三张bomb图片＝一颗炸弹
	Image img1=null;
	Image img2=null;
	Image img3=null;
	
	public MyPanel(){
		mt=new MyTank(180,130);
		for(int i=0;i<etSize;i++){
			EnemyTank et=new EnemyTank(20+50*i,0);
			et.setDirect(2);
			//启动线程-enemyTanks
			Thread t=new Thread(et);
			t.start();
			//给敌机添加子弹
			Bullet ebt=new Bullet(et.x+10, et.y+15, et.direct);
			et.bullet.add(ebt);
			Thread tb=new Thread(ebt);
			tb.start();
			ets.add(et);
		}
		
		//initialize imgs
		img1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb1.jpg"));
		img2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb2.jpg"));
		img3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb3.jpg"));
	}	
	//paint Tank（can be encapsulate later）
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0, 0, MyTankGame3.width, MyTankGame3.length);
		//draw myTank
		this.drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), mt.getSpeed(), mt.getType());
		
		//draw EnemyTank
		for(int i=0;i<ets.size();i++){
			EnemyTank temp=ets.get(i);
			if(temp.isAlive==true){
				this.drawTank(temp.getX(),temp.getY(), g, temp.getDirect(), temp.getSpeed(), temp.getType());
				//画出敌人的子弹
				for(int j=0;j<temp.bullet.size();j++){
					//取出子弹
					Bullet eb=temp.bullet.get(j);
					if(eb.isAlive){
						g.setColor(Color.yellow);
						g.fill3DRect(eb.getX(), eb.getY(), 2, 2, false);
					}
					if(eb.isAlive==false){
						temp.bullet.remove(eb);
					}
				}
			}
			
		}
		
		//bullet
		for(int i=0;i<this.mt.bullet.size();i++){
			Bullet bts= mt.bullet.get(i);
			if(bts!=null && bts.isAlive==true){
				g.setColor(Color.yellow);
				g.fill3DRect(bts.getX(), bts.getY(), 2, 2, false);
			}
			if(bts.isAlive==false){
				mt.bullet.remove(bts);
			}
		}
		
		//Bomb
		for(int i=0;i<this.bombs.size();i++){
			System.out.println("bombs.size()="+bombs.size());
			Bomb b=bombs.get(i);
			if(b.hp>6){
				g.drawImage(img1, b.x, b.y, 30,30,this);
			}else if(b.hp>3){
				g.drawImage(img2, b.x, b.y, 30,30,this);
			}else{
				g.drawImage(img3, b.x, b.y, 30,30,this);
			}
			b.hpDown();
			if(b.hp==0){
				bombs.remove(b);
			}
		}
	}
	
	
	//判断子弹是否击中（敌方）坦克
	public void hitTank(Bullet bt, EnemyTank et){
		//判断该坦克的方向
		switch(et.direct){
		//direct: 0-up,1-right,2-down,3-left
		case 0:
		case 2:
			if(bt.x>et.x && bt.x<et.x+20 && bt.y>et.y && bt.y<et.y+30){
				//击中;子弹死亡;敌机死亡
				bt.isAlive=false;
				et.isAlive=false;
				//创建炸弹，放入Vector
				Bomb b=new Bomb(et.x,et.y);
				bombs.add(b);
			}
		case 1:
		case 3:
			if(bt.x>et.x && bt.x<et.x+30 && bt.y>et.y && bt.y<et.y+20){
				//击中
				bt.isAlive=false;
				et.isAlive=false;
				Bomb b=new Bomb(et.x,et.y);
				bombs.add(b);
			}
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
			System.out.println("mt.bullet.size()="+mt.bullet.size());
			if(mt.bullet.size()<5){
				//最多5发子弹
				this.mt.fire();
			}
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

			//判断子弹是否击中坦克
			for(int i=0;i<mt.bullet.size();i++){
				Bullet b=mt.bullet.get(i);
				if(b.isAlive==true){
					for(int j=0;j<ets.size();j++){
						EnemyTank et=ets.get(j);
						if(et.isAlive==true){
							this.hitTank(b, et);
						}
					}
				}
			}
			
			//判断是否给enemyTank 添加子弹
			for(int i=0;i<ets.size();i++){
				EnemyTank et=ets.get(i);
				if(et.isAlive==true){
					Bullet bt=null;
					if(et.bullet.size()<1){
						switch(et.direct){
						case 0:
							bt=new Bullet(et.x+9,et.y-2,0);
							et.bullet.addElement(bt);
							break;
						case 1:
							bt=new Bullet(et.x+25,et.y+14,1);
							et.bullet.addElement(bt);
							break;
						case 2:
							bt=new Bullet(et.x+9,et.y+31,2);
							et.bullet.addElement(bt);
							break;
						case 3:
							bt=new Bullet(et.x-7,et.y+13,3);
							et.bullet.addElement(bt);
							break;
						}
						Thread th=new Thread(bt);
						th.start();
					}//if
				}
			}
					
			this.repaint();
		}
	}
}
