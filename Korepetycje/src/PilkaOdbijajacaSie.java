import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PilkaOdbijajacaSie extends JPanel {
	int x = 50;
	int y = 50;
	int xspeed = 50;
	int srednica = 50;
	Color kolor = Color.RED;
	
	public Dimension getPreferredSize() {
		return  new Dimension(1000,500);
	}
	
	protected void paintComponent(Graphics  g) {
		super.paintComponent(g);
		
		g.setColor(kolor);
		g.fillOval(x, y, 50, 50);
	}
	
	public void animacja() {
		
		boolean b =true;
		
		while(b) {
			x= x + xspeed;
			if((x+srednica) >= getWidth()) {
				xspeed=-xspeed;	
			} else if (x<=0) {
				xspeed=-xspeed;	
			}
			

			
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	public static void main(String[] args) {
		JFrame window  = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PilkaOdbijajacaSie pilka = new PilkaOdbijajacaSie();
		window.add(pilka);
		window.setVisible(true);
		window.pack();
		
		pilka.animacja();

	}

}
