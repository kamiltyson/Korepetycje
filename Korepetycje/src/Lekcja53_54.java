import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class WheelOfFortune extends JPanel implements MouseListener, ActionListener{
	
	private Graphics2D g2;
	
	int i = 0;
	
    JPanel panel;
    JTextField textField;
    JLabel label;
    JButton buttonStart;
    Boolean booleanbuttonStart = false;
    JLabel label2;
    String str;
    
    Timer timer;
    double change = 0.2;
    double angle = 0;
    double angleLine1 = -2.062;
    double angleLine2 = -1.57;
    double angleLine3 = -0.927;
    double angleLine4 = -0.126;
    double angleLine5 = -5.604;
    double angleLine6 = -5.261;
    double angleLine7 = -4.218;
    
    double angleCircle1 = 3.141;
    double angleCircle2 = -1.848;
    double angleCircle3 = -1.264;
    double angleCircle4 = -0.509;
    double angleCircle5 = 0.356;
    double angleCircle6 = 0.831;
    double angleCircle7 = 1.570;
    
    double rand=0;
    
    double multiplyArrowAngle = -1;
    
    public WheelOfFortune() {
    	
	    addMouseListener(this);
	    
	    panel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL, 0, 0));
        panel.setBounds(0, 0, 150, 500);
        add(panel);
        label = new JLabel("Podaj Kwotê:");
        textField = new JTextField("100",2);
        buttonStart = new JButton("Start");
        panel.setOpaque(false);
        panel.add(label);
        panel.add(textField);
        panel.add(buttonStart);
        str = new String("Wybra³eœ pole nr " + i);
        label2 = new JLabel(str);
        panel.add(label2);
        
        setVisible(true);
        buttonStart.setActionCommand("Start");
        buttonStart.addActionListener(this);
        
        timer = new Timer(50, this);
        timer.start();
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		rysujKolo(g2);
		
		rysujLinie(g2);
		
		rysujKolaDoWyboru(g2);
		
		rysujStrzalke(g2);
		
        
	}
	
	private void rysujKolo(Graphics2D g2){
		
		g2.drawOval(500, 250, 500, 500);
		
	}
	
	private void rysujLinie(Graphics2D g2){
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine1) * 250), (int) (500 + Math.sin(angleLine1) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine2) * 250), (int) (500 + Math.sin(angleLine2) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine3) * 250), (int) (500 + Math.sin(angleLine3) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine4) * 250), (int) (500 + Math.sin(angleLine4) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine5) * 250), (int) (500 + Math.sin(angleLine5) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine6) * 250), (int) (500 + Math.sin(angleLine6) * 250));
		
		g2.drawLine(750, 500, (int) (750 + Math.cos(angleLine7) * 250), (int) (500 + Math.sin(angleLine7) * 250));
		
	}
	
	private void rysujKolaDoWyboru(Graphics2D g2){
		
		if (i!=1)
			g2.drawOval((int) (750 + Math.cos(angleCircle1) * 215 - 15), (int) (500 + Math.sin(angleCircle1) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle1) * 215 - 15), (int) (500 + Math.sin(angleCircle1) * 215 - 15), 30, 30);
		
		if (i!=2)
			g2.drawOval((int) (750 + Math.cos(angleCircle2) * 215 - 15), (int) (500 + Math.sin(angleCircle2) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle2) * 215 - 15), (int) (500 + Math.sin(angleCircle2) * 215 - 15), 30, 30);
		
		if (i!=3)
			g2.drawOval((int) (750 + Math.cos(angleCircle3) * 215 - 15), (int) (500 + Math.sin(angleCircle3) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle3) * 215 - 15), (int) (500 + Math.sin(angleCircle3) * 215 - 15), 30, 30);
		
		if (i!=4)
			g2.drawOval((int) (750 + Math.cos(angleCircle4) * 215 - 15), (int) (500 + Math.sin(angleCircle4) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle4) * 215 - 15), (int) (500 + Math.sin(angleCircle4) * 215 - 15), 30, 30);
		
		if (i!=5)
			g2.drawOval((int) (750 + Math.cos(angleCircle5) * 215 - 15), (int) (500 + Math.sin(angleCircle5) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle5) * 215 - 15), (int) (500 + Math.sin(angleCircle5) * 215 - 15), 30, 30);
		
		if (i!=6)
			g2.drawOval((int) (750 + Math.cos(angleCircle6) * 215 - 15), (int) (500 + Math.sin(angleCircle6) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle6) * 215 - 15), (int) (500 + Math.sin(angleCircle6) * 215 - 15), 30, 30);
		
		if (i!=7)
			g2.drawOval((int) (750 + Math.cos(angleCircle7) * 215 - 15), (int) (500 + Math.sin(angleCircle7) * 215 - 15), 30, 30);
		else
			g2.fillOval((int) (750 + Math.cos(angleCircle7) * 215 - 15), (int) (500 + Math.sin(angleCircle7) * 215 - 15), 30, 30);
		
	}
	
	private void rysujStrzalke(Graphics2D g2){
	
		g2.drawLine(625, 500, 650, 475);
		
		g2.drawLine(650, 475, 650, 483);
		
		g2.drawLine(650, 483, 750, 483);
		
		g2.drawLine(750, 483, 750, 512);
		
		g2.drawLine(750, 512, 650, 512);
		
		g2.drawLine(650, 512, 650, 525);
		
		g2.drawLine(650, 525, 625, 500);
		
	}
	
	

	public void mouseClicked(MouseEvent e) {
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if ( (xmouse>=(int) (750 + Math.cos(angleCircle1) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle1) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle1) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle1) * 215 + 15)) ) {
			
			i = 1;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle2) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle2) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle2) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle2) * 215 + 15)) ) {
			
			i = 2;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle3) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle3) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle3) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle3) * 215 + 15)) ) {
			
			i = 3;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle4) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle4) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle4) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle4) * 215 + 15)) ) {
			
			i = 4;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle5) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle5) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle5) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle5) * 215 + 15)) ) {
			
			i = 5;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle6) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle6) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle6) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle6) * 215 + 15)) ) {
			
			i = 6;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		else if ( (xmouse>=(int) (750 + Math.cos(angleCircle7) * 215 - 15) && xmouse<=(int) (750 + Math.cos(angleCircle7) * 215 + 15))  && (ymouse>=(int) (500 + Math.sin(angleCircle7) * 215 - 15) && ymouse<=(int) (500 + Math.sin(angleCircle7) * 215 + 15)) ) {
			
			i = 7;
			str = new String("Wybra³eœ pole nr " + i);
			label2.setText(str);
			repaint();
			
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if ( ("Start".equals(e.getActionCommand()) || booleanbuttonStart==true) && i!=0) {
				
				if (booleanbuttonStart==false) {
					rand += 2 + Math.random();
				}
				
				if (((double)Math.round(((double)Math.round(angle * 10d) / 10d) % (-6.4) * 10d) / 10d) % (-6.4) == 0.0) {
					multiplyArrowAngle -=2;
				}
			
	    		booleanbuttonStart=true;
	    	    angle -= change;
	    	    
	    	    angleLine1 -= change;
	    	    angleLine2 -= change;
	    	    angleLine3 -= change;
	    	    angleLine4 -= change;
	    	    angleLine5 -= change;
	    	    angleLine6 -= change;
	    	    angleLine7 -= change;
	    	    
	    	    angleCircle1 -= change;
	    	    angleCircle2 -= change;
	    	    angleCircle3 -= change;
	    	    angleCircle4 -= change;
	    	    angleCircle5 -= change;
	    	    angleCircle6 -= change;
	    	    angleCircle7 -= change;
	    	    
	    	    if(angle <= (-rand * 2 * Math.PI)) {
	    	    	
	    	    	if (i==1) {
	    	    		if (((multiplyArrowAngle * Math.PI)<=angleLine1) && ((multiplyArrowAngle * Math.PI)>=angleLine7)) {
	    	    			str = new String("Wygra³eœ " + 3*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("1 " + multiplyArrowAngle * Math.PI + " " + angleLine1 + " " + angleLine7);
	    	    			
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("1 " + multiplyArrowAngle * Math.PI + " " + angleLine1 + " " + angleLine7);
	    	    		}
	    	    	}
	    	    	else if (i==2) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine2 && multiplyArrowAngle * Math.PI>=angleLine1) {
	    	    			str = new String("Wygra³eœ " + 12*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("2 " + multiplyArrowAngle * Math.PI + " " + angleLine2 + " " + angleLine1);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("2 " + multiplyArrowAngle * Math.PI + " " + angleLine2 + " " + angleLine1);
	    	    		}
	    	    	}
	    	    	else if (i==3) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine3 && multiplyArrowAngle * Math.PI>=angleLine2) {
	    	    			str = new String("Wygra³eœ " + 8*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("3 " + multiplyArrowAngle * Math.PI + " " + angleLine3 + " " + angleLine2);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("3 " + multiplyArrowAngle * Math.PI + " " + angleLine3 + " " + angleLine2);
	    	    		}
	    	    	}
	    	    	else if (i==4) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine4 && multiplyArrowAngle * Math.PI>=angleLine3) {
	    	    			str = new String("Wygra³eœ " + 8*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("4 " + multiplyArrowAngle * Math.PI + " " + angleLine4 + " " + angleLine3);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("4 " + multiplyArrowAngle * Math.PI + " " + angleLine4 + " " + angleLine3);
	    	    		}
	    	    	}
	    	    	else if (i==5) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine5 && multiplyArrowAngle * Math.PI>=angleLine4) {
	    	    			str = new String("Wygra³eœ " + 8*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("5 " + multiplyArrowAngle * Math.PI + " " + angleLine5 + " " + angleLine4);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("5 " + multiplyArrowAngle * Math.PI + " " + angleLine5 + " " + angleLine4);
	    	    		}
	    	    	}
	    	    	else if (i==6) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine6 && multiplyArrowAngle * Math.PI>=angleLine5) {
	    	    			str = new String("Wygra³eœ " + 16*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("6 " + multiplyArrowAngle * Math.PI + " " + angleLine6 + " " + angleLine5);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("6 " + multiplyArrowAngle * Math.PI + " " + angleLine6 + " " + angleLine5);
	    	    		}
	    	    	}
	    	    	else if (i==7) {
	    	    		if (multiplyArrowAngle * Math.PI<=angleLine7 && multiplyArrowAngle * Math.PI>=angleLine6) {
	    	    			str = new String("Wygra³eœ " + 6*Integer.parseInt(textField.getText()));
	    	    			label2.setText(str);
	    	    			System.out.println("7 " + multiplyArrowAngle * Math.PI + " " + angleLine7 + " " + angleLine6);
	    	    		}
	    	    		else {
	    	    			str = new String("Przegra³eœ " + textField.getText());
	    	    			label2.setText(str);
	    	    			System.out.println("7 " + multiplyArrowAngle * Math.PI + " " + angleLine7 + " " + angleLine6);
	    	    		}
	    	    	}
	    	    	
	    	    	booleanbuttonStart = false;
	    	        i=0;
	    	    }

	    	    repaint();
	    	    
    	    
        }
	}
	
	public Dimension getPreferredSize() {
		
		return new Dimension(1500,1000);
		
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}

public class Lekcja53_54 {
	
	private WheelOfFortune wheeloffortunepanel;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run(){
					try {
						new Lekcja53_54().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}

	public void createAndShowGUI() throws Exception{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		wheeloffortunepanel = new WheelOfFortune();
		window.add(wheeloffortunepanel);
		window.setVisible(true);
		window.pack();
	}
	
}
//Ko³o fortuny, wybierasz obszar, obstawiasz gotówkê. Ko³o fortuny obraca siê z t¹ sam¹ predkoœci¹ ale z losow¹ liczb¹ obrotów.
//Zmiana wspó³rzednych koñca lini + mniejszych kó³ek
//Liczba obrotów miêdzy 2 a 3.
//Przeczytaæ synchronized block java