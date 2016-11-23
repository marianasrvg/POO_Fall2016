package com.game.shift.graficos;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.*;

import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.entity.mob.Barra;
import com.game.shift.entity.mob.Bonus;
import com.game.shift.entity.mob.Mob;
import com.game.shift.entity.mob.Obstacle;
import com.game.shift.entity.mob.PlayerOne;
import com.game.shift.entity.mob.PlayerTwo;
import com.game.shift.input.Keyboard;

public class Background extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static int width_p = 300;
	public static int height_p = width_p / 16 * 9;
	public static int scale = 3;
	public static String title = "Shift";
	public static final int N_OBS = 20;
	

	private Thread thread;
	private boolean running = false;
	private Keyboard key;
	private JFrame frame;
	private PlayerOne playerone;
	private PlayerTwo playertwo;
	private Obstacle obstaculo[] = new Obstacle[N_OBS];
	private Barra barra;
	private Timing timer = new Timing();
	private Bonus tbonus;
	
	private BufferedImage image = new BufferedImage(width_p, height_p, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	public Background(){
		Dimension size = new Dimension(width_p*scale, height_p*scale);
		setPreferredSize(size);
		
		screen = new Screen(width_p,height_p);
		frame = new JFrame();
		key = new Keyboard();
		playerone = new PlayerOne(key);
		playertwo = new PlayerTwo(key);
		for(int i = 0; i < N_OBS/2; i++){
			obstaculo[i] = new Obstacle(key, 1);
			obstaculo[i+(N_OBS/2)] = new Obstacle(key,2);
		}
		barra = new Barra(key);
		tbonus = new Bonus(timer);
		frameCaracteristicas();
				
	}
	
	private void frameCaracteristicas(){
		frame.addKeyListener(key);
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop(){
		running = false;
		try{
			thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis(); 
		final double ns = 1000000000.0 / 60.0; 
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		
		while(running){
			long now = System.nanoTime();
			delta+= (now-lastTime) /ns;
			lastTime = now;
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
				if( System.currentTimeMillis() - timer > 1000){ //lo va a hacer una vez por sec. 
					timer += 1000;
					frame.setTitle(title+ " | "+ updates + "ups, " + frames + " fps");
					updates = 0;
					frames = 0;
				}
		}
	}
	
	public void update(){
		key.update();
		playerone.update(screen);
		playertwo.update(screen);
		for(int i = 0; i < obstaculo.length; i++){
			obstaculo[i].update(screen);
		}
		tbonus.update(screen, timer);
		if(tbonus.active){
			tbonus.update(screen, timer);
		}
		//playerone.collisionObstaculos(screen);
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if ( bs == null){
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		screen.render();
		screen.setOffset(0, 0);
		playerone.render(screen);
		playertwo.render(screen);
		for(int i = 0; i < obstaculo.length; i++){
			obstaculo[i].render(screen);
		}
		if(tbonus.active){
			tbonus.render(screen);
		}
		
		
		barra.render(screen);
		timer.render();
		if(timer.itsTime()){
			playerone.setPoints(Mob.mPoints);
			playertwo.setPoints(Mob.mPoints);
		}
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0xF2F6FF));
		g.setFont(new Font("Hyperspace", 0, 16));
		//System.out.println(timer.timerString());
		g.drawString(timer.timerString(), (width_p/2)*scale-30, 20);
		g.drawString(playerone.toString(), 10, 20);
		g.drawString(playertwo.toString(), 280*scale, 20);
		
		g.dispose();
		bs.show();
		if(playerone.getPoints() < 0 || playertwo.getPoints() < 0){
			stop();
		}
		
	}	 
}
