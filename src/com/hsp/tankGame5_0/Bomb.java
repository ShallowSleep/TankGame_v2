package com.hsp.tankGame5_0;

public class Bomb {
	int x,y;
	int hp=9;//Bomb'hp=life
	boolean isAlive=true;
	
	public Bomb(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	//hp decrease
	public void hpDown(){
		if(hp>0){
			hp--;
		}else{
			this.isAlive=false;
		}
	}
	
}
