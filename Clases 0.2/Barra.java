package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Barra extends Mob {
	private Keyboard input;
	static int posX = (Background.width_p/2);
	
	
	public Barra(Keyboard input){
		this.input = input;
	}
	
	public Barra(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
	}
	
	public void update(){

	}
	
	public void move(int xa, int ya){
	}	

	

	public void render(Screen screen){
		//screen.renderMob(x-16, y-16, Sprite.player, 32);
		for (int i = 9; i < Background.height_p; i = i+16){
		//	screen.renderMob(posX, y-i, Sprite.barra2, 16);
			screen.renderMob(posX, y+i, Sprite.barra2, 16);
		//	screen.renderMob(posX-Sprite.barra1.SIZE, y-i, Sprite.barra1, 16);
			screen.renderMob(posX-Sprite.barra1.SIZE, y+i, Sprite.barra1, 16);
		}
	}
}
