package com.game.shift.entity.mob;

import com.game.shift.graficos.Sprite;

public abstract class Mob{
	public int x, y;
	protected Sprite sprite;
	protected int dir = 0;
	
	public void move(){}
	public void update(){}
	public void render(){}
	
}
