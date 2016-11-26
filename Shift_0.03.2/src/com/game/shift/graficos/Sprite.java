package com.game.shift.graficos;

public class Sprite {
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	public int id;
	public static final int BORDE = 3;
	public static final int INFO = 10;
	
	public static Sprite universe = new Sprite (16, 0, 0, SpriteSheet.tiles, 0);
	public static Sprite player = new Sprite(8, 0, 0, SpriteSheet.player, 5);
	public static Sprite barra = new Sprite(16, 1, 3, SpriteSheet.tiles, 1);
	public static Sprite obstaculo_blue = new Sprite (4, 1, 0, SpriteSheet.bolitas, 3);
	public static Sprite obstaculo_pink = new Sprite (4, 2, 0, SpriteSheet.bolitas, 2);
	public static Sprite obstaculo_yellow = new Sprite (4, 2, 2, SpriteSheet.bolitas, 9);
	public static Sprite informacion = new Sprite (16,3,2, SpriteSheet.tiles, 7);
	public static Sprite bonus_t = new Sprite(6,0,0,SpriteSheet.bonus, 6);
	public static Sprite contorno = new Sprite(16,0,2, SpriteSheet.tiles, 1);
	public static Sprite arrow = new Sprite(16, 0, 3, SpriteSheet.tiles, 8);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet, int id){
		SIZE = size;
		pixels = new int [SIZE*SIZE];
		this.x = x*SIZE;
		this.y = y*SIZE;
		this.sheet = sheet;
		this.id = id;
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
