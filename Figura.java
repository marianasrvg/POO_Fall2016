package shift;

import java.lang.String;

public abstract class Figura {
	private final int MAX_SIZE = 250;
	public final double MAX_VEL = 200;
	private final int X = 1024, Y = 680;
	private final int S = 5;
	private final double V = 2;
	private int size;
	private int posX, posY;
	private double velocidad;
	private String image = null;
	
	
	public Figura(){
		//dónde lo voy a crear
		posX = (int)(Math.random()*X);
		posY = (int)(Math.random()*Y);
		//de donde voy a sacar image
		this.image = ""; 
		//con que velocidad empieza
		this.velocidad = 1;
		
	}
	public void mover (int x, int y){
		posX = (x<0)?0:(x>this.X)?this.X:x;
		posY = (y<0)?0:(y>this.Y)?this.Y:y;
	}
	public void cambiarTamaño (int tamaño){
		int temp = 0;
		temp = this.size;
		
		if(tamaño == 0) return;
		else if (tamaño == -1) temp -= this.S;
		else temp += this.S;
		
		if (temp > MAX_SIZE) this.size = MAX_SIZE;
		else this.size = temp;
		
		return;
	}
	
	public void velocidad (int velocidad){
		this.velocidad = (velocidad>MAX_VEL)?MAX_VEL:(velocidad<0)?0:velocidad;
		return;
	}
	
	public void cambiarImagen(String s){
		this.image = s;
	}
	public boolean prediccionColision(Terreno t, Figura f){
		
	}
	
}
