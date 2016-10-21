package shift;

public class Jugador extends Figura {
	private String nombre = "";
	private boolean opacidad = true;
	private boolean colision = false;
	private int u_timer = 0;
	private int timer = 0;
	
	public Jugador(){
		super();
	}
	public Jugador(String n){
		this();
		this.asignarNombre(n);
	}
	public void asignarNombre(String s){
		nombre = s;
		return;
	}
	public String getNombre(){
		return nombre;
	}
	public void setOpacidad(boolean b){
		opacidad = b;
		return;
	}
	public boolean getOpacidad(){
		return this.opacidad;
	}
	public void setColision(boolean b){
		this.colision = b;
	}
	public boolean getColision(){
		return colision;
	}
	public void setU_Timer(int u_time){
		this.u_timer = u_time;
	}
	public int getU_Timer(){
		return this.u_timer;
	}
	public void setTimer(int time){
		this.timer = time;
	}
	public int getTimer(){
		return this.timer;
	}
	
	
	
}
