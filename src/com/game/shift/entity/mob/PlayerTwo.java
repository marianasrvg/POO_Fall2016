package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.input.Keyboard;

public class PlayerTwo extends Player {
	
	public PlayerTwo(Keyboard input){
		super(input);
		posRandom();
	}
	
	public void update(Screen screen){
		collisionObstaculos(screen);
		int xa = 0, ya = 0;
		if(input.up2) ya--;
		if(input.down2) ya++;
		if(input.left2) xa--;
		if(input.right2) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya, screen); 
	}
	
	public void move(int xa, int ya, Screen screen){
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(collisionBarra(screen)) x++;
		
		if( x + xa >= Background.width_p-8) xa--;
		if( x + xa <= 0) xa++;
		if( y + ya >= Background.height_p-8) y--;
		if( y + ya <= 10) ya++;
		
			x += xa;
			y += ya;
			
	}
	
	private void posRandom(){
		x = 3*Background.width_p/4;
		y = Background.height_p/2;
	}
}
