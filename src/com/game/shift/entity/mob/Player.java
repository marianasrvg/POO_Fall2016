package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Player extends Mob {
	protected Keyboard input;
	protected int points;
	protected boolean take = false;
	protected boolean activBonus = false;
	protected boolean arrBonus[] = {false, false, false};
	protected boolean pointsBonus = false;
	public static int mPoints = 10;
	
	public Player(Keyboard input){
		this.input = input;
		this.points = mPoints;
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
	
	protected boolean collisionBarra(Screen screen){
		try{
			for(int i = -1; i < 8; i++){
				for(int j = -1; j < 8; j++){
					if(screen.pixels[((x+j)+(y+i)*Background.width_p)] == 0xFF56E0FF){
						setPoints(-5);
						return true;
					}
				}
			}
		}catch (Exception e){
			return true;
		}
		return false;
	}
	
	protected void collisionObstaculos(Screen screen){
		try{
			for(int i = 0; i < Sprite.player.SIZE; i++){
				for(int j = 0; j < Sprite.player.SIZE; j++){
					if(screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFF7FFF8E || screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFFFF6890) {
						if(!take){ 
							setPoints(-1);
							take = true;
						}
					}
				}
			}
		} catch (Exception e){
		}
		take = false;
		return;
	}
	
	protected void collisionBonus(Screen screen){
		try{
			for(int i = 0; i < Sprite.bonus_t.SIZE; i++){
				for(int j = 0; j < Sprite.bonus_t.SIZE; j++){
					if(screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFFFF7F16 || screen.pixels[(x+j)+((y+i)*Background.width_p)] == 0xFFFF7F16){
						this.activBonus = true;
					}else{
						
					}
				}
			}
		}catch(Exception e){
			
		}
	}
	
	protected void setBonusActive(){
		if(this.activBonus){
			int rand = (int)(Math.random() * (3));
			arrBonus[rand] = true;
		}
	}
	
	protected void wichBonus(){
		if(arrBonus[0]){
			//hacer puntos por 2
		}
		if(arrBonus[1]){
			//inmune a colisiones con objetos(escudito)
		}
		if(arrBonus[2]){
			//inmune a las colisiones con las barras
		}
	}
	
	protected void 
	
	public void render(Screen screen){
		screen.renderMob(x, y, Sprite.player, 8);
	}
}
