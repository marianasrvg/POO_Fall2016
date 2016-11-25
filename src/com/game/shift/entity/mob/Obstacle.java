package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;

public class Obstacle extends Mob {

	private int lado_terreno = 1;
	
	

	public Obstacle(int lado){
		this.lado_terreno = lado;
		posRandom();
	}

	public void update(Screen screen){
		move(screen);
	}
	
	public void move(Screen screen){
		if(collisionObstacle(screen)) {
			vx*= -1;
			vy *= -1;
		}
		if(collisionBarra(screen)) {
			vx*= -1;
		}
		if( x + vx >= Background.width_p - (Sprite.BORDE+2))  vx *= -1;
		if( x + vx <= 0)  vx *= -1;
		if( y + vy >= Background.height_p - (Sprite.BORDE+2))  vy *= -1;
		if( y + vy <= Sprite.INFO)  vy *= -1;
		
		x += vx;
		y += vy;
		
		
	}
	
	protected boolean collisionBarra(Screen screen){
		try{
			if(screen.pixels[x+y*Background.width_p] == 0xFF56E0FF)
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}
	
	protected boolean collisionObstacle(Screen screen){
		try{
			if(screen.pixels[x+y*Background.width_p] == 0xFF7FFF8E) 
				return true;
			if(screen.pixels[x+y*Background.width_p] == 0xFFFF6890) 
				return true;
		} catch (Exception e){
			return true;
		}
		return false;
	}

	private void posRandom(){
		if(lado_terreno == 1){
			x = (int)(Math.random()*(Sprite.BORDE - (Barra.posX-Sprite.obstaculo_blue.SIZE)) + Barra.posX-Sprite.obstaculo_blue.SIZE);
			if (x >= Background.width_p/4 && x <= (Background.width_p/4)+Sprite.obstaculo_blue.SIZE)
				posRandom();
		}
		if(lado_terreno == 2)
			x = (int)(Math.random()* ((Barra.posX+Sprite.obstaculo_pink.SIZE)-(Background.width_p-Sprite.BORDE))+ Background.width_p-Sprite.BORDE);
			if( x >= 3*Background.width_p/4 && x <= (3*Background.width_p/4)+Sprite.obstaculo_blue.SIZE)
				posRandom();
		y =(int)(Math.random()*((Sprite.INFO+Sprite.BORDE)-(Background.height_p-Sprite.BORDE*2))+ Background.height_p-Sprite.BORDE*2);
		if( y >= Background.height_p/2 && y <= (Background.height_p/2)+Sprite.obstaculo_blue.SIZE)
			posRandom();
		//if x, y in range pos player again
	}
	
	public void render(Screen screen){
		if(lado_terreno == 1)
			screen.renderMob(x, y, Sprite.obstaculo_blue, 4);
		if(lado_terreno == 2)
			screen.renderMob(x, y, Sprite.obstaculo_pink, 4);
	}
}