/*
 * 1.创建 times
 */
package com.hsp.tankGame5_0;

import java.util.*;

public class EnemyTank extends Tank implements Runnable{
	int type=1;
	//Boolean isAlive = true;
	int times=0;//控制子弹发射
	Vector<Bullet> bullet=new Vector<Bullet>();
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){	
			//direct: 0-up,1-right,2-down,3-left
			switch(this.direct){
			case 0:
				for(int i=1;i<30;i++){
					if(y>0){
						y-=this.speed;
					}
					try {
						Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}			
				break;
			case 1:
				for(int i=1;i<30;i++){
					if(x<370){
						x+=this.speed;
					}
					try {
						Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}			
				break;
			case 2:
				for(int i=1;i<30;i++){
					if(y<250){
						y+=this.speed;
					}
					try {
						Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}			
				break;
			case 3:
				for(int i=1;i<30;i++){
					if(x>0){
						x-=this.speed;
					}
					try {
						Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}			
				break;
			}
			
			//发射子弹
			this.times++;
			System.out.println("times++");
			if(times%2==0){
				if(isAlive){
					if(bullet.size()<3){
						Bullet bt=null;				
							switch(direct){
							case 0:
								bt=new Bullet(x+9,y-2,0);
								bullet.add(bt);
								break;
							case 1:
								bt=new Bullet(x+25,y+14,1);
								bullet.add(bt);
								break;
							case 2:
								bt=new Bullet(x+9,y+31,2);
								bullet.add(bt);
								break;
							case 3:
								bt=new Bullet(x-7,y+13,3);
								bullet.add(bt);
								break;
							}
							Thread th=new Thread(bt);
							th.start();
					}
				}
			}
			
			//change direction
			this.direct=(int)(Math.random()*4);
			//判断isAlive, thread dead
			if(this.isAlive==false){
				break;
			}
			
		}
	}
}
