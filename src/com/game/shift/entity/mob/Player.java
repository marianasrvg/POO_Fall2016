package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Player extends Mob {
	protected Keyboard input;
	protected int points;
	protected boolean take = false;
	protected boolean arrBonus[] = {false, false, false};
	protected boolean activBonus = false;
	public static int mPoints = 10;
	
	public Player(Keyboard input){
		this.input = input;
		this.points = mPoints;
	}
	
	public void setPoints(int x){
		if(arrBonus[0]){
			points += (2*x);
		}else{
			points += x;
		}
	}
	
	public int getPoints(){
		return points;
	}
	
	public String toString(){
		if (getPoints()<0) return "0";
		return String.valueOf(getPoints());
	}
	
	protected boolean collisionBorder(Screen screen, int dir){
		try{
			switch(dir){//////
				case 0:
					if(screen.pixels[x+(y-vy)*Background.width_p] == 0xFF56E0FF){
						setPoints(-5);
						return true;
					}
					break;
				case 1:
				//	for(int z = 0; z < vx; z++){ con esto podriamos checar con el cambio de velocidad
					for(int i = 0; i < Sprite.player.SIZE; i++){
							if(screen.pixels[(x+i+vx)+y*Background.width_p] == 0xFF56E0FF){
								setPoints(-5);
								return true;
							}
					}
				//	}
					break;
				case 2:
					for(int i = 0; i < Sprite.player.SIZE; i++){
						if(screen.pixels[x+(y+i+vy)*Background.width_p] == 0xFF56E0FF){
							setPoints(-5);
							return true;
						}
					}
					break;
				case 3:
					if(screen.pixels[(x-vx)+y*Background.width_p] == 0xFF56E0FF){
						setPoints(-5);
						return true;
					}
					break;		
			}
		}catch (Exception e){
			return true;
		}
		return false;
	}
	
	protected void collisionObstacle(Screen screen, int dir){
		try{
			//switch(dir){
			//case 0:
				for(int i = 0; i < Sprite.player.SIZE; i++){
					for(int j = 0; j < Sprite.player.SIZE; j++){
						if(screen.pixels[(x+j)+(y+i)*Background.width_p] == 0xFF7FFF8E || 
								screen.pixels[(x+j)+(y+i)*Background.width_p] == 0xFFFF6890){
							if(!take && arrBonus[1]){
								//No se modifican los puntos y eso asi que aqui no se manda a llamar la funcion
								take = true;
							}
							if(!take){
								System.out.println("true");
								setPoints(-1);
								take = true;
							}
						}
					}
				}
				//break;
			//case 1:
	/*			for(int i = 0; i < Sprite.player.SIZE; i++){
					for(int j = 0; j < Sprite.player.SIZE; j++){
						if(screen.pixels[(x+i)+(y+j)*Background.width_p] == 0xFF7FFF8E || 
								screen.pixels[(x+i)+(y+j)*Background.width_p] == 0xFFFF6890){
							if(!take){
								System.out.println("true");
								setPoints(-1);
								take = true;
							}
						}
					}
				}
				//break;
			//case 2:
				for(int i = 0; i < Sprite.player.SIZE; i++){
					for(int j = 0; j < Sprite.player.SIZE; j++){
						if(screen.pixels[(x+j)+(y+i)*Background.width_p] == 0xFF7FFF8E ||
								screen.pixels[(x+j)+(y+i)*Background.width_p] == 0xFFFF6890){
							if(!take){
								System.out.println("true");
								setPoints(-1);
								take = true;
							}	
						}
					}
				}
			//	break;
			//case 3:
				for(int i = 0; i < Sprite.player.SIZE; i++){
					for(int j = 0; j < Sprite.player.SIZE; j++){
						if(screen.pixels[(x+i)+(y+j)*Background.width_p] == 0xFF7FFF8E ||
								screen.pixels[(x+i)+(y+j)*Background.width_p] == 0xFFFF6890){
							if(!take){
								System.out.println("true");
								setPoints(-1);
								take = true;
							}
						}
					}
				}*/
			//	break;		
		//	}
		}catch(Exception e){
			
		}
	}
	
	protected void setBonusActive(){
		if(this.activBonus){
			int rand = (int)(Math.random() * (3));
			arrBonus[rand] = true;
		}
	}
	/*
	protected void wichBonus(){
		if(arrBonus[0]){
			//hacer puntos por 2
			
		}
		if(arrBonus[1]){
			//inmune a colisiones con objetos(escudito)
>>>>>>> origin/master
		}
		} catch (Exception e){
		}
		take = false;
		return;
	}
<<<<<<< HEAD
	
=======
	*/
	public void render(Screen screen){
		
		if(arrBonus[1]){
			//Cambiar el render al del jugador con escutido y asi. 
			screen.renderMob(x, y, Sprite.player, 8);
		}
		
		screen.renderMob(x, y, Sprite.player, 8);
	}

	public void move(int xa, int ya, Screen screen){
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if ( dir == 0 && collisionBorder(screen, dir)) ya++;
		if ( dir == 1 && collisionBorder(screen, dir)) xa--;
		if ( dir == 2 && collisionBorder(screen, dir)) ya--;
		if ( dir == 3 && collisionBorder(screen, dir)) xa++;
		
		x += xa;
		y += ya;
	}

}
