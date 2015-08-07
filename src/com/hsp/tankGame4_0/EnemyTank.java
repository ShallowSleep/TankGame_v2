/*
 * 可移动
 */
package com.hsp.tankGame4_0;

import java.util.*;

public class EnemyTank extends Tank implements Runnable{
	int type=1;
	Boolean isAlive = true;
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
				for(int i=1;i<35;i++){
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
				for(int i=1;i<35;i++){
					if(x<400){
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
				for(int i=1;i<35;i++){
					if(y<300){
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
				for(int i=1;i<40;i++){
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
			//change direction
			this.direct=(int)(Math.random()*4);
			//判断isAlive, thread dead
			if(this.isAlive==false){
				break;
			}
			
		}
	}
}
