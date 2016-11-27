package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.graficos.SpriteSheet;
import com.game.shift.input.Keyboard;

public class Player extends Bonus {
	protected Keyboard input;
	protected int points;
	protected boolean take = false;
	protected boolean arrBonus[] = {false, false, false};
	public static int mPoints = 100;
	private int lessP = -10;
	private String sms = "";
	protected long bonus_Time = 0;
	
	
	public Player(Keyboard input, Background world, Sprite sprite){
		this.input = input;
		this.points = mPoints;
		this.world = world;
		this.sprite = sprite;
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
	
	public String getsms(){
		return sms;
	}
	
	protected void setsms(String s){
		sms = s;
		return;
	}
	
	
	protected boolean collisionBorder(Screen screen, int dir){
		try{
			switch(dir){
				case 0:
					if(screen.pixels[x+(y-vy)*Background.width_p] == SpriteSheet.COLORES[world.barra.sprite.id]){
						lessPoint();
						return true;
					}
					break;
				case 1:
				//	for(int z = 0; z < vx; z++){ con esto podriamos checar con el cambio de velocidad
					for(int i = 0; i < this.sprite.SIZE; i++){
							if(screen.pixels[(x+i+vx)+y*Background.width_p] == SpriteSheet.COLORES[world.barra.sprite.id]){
								lessPoint();
								return true;
							}
					}
				//	}
					break;
				case 2:
					for(int i = 0; i < this.sprite.SIZE; i++){
						if(screen.pixels[x+(y+i+vy)*Background.width_p] == SpriteSheet.COLORES[world.barra.sprite.id]){
							lessPoint();
							return true;
						}
					}
					break;
				case 3:
					if(screen.pixels[(x-vx)+y*Background.width_p] == SpriteSheet.COLORES[world.barra.sprite.id]){
						lessPoint();
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
				for(int i = 0; i < Sprite.player.SIZE; i++){
					for(int j = 0; j < Sprite.player.SIZE; j++){
						if(screen.pixels[(x+j)+(y+i)*Background.width_p] == SpriteSheet.COLORES[world.obstacle_l[0].sprite.id] || 
								screen.pixels[(x+j)+(y+i)*Background.width_p] == SpriteSheet.COLORES[world.obstacle_r[0].sprite.id] ){
							if(!take && arrBonus[1]){
								//No se modifican los puntos y eso asi que aqui no se manda a llamar la funcion
								take = true;
							}
							if(!take){
								//sdSystem.out.println("true");
								setPoints(-1);
								take = true;
							}
						}
					}
				}
		}catch(Exception e){
		}
		take = false;
	}
	
	protected void setBonusActive(){
		if(taken){
			int rand = (int)(Math.random() * (3));
			arrBonus[rand] = true;
		}
	}

	public void render(Screen screen){
		screen.renderMob(x, y, sprite, sprite.SIZE);
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
	
	protected void bonusBarra(){}
	
	protected void checkBonus(){
		if(arrBonus[0]){
			this.setSprite(Sprite.player_b);
			arrBonus[0] = false;
			setlessP(0);
			world.tbonus.taken = false;
			taken = false;
			setsms("Protection MODE ON!");
			bonus_Time = world.timer.getTime();
		}
		if(arrBonus[1]){
			this.setPoints(10);
			arrBonus[1] = false;
			world.tbonus.taken = false;
			taken = false;
			setsms("+10 POINTS");
			bonus_Time = world.timer.getTime();
		}
		if(arrBonus[2]){
			bonusBarra();
			arrBonus[2] = false;
			world.tbonus.taken = false;
			taken = false;
			setsms("Size does matter");
			bonus_Time = world.timer.getTime();
		}		
	}

	private void lessPoint(){
		setPoints(lessP);
	}
	
	private void setlessP( int l){
		lessP = l;
	}

	protected void timeClear(){
		if((bonus_Time - world.timer.getTime()) % 1000 == 0){
			setsms("");
			this.setSprite(Sprite.player);
			setlessP(-10);
			bonus_Time = 0;
		}
	}
}
