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
		timeClear();
		collisionObstacle(screen, dir);
		int xa = 0, ya = 0;
		if(input.up1) ya--;
		if(input.down1) ya++;
		if(input.left1) xa--;
		if(input.right1) xa++;	
		
		if(xa!= 0 || ya!= 0) move (xa, ya, screen); 
		WarningBonus();
		setBonusActive();
		checkBonus();
		
	}
		
	private void posRandom(){
		x = Background.width_p/4;
		y = Background.height_p/2;
	}
	

	
	protected void bonusBarra(){
		for ( int j = 1; j <= 20; j++){
			world.barra.setXY(world.barra.x+1, world.barra.y);
		}
		arrBonus[2] = false;
	}

	private void WarningBonus(){
		if(getPoints() < 100 && getPoints() > 80){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_blue);
			}
			return;
		}
		if(getPoints() < 80 && getPoints() > 60){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_pink);
			}
			return;
		}
		if(getPoints() < 60 && getPoints() > 40){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_yellow);
			}
			return;
		}
		if(getPoints() < 40 && getPoints() > 20){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_blue);
			}
			return;
		}
		if(getPoints() < 20 && getPoints() > 0){
			for(int i = 0; i < Background.N_OBS; i++){
				world.obstacle_l[i].setSprite(Sprite.obstaculo_pink);
				setsms("20 points left");
			}
			return;
		}
	}
	
}
