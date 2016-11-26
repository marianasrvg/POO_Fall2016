package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class PlayerOne extends Player {
	
	public PlayerOne(Keyboard input, Background world, Sprite sprite){
		super(input, world, sprite);
		posRandom();
	}
	
	public void update(Screen screen){
		collisionObstacle(screen, dir);
		int xa = 0, ya = 0;
		if(input.up1) ya--;
		if(input.down1) ya++;
		if(input.left1) xa--;
		if(input.right1) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya, screen); 
		ActivatebonusPoints();
	}
		
	private void posRandom(){
		x = Background.width_p/4;
		y = Background.height_p/2;
	}

	private void ActivatebonusPoints(){
		for(int i = 1; i <= 10; i++){
			if(getPoints() >=  ((i*100)+100) && getPoints() <= ((i*100)+110) && !activeBonusP && !world.playertwo.activeBonusP){
				for ( int j = 1; j <= 10; j++){
					world.barra.setXY(world.barra.x+1, world.barra.y);
				}
				activeBonusP = true;
				break;
			}
			if(getPoints() >= (i*100)+150 && getPoints() <= (i*100)+190 ){
				activeBonusP = false;
			}
		}
		if(getPoints() >= 50 && getPoints()<=60){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_pink);
				world.obstacle_r[i].setSprite(Sprite.obstaculo_blue);
			}
		}
		if(getPoints() >= 80 && getPoints()<=90){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_blue);
				world.obstacle_r[i].setSprite(Sprite.obstaculo_pink);
			}
		}
	}
	
}
