import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class OneArmedBandit extends JPanel implements MouseListener, ActionListener{
	
	private Graphics2D g2;
	
    Timer timer;
    private int timealive = 0;
    private Random ran = new Random();
    private int timerandom = 50;
	
    JPanel panel;
    JTextField textField;
    JLabel label;
    JButton buttonStart;
    Boolean booleanbuttonStart = false;
    JLabel label2;
    String str;
    
    Boolean IsStartSelected = false;
    
    private int[] i = {0, 1, 2, 3, 4};
    private int[] velocity = {1, 3, 4, 3, 4};
    
    private int x = 480;
    private int y1 = 320;
    private int y2 = 450;
    private int y3 = 590;
    private int[] liczbaKsztaltow = {0, 0, 0, 0, 0};
    private int wygrana = 0;

	private enum STAWKA{
		BRAK,
		JEDEN,
		PIEC,
		DZIESIEC,
	}
	
	private STAWKA stawka = STAWKA.BRAK;
	
	private enum STATE{
		KOLO,
		TROJKAT,
		KWADRAT,
		GWIAZDA,
		SIODEMKA
	}
	
	private STATE[] State = {STATE.KOLO, STATE.TROJKAT, STATE.KWADRAT, STATE.GWIAZDA, STATE.SIODEMKA};
	
	private static final Logger LOGGER = Logger.getLogger(OneArmedBandit.class.getName());
	private Handler consoleHandler = null;
	private Handler fileHandler  = null;
	private int[] najwiekszeWygrane = {0,0,0};
	private String[] datyNajwiekszychWygranych = {"","",""};
    
    public OneArmedBandit() {

	    addMouseListener(this);
        
        timer = new Timer(100, this);
        timer.start();
        
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		
		rysujTlo(g2);
		
		rysujStart(g2, 1150, 470);
		
		rysujStawki(g2, x, y3+150);
		
		coRysowac(i[0], g2, x);
		coRysowac(i[1], g2, x+120);
		coRysowac(i[2], g2, x+240);
		coRysowac(i[3], g2, x+360);
		coRysowac(i[4], g2, x+480);
		
		rysujRamke(g2, 465, 435, 615, 135);
		
		rysujWygrana(g2, x+220, y3+300, 150, 100);
	}
	
	private void rysujStart(Graphics2D g2, int x, int y) {
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		if (IsStartSelected) {
			
			g2.setColor(Color.YELLOW);
			g2.fillRect(x, y, 180, 60);
			g2.setColor(Color.black);
			g2.drawString("START",x+10, y+50);
			
		}
		else {
			
			g2.setColor(Color.white);
			g2.drawRect(x, y, 180, 60);
			g2.drawString("START",x+10, y+50);
			
		}
		
	}
	
	private void rysujStawki(Graphics2D g2, int x, int y) {
		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		if (stawka == STAWKA.BRAK) {
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("1",x+65, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+220, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("5",x+285, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+420, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("10", x+465, y+70);
			
		}
		if (stawka == STAWKA.JEDEN) {
			
			g2.setColor(Color.YELLOW);
			g2.fillRect(x, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("1",x+65, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+220, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("5",x+285, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+420, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("10", x+465, y+70);

		}
		else if (stawka == STAWKA.PIEC) {
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("1",x+65, y+70);
			
			g2.setColor(Color.YELLOW);
			g2.fillRect(x+220, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("5",x+285, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+420, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("10", x+465, y+70);

		}
		else if (stawka == STAWKA.DZIESIEC) {
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("1",x+65, y+70);
			
			g2.setColor(new Color(255, 255, 255));
			g2.fillRect(x+220, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("5",x+285, y+70);
			
			g2.setColor(Color.YELLOW);
			g2.fillRect(x+420, y, 150, 100);
			g2.setColor(new Color(32, 32, 32));
			g2.drawString("10", x+465, y+70);

		}
		
	}
	
	private void rysujTlo(Graphics2D g2){
		
		g2.setColor(new Color(32, 102, 32));
		g2.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
		
	}
	
	private void coRysowac(int i, Graphics2D g2, int x){
		
		if (i==0) {
			rysujSiodemke(g2, x, y1);
			rysujKolo(g2, x, y2);
			rysujTrojkat(g2, x, y3);
		}
		else if (i==1){
			rysujKolo(g2, x, y1);
			rysujTrojkat(g2, x, y2);
			rysujKwadrat(g2, x, y3);
		}
		else if (i==2){
			rysujTrojkat(g2, x, y1);
			rysujKwadrat(g2, x, y2);
			rysujGwiazde(g2, x, y3);
		}
		else if (i==3){
			rysujKwadrat(g2, x, y1);
			rysujGwiazde(g2, x, y2);
			rysujSiodemke(g2, x, y3);
		}
		else if (i==4){
			rysujGwiazde(g2, x, y1);
			rysujSiodemke(g2, x, y2);
			rysujKolo(g2, x, y3);
		}
		
	}
	
	private void rysujKolo(Graphics2D g2, int x, int y){
		
		g2.setColor(new Color(234, 46, 44));
		g2.fillOval(x, y, 100, 100);
		
	}
	
	private void rysujKwadrat(Graphics2D g2, int x, int y){
		
		g2.setColor(Color.BLUE);
		g2.fillRect(x, y, 100, 100);
		
	}

	private void rysujTrojkat(Graphics2D g2, int x, int y){
		
		g2.setColor(new Color(74, 171, 66));
		g2.fillPolygon(new int[] {x, x+50, x+100}, new int[] {y+100, y, y+100}, 3);
		
	}
	
	private void rysujGwiazde(Graphics2D g2, int x, int y){
		
		g2.setColor(Color.YELLOW);
		g2.fillPolygon(new int[] {x, x+37, x+50, x+63, x+100, x+68, x+80, x+50, x+20, x+32,x}, new int[] {y+40, y+40, y, y+40, y+40, y+63, y+100, y+77, y+100, y+63, y+40}, 11);
		
	}
	
	private void rysujSiodemke(Graphics2D g2, int x, int y){
		
		g2.setColor(new Color(32, 32, 32));
		g2.fillPolygon(new int[] {x, x+100, x+20, x, x+60, x, x}, new int[] {y, y, y+100, y+100, y+20, y+20, y}, 7);
		
	}
	
	private void rysujRamke(Graphics2D g2, int x1, int y1, int x2, int y2){
		
		g2.setColor(new Color(234, 46, 44));
		g2.setStroke(new BasicStroke(10));
		g2.drawRect(x1, y1, x2, y2);
		
	}
	
	private void rysujWygrana(Graphics2D g2, int x1, int y1, int x2, int y2){
		
		g2.setColor(new Color(55,55,55));
		g2.fillRect(x1, y1, x2, y2);
		g2.setColor(new Color(255, 255, 255));
		if (wygrana<10) {
			g2.drawString(Integer.toString(wygrana),x1+60, y1+65);
		}
		else {
			g2.drawString(Integer.toString(wygrana),x1+50, y1+65);
		}
		
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if ( (xmouse>=1150 && xmouse<=1150+180) && (ymouse>=470 && ymouse<=470+60) ) {
			if (stawka!=STAWKA.BRAK) {
				IsStartSelected = true;
				booleanbuttonStart = true;
				repaint();
			}
		}
		
		if ( (xmouse>=x && xmouse<=x+150) && (ymouse>=y3+150 && ymouse<=y3+150+100) ) {
			
			if (!booleanbuttonStart) {
				stawka = STAWKA.JEDEN;
				repaint();
			}

		}
		else if ( (xmouse>=x+220 && xmouse<=x+220+150) && (ymouse>=y3+150 && ymouse<=y3+150+100) ) {
			
			if (!booleanbuttonStart) {
				stawka = STAWKA.PIEC;
				repaint();
			}
			
		}
		else if ( (xmouse>=x+420 && xmouse<=x+420+150) && (ymouse>=y3+150 && ymouse<=y3+150+100) ) {
			
			if (!booleanbuttonStart) {
				stawka = STAWKA.DZIESIEC;
				repaint();
			}
			
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (booleanbuttonStart) {
	    		
				if (timealive==0) {
					timerandom = 50 + ( ran.nextInt(6) * 10 );
				}
	    		timealive+=timer.getDelay()/100;
	    		wygrana = 0;
	    		
	    		for (int j = 0; j < 5; j++) {
	    			
	    			System.out.println(timealive);
	    			
	    			if (timealive>timerandom+8) {
	    				
	    				for (int k=0; k<5; k++) {
	    					
	    					if (i[k]==0) {
	    						State[k]=STATE.KOLO;
	    						liczbaKsztaltow[0]++;
	    					}
	    					else if(i[k]==1) {
	    						State[k]=STATE.TROJKAT;
	    						liczbaKsztaltow[1]++;
	    					}
	    					else if(i[k]==2) {
	    						State[k]=STATE.KWADRAT;
	    						liczbaKsztaltow[2]++;
	    					}
	    					else if(i[k]==3) {
	    						State[k]=STATE.GWIAZDA;
	    						liczbaKsztaltow[3]++;
	    					}
	    					else if(i[k]==4) {
	    						State[k]=STATE.SIODEMKA;
	    						liczbaKsztaltow[4]++;
	    					}
	    					
	    				}
	    				
	    				if (getLargest(liczbaKsztaltow, 5)==3) {
	    					if (stawka == STAWKA.JEDEN) {
	    						wygrana = 2;
	    					}
	    					else if (stawka == STAWKA.PIEC) {
	    						wygrana = 10;
	    					}
	    					else if (stawka == STAWKA.DZIESIEC) {
	    						wygrana = 20;
	    					}
	    				}
	    				else if (getLargest(liczbaKsztaltow, 5)==4) {
	    					if (stawka == STAWKA.JEDEN) {
	    						wygrana = 3;
	    					}
	    					else if (stawka == STAWKA.PIEC) {
	    						wygrana = 15;
	    					}
	    					else if (stawka == STAWKA.DZIESIEC) {
	    						wygrana = 30;
	    					}
	    				}
	    				if (getLargest(liczbaKsztaltow, 5)==5) {
	    					if (stawka == STAWKA.JEDEN) {
	    						wygrana = 4;
	    					}
	    					else if (stawka == STAWKA.PIEC) {
	    						wygrana = 20;
	    					}
	    					else if (stawka == STAWKA.DZIESIEC) {
	    						wygrana = 40;
	    					}
	    				}
	    				
	    		    	try{
	    		            //Creating consoleHandler and fileHandler
	    		            consoleHandler = new ConsoleHandler();
	    		            fileHandler  = new FileHandler("./OneArmedBandit.log", true);
	    		            fileHandler.setFormatter(new MyFormatter());
	    		             
	    		            //Assigning handlers to LOGGER object
	    		            LOGGER.addHandler(consoleHandler);
	    		            LOGGER.addHandler(fileHandler);
	    		             
	    		            //Setting levels to handlers and LOGGER
	    		            consoleHandler.setLevel(Level.FINEST);
	    		            fileHandler.setLevel(Level.FINEST);
	    		            LOGGER.setLevel(Level.FINEST);
	    		        }catch(IOException exception){
	    		            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
	    		        }
	    		        
	    		    	if (stawka == STAWKA.JEDEN) {
	    		    		LOGGER.log(Level.FINEST, "Stawka " + 1);
    					}
    					else if (stawka == STAWKA.PIEC) {
    						LOGGER.log(Level.FINEST, "Stawka " + 5);
    					}
    					else if (stawka == STAWKA.DZIESIEC) {
    						LOGGER.log(Level.FINEST, "Stawka " + 10);
    					}
	    		    	LOGGER.log(Level.FINEST, "Czas losowania " + (double) (timealive/10) + " sekund");
	    		    	LOGGER.log(Level.FINEST, "Liczba kszta³tów " + getLargest(liczbaKsztaltow, 5));
	    		    	LOGGER.log(Level.FINEST, "Wygrana " + wygrana);
	    				
	    				for (int k=0; k<5; k++) {
	    					liczbaKsztaltow[k] = 0;
	    				}
	    				
	    				j=5;
	    				booleanbuttonStart = false;
	    				timealive = 0;
	    				IsStartSelected = false;
	    				
	    				stawka = STAWKA.BRAK;
	    			}
	    			else if (timealive>timerandom+6 && j==0) {
	    				j=4;
	    			}
	    			else if (timealive>timerandom+4 && j==0) {
	    				j=3;
	    			}
	    			else if (timealive>timerandom+2 && j==0) {
	    				j=2;
	    			}
	    			else if (timealive>timerandom && j==0) {
	    				j=1;
	    			}
	    			
	    			if (j<5) {
		    			i[j]+=velocity[j];
		    			if (i[j]>=5) {
		    				i[j]=i[j]-5;
		    			}
	    			}

	    					
	    		}
	    
	    	    repaint();
	    	    
    	    
        }
	}
	
	public static int getLargest(int[] a, int total){  
		int temp;  
		for (int i = 0; i < total; i++)   
		        {  
		            for (int j = i + 1; j < total; j++)   
		            {  
		                if (a[i] > a[j])   
		                {  
		                    temp = a[i];  
		                    a[i] = a[j];  
		                    a[j] = temp;  
		                }  
		            }  
		        }  
		       return a[total-1];  
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

public class Lekcja55_56_57 {

	
	private OneArmedBandit oneArmedBandit;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run(){
					try {
						new Lekcja55_56_57().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}

	public void createAndShowGUI() throws Exception{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Do you really want to exit the application ?",
                        "Confirm Close", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (choose == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                    System.out.println("close");
                    
                  
                } else {
                    System.out.println("do nothing");
                    
                }
            }
        });
		window.setResizable(false);
		oneArmedBandit = new OneArmedBandit();
		window.add(oneArmedBandit);
		window.setVisible(true);
		window.pack();
	}
	

}

class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getThreadID()+"::"+record.getSourceClassName()+"::"
                +record.getSourceMethodName()+"::"
                +new Date(record.getMillis())+"::"
                +record.getMessage()+"\n";
    }

}


//jednoreki bandyta na 5 rolek, 5-6 ró¿nych ikon
//Na podstawie pliku log wy³oniæ 3 najwiêksze wygrane