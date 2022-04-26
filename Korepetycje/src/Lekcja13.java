import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Lekcja13 extends JPanel implements ActionListener {
	double angle = -0.2;
    Timer timer;
    Image ziemia;
    Image ksiezyc;
    Image slonce2;
    int i=0;
    int j=0;
    JPanel panel;
    JTextField textField;
    JLabel label;
    JButton buttonStart;
    int mnoznik;
    Boolean booleanbuttonStart = false;
    JLabel label2;
    String str;
    
    Lekcja13() {
    	setLayout(null);
        try {
        	ziemia = ImageIO.read(new File("ziemia.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
        	ksiezyc = ImageIO.read(new File("ksiezyc.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        slonce2 = Toolkit.getDefaultToolkit().createImage("slonce.gif");
        
        panel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL, 0, 0));
        panel.setBounds(0, 0, 150, 500);
        add(panel);
        label = new JLabel("Podaj lata:");
        textField = new JTextField("1",2);
        buttonStart = new JButton("Start");
        panel.setOpaque(false);
        panel.add(label);
        panel.add(textField);
        panel.add(buttonStart);
        str = new String("Minê³o "+ i +" lat ");
        label2 = new JLabel(str + j +" dni");
        panel.add(label2);
        
        setVisible(true);
        buttonStart.setActionCommand("Start");
        buttonStart.addActionListener(this);
        
        
        timer = new Timer(100, this);
        timer.start();
    }
      
    
    public void actionPerformed(ActionEvent e) {
    	
    	if ("Start".equals(e.getActionCommand()) || booleanbuttonStart==true) {
    		booleanbuttonStart=true;
    		mnoznik = Integer.valueOf(textField.getText());
    	    angle -= mnoznik*0.01;
    	    if(angle < (-2* Math.PI - 0.2)) {
    	        angle = -0.2;
    	        i++;
    	        j=0;
    	    }
    	    
    	    j = (int) ((angle+0.2) / (-2* Math.PI)*365);
    	    
            if (i==1){
            	str = new String("Min¹³ "+ i +" rok ");
    	        }
            else if (i>1 && i<=4){
            	str = new String("Minê³y "+ i +" lata ");
    	        }
            else {
            	str = new String("Minê³o "+ i +" lat ");
    	        }
            label2.setText(str + j +" dni");

    	    repaint();
    	    
        }
    }
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		

		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(slonce2,(width/2)-(width/10),(height/2)-(height/10),(width/5), (height/10), null);
		int x = (int) (Math.cos(angle) * (width / 3)+(width / 2)-(width/25));
		int y = (int) (Math.sin(angle) * (height / 3)+(height / 2)-(height/40));
		int x2 = (int)(Math.cos(13*angle) * (width / 9.09)+(x+(width / 20))-(width / 33.3));
		int y2 = (int)(Math.sin(13*angle) * (height / 15.15)+(y+(height / 66.67))-(height / 250));
		g.drawImage(ziemia,x,y,(width/10), (height/20), null);
		g.drawImage(ksiezyc,x2,y2,(int)(width/16.67), (int)(height/33.33), null);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		//pi¹tek 18:00, niedziela 17:30
		//lewy górny licznik ile lat w ci¹gu minuty
		//ksiê¿yc obraca siê 13 razy w ci¹gu roku
		//mechanizm za³adowania GIF, s³oñce obraca siê wokó³ w³asnej osi
		//wskazówka (po prostu linia)
		JFrame frame = new JFrame("Lekcja13");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new Lekcja13());
		frame.setSize(500, 500);
		frame.setVisible(true);
			}
		});
	}
}