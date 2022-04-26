import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class GameMenu extends JPanel{
	
	private Graphics2D g2;
	private static boolean IsStartSelected = false;
	private static boolean IsLoadSelected = false;
	private static boolean IsRankingSelected = false;
	
	public void paint(Graphics g){
		g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 100)); 
		g2.drawString("SPACE SHOOTER",350,200);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
		
		if (IsStartSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 300, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("START",620,385);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 300, 490, 100);
			g2.drawString("START",620,385);
		}

		if (IsLoadSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 500, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("LOAD",650,585);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 500, 490, 100);
			g2.drawString("LOAD",650,585);
		}
		
		if (IsRankingSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 700, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("RANKING",550,785);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 700, 490, 100);
			g2.drawString("RANKING",550,785);
		}
		
	}
	
	public static boolean CheckIsStartSelected() {
		return IsStartSelected;
	}
	
	public static boolean CheckIsLoadSelected() {
		return IsLoadSelected;
	}
	
	public static boolean CheckIsRankingSelected() {
		return IsRankingSelected;
	}
	
	public static void SetIsStartSelectedT() {
		IsStartSelected = true;
	}
	
	public static void SetIsStartSelectedF() {
		IsStartSelected = false;
	}
	
	public static void SetIsLoadSelectedT() {
		IsLoadSelected = true;
	}
	
	public static void SetIsLoadSelectedF() {
		IsLoadSelected = false;
	}
	
	public static void SetIsRankingSelectedT() {
		IsRankingSelected = true;
	}
	
	public static void SetIsRankingSelectedF() {
		IsRankingSelected = false;
	}

}

class Ranking extends JPanel{
	
	private Graphics2D g2;
	private static boolean IsBackSelected = false;
	
	public void paint(Graphics g){
		g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 100)); 
		g2.drawString("RANKING",520,250);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		int i = 0;
		Scanner scanner;
		
		try {
			scanner = new Scanner(new File("Ranking.txt"));
			while(scanner.hasNextLine()) {
				String linia = scanner.nextLine();
				g2.setColor(Color.white);
				g2.drawString(linia.split(" ")[0],320,350 + i*100);
				g2.setColor(Color.YELLOW);
				g2.drawString(String.valueOf((int)(Integer.valueOf(linia.split(" ")[1]))), (1100-(linia.split(" ")[1].length()*25)), 350 + i*100 );
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (IsBackSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(70, 70, 200, 80);
			g2.setColor(Color.black);
			g2.drawString("BACK",100,130);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(70, 70, 200, 80);
			g2.drawString("BACK",100,130);
		}
		
	}
	
	public static boolean CheckIsBackSelected() {
		return IsBackSelected;
	}
	
	public static void SetIsBackSelectedT() {
		IsBackSelected = true;
	}
	
	public static void SetIsBackSelectedF() {
		IsBackSelected = false;
	}

}

class PauseMenu extends JPanel{
	
	private Graphics2D g2;
	private static boolean IsResumeSelected = false;
	private static boolean IsSaveSelected = false;
	private static boolean IsShopSelected = false;
	
	public void paint(Graphics g){
		g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 100)); 
		g2.drawString("PAUSE",610,200);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
		
		if (IsResumeSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 300, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("RESUME",580,385);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 300, 490, 100);
			g2.drawString("RESUME",580,385);
		}

		if (IsSaveSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 500, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("SAVE",650,585);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 500, 490, 100);
			g2.drawString("SAVE",650,585);
		}
		
		if (IsShopSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(540, 700, 490, 100);
			g2.setColor(Color.black);
			g2.drawString("SHOP",650,785);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(540, 700, 490, 100);
			g2.drawString("SHOP",650,785);
		}
		
	}
	
	public static boolean CheckIsResumeSelected() {
		return IsResumeSelected;
	}
	
	public static boolean CheckIsSaveSelected() {
		return IsSaveSelected;
	}
	
	public static boolean CheckIsShopSelected() {
		return IsShopSelected;
	}
	
	public static void SetIsResumeSelectedT() {
		IsResumeSelected = true;
	}
	
	public static void SetIsResumeSelectedF() {
		IsResumeSelected = false;
	}
	
	public static void SetIsSaveSelectedT() {
		IsSaveSelected = true;
	}
	
	public static void SetIsSaveSelectedF() {
		IsSaveSelected = false;
	}
	
	public static void SetIsShopSelectedT() {
		IsShopSelected = true;
	}
	
	public static void SetIsShopSelectedF() {
		IsShopSelected = false;
	}

}

class Shop extends JPanel{
	
	private Graphics2D g2;
	private static boolean IsBackSelected = false;
	private static boolean IsTwoLasersSelected = false;
	private static boolean IsThreeLasersSelected = false;
	Image Two_lasers, Three_lasers;
	
	public Shop() {
		try {
			Two_lasers = ImageIO.read(new File("Two_lasers.png"));
			Three_lasers = ImageIO.read(new File("Three_lasers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 100)); 
		g2.drawString("SHOP",630,250);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		if (IsBackSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(70, 70, 200, 80);
			g2.setColor(Color.black);
			g2.drawString("BACK",100,130);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(70, 70, 200, 80);
			g2.drawString("BACK",100,130);
		}

		if (IsTwoLasersSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(450, 350, 300, 300);
			g2.setColor(Color.BLACK);
			g2.drawString("(5)",570,450);
			g2.drawString("Price 15",520,600);
			g2.drawImage(Two_lasers, 550, 470, 100, 60, null);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(450, 350, 300, 300);
			g2.drawString("(5)",570,450);
			g2.drawString("Price 15",520,600);
			g2.drawImage(Two_lasers, 550, 470, 100, 60, null);
		}
		
		if (IsThreeLasersSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(800, 350, 300, 300);
			g2.setColor(Color.BLACK);
			g2.drawString("(5)",920,450);
			g2.drawString("Price 25",870,600);
			g2.drawImage(Three_lasers, 900, 470, 104, 64, null);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(800, 350, 300, 300);
			g2.drawString("(5)",920,450);
			g2.drawString("Price 25",870,600);
			g2.drawImage(Three_lasers, 900, 470, 104, 64, null);
		}
		
	}
	
	public static boolean CheckIsBackSelected() {
		return IsBackSelected;
	}
	
	public static boolean CheckIsTwoLasersSelected() {
		return IsTwoLasersSelected;
	}
	
	public static boolean CheckIsThreeLasersSelected() {
		return IsThreeLasersSelected;
	}
	
	public static void SetIsBackSelectedT() {
		IsBackSelected = true;
	}
	
	public static void SetIsBackSelectedF() {
		IsBackSelected = false;
	}
	
	public static void SetIsTwoLasersSelectedT() {
		IsTwoLasersSelected = true;
	}
	
	public static void SetIsTwoLasersSelectedF() {
		IsTwoLasersSelected = false;
	}
	
	public static void SetIsThreeLasersSelectedT() {
		IsThreeLasersSelected = true;
	}
	
	public static void SetIsThreeLasersSelectedF() {
		IsThreeLasersSelected = false;
	}

}

class GameOver extends JPanel implements MouseListener, MouseMotionListener{
	
	private JPanel panel;
	private JTextField textField;
	private Graphics2D g2;
	private Image Space, Two_lasers, Three_lasers;
	private File Fselect;
	private int coincounter;
	private int twolasercounter;
	private int threelasercounter;
	private static boolean IsBackSelected = false;
	private static boolean IsEnterSelected = false;
	
	public void setCoincounter(int coincounter) {
		this.coincounter = coincounter;
	}
	
	public void setTwolasercounter(int twolasercounter) {
		this.twolasercounter = twolasercounter;
	}

	public void setThreelasercounter(int threelasercounter) {
		this.threelasercounter = threelasercounter;
	}
	
	public GameOver() {
		addMouseListener(this);
	    addMouseMotionListener(this);
		plikiGraficzne();
		plikiDzwiekowe();
	    panel = new JPanel(new FlowLayout(SwingConstants.LEADING, 550, 275));
	    panel.setOpaque(false);
	    textField = new JTextField(10);
	    textField.setFont(new Font("TimesRoman", Font.PLAIN, 50));
	    panel.add(textField);
	    add(panel);
	    setVisible(true);
	}
	
	public void plikiGraficzne() {
		try {
        	Space = ImageIO.read(new File("Space.png"));
        	Two_lasers = ImageIO.read(new File("Two_lasers.png"));
			Three_lasers = ImageIO.read(new File("Three_lasers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void plikiDzwiekowe() {
		Fselect = new File("select.wav");
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,1000);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		rysujTlo(g2);
		rysujLiczbeAmunicji(g2);
		rysujLiczbeMonet(g2);
		g2.setColor(Color.white);
		g2.setFont(new Font("TimesRoman", Font.BOLD, 100)); 
		g2.drawString("GAME OVER",430,250);
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		
		if (IsBackSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(70, 70, 200, 80);
			g2.setColor(Color.black);
			g2.drawString("BACK",100,130);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(70, 70, 200, 80);
			g2.drawString("BACK",100,130);
		}
		
		if (IsEnterSelected) {
			g2.setColor(Color.YELLOW);
			g2.fillRect(650, 400, 200, 80);
			g2.setColor(Color.black);
			g2.drawString("ENTER", 665, 460);
		}
		else {
			g2.setColor(Color.white);
			g2.drawRect(650, 400, 200, 80);
			g2.drawString("ENTER", 665, 460);
		}
	}
	
	private void rysujTlo(Graphics2D g2){
		g2.drawImage(Space, 0, 0, null);
	}
	
	private void rysujLiczbeAmunicji(Graphics2D g2) {
		
		g2.drawImage(Three_lasers, 1210, 23, 26, 16, null);
		g2.setColor(new Color(163,73,164));
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(threelasercounter), 1245, 40);
		
		g2.drawImage(Two_lasers, 1300, 23, 25, 15, null);
		g2.setColor(new Color(163,73,164));
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(twolasercounter), 1340, 40);
		
	}
	
	private void rysujLiczbeMonet(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(coincounter), 1400, 40);
	}
	
	public static boolean CheckIsBackSelected() {
		return IsBackSelected;
	}
	
	public static boolean CheckIsEnterSelected() {
		return IsEnterSelected;
	}
	
	public static void SetIsBackSelectedT() {
		IsBackSelected = true;
	}
	
	public static void SetIsBackSelectedF() {
		IsBackSelected = false;
	}
	
	public static void SetIsEnterSelectedT() {
		IsEnterSelected = true;
	}
	
	public static void SetIsEnterSelectedF() {
		IsEnterSelected = false;
	}

	public void mouseMoved(MouseEvent e) {
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
			if (!CheckIsBackSelected()) {
				SetIsBackSelectedT();
				puscDzwiek(Fselect);
				repaint();
			}
			
		}
		else {
			if (CheckIsBackSelected()) {
				SetIsBackSelectedF();
				repaint();
			}
		}
		
		if ( (xmouse>=650 && xmouse<=850)  && (ymouse>=400 && ymouse<=480) ) {
			if (!CheckIsEnterSelected()) {
				SetIsEnterSelectedT();
				puscDzwiek(Fselect);
				repaint();
			}
		}
		else {
			if (CheckIsEnterSelected()) {
				SetIsEnterSelectedF();
				repaint();
			}
		}
		
	}
	
	public void mouseClicked(MouseEvent e) {
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
			
			puscDzwiek(Fselect);
			repaint();
			SetIsBackSelectedF();
			((Window) getRootPane().getParent()).dispose();
		}
		
		if ( (xmouse>=650 && xmouse<=850)  && (ymouse>=400 && ymouse<=480) ) {
			
			addtoranking();
			puscDzwiek(Fselect);
			repaint();
			SetIsEnterSelectedF();
			((Window) getRootPane().getParent()).dispose();
			
		}
	}
	
	private void addtoranking() {
		String name = textField.getText();
		Scanner scanner;
		PrintWriter pw;
		String tabname [] = new String[5];
		int tabcoin [] = new int[5];
		String tabname2 [] = new String[6];
		int tabcoin2 [] = new int[6];
		try {
			
			scanner = new Scanner(new File("Ranking.txt"));
			
			int i = 0;
			while(scanner.hasNextLine()) {
				String linia = scanner.nextLine();
				tabname [i] = linia.split(" ")[0];
				tabcoin [i] = Integer.valueOf(linia.split(" ")[1]);
				i++;
			}
			
			scanner.close();
			
			int x = 0;
			boolean IsNewNameAdded = false;
			
			for (i = 0; i < 6; i++) {
				if ( coincounter > tabcoin [x] && !IsNewNameAdded) {
					tabname2 [i] = name;
					tabcoin2 [i] = coincounter;
					IsNewNameAdded = true;
				}
				else {
					tabname2 [i] = tabname [x];
					tabcoin2 [i] = tabcoin [x];
					if (x<4) {
						x+=1;
					}
				}
	        }
			
			pw = new PrintWriter(new File("Ranking.txt"));
			
			for(int j = 0; j < 5; j++) {
				pw.print(tabname2 [j] + " ");
				pw.print(tabcoin2 [j] + "\n");
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void puscDzwiek(File file) {
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioStream.getFormat();
        	DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
        	audioClip.open(audioStream);
        	audioClip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void mouseDragged(MouseEvent e) {
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

class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	
	private int x = 737, y = 900;
	private int xlaser, ylaser;
	private int z = 0;
	private int ybackground = 0;
	private int xenemy = 0;
	private int xenemychange = 150;
	private int yenemy2change = 10;
	private int yenemy3change = 10;
	private int ycoinchange = 10;
	private int ylifechange = 10;
	private int coincounter = 0;
	private int timecounter = 0;
	private int timedivider = 2000;
	private int twolasercounter = 0;
	private int threelasercounter = 0;
	private int heavybeamcounter = 0;
	private int life = 100;
	private int energyshieldcounter = 0;
	private int velX = 0;
	private int velY = 0;
	private boolean czyStatekDostal = false;
	private boolean czyMaStrzelacLaserem = false;
	private boolean czyPrzeciwnikWybuchl = false;
	private boolean UruchomCiezkaWiazke = false;
	private boolean czyMaStrzelacCiezkaWiazka = false;
	private boolean czyMaStrzelacDwomaLaserami = false;
	private boolean czyMaStrzelacTrzemaLaserami = false;
	private boolean czyMaTarcze = false;
	private boolean UruchomTarcze = false;
	private Graphics2D g2;
	private Thread t;
	private GameMenu gamemenu = new GameMenu();
	private Ranking ranking = new Ranking();
	private PauseMenu pausemenu = new PauseMenu();
	private Shop shop = new Shop();
	private GameOver gameover = new GameOver();
	private enum STATE{
		MENU,
		GAME,
		RANKING,
		PAUSE,
		SHOP,
		GAMEOVER
	}
	
	private STATE State = STATE.MENU;
	
	Timer timer;
	Image Space, Ship, Enemylaser, Laser, Laser_left, Laser_right, Heavybeam, EnemyShip1, EnemyShip2, EnemyShip3, Coin, Life, Shield, LaserUpgrade, Explosion, Two_lasers, Three_lasers;
	File Fmain_theme, Fselect, Fstartgame, Flaser, Fheavylaser, Fshield, Fexplosion, Fcoin, Flife, Fendgame;
	ArrayList<Point> LaserList = new ArrayList<Point>();
	ArrayList<Point> EnemyLaserList = new ArrayList<Point>();
	ArrayList<Point> Enemy1List = new ArrayList<Point>();
	ArrayList<Integer> Enemy1Listxenemy1change = new ArrayList<Integer>();
	ArrayList<Point> Enemy2List = new ArrayList<Point>();
	ArrayList<Point> Enemy3List = new ArrayList<Point>();
	ArrayList<Integer> Enemy3Listxenemy3change = new ArrayList<Integer>();
	ArrayList<Point> CoinList = new ArrayList<Point>();
	ArrayList<Point> LifeList = new ArrayList<Point>();
	ArrayList<Point> ShieldList = new ArrayList<Point>();
	ArrayList<Point> LaserUpgradeList = new ArrayList<Point>();
	ArrayList<Point> LeftLaserList = new ArrayList<Point>();
	ArrayList<Point> RightLaserList = new ArrayList<Point>();
	Clip mainaudioClip;
	
	private JPanel panel;
	private JTextField textField;
	private JLabel label;

	
	public GamePanel() {
		addMouseListener(this);
	    addMouseMotionListener(this);
		plikiDzwiekowe();
		plikiGraficzne();
		xenemy += xenemychange;
		timer = new Timer(100, this);
    	
    	if (State==STATE.GAME) {
    		puscDzwiek(Fstartgame);
    		timer.start();
    		glownyDzwiek();
    	}

	}
	
	public void plikiGraficzne() {
		try {
        	Space = ImageIO.read(new File("Space.png"));
        	z = ImageIO.read(new File("Space.png")).getHeight();
        	Ship = ImageIO.read(new File("space_ship.png"));
        	Enemylaser = ImageIO.read(new File("enemy_laser.png"));
        	Laser = ImageIO.read(new File("laser.png"));
        	Laser_left = ImageIO.read(new File("laser_left.png"));
        	Laser_right = ImageIO.read(new File("laser_right.png"));
        	Heavybeam = ImageIO.read(new File("heavybeam.png"));
        	EnemyShip1  = ImageIO.read(new File("enemy_spaceship_1.png"));
        	EnemyShip2  = ImageIO.read(new File("enemy_spaceship_2.png"));
        	EnemyShip3  = ImageIO.read(new File("enemy_spaceship_3.png"));
        	Coin  = ImageIO.read(new File("coin.png"));
        	Life  = ImageIO.read(new File("heart.png"));
        	Shield  = ImageIO.read(new File("energy_shield.png"));
        	LaserUpgrade  = ImageIO.read(new File("laser_upgrade.png"));
        	Explosion  = ImageIO.read(new File("explosion.png"));
        	Two_lasers = ImageIO.read(new File("Two_lasers.png"));
			Three_lasers = ImageIO.read(new File("Three_lasers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void plikiDzwiekowe() {
		Fselect = new File("select.wav");
		Fstartgame = new File("game_start.wav");
		Flaser = new File("laser.wav");
		Fheavylaser = new File("heavy_beam_weapon.wav");
		Fshield = new File("energy_shield.wav");
		Fexplosion = new File("explosion.wav");
		Fcoin = new File("coin.wav");
		Flife = new File("Life.wav");
		Fendgame = new File("game_over.wav");
	}
	
	public void glownyDzwiek() {
		t = new Thread(new Runnable() {
			
			public void run() {
	        	Fmain_theme = new File("main_theme.wav");
	        	AudioInputStream audioStream;
				try {
					audioStream = AudioSystem.getAudioInputStream(Fmain_theme);
					AudioFormat format = audioStream.getFormat();
		        	DataLine.Info info = new DataLine.Info(Clip.class, format);
		        	mainaudioClip = (Clip) AudioSystem.getLine(info);
		        	mainaudioClip.open(audioStream);
		        	mainaudioClip.start();
		        	mainaudioClip.loop(mainaudioClip.LOOP_CONTINUOUSLY);
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
	        	
			}
		});
    	
    	t.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		ybackground+=15;
		if (ybackground>=z)
			ybackground=0;
		
		repaint();
	}

	
	public void paint(Graphics g){
		super.paint(g);
		g2 = (Graphics2D) g;
		if (State==STATE.MENU) {
			rysujTlo(g2);
			gamemenu.paint(g2);
		}
		if (State==STATE.RANKING) {
			rysujTlo(g2);
			ranking.paint(g2);
		}
		if (State==STATE.PAUSE) {
			rysujTlo(g2);
			pausemenu.paint(g2);
		}
		if (State==STATE.SHOP) {
			rysujTlo(g2);
			shop.paint(g2);
		}
		if (State==STATE.GAMEOVER) {
			
			gameover.setCoincounter(coincounter);
			gameover.setTwolasercounter(twolasercounter);
			gameover.setThreelasercounter(threelasercounter);
			JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.add(gameover);
			window.setVisible(true);
			window.pack();
			State=STATE.MENU;
			repaint();
	        
		}
		if (State==STATE.GAME) {
			timecounter+=100;
			tick();
			rysujTlo(g2);
			
			rysujtrzyLasery2(g2);
			
			rysujLaser2(g2);
			
			if (!czyMaStrzelacCiezkaWiazka) {
				rysujtrzyLasery(g2);
				rysujdwaLasery(g2);
				rysujLaser(g2);
			}
			
			rysujWiazke(g2);
			rysujMonete(g2);
			rysujZycie(g2);
			rysujTarcze(g2);
			rysujUlepszenieLasera(g2);
			
			rysujEnemyLaser2(g2);
			rysujEnemyLaser(g2);
			
			rysujWrogiStatek1(g2);
			rysujWrogiStatek2(g2);
			rysujWrogiStatek3(g2);
			
			if (timecounter % 60000 == 0) {
				if (timedivider>1000) {
					timedivider-=100;
				}
			}
			
			if (timecounter % (3*timedivider) == 0) {
				
				for (int i = 0; i < 3; i++) {
					
					if((xenemy) >= getWidth()) {
						xenemychange=-xenemychange;	
					} else if (xenemy<=0) {
						xenemychange=-xenemychange;	
					}
					Enemy3List.add(new Point(xenemy,0));
					Enemy3Listxenemy3change.add(50);
					g2.drawImage(EnemyShip3, xenemy, 0, 50, 50, null);
					xenemy += xenemychange;

				}

			}
			else if (timecounter % (2*timedivider) == 0) {
				
				for (int i = 0; i < 3; i++) {
					
					if((xenemy) >= getWidth()) {
						xenemychange=-xenemychange;	
					} else if (xenemy<=0) {
						xenemychange=-xenemychange;	
					}
					Enemy2List.add(new Point(xenemy,0));
					g2.drawImage(EnemyShip2, xenemy, 0, 50, 50, null);
					xenemy += xenemychange;
					
				}
				
			}
			else if (timecounter % timedivider == 0) {
				
				for (int i = 0; i < 3; i++) {
					
					if((xenemy) >= getWidth()) {
						xenemychange=-xenemychange;	
					} else if (xenemy<=0) {
						xenemychange=-xenemychange;	
					}
					Enemy1List.add(new Point(xenemy,0));
					Enemy1Listxenemy1change.add(50);
					g2.drawImage(EnemyShip1, xenemy, 0, 50, 50, null);
					xenemy += xenemychange;

				}
				
			}

			if(timecounter % 60000 == 0) {
				LifeList.add(new Point(737,0));
				g2.drawImage(Life, 737, 0, 30, 30, null);
			}
			if(timecounter % 120000 == 0) {
				ShieldList.add(new Point(737,0));
				g2.drawImage(Shield, 737, 0, 30, 30, null);
			}
			if(timecounter % 180000 == 0) {
				LaserUpgradeList.add(new Point(737,0));
				g2.drawImage(LaserUpgrade, 737, 0, 30, 30, null);
			}
			
			rysujStatek(g2);
			rysujPasekzdrowia(g2);
			
		}
		if ( State==STATE.GAME || State==STATE.SHOP || State==STATE.GAMEOVER) {
			rysujLiczbeAmunicji(g2);
			rysujLiczbeMonet(g2);
		}
		
 
	}

	private void rysujLiczbeAmunicji(Graphics2D g2) {
		
		g2.drawImage(Three_lasers, 1210, 23, 26, 16, null);
		g2.setColor(new Color(163,73,164));
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(threelasercounter), 1245, 40);
		
		g2.drawImage(Two_lasers, 1300, 23, 25, 15, null);
		g2.setColor(new Color(163,73,164));
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(twolasercounter), 1340, 40);
		
	}

	private void rysujTlo(Graphics2D g2){
		g2.drawImage(Space, 0, ybackground, null);
		g2.drawImage(Space, 0, ybackground - z, null);
	}
	
	private void tick() {
		x+=velX;
		y+=velY;
		
		if (x + 50 >= getWidth()) {
			x = getWidth() - 50;
		}
		if (x <= 0) {
			x = 0;
		}
		if (y <= 0) {
			y = 0;
		}
		if (y + 50 >= getHeight()) {
			y = getHeight() - 50;
		}
	}
	
	private void SetVelX(int velX) {
		this.velX = velX;
	}
	
	private void SetVelY(int velY) {
		this.velY = velY;
	}
	
	private void rysujStatek(Graphics2D g2) {
		if (CoinList!=null) {
			for (Iterator<Point> iterator = CoinList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(new Rectangle(p.x,p.y,25,25).intersects(new Rectangle(x, y, 50, 50))) {
					iterator.remove();
					coincounter++;
					puscDzwiek(Fcoin);
				}
			}
		}
		if (LaserUpgradeList!=null) {
			for (Iterator<Point> iterator = LaserUpgradeList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(new Rectangle(p.x,p.y,50,50).intersects(new Rectangle(x, y, 50, 50))) {
					iterator.remove();
					czyMaStrzelacCiezkaWiazka = true;
					UruchomCiezkaWiazke = true;
				}
			}
		}
		if (LifeList!=null) {
			for (Iterator<Point> iterator = LifeList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(new Rectangle(p.x,p.y,30,30).intersects(new Rectangle(x, y, 50, 50))) {
					iterator.remove();
					puscDzwiek(Flife);
					if (life<100) {
						life += 20;
					}
				}
			}
		}
		if (ShieldList!=null) {
			for (Iterator<Point> iterator = ShieldList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(new Rectangle(p.x,p.y,30,30).intersects(new Rectangle(x, y, 50, 50))) {
					iterator.remove();
					czyMaTarcze = true;
					UruchomTarcze = true;
				}
			}
		}
		
		g2.drawImage(Ship, x, y, 50, 50, null);
		g2.setColor(new Color(0,255,255));
		if (czyMaTarcze) {
			energyshieldcounter+=100;
			if (UruchomTarcze) {
				puscDzwiek2(Fshield);
				UruchomTarcze = false;
			}
			if (energyshieldcounter == 10000) {
				energyshieldcounter=0;
				czyMaTarcze=false;
			}
			g2.drawOval(x-5, y-5, 57, 57);
		}
		
	}
	
	private void rysujEnemyLaser2(Graphics2D g2) {
		
		if (EnemyLaserList!=null) {
			
			for (Iterator<Point> iterator = EnemyLaserList.iterator(); iterator.hasNext();) {
				
				Point p = iterator.next();
				p.y = (int) (p.getY()+20);
				
				if (new Rectangle(p.x,p.y,10,30).intersects(new Rectangle(x,y,50,50))) {
					
					czyStatekDostal = true;
					
					if (!czyMaTarcze) {
						if (life-20>0) {
							life-=20;
						}
						else {
							timer.stop();
							mainaudioClip.stop();
							puscDzwiek(Fendgame);
							State = STATE.GAMEOVER;
							repaint();
						}
					}	
					
				}
				
				if (czyStatekDostal || p.getY()+30>=getHeight()) {
					czyStatekDostal=false;
					iterator.remove();
				}
				
				g2.drawImage(Enemylaser, p.x, p.y,10, 30, null);
				
			}
			
		}

	}
	
	private void rysujEnemyLaser(Graphics2D g2) {
		
		if (timecounter % timedivider == 0) {
			
			for (Iterator<Point> iterator = Enemy1List.iterator(); iterator.hasNext();) {
				
				Point p = iterator.next();
				xlaser = p.x + 20;
				ylaser = p.y+30;
				EnemyLaserList.add(new Point(xlaser,ylaser));
				g2.drawImage(Enemylaser, xlaser, ylaser, 10, 30, null);
				
			}
			for (Iterator<Point> iterator2 = Enemy2List.iterator(); iterator2.hasNext();) {
				
				Point p2 = iterator2.next();
				xlaser = p2.x + 20;
				ylaser = p2.y+30;
				EnemyLaserList.add(new Point(xlaser,ylaser));
				g2.drawImage(Enemylaser, xlaser, ylaser, 10, 30, null);
				
			}
			for (Iterator<Point> iterator3 = Enemy3List.iterator(); iterator3.hasNext();) {
				
				Point p3 = iterator3.next();
				xlaser = p3.x + 20;
				ylaser = p3.y+30;
				EnemyLaserList.add(new Point(xlaser,ylaser));
				g2.drawImage(Enemylaser, xlaser, ylaser, 10, 30, null);
				
			}
			
		}

	}
	
	private void rysujWrogiStatek1(Graphics2D g2) {
		if (Enemy1List!=null) {
			int i = 0;
			for (Iterator<Point> iterator = Enemy1List.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				Integer p2 = Enemy1Listxenemy1change.get(i);
				if(p.x + p2 < getWidth()) {
					p.x+=p2;
				}
				else {
					p2=-p2;
					Enemy1Listxenemy1change.set(i, p2);
					p.x+=p2;
				}
				if(p.x <= 0) {
					p2=-p2;
					Enemy1Listxenemy1change.set(i, p2);
				}
				g2.drawImage(EnemyShip1, p.x, p.y,50, 50, null);
				i+=1;
			}
		}
	}
	
	private void rysujWrogiStatek2(Graphics2D g2) {
		if (Enemy2List!=null) {
			for (Iterator<Point> iterator = Enemy2List.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(p.y + yenemy2change < getHeight()) {
					p.y+=yenemy2change;
				}
				else {
					iterator.remove();
				}
				g2.drawImage(EnemyShip2, p.x, p.y,50, 50, null);
			}
		}
	}
	
	private void rysujWrogiStatek3(Graphics2D g2) {
		if (Enemy3List!=null) {
			int i = 0;
			for (Iterator<Point> iterator = Enemy3List.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				Integer p2 = Enemy3Listxenemy3change.get(i);
				if(p.x + p2 < getWidth()) {
					p.x+=p2;
				}
				else if (p.x + p2 >= getWidth()) {
					p2=-p2;
					Enemy3Listxenemy3change.set(i, p2);
					p.x+=p2;
				}
				if(p.x <= 0) {
					p2=-p2;
					Enemy3Listxenemy3change.set(i, p2);
				}
				if(p.y + yenemy3change < getHeight()) {
					p.y+=yenemy3change;
				}
				else {
					iterator.remove();
					Enemy3Listxenemy3change.remove(i);
					i-=1;
				}
				g2.drawImage(EnemyShip3, p.x, p.y,50, 50, null);
				i+=1;
			}
		}
	}
	
	private void rysujMonete(Graphics2D g2) {
		if (CoinList!=null) {
			for (Iterator<Point> iterator = CoinList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(p.y + ycoinchange < getHeight()) {
					p.y+=ycoinchange;
				}
				else {
					iterator.remove();
				}
				g2.drawImage(Coin, p.x, p.y, 25, 25, null);
			}
		}
	}
	
	private void rysujZycie(Graphics2D g2) {
		if (LifeList!=null) {
			for (Iterator<Point> iterator = LifeList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(p.y + 10 < getHeight()) {
					p.y+=10;
				}
				else {
					iterator.remove();
				}
				g2.drawImage(Life, p.x, p.y, 30, 30, null);
			}
		}
	}
	
	private void rysujTarcze(Graphics2D g2) {
		if (ShieldList!=null) {
			for (Iterator<Point> iterator = ShieldList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(p.y + 10 < getHeight()) {
					p.y+=10;
				}
				else {
					iterator.remove();
				}
				g2.drawImage(Shield, p.x, p.y, 50, 50, null);
			}
		}
	}
	
	private void rysujUlepszenieLasera(Graphics2D g2) {
		if (LaserUpgradeList!=null) {
			for (Iterator<Point> iterator = LaserUpgradeList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				if(p.y + 10 < getHeight()) {
					p.y+=10;
				}
				else {
					iterator.remove();
				}
				g2.drawImage(LaserUpgrade, p.x, p.y, 50, 50, null);
			}
		}
	}
	
	private void rysujLaser2(Graphics2D g2) {
		if (LaserList!=null) {
			for (Iterator<Point> iterator = LaserList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				p.y = (int) (p.getY()-60);
				if (Enemy1List!=null) {
					int i = 0;
					for (Iterator<Point> iterator2 = Enemy1List.iterator(); iterator2.hasNext();) {
						Point p2 = iterator2.next();
						if (new Rectangle(p.x,p.y,10,30).intersects(new Rectangle(p2.x,p2.y,50,50))) {
							g2.drawImage(Explosion,p2.x,p2.y,50,50,null);
							iterator2.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy1Listxenemy1change.remove(i);
							i-=1;
							CoinList.add(p2);
						}
						i+=1;
					}
				}
				if (Enemy2List!=null) {
					for (Iterator<Point> iterator3 = Enemy2List.iterator(); iterator3.hasNext();) {
						Point p3 = iterator3.next();
						if (new Rectangle(p.x,p.y,10,30).intersects(new Rectangle(p3.x,p3.y,50,50))) {
							g2.drawImage(Explosion,p3.x,p3.y,50,50,null);
							iterator3.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							CoinList.add(p3);
						}
					}
				}
				if (Enemy3List!=null) {
					int i = 0;
					for (Iterator<Point> iterator4 = Enemy3List.iterator(); iterator4.hasNext();) {
						Point p4 = iterator4.next();
						if (new Rectangle(p.x,p.y,10,30).intersects(new Rectangle(p4.x,p4.y,50,50))) {
							g2.drawImage(Explosion,p4.x,p4.y,50,50,null);
							iterator4.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy3Listxenemy3change.remove(i);
							i-=1;
							CoinList.add(p4);
						}
						i+=1;
					}
				}
				if (czyPrzeciwnikWybuchl == true || p.getY()-30<=0) {
					iterator.remove();
					czyPrzeciwnikWybuchl = false;
				}
				g2.drawImage(Laser, p.x, p.y,10, 30, null);
			}
		}
	}
	
	private void rysujLaser(Graphics2D g2) {
		if (czyMaStrzelacLaserem) {
			xlaser = x + 20;
			ylaser = y-30;
			LaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser, xlaser, ylaser, 10, 30, null);
			puscDzwiek(Flaser);
		}

	}
	
	private void rysujtrzyLasery2(Graphics2D g2) {
		if (LeftLaserList!=null) {
			for (Iterator<Point> iterator = LeftLaserList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				p.x = (int) (p.getX()-20);
				p.y = (int) (p.getY()-45);
				if (Enemy1List!=null) {
					int i = 0;
					for (Iterator<Point> iterator2 = Enemy1List.iterator(); iterator2.hasNext();) {
						Point p2 = iterator2.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p2.x,p2.y,50,50))) {
							g2.drawImage(Explosion,p2.x,p2.y,50,50,null);
							iterator2.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy1Listxenemy1change.remove(i);
							i-=1;
							CoinList.add(p2);
						}
						i+=1;
					}
				}
				if (Enemy2List!=null) {
					for (Iterator<Point> iterator3 = Enemy2List.iterator(); iterator3.hasNext();) {
						Point p3 = iterator3.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p3.x,p3.y,50,50))) {
							g2.drawImage(Explosion,p3.x,p3.y,50,50,null);
							iterator3.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
						}
					}
				}
				if (Enemy3List!=null) {
					int i = 0;
					for (Iterator<Point> iterator4 = Enemy3List.iterator(); iterator4.hasNext();) {
						Point p4 = iterator4.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p4.x,p4.y,50,50))) {
							g2.drawImage(Explosion,p4.x,p4.y,50,50,null);
							iterator4.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy3Listxenemy3change.remove(i);
							i-=1;
						}
						i+=1;
					}
				}
				if (czyPrzeciwnikWybuchl == true || p.getY()-30<=0) {
					iterator.remove();
					czyPrzeciwnikWybuchl = false;
				}
				g2.drawImage(Laser_left, p.x, p.y,22, 32, null);
			}
			
		}
		if (RightLaserList!=null) {
			for (Iterator<Point> iterator = RightLaserList.iterator(); iterator.hasNext();) {
				Point p = iterator.next();
				p.x = (int) (p.getX()+20);
				p.y = (int) (p.getY()-45);
				if (Enemy1List!=null) {
					int i = 0;
					for (Iterator<Point> iterator2 = Enemy1List.iterator(); iterator2.hasNext();) {
						Point p2 = iterator2.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p2.x,p2.y,50,50))) {
							g2.drawImage(Explosion,p2.x,p2.y,50,50,null);
							iterator2.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy1Listxenemy1change.remove(i);
							i-=1;
							CoinList.add(p2);
						}
						i+=1;
					}
				}
				if (Enemy2List!=null) {
					for (Iterator<Point> iterator3 = Enemy2List.iterator(); iterator3.hasNext();) {
						Point p3 = iterator3.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p3.x,p3.y,50,50))) {
							g2.drawImage(Explosion,p3.x,p3.y,50,50,null);
							iterator3.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
						}
					}
				}
				if (Enemy3List!=null) {
					int i = 0;
					for (Iterator<Point> iterator4 = Enemy3List.iterator(); iterator4.hasNext();) {
						Point p4 = iterator4.next();
						if (new Rectangle(p.x,p.y,22,32).intersects(new Rectangle(p4.x,p4.y,50,50))) {
							g2.drawImage(Explosion,p4.x,p4.y,50,50,null);
							iterator4.remove();
							czyPrzeciwnikWybuchl = true;
							puscDzwiek(Fexplosion);
							Enemy3Listxenemy3change.remove(i);
							i-=1;
						}
						i+=1;
					}
				}
				if (czyPrzeciwnikWybuchl == true || p.getY()-30<=0) {
					iterator.remove();
					czyPrzeciwnikWybuchl = false;
				}
				g2.drawImage(Laser_right, p.x, p.y,22, 32, null);
			}
		}
		
	}

	private void rysujtrzyLasery(Graphics2D g2) {
		if (czyMaStrzelacTrzemaLaserami) {
			xlaser = x;
			ylaser = y-30;
			LeftLaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser_left, xlaser, ylaser, 22, 32, null);
			xlaser = x + 20;
			ylaser = y-30;
			LaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser, xlaser, ylaser, 10, 30, null);
			xlaser = x + 27;
			ylaser = y-30;
			RightLaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser_right, xlaser, ylaser, 22, 32, null);
			puscDzwiek(Flaser);
		}
	}

	private void rysujdwaLasery(Graphics2D g2) {
		if (czyMaStrzelacDwomaLaserami) {
			xlaser = x;
			ylaser = y-30;
			LaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser, xlaser, ylaser, 10, 30, null);
			xlaser = x + 40;
			ylaser = y-30;
			LaserList.add(new Point(xlaser,ylaser));
			g2.drawImage(Laser, xlaser, ylaser, 10, 30, null);
			puscDzwiek(Flaser);
		}
	}
	
	private void rysujWiazke(Graphics2D g22) {
		if (czyMaStrzelacCiezkaWiazka) {
			heavybeamcounter += 100;
			if (heavybeamcounter == 10000) {
				heavybeamcounter=0;
				czyMaStrzelacCiezkaWiazka=false;
			}
			xlaser = x+5;
			ylaser = y-30;
			g2.drawImage(Heavybeam, xlaser, 0, 40, ylaser, null);
			if (UruchomCiezkaWiazke) {
				puscDzwiek2(Fheavylaser);
				UruchomCiezkaWiazke = false;
			}
				if (Enemy1List!=null) {
					for (Iterator<Point> iterator2 = Enemy1List.iterator(); iterator2.hasNext();) {
						Point p2 = iterator2.next();
						if (new Rectangle(xlaser, 0, 40, ylaser).intersects(new Rectangle(p2.x,p2.y,50,50))) {
							g2.drawImage(Explosion,p2.x,p2.y,50,50,null);
							iterator2.remove();
							puscDzwiek(Fexplosion);
						}
					}
				}
				if (Enemy2List!=null) {
					for (Iterator<Point> iterator3 = Enemy2List.iterator(); iterator3.hasNext();) {
						Point p3 = iterator3.next();
						if (new Rectangle(xlaser, 0, 40, ylaser).intersects(new Rectangle(p3.x,p3.y,50,50))) {
							g2.drawImage(Explosion,p3.x,p3.y,50,50,null);
							iterator3.remove();
							puscDzwiek(Fexplosion);
						}
					}
				}
				if (Enemy3List!=null) {
					for (Iterator<Point> iterator4 = Enemy3List.iterator(); iterator4.hasNext();) {
						Point p4 = iterator4.next();
						if (new Rectangle(xlaser, 0, 40, ylaser).intersects(new Rectangle(p4.x,p4.y,50,50))) {
							g2.drawImage(Explosion,p4.x,p4.y,50,50,null);
							iterator4.remove();
							puscDzwiek(Fexplosion);
						}
					}
				}
		}
	}
	
	private void puscDzwiek(File file) {
		AudioInputStream audioStream;
		try {
			audioStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioStream.getFormat();
        	DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
        	audioClip.open(audioStream);
        	audioClip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void puscDzwiek2(File file) {
		Thread theavybeam = new Thread(new Runnable() {
			
			public void run() {
	        	AudioInputStream audioStream;
				try {
					audioStream = AudioSystem.getAudioInputStream(file);
					AudioFormat format = audioStream.getFormat();
		        	DataLine.Info info = new DataLine.Info(Clip.class, format);
					Clip audioClip = (Clip) AudioSystem.getLine(info);
		        	audioClip.open(audioStream);
		        	audioClip.start();
		    		audioClip.loop(audioClip.LOOP_CONTINUOUSLY);
		    		Thread.sleep(10000);
		    		audioClip.stop();
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
    	
		theavybeam.start();
	}
	
	private void rysujPasekzdrowia(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRect(50, 25, life, 12);
		g2.setColor(Color.WHITE);
		g2.fillRect(50+life, 25, 100-life, 12);
		g2.setColor(Color.BLACK);
		g2.drawLine(70, 25, 70, 37);
		g2.drawLine(90, 25, 90, 37);
		g2.drawLine(110, 25, 110, 37);
		g2.drawLine(130, 25, 130, 37);
		
	}

	private void rysujLiczbeMonet(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.setFont(new Font("Gotham Medium", Font.BOLD, 25));
		g2.drawString(String.valueOf(coincounter), 1400, 40);
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,1000);
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_P) {
			if (timer.isRunning()) {
				timer.stop();
				mainaudioClip.stop();
				State = STATE.PAUSE;
				repaint();
			}
			else {
				State = STATE.GAME;
				timer.start();
				glownyDzwiek();
			}
		}
			
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_D)
				SetVelX(25);
			if (key == KeyEvent.VK_A)
				SetVelX(-25);
			if (key == KeyEvent.VK_W)
				SetVelY(-25);
			if (key == KeyEvent.VK_S)
				SetVelY(25);
			if (key == KeyEvent.VK_SPACE) {
				if (threelasercounter>0) {
					czyMaStrzelacTrzemaLaserami = true;
					threelasercounter--;
				}
				else if (twolasercounter>0) {
					czyMaStrzelacTrzemaLaserami = false;
					czyMaStrzelacDwomaLaserami = true;
					twolasercounter--;
				}
				else {
					czyMaStrzelacTrzemaLaserami = false;
					czyMaStrzelacDwomaLaserami = false;
					czyMaStrzelacLaserem = true;
				}
			}
		}
		
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_D) {
				SetVelX(0);
			}

			if (key == KeyEvent.VK_A) {
				SetVelX(0);
			}

			if (key == KeyEvent.VK_W) {
				SetVelY(0);
			}

			if (key == KeyEvent.VK_S) {
				SetVelY(0);
			}

			if (key == KeyEvent.VK_SPACE) {
				czyMaStrzelacTrzemaLaserami = false;
				czyMaStrzelacDwomaLaserami = false;
				czyMaStrzelacLaserem = false;
			}
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if (State==STATE.MENU) {
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=300 && ymouse<=400) ) {
				if (!GameMenu.CheckIsStartSelected()) {
					GameMenu.SetIsStartSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
				
			}
			else {
				if (GameMenu.CheckIsStartSelected()) {
					GameMenu.SetIsStartSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=500 && ymouse<=600) ) {
				if (!GameMenu.CheckIsLoadSelected()) {
					GameMenu.SetIsLoadSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (GameMenu.CheckIsLoadSelected()) {
					GameMenu.SetIsLoadSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=700 && ymouse<=800) ) {
				if (!GameMenu.CheckIsRankingSelected()) {
					GameMenu.SetIsRankingSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (GameMenu.CheckIsRankingSelected()) {
					GameMenu.SetIsRankingSelectedF();
					repaint();
				}
			}
		}
		
		if (State==STATE.RANKING) {
			if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
				if (!Ranking.CheckIsBackSelected()) {
					Ranking.SetIsBackSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
				
			}
			else {
				if (Ranking.CheckIsBackSelected()) {
					Ranking.SetIsBackSelectedF();
					repaint();
				}
			}
		}
		
		if (State==STATE.PAUSE) {
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=300 && ymouse<=400) ) {
				if (!PauseMenu.CheckIsResumeSelected()) {
					PauseMenu.SetIsResumeSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
				
			}
			else {
				if (PauseMenu.CheckIsResumeSelected()) {
					PauseMenu.SetIsResumeSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=500 && ymouse<=600) ) {
				if (!PauseMenu.CheckIsSaveSelected()) {
					PauseMenu.SetIsSaveSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (PauseMenu.CheckIsSaveSelected()) {
					PauseMenu.SetIsSaveSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=700 && ymouse<=800) ) {
				if (!PauseMenu.CheckIsShopSelected()) {
					PauseMenu.SetIsShopSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (PauseMenu.CheckIsShopSelected()) {
					PauseMenu.SetIsShopSelectedF();
					repaint();
				}
			}
		}
		
		if (State==STATE.SHOP) {
			if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
				if (!Shop.CheckIsBackSelected()) {
					Shop.SetIsBackSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
				
			}
			else {
				if (Shop.CheckIsBackSelected()) {
					Shop.SetIsBackSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=450 && xmouse<=750)  && (ymouse>=350 && ymouse<=650) ) {
				if (!Shop.CheckIsTwoLasersSelected()) {
					Shop.SetIsTwoLasersSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (Shop.CheckIsTwoLasersSelected()) {
					Shop.SetIsTwoLasersSelectedF();
					repaint();
				}
			}
			
			if ( (xmouse>=800 && xmouse<=1100)  && (ymouse>=350 && ymouse<=650) ) {
				if (!Shop.CheckIsThreeLasersSelected()) {
					Shop.SetIsThreeLasersSelectedT();
					puscDzwiek(Fselect);
					repaint();
				}
			}
			else {
				if (Shop.CheckIsThreeLasersSelected()) {
					Shop.SetIsThreeLasersSelectedF();
					repaint();
				}
			}
		}
		
	}
	
	private void resetSettings() {
		
		life = 100;
		coincounter = 0;
		threelasercounter = 0;
		twolasercounter = 0;
		LaserList.clear();
		EnemyLaserList.clear();
		Enemy1List.clear();
		Enemy1Listxenemy1change.clear();
		Enemy2List.clear();
		Enemy3List.clear();
		Enemy3Listxenemy3change.clear();
		CoinList.clear();
		LifeList.clear();
		ShieldList.clear();
		LaserUpgradeList.clear();
		LeftLaserList.clear();
		RightLaserList.clear();
		x = 737;
		y = 900;
		SetVelX(0);
		SetVelY(0);
		
	}
	
	public void mouseClicked(MouseEvent e) {
		
		int xmouse = e.getX();
		int ymouse = e.getY();
		
		if (State==STATE.MENU) {
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=300 && ymouse<=400) ) {
				
				puscDzwiek(Fselect);
				GameMenu.SetIsStartSelectedF();
				resetSettings();
				State=STATE.GAME;
	    		puscDzwiek(Fstartgame);
	    		timer.start();
	    		glownyDzwiek();
	    		
			
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=500 && ymouse<=600) ) {
				
				puscDzwiek(Fselect);
				GameMenu.SetIsLoadSelectedF();
				
				Scanner scanner;
				
				try {
					scanner = new Scanner(new File("Save.txt"));
					while(scanner.hasNextLine()) {
						String linia = scanner.nextLine();
						if (String.valueOf(linia.split(" ")[0]).equals("life")) {
							life = Integer.valueOf(linia.split(" ")[1]);
						}
						if (String.valueOf(linia.split(" ")[0]).equals("threelasercounter")) {
							threelasercounter = Integer.valueOf(linia.split(" ")[1]);
						}
						if (String.valueOf(linia.split(" ")[0]).equals("twolasercounter")) {
							twolasercounter = Integer.valueOf(linia.split(" ")[1]);
						}
						if (String.valueOf(linia.split(" ")[0]).equals("coincounter")) {
							coincounter = Integer.valueOf(linia.split(" ")[1]);
						}
					}
					scanner.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				State=STATE.GAME;
	    		puscDzwiek(Fstartgame);
	    		timer.start();
	    		glownyDzwiek();
				
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=700 && ymouse<=800) ) {
				
				puscDzwiek(Fselect);
				State = STATE.RANKING;
				repaint();
				GameMenu.SetIsRankingSelectedF();
			}
			
		}
		
		if (State==STATE.RANKING) {
			
			if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
				
				puscDzwiek(Fselect);
				State = STATE.MENU;
				repaint();
				Ranking.SetIsBackSelectedF();
				
			}
			
		}
		
		if (State==STATE.PAUSE) {
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=300 && ymouse<=400) ) {

				State = STATE.GAME;
				timer.start();
				glownyDzwiek();
				PauseMenu.SetIsResumeSelectedF();
			
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=500 && ymouse<=600) ) {
				
				puscDzwiek(Fselect);
				repaint();
				PauseMenu.SetIsSaveSelectedF();
				
				PrintWriter pw;
				try {
					pw = new PrintWriter(new File("Save.txt"));
					
					pw.print("life ");
					pw.print(life + "\n");
					pw.print("threelasercounter ");
					pw.print(threelasercounter + "\n");
					pw.print("twolasercounter ");
					pw.print(twolasercounter + "\n");
					pw.print("coincounter ");
					pw.print(coincounter + "\n");
					
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			if ( (xmouse>=540 && xmouse<=1030)  && (ymouse>=700 && ymouse<=800) ) {
				
				puscDzwiek(Fselect);
				State = STATE.SHOP;
				repaint();
				PauseMenu.SetIsShopSelectedF();
				
			}
			
		}
		if (State==STATE.SHOP) {
			if ( (xmouse>=70 && xmouse<=270)  && (ymouse>=70 && ymouse<=150) ) {
				
				puscDzwiek(Fselect);
				State = STATE.PAUSE;
				repaint();
				Shop.SetIsBackSelectedF();
				
			}
			
			if ( (xmouse>=450 && xmouse<=750)  && (ymouse>=350 && ymouse<=650) ) {
				
				puscDzwiek(Fcoin);
				if (coincounter-15>=0) {
					coincounter-=15;
					twolasercounter+=5;
					repaint();
				}
				
			}
			
			if ( (xmouse>=800 && xmouse<=1100)  && (ymouse>=350 && ymouse<=650) ) {
				
				puscDzwiek(Fcoin);
				if (coincounter-25>=0) {
					coincounter-=25;
					threelasercounter+=5;
					repaint();
				}
				
			}
			
		}

	}
	
	public void mouseDragged(MouseEvent e) {
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

public class Lekcja21_22_23_24_25_26 {
	
	private GamePanel gamepanel;
	private GameOver gameoverpanel;

	public static void main(String[] args) {
		// space shooter
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run(){
					try {
						new Lekcja21_22_23_24_25_26().createAndShowGUI();
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
		gamepanel = new GamePanel();
		window.add(gamepanel);
		window.setVisible(true);
		window.addKeyListener(gamepanel);
		window.pack();
		
	}
}
