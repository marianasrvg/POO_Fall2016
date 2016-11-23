package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class PlayerOne extends Mob {
	
	private Keyboard input;
	private int points;
	private boolean take = false;
	
	
	
	public PlayerOne(Keyboard input){
		this.input = input;
		posRandom();
		points = 10;
	}
	
	public void setPoints(int x){
		points += x;
	}
	public int getPoints(){
		return points;
	}
	
	public String toString(){
		if (getPoints()<0) return "0";
		return String.valueOf(getPoints());
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
		
		if(collisionBarra(screen)) xa--;
		
		
		if( x + xa >= Background.width_p-8) xa--;
		if( x + xa <= 0) xa++;
		if( y + ya >= Background.height_p-8) y--;
		if( y + ya <= 10) ya++;
		
		x += xa;
		y += ya;	
	}
	
	private boolean collisionBarra(Screen screen){
		try{
			if(screen.pixels[(x+y*Background.width_p)+7] == 0xFF56E0FF){
				setPoints(-5);
				return true;
			}
		}catch (Exception e){
			return true;
		}
		return false;
	}
	
	public void collisionObstaculos(Screen screen){
		try{
			for(int i = 0; i < 8; i++){
				for(int j = 0; j < 8; j++){
					if(screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFF7FFF8E || screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFFFF6890) {
						if(!take){ 
							setPoints(-1);
							take = true;
						}
					}
				}
			}
			
		} catch (Exception e){
			//System.out.println("die");
		}
		take = false;
		return;
	}
	

	
	private void posRandom(){
		x = (int)(Math.random()*(Barra.posX - 10));
		y = 10 + (int)(Math.random()*Background.height_p);
	}

	public void render(Screen screen){
		screen.renderMob(x, y, Sprite.player, 8);
		//collisionObstaculos(screen);
	}
	
}
