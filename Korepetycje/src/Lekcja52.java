import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class IntLocation {
	
    private int x;
    private int y;
    
    public IntLocation(int x, int y) {
    	
    	this.x = x;
    	this.y = y;
    	
    }
    
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

class IntSpeed {
	
	private int xvelocity;
	private int yvelocity;
	
	public IntSpeed(int xvelocity, int yvelocity) {
    	
    	this.xvelocity = xvelocity;
    	this.yvelocity = yvelocity;
    	
    }
    
	public int getXvelocity() {
		return xvelocity;
	}

	public void setXvelocity(int xvelocity) {
		this.xvelocity = xvelocity;
	}
	
	public int getYvelocity() {
		return yvelocity;
	}

	public void setYvelocity(int yvelocity) {
		this.yvelocity = yvelocity;
	}
}

class IntDimensions {
	
	private int x;
	private int y;
	
	public IntDimensions(int x, int y) {
    	
    	this.x = x;
    	this.y = y;
    	
    }
    
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}

class CosmosPanel extends JPanel implements ActionListener{
	
	Image Space, Meteor;
	private Graphics2D g2;
	Timer timer;
	final static Random random = new Random();
	ArrayList<IntLocation> meteoryLokalizacja = new ArrayList<IntLocation>();
	ArrayList<IntSpeed> meteoryPredkosc = new ArrayList<IntSpeed>();
	ArrayList<IntDimensions> meteoryWymiary = new ArrayList<IntDimensions>();
	
	public CosmosPanel() {
		
		plikiGraficzne();
		timer = new Timer(100, this);
		
	}
	
	public void plikiGraficzne() {
		
		try {
			Space = ImageIO.read(new File("Space.png"));
			Meteor = ImageIO.read(new File("Meteor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void rysujTlo(Graphics2D g2){
		g2.drawImage(Space, 0, 0, null);
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		g2 = (Graphics2D) g;
		timer.start();
		rysujTlo(g2);
		for(int i = 0; i < meteoryLokalizacja.size(); i++) {
			
			if (i != meteoryLokalizacja.size()-1)
				meteoryLokalizacja.set(i, new IntLocation( meteoryLokalizacja.get(i).getX() + meteoryPredkosc.get(i).getXvelocity(), meteoryLokalizacja.get(i).getY() + meteoryPredkosc.get(i).getYvelocity() ) );
			System.out.println("Lokalizacja x " + meteoryLokalizacja.get(i).getX() + " Lokalizacja y " + meteoryLokalizacja.get(i).getY());
			g2.drawImage(Meteor, meteoryLokalizacja.get(i).getX(), meteoryLokalizacja.get(i).getY(), meteoryWymiary.get(i).getX(), meteoryWymiary.get(i).getY(), null);
			
		}
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		meteoryWymiary.add( new IntDimensions( (int) ((Math.random() * (100-50)) + 50), (int) ((Math.random() * (100-50)) + 50) ));
		
		int i = random.nextInt(8);
		int x = 0;
		int y = 0;
		if (i == 0) {
			x = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getX();
			y = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getY();
		}
		else if (i == 1) {
			x = (int) ((Math.random() * (1500-0)) + 0);
			y = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getY();
		}	
		else if (i == 2)  {
			x = 1501;
			y = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getY();
		}	
		else if (i == 3)  {
			x = 1501;
			y = (int) ((Math.random() * (1500-0)) + 0);
		}	
		else if (i == 4)  {
			x = 1501;
			y = 1501;
		}	
		else if (i == 5)  {
			x = (int) ((Math.random() * (1500-0)) + 0);
			y = 1501;
		}	
		else if (i == 6)  {
			x = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getX();
			y = 1501;
		}	
		else if (i == 7)  {
			x = (-1) * meteoryWymiary.get(meteoryWymiary.size()-1).getX();
			y = (int) ((Math.random() * (1500-0)) + 0);
		}	
		
		
		meteoryLokalizacja.add( new IntLocation( x, y ) );
		
		i = random.nextInt(2);
		if (i == 0) 
			x = (-1) * (int) ((Math.random() * (20-5)) + 5);
		else if (i == 1) 
			x = 1 * (int) ((Math.random() * (20-5)) + 5);
		
		i = random.nextInt(2);
		if (i == 0) 
			y = (-1) * (int) ((Math.random() * (20-5)) + 5);
		else if (i == 1) 
			y = 1 * (int) ((Math.random() * (20-5)) + 5);
		
		meteoryPredkosc.add( new IntSpeed( x, y ));

		repaint();
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,1000);
	}
	
}

public class Lekcja52 {
	
	private CosmosPanel cosmospanel;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
					try {
						new Lekcja52().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
	}
	
	public void createAndShowGUI() throws Exception {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		cosmospanel = new CosmosPanel();
		window.add(cosmospanel);
		window.setVisible(true);
		window.pack();
		
	}
	
}
//Okienko z metorytami, których iloœæ jest nieskoñczona, o ró¿nym wymiarze i wektorze prêdkoœci
//Crash jeœli wiêkszy to zostaje, mniejszy znika