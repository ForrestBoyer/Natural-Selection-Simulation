/**
 * @(#)NaturalSelectionPanel.java
 *
 * @author Forrest Boyer
 * @version 1.00 2019/3/5
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class NaturalSelectionPanel extends JPanel implements KeyListener, ActionListener {

	javax.swing.Timer timer;

	private double time = 0;
	private int score = 0, highscore = 0;
	private ArrayList<Player> players = new ArrayList<Player>();
	private double avgRepChance, avgDeathChance;
	private int avgMutNum;
	private int speed, size, mPS;
	private double rC, dC, mutVar;
	
	private JTextField simSpeed = new JTextField("100");
	private	JTextField popSize = new JTextField("100");
    private JTextField repChance = new JTextField(".1");
    private JTextField deathChance = new JTextField(".09");
    private JTextField maxPopSize = new JTextField("10000");
    private JTextField mutationVariance = new JTextField(".05");
		
    public NaturalSelectionPanel() {
    	
    	//---------------------- Option Dialog Box --------------------------------
    	
      	JPanel myPanel = new JPanel();
      	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
      	myPanel.add(new JLabel("Simulation Speed(ms) - Lower is faster:"));
     	myPanel.add(simSpeed);
     	myPanel.add(new JLabel("Starting Population size:"));
     	myPanel.add(popSize);
     	myPanel.add(new JLabel("Maximun Population size:"));
     	myPanel.add(maxPopSize);
     	myPanel.add(new JLabel("Starting Replication Chance:"));
     	myPanel.add(repChance);
     	myPanel.add(new JLabel("Starting Death Chance:"));
     	myPanel.add(deathChance);
     	myPanel.add(new JLabel("Mutation Variance:"));
     	myPanel.add(mutationVariance);
     	
     	int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Settings", JOptionPane.OK_CANCEL_OPTION);
      	if (result == JOptionPane.OK_OPTION) {
      		
      		speed = Integer.parseInt(simSpeed.getText());
      		mPS = Integer.parseInt(maxPopSize.getText());
      		size = Integer.parseInt(popSize.getText());
      		
      		rC = Double.parseDouble(repChance.getText());
      		dC = Double.parseDouble(deathChance.getText());
      		mutVar = Double.parseDouble(mutationVariance.getText());

      	}
    	
    	//---------------------------------------------------------------------------
    	
    	for(int i = 0; i < size; i++){
   			players.add(new Player((int)(Math.random() * 700), (int)(Math.random() * 650), 20, 20, new Color(255, 122, 0), rC, dC, mutVar));
   		}
    	
    	timer = new javax.swing.Timer(speed, this);
    	timer.start();
    	setBackground(Color.WHITE);
 		
 		addKeyListener(this);
 		setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    
    public void actionPerformed(ActionEvent E) {
    	time += speed;
    	for(int i = 0; i < players.size(); i++){
    		if(players.size() > mPS){
    			for(int j = 0; j < mPS - (mPS / 100); j++){
    				players.remove((int)(Math.random() * players.size() - 1));
    			}
    				
    		}
    		else if((double)(Math.random()) < players.get(i).getDeathChance()){
    			players.remove(i);
    			i--;
    		}
    		else if((double)(Math.random()) < players.get(i).getRepChance()){
    			
    			if(Math.random() < .1){
    				players.add(new Player(players.get(i).copy()));
    				players.get(0).mutate();
    			}
    			else
    				players.add(new Player((int)(Math.random() * 700), (int)(Math.random() * 650), players.get(i).getWidth(), players.get(i).getHeight(), players.get(i).getColor()));
    		}
    	}
    	double repSum = 0;
    	double deathSum = 0;
    	int mutSum = 0;
    	for(int i = 0; i < players.size(); i++){
    		repSum += players.get(i).getRepChance();
    		deathSum += players.get(i).getDeathChance();
    		mutSum += players.get(i).getGreen();
    	}
    	avgRepChance = repSum / players.size();
    	avgDeathChance = deathSum / players.size();
    	avgMutNum = mutSum / players.size();
    	repaint();
    }
    
    public void keyPressed(KeyEvent e) {
    	
    	switch(e.getKeyCode()) {
    		
    		case KeyEvent.VK_LEFT://left arrow
    			break;
    			
    		case KeyEvent.VK_RIGHT: //right arrow				
    			break;
    			
    		case KeyEvent.VK_SPACE://spacebar
    			break;
    			
    		default:
    			break;
    	}
    }
    
    	public void end(){

    	}
    	
    	public void paintComponent(Graphics g){
    		
     		 super.paintComponent(g);
     		 
     		 for(int i = 0; i < players.size(); i++){
     		 	players.get(i).draw(g);
     		 } 	
     		 
     		 g.setColor(Color.BLACK);
     		 
     		 score = players.size();
     		 
     		 g.drawString("Time: " + String.valueOf(time / 1000) + " seconds", 5, 720);
     		 g.drawString("Alive: " + String.valueOf(score), 5, 730);
     		 g.drawString("Avg Rep Chance: " + String.valueOf(avgRepChance), 5, 740);
     		 g.drawString("Avg Death Chance: " + String.valueOf(avgDeathChance), 5, 750);
     		 g.drawString("Avg Mutation Number: " + String.valueOf(avgMutNum), 5, 760);
     		 //g.drawString("Highscore: " + String.valueOf(highscore), 5, 650);
     		 	
    	}			 

}
    	
    	
    
    
    