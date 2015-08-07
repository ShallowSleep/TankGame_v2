package com.hsp.tankGame4_0;
import java.util.*;
class MyTank extends Tank{
	int type=0;
	Vector<Bullet> bullet=new Vector<Bullet>();
	Bullet bt;

	public MyTank(int x, int y){
		super(x,y);
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}	
	
	//MyTank move up
	public void moveUp(){
		y-=speed;
	}
	//MyTank move down
	public void moveDown(){
		y+=speed;
	}
	//MyTank move right
	public void moveRight(){
		x+=speed;
	}
	//MyTank move left
	public void moveLeft(){
		x-=speed;
	}

	public void fire(){
		//direct: 0-up,1-right,2-down,3-left
		switch(this.direct){
		case 0:
			bt=new Bullet(x+9,y-2,0);
			bullet.addElement(bt);
			break;
		case 1:
			bt=new Bullet(x+25,y+14,1);
			bullet.addElement(bt);
			break;
		case 2:
			bt=new Bullet(x+9,y+31,2);
			bullet.addElement(bt);
			break;
		case 3:
			bt=new Bullet(x-7,y+13,3);
			bullet.addElement(bt);
			break;
		}
		Thread thbt=new Thread(bt);
		thbt.start();
	}
}

