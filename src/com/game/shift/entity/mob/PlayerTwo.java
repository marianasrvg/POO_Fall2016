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
		collisionObstacle(screen, dir);
		int xa = 0, ya = 0;
		if(input.up2) ya--;
		if(input.down2) ya++;
		if(input.left2) xa--;
		if(input.right2) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya, screen); 
	}
		
	private void posRandom(){
		x = 3*Background.width_p/4;
		y = Background.height_p/2;
	}
}
