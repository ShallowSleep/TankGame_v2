package com.hsp.tankGame4_0;

class Tank{
	//location(x,y)
	int x=0;
	int y=0;	
	//direct: 0-up,1-right,2-down,3-left
	int direct=0;
	//speed
	int speed=1;
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
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
	
	public Tank(int x, int y){
		this.x=x;
		this.y=y;
	}
}
