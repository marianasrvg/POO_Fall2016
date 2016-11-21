package com.game.shift.graficos;

public class Sprite {
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite universe = new Sprite (16, 0, 0, SpriteSheet.tiles);
	public static Sprite player = new Sprite(8, 0, 0, SpriteSheet.player);
	public static Sprite barra1 = new Sprite (16, 1, 2, SpriteSheet.tiles);
	public static Sprite barra2 = new Sprite (16, 2, 2, SpriteSheet.tiles);
	public static Sprite obstaculo_blue = new Sprite (4, 1, 0, SpriteSheet.bolitas);
	public static Sprite obstaculo_pink = new Sprite (4, 2, 0, SpriteSheet.bolitas);
	public static Sprite informacion = new Sprite (16,3,2, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int [SIZE*SIZE];
		this.x = x*SIZE;
		this.y = y*SIZE;
		this.sheet = sheet;
		load();
	}
	
	public void load(){
		for ( int y = 0; y < SIZE; y++){
			for (int  x = 0; x < SIZE; x++){
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x)+(y+this.y)*sheet.SIZE];
			}
		}
	}
}
