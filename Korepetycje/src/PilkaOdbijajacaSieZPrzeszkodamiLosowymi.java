import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PilkaOdbijajacaSieZPrzeszkodamiLosowymi extends JPanel {
	int x = 25;
	int y = 25;
	int xspeed = 2;
	int yspeed = 1;
	int srednica = 25;
	Color kolor = Color.RED;
	Color kolor2 = Color.BLACK;
	Rectangle[] kwadraty;
	int xkw = 0;
	int ykw = 0;
	Random rand = new Random();
	
	public Dimension getPreferredSize() {
		return  new Dimension(500,500);
	}
	
	public PilkaOdbijajacaSieZPrzeszkodamiLosowymi(){
		
		kwadraty = new Rectangle[3];
		
		for(int i = 0; i < kwadraty.length; i++) {
			xkw = 1+(int)(Math.random() *(400/50));
			ykw = 1+(int)(Math.random() *(400/50));
			kwadraty[i] = new Rectangle(xkw*50,ykw*50,50,50);
		}
		
		for(int i = 0; i < kwadraty.length; i++) {
			for(int j = i+1; j < kwadraty.length; j++) {
				while (kwadraty[i].intersects(kwadraty[j])) {
					xkw = 1+(int)(Math.random() *(400/50));
					ykw = 1+(int)(Math.random() *(400/50));
					kwadraty[j] = new Rectangle(xkw*50,ykw*50,50,50);
				}
			}
		}
		
	}
	
	
	protected void paintComponent(Graphics  g) {
		super.paintComponent(g);
		
		
		g.setColor(kolor2);
		for(int i = 0; i < kwadraty.length; i++) {
			g.fillRect(kwadraty[i].x,kwadraty[i].y,kwadraty[i].width,kwadraty[i].height);
		}
		g.setColor(kolor);
		g.fillOval(x, y, srednica, srednica);
	}
	
	public void animacja() {
		
		boolean b =true;
		
		while(b) {
			x= x + xspeed;
			if((x+srednica) >= getWidth()) {
				xspeed= -xspeed;
			} else if (x<=0) {
				xspeed= -xspeed;
			}
			y= y + yspeed;
			if((y+srednica) >= getHeight()){
				yspeed= -yspeed;
			} else if (y<=0) {
				yspeed= -yspeed;
			}
			
			for(int i = 0; i < kwadraty.length; i++) {
				if (collides(x,y,srednica,kwadraty[i])){
					
					double closestX = clamp(x +(double)(srednica/2), kwadraty[i].x, kwadraty[i].x + kwadraty[i].width);
					double closestY = clamp(y +(double)(srednica/2), kwadraty[i].y, kwadraty[i].y + kwadraty[i].height);
					
					double distanceX = Math.abs(x +(double)(srednica/2) - closestX);
					double distanceY = Math.abs(y +(double)(srednica/2) - closestY);
					
					if (distanceX>distanceY) {
						xspeed= -xspeed;
						if (distanceX < (double)(srednica/2) - Math.abs(xspeed)) {
							x = x + (xspeed/Math.abs(xspeed))*(int)((double)(srednica/2)-distanceX);
						}
					}
					else if (distanceX<distanceY) {
						yspeed= -yspeed;
						if (distanceY < (double)(srednica/2) - Math.abs(yspeed)) {
							y = y + (yspeed/Math.abs(yspeed))*(int)((double)(srednica/2)-distanceY);
						}
					}
					else if (distanceX==distanceY){
						xspeed= -xspeed;
						yspeed= -yspeed;
						if ((distanceX < (double)(srednica/2) - Math.abs(xspeed))
							&& (distanceY < (double)(srednica/2) - Math.abs(yspeed))){
							x = x + (xspeed/Math.abs(xspeed))*(int)((double)(srednica/2)-distanceX);
							y = y + (yspeed/Math.abs(yspeed))*(int)((double)(srednica/2)-distanceY);
						}
					}
				}
			}

			
			try {
				Thread.sleep(4);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	public boolean collides(double x, double y, double srednica, Rectangle r1) {
		double closestX = clamp(x +(double)(srednica/2), r1.x, r1.x + r1.width);
		double closestY = clamp(y +(double)(srednica/2), r1.y, r1.y + r1.height);
		
		double distanceX = x +(double)(srednica/2) - closestX;
		double distanceY = y +(double)(srednica/2) - closestY;
		return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow((double)(srednica/2), 2);
	}
	 
	public static double clamp(double value, double min, double max) {
		double x = value;
	    if (x < min) {
	        x = min;
	    } else if (x > max) {
	        x = max;
	    }
	    return x;
	}
	
	public static void main(String[] args) {
		JFrame window  = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PilkaOdbijajacaSieZPrzeszkodamiLosowymi pilka = new PilkaOdbijajacaSieZPrzeszkodamiLosowymi();
		window.add(pilka);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		
		pilka.animacja();

	}

}
