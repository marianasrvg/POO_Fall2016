package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;
import com.game.shift.graficos.SpriteSheet;

public class Bonus extends Mob {

	public boolean taken = false;
	public boolean active = false;
	
	public Bonus(){}
	

	public Bonus(Background world) {
		this.world = world;
		spawnRand();
		activate();
	}

	public void update(Screen screen) {
		move(screen);
		activate();
		collisionPlayer(screen);
	}

	public void move(Screen screen) {
		if (x + vx > Background.width_p - Sprite.bonus_t.SIZE)
			vx *= -1;
		if (x + vx <= Sprite.BORDE)
			vx *= -1;
		if (y + vy > Background.height_p - Sprite.bonus_t.SIZE)
			vy *= -1;
		if (y + vy <= Sprite.INFO)
			vy *= -1;

		x += vx;
		y += vy;
	}

	private void spawnRand() {
		x = (int) (Math.random() * (Sprite.BORDE - (Background.width_p-Sprite.BORDE)) + (Background.width_p-Sprite.BORDE));
		y = (int) (Math.random() * ((Sprite.BORDE+Sprite.INFO)-(Background.height_p - Sprite.BORDE ))+ (Background.height_p - Sprite.BORDE ));
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, Sprite.bonus_t, 6);
	}

	public void activate() {
		if(world.timer.getTime() % 12 == 0){
			active = true;
		}
	}

	public void collisionPlayer(Screen screen) {
		try {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (screen.pixels[(x + j) + ((y + i) * Background.width_p)] == SpriteSheet.COLORES[world.playerone.sprite.id]
							|| screen.pixels[(x + j) + ((y + i) * Background.width_p)] == SpriteSheet.COLORES[world.playertwo.sprite.id]) {
						taken = true;
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
