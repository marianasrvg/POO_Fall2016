package com.game.shift.graficos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScoresWindow extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel score = new JLabel("Scores");
	private JLabel p1score = new JLabel("Player 1 Score");
	private JLabel p2score = new JLabel("Player 2 Score");
	private Vector<String> readP1File = new Vector<String>();
	private Vector<String> readP2File = new Vector<String>();
	private JList<String> scores_1 = new JList<String>(readP1File);
	private JList<String> scores_2 = new JList<String>(readP2File);
	private JScrollPane show1 = new JScrollPane(scores_1);
	private JScrollPane show2 = new JScrollPane(scores_2);
	private JButton back = new JButton("<-Back");
	private MainMenu mainM;
	
	public ScoresWindow(MainMenu main){
		this.setTitle("Scores");
		this.setSize(900,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.initComponents();
		this.setVisible(true);
		mainM = main;
	}
	
	
	private void initComponents(){
		JPanel main = (JPanel)getContentPane();
		
		main.setLayout(null);
		score.setBounds(350, 15, 80, 30);
		p1score.setBounds(200, 20, 80, 30);
		p2score.setBounds(310, 20, 80, 30);
		show1.setBounds(200, 60, 80, 100);
		show2.setBounds(310, 60, 80, 100);
		back.setBounds(10, 10, 80, 30);
		back.addActionListener(this);
		main.add(score);
		main.add(p1score);
		main.add(p2score);
		main.add(show1);
		main.add(show2);
		main.add(back);
	}
	
	public void showScores(){
		mainM.setVisible(false);
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton j = (JButton)e.getSource();
		if(j.equals(back)){
			this.setVisible(false);
			mainM.setVisible(true);
		}
	}
}
