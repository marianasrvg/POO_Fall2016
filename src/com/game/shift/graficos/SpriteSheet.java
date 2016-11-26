package com.game.shift.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	public final int SIZE;
	public int[] pixels;
	// BACKGROUND - BARRA - ROSA - VERDE - BLANCO - PLAYER - BONUS - INFO - ARROW
	public static final int COLORES[] = {0x00000000, 0xFF56E0FF, 0xFFFF6890, 
			0xFF7FFF8E, 0xFFFFFFFF, 0xFFFEFFC4, 0xFF7F16, 0x00404040, 0x00FF265C};
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	public static SpriteSheet bolitas = new SpriteSheet("/textures/obstaclesheet.png", 256);
	public static SpriteSheet player = new SpriteSheet("/textures/playersheet.png", 256);
	public static SpriteSheet bonus = new SpriteSheet("/textures/bonusheet.png", 256);
	
	public SpriteSheet(String path, int size){
		this.path = path;
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	private void load(){
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0,0,w,h,pixels,0,w);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
