import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MainPanel3 extends JPanel {

	public MainPanel3() {
		LewyPanel3 lp = new LewyPanel3();
		setLayout(null);
		lp.setBounds(0, 0, 100, 300);
		add(lp);
		
		PrawyPanel3 pp = new PrawyPanel3();
		pp.setBounds(100, 0, 400, 300);
		add(pp);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 300);
	}

}

class LewyPanel3 extends JPanel implements ChangeListener {
	
	static final int LEN_MIN = 100;
    static final int LEN_MAX = 180;
    static final int LEN_INIT  = LEN_MIN;
    public static int length = 100;
    
    public LewyPanel3() {
    	
    	setLayout(null);
    	
    	JSlider dlugoscSznurka = new JSlider(JSlider.VERTICAL,
				LEN_MIN, LEN_MAX, LEN_INIT);
		dlugoscSznurka.addChangeListener(this);
		dlugoscSznurka.setMajorTickSpacing(10);
		dlugoscSznurka.setPaintTicks(true);
		dlugoscSznurka.setBounds(10, 10, 80, 270);
		
		Hashtable labelTable = new Hashtable();
		labelTable.put( (Integer)( LEN_MIN ), new JLabel("100") );
		labelTable.put( (Integer)( LEN_MAX ), new JLabel("180") );
		dlugoscSznurka.setLabelTable(labelTable);
		
		dlugoscSznurka.setPaintLabels(true);
		add(dlugoscSznurka);
		
    }

	public Dimension getPreferredSize() {
		return new Dimension(100, 300);
	}

	public void stateChanged(ChangeEvent e) {
		length = (int)(((JSlider) e.getSource()).getValue());
	}
	
	static int getLength() {
		return length;
	}

}

class PrawyPanel3 extends JPanel implements ActionListener,MouseListener, MouseMotionListener {
	
	double angle = Math.PI / 2;
	int length = LewyPanel3.getLength();
	int anchorX, anchorY, preX, preY, ballX, ballY;
	double angleAccel, angleVelocity = 0, dt = 0.1;
	Timer timer;
	boolean frozen = false;
	boolean pressOut = false;
	boolean isFirstTime = true;
	
	public PrawyPanel3() {
	    addMouseMotionListener(this);
	    addMouseListener(this);
		timer = new Timer(15, this);
		timer.start();
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
	    g.fillRect(0, 0, getWidth(), getHeight());
	    g.setColor(Color.BLACK);
	    anchorX = getWidth() / 2;
	    anchorY = getHeight() / 4;
	    ballX = anchorX + (int) (Math.sin(angle) * length);
	    ballY = anchorY + (int) (Math.cos(angle) * length);
	    g.fillOval(anchorX - 3, anchorY - 4, 7, 7);
	    g.fillOval(ballX - 15, ballY - 15, 30, 30);
	    g.drawLine(anchorX, anchorY, ballX, ballY);

	}
	
	public void startAnimation() {
        //Start (or restart) animating!
        timer.start();
        frozen = false;
    }

    public void stopAnimation() {
        //Stop the animating thread.
        timer.stop();
        frozen = true;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	    length = LewyPanel3.getLength();
    	angleAccel = -9.81 / length * Math.sin(angle);
	    angleVelocity += angleAccel * dt;
	    angle += angleVelocity * dt;
	    angle *=0.99;
	    repaint();
	}
	
	public boolean collides(double x, double y, double srednica, double Xmyszki, double Ymyszki) {
		double distanceX = x +(double)(srednica/2) - Xmyszki;
		double distanceY = y +(double)(srednica/2) - Ymyszki;
		return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) <= Math.pow((double)(srednica/2), 2);
	}
	
	public void updateLocation(MouseEvent e) {
		if (Math.abs(Math.atan2((preX + e.getX()+15)-anchorX, (preY + e.getY()+15)-anchorY))<=(Math.PI / 2)) {
			angle=Math.atan2((preX + e.getX()+15)-anchorX, (preY + e.getY()+15)-anchorY);
		}
		angleAccel = 0; 
		angleVelocity = 0;
	    repaint();
		
	  }
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	    preX = ballX - 15 - e.getX();
	    preY = ballY - 15 - e.getY();
	    
		if (collides(ballX - 15,ballY - 15,30,e.getX(), e.getY())) {
			updateLocation(e);
			stopAnimation();
		}
		      
	    else {
			pressOut = true;
	    }
	}
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (!pressOut) {
			updateLocation(e);
			stopAnimation();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (collides(ballX - 7,ballY - 7,14,e.getX(), e.getY())) {
	      updateLocation(e);
	      startAnimation();
		}
	    else {
			pressOut = false;
			startAnimation();
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(400, 300);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}

public class Lekcja14{
	
	private JFrame frame;
	private MainPanel3 containerPane;
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Lekcja14().createAndShowGui();
			}
		});
	    
	}
	
	public void createAndShowGui() {
		frame = new JFrame("Jframe");
		containerPane = new MainPanel3();

		frame.add(containerPane);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
	
}
//1. Przeci¹ganie pi³ki
//2. Wyhamowanie pi³ki do 0. (Grawitacja)
//3. JSlider (JFrame na ca³y rozmiar okienka. W nim Jeden JPanel G³owny. W nim jeden JPanel do suwaka, drugi do wahad³a)