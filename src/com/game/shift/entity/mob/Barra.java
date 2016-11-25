package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.input.Keyboard;

public class Barra extends Mob {
	static int posX = (Background.width_p/2);
	
	
	public Barra(){
	}
	
	public static void changePosX(int pos){
		posX = pos;
	}
	
	public Barra(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){}
	
	public void move(int xa, int ya){}	

	public void render(Screen screen){
		for (int i = 9; i < Background.height_p; i = i+16){
			screen.renderMob(posX, y+i, Sprite.barra2, 16);
			screen.renderMob(posX-Sprite.barra1.SIZE, y+i, Sprite.barra1, 16);
		}
	}
}
