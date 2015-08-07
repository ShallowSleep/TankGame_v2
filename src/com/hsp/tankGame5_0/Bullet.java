package com.hsp.tankGame5_0;

public class Bullet implements Runnable{	
	int x=0;
	int y=0;
	int direct=0;
	int speed=3;
	boolean isAlive=true;

	public Bullet(int x, int y, int direct){
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}	
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(direct){
			case 0:
				y-=speed;
				break;
			case 1:
				x+=speed;
				break;	
			case 2:
				y+=speed;
				break;
			case 3:
				x-=speed;
				break;
			}
			//test
			//System.out.println("x="+x+", y="+y);
			//when bullet dead???
			if(x<1||x>MyTankGame3.width-3||y<1||y>MyTankGame3.length-3){
				this.isAlive=false;
				break;
			}
		}
	}
}
