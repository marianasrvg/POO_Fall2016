package com.game.shift.entity;

import com.game.shift.Screen;

// equivalente a Figura


public abstract class Entity {
	
	public int x, y;
	private boolean removed = false;
	
	public void update(){
	}
	
	public void render(Screen screen){
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
}
