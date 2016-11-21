package com.game.shift.entity.mob;

import com.game.shift.entity.Entity;
import com.game.shift.graficos.Sprite;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	public static int mPoints = 10;
	
	public void move(){
	}
	
	public void update(){
	}
	
	protected boolean collision(){
		return false;
	}
	
	public void render(){
		
	}
}
