package com.game.shift.graficos;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.*;

import com.game.shift.Screen;
import com.game.shift.Timing;
import com.game.shift.entity.mob.Barra;
import com.game.shift.entity.mob.Bonus;
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
	public static final int N_OBS = 10;
	private static final String loc1 = "res/files/score_p1.txt";
	private static final String loc2 = "res/files/socre_p2.txt";
	

	private Thread thread;
	private boolean running = false;
	public Keyboard key;
	public JFrame frame;
	public PlayerOne playerone;
	public PlayerTwo playertwo;
	public Obstacle obstacle_l[] = new Obstacle[N_OBS];
	public Obstacle obstacle_r[] = new Obstacle[N_OBS];
	public Barra barra;
	public Timing timer = new Timing();
	public Bonus tbonus;
	
	private BufferedImage image = new BufferedImage(width_p, height_p, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	
	public Background(){
		Dimension size = new Dimension(width_p*scale, height_p*scale);
		setPreferredSize(size);
		
		screen = new Screen(width_p,height_p);
		frame = new JFrame();
		key = new Keyboard();
		playerone = new PlayerOne(key, this, Sprite.player);
		playertwo = new PlayerTwo(key, this, Sprite.player);
		barra = new Barra(Sprite.barra);
		for(int i = 0; i < N_OBS; i++){
			obstacle_l[i] = new Obstacle(1, Sprite.obstaculo_blue, this);
			obstacle_r[i] = new Obstacle(2, Sprite.obstaculo_pink, this);
		}
		
		tbonus = new Bonus(this);
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
		for(int i = 0; i < N_OBS; i++){
			obstacle_l[i].update(screen);
			obstacle_r[i].update(screen);
		}
		tbonus.activate();
		if(tbonus.active){
			tbonus.update(screen);
		}
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
		for(int i = 0; i < N_OBS; i++){
			obstacle_l[i].render(screen);
			obstacle_r[i].render(screen);
		}
		if(tbonus.active){
			tbonus.render(screen);
		}
		
		barra.render(screen);
		timer.render();
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(new Color(0xF2F6FF));
		g.setFont(new Font("Hyperspace", 0, 16));
		g.drawString(timer.timerString(), (width_p/2)*scale-10, 20);
		g.drawString(playerone.toString(), 10, 20);
		g.drawString(playertwo.toString(), 280*scale, 20);
		g.drawString(playerone.getsms(), 75*scale, 20);
		g.drawString(playertwo.getsms(), 200*scale, 20);
		g.dispose();
		bs.show();
		if(playerone.getPoints() < 0 || playertwo.getPoints() < 0 || timer.getTime() == 0){
			running = false;
			this.writeScores();
		}
	}
	
	public void writeScores(){
		if(running == false){
			try{
				FileWriter fw1 = new FileWriter(loc1, true);
				FileWriter fw2 = new FileWriter(loc2, true);
				BufferedWriter bf1 = new BufferedWriter(fw1);
				BufferedWriter bf2 = new BufferedWriter(fw2);
				
				bf1.write(""+playerone.getPoints());
				bf1.newLine();
				bf2.write(""+playertwo.getPoints());
				bf2.newLine();
				bf1.close();
				bf2.close();
				fw1.close();
				fw2.close();
				
			}catch(Exception e){
				System.out.println(e.getStackTrace());
			}
		}
	}
}
