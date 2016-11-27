package com.game.shift.graficos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
	private JLabel mainTitle = new JLabel("Keep the move!"); // Titulo del juego
	private JLabel ver = new JLabel("0.03.2"); //Version del juego
	private JButton play = new JButton("PLAY!"); //Boton para jugar
	private JButton howTo = new JButton("How to play"); //Boton para saber como se juega
	private JButton bye = new JButton("Exit game"); //Boton para salir del juego
	private JButton scores = new JButton("High Scores"); //Boton para ver los 
	
	public MainMenu(){
		this.setTitle("Keep the Move!");
		this.setSize(900,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		
		
		this.setVisible(true);
		
	}

	
	private void initComponents(){
		JPanel main = (JPanel) getContentPane();
		
		main.setLayout(null);
		main.setBackground(Color.DARK_GRAY);
		mainTitle.setBounds(400, 50, 80, 140);
		ver.setBounds(850, 450, 80, 30);
		play.setBounds(400, 200, 80, 50);
		howTo.setBounds(400, 260, 80, 50);
		bye.setBounds(400, 320, 80, 50);
		scores.setBounds(400, 400, 80, 50);
		
		play.addActionListener(this);
		howTo.addActionListener(this);
		
		main.add(mainTitle);
		main.add(ver);
		main.add(play);
		main.add(howTo);
		main.add(bye);
		main.add(scores);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton) e.getSource();
		if(j.equals(play)){
			new Background();
		}
		if(j.equals(howTo)){
			
		}if(j.equals(bye)){
			
		}
		
	}
	
}
