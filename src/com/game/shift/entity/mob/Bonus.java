package com.game.shift.entity.mob;

import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.graficos.Background;
import com.game.shift.graficos.Sprite;

public class Bonus extends Mob {

	public boolean taken = false;
	public boolean active = false;
	

	public Bonus(Timing t) {
		spawnRand();
		activate(t);
	}

	public void update(Screen screen, Timing t) {
		move(screen);
		activate(t);
		collisionPlayer(screen);
	}

	public void move(Screen screen) {
		if (x + vx > Background.width_p - Sprite.bonus_t.SIZE)
			vx *= -1;
		if (x + vx <= 0)
			vx *= -1;
		if (y + vy > Background.height_p - Sprite.bonus_t.SIZE)
			vy *= -1;
		if (y + vy <= 9)
			vy *= -1;

		x += vx;
		y += vy;
	}

	private void spawnRand() {
		x = (int) (Math.random() * (Sprite.BORDE - (Background.width_p+Sprite.BORDE)) + (Background.width_p+Sprite.BORDE));
		y = (int) (Math.random() * ((Sprite.BORDE+Sprite.informacion.SIZE)-(Background.height_p + Sprite.BORDE ))+ (Background.height_p + Sprite.BORDE ));
	}

	public void render(Screen screen) {
		screen.renderMob(x, y, Sprite.bonus_t, 6);
	}

	public void activate(Timing t) {
		int mult = 1000;
		if (t.getTime() % mult == 0 && this.taken == false) {
			this.active = true;
			mult *= 10;
		} else if (this.taken == true) {
			this.active = false;
			this.taken = false;
		}		
		mult *= 1;
	}

	public void collisionPlayer(Screen screen) {
		try {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (screen.pixels[(x + j) + ((y + i) * Background.width_p)] == 0xFFFEFFC4
							|| screen.pixels[(x + j) + ((y + i) * Background.width_p)] == 0xFFFEFFC4) {
						taken = true;
					}
				}
			}
		} catch (Exception e) {

		}
	}
}
