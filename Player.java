/**
 * @(#)Player.java
 *
 * @author Forrest Boyer
 * @version 1.00 2019/5/28
 */

import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Player{
	
	private int cornerX, cornerY, bottomY; // Top Left Corner
	private int height, width; //width and height of player
  	private Color color; //color of player
  	private boolean colliding = false;
  	private double repChance = .1, deathChance = .095, mutationVariance = .05;
  	
  	public Player(Player p){
  		cornerX = p.getX();
  		cornerY = p.getY();
  		height = p.getHeight();
  		width = p.getWidth();
  		color = p.getColor();
  		bottomY = cornerY + 50;
  		repChance = p.getRepChance();
  		deathChance = p.getDeathChance();
  		mutationVariance = p.getMutationVariance();
  	}

    public Player(int x, int y, int w, int h, Color c, double rep, double death, double mutVar) {
    	cornerX = x;
    	cornerY = y;
    	height = h;
    	width = w;
    	color = c;
    	bottomY = cornerY + 50;
    	repChance = rep;
    	deathChance = death;
    	mutationVariance = mutVar;
    }
    
    public Player(int x, int y, int w, int h, Color c) {
    	cornerX = x;
    	cornerY = y;
    	height = h;
    	width = w;
    	color = c;
    	bottomY = cornerY + 50;
    }
    
    public int getGreen(){
    	return color.getGreen();
    }
    
    public Player copy(){
    	Player p = new Player(cornerX, cornerY, width, height, color, repChance, deathChance, mutationVariance);
    	return p;
    }
    
    public double getMutationVariance(){
    	return mutationVariance;
    }
    
    public void mutate(){
    	int choice = (int)(Math.random() * 4);
    	if(choice == 0 && repChance != 1){
    		repChance += mutationVariance;
    		width++;
    		height++;
    		if(color.getGreen() != 255)
    			color = new Color(color.getRed(), color.getGreen() + 1, color.getBlue());
    	}
    	else if(choice == 1 && repChance != 0){
    		repChance -= mutationVariance;
    		width--;
    		height--;
    		if(color.getGreen() != 0)
    			color = new Color(color.getRed(), color.getGreen() - 1, color.getBlue());
    }
    	else if(choice == 2 && deathChance != 1){
    		deathChance += mutationVariance;
    		width--;
    		height--;
    		if(color.getGreen() != 0)
    			color = new Color(color.getRed(), color.getGreen() - 1, color.getBlue());
    	}
    	else if(choice == 3 && deathChance != 0){
    		deathChance -= mutationVariance;
    		width++;
    		height++;
    		if(color.getGreen() != 255)
    			color = new Color(color.getRed(), color.getGreen() + 1, color.getBlue());
    	}
    }
    
    public void colorUp(){
    	
    }
    
    public void colorDown(){
    	
    }
    
    public void setRepChance(double x){
    	repChance = x;
    }
    
    public void setDeathChance(double x){
    	deathChance = x;
    }
    
    public double getRepChance(){
    	return repChance;
    }
    
    public double getDeathChance(){
    	return deathChance;
    }
    
   public Color getColor(){
   		return color;
    }
   
   public int getBottomY(){
   		return bottomY;
   }

   public int getHeight(){
   		return height;
   }
   
    public void setHeight(int h)
   {
   		height = h;
   }
   
    public void setWidth(int w)
   {
   		width = w;
   }
   
    public int getWidth(){
   	  return width;
   }
   
    public int getX(){
   	  return cornerX;
   }
   
    public int getY(){
   	  return cornerY;
   }
   
    public void setX(int x){
   	  cornerX = x;
   }
    public void setY(int x){
   	  cornerY = x;
   }
    
    public void moveLeft() {
   		//Left border
    		cornerX -= 20;
    		if(cornerX < 0)
    			cornerX = 480;
    }
    
    public void moveRight() {
    	//Right border
    		cornerX += 20;	
    		if(cornerX > 500)
    			cornerX = 0;
    } 

    public boolean getColliding(){
    	return colliding;
    }
    
    public void setColliding(boolean f){
    	colliding = f;
    }
   
    public void draw(Graphics g){
    	g.setColor(getColor());
    	g.fillOval(getX(),getY(), getWidth(),getHeight());
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
    
    public String toString(){
    	return "CornerX - " + cornerX + "\nCornerY - " + cornerY + "\nHeight - " + height + "\nWidth - " + width + "\nColor - " + color + "\nReplication Chance - " + repChance + "\nDeath Chance - " + deathChance;
    }
}