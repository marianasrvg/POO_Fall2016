package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.input.Keyboard;

public class PlayerOne extends Player {
	
	public PlayerOne(Keyboard input){
		super(input);
		posRandom();
	}
	
	public void update(Screen screen){
		collisionObstaculos(screen);
		int xa = 0, ya = 0;
		if(input.up1) ya--;
		if(input.down1) ya++;
		if(input.left1) xa--;
		if(input.right1) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya, screen); 
	}
	
	public void move(int xa, int ya, Screen screen){
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if ( dir == 1 && collisionBarra(screen)) xa--;
		if( dir == 3 && collisionBarra(screen)) xa++;
		if( dir == 2 && collisionBarra(screen)) ya--;
		
		if( x + xa >= Background.width_p-8) xa--;
		if( x + xa <= 0) xa++;
		if( y + ya >= Background.height_p-8) ya--;
		if( y + ya <= 10) ya++;
		
		x += xa;
		y += ya;	
	}
	
	private void posRandom(){
		x = Background.width_p/4;
		y = Background.height_p/2;
	}


	
}
