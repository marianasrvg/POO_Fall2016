package com.game.shift;

public class Timing {
	private long time = 5000;
	private long now = 0;
	//private boolean d, c, m, dm, mll;
	public static int morePoints = 1000;  
	
	public Timing(){
		now = System.currentTimeMillis();
	/*	d = false;
		c = false;
		m = false;
		dm = false;
		mll = false;*/
	}
/*	public boolean itsTime(){
		if(time % morePoints == 0) return true;
		return false;
	}*/
	public long getTime(){
		return time;
	}
	
	public void render(){
		if(System.currentTimeMillis() - now > 1000){
			time = ((time-1)<0)?0: time - 1;
	/*		if(time % 10 == 0){
				d = true;
			}
			if(time % 100 == 0){
				c = true;
			}
			if(time%1000 == 0){
				m = true;
			}
			if(time%10000 == 0){
				dm = true;
			}*/
		}
		
	}
	
	public String timerString(){
		return String.valueOf(time).substring(0, 3);
	/*	if(mll)
			return String.valueOf(time).substring(0, 4);
		
		if(dm)
			return "0"+String.valueOf(time).substring(0, 3);
		if(m)
			return "00"+String.valueOf(time).substring(0, 2);
		if(c)
			return "000"+String.valueOf(time).substring(0, 1);
		//if(d)
		return "0000"+String.valueOf(time).substring(0, 0);
		//return "00000"+String.valueOf(time).substring(0, 0);*/
	}
}
