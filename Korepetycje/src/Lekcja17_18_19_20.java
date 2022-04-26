import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

class MainFrame extends JFrame implements KeyListener{
	
	private JMenuBar mb;
	private JMenu plik_menu;
	private JMenu opcje_menu;
	private JMenu obroc_menu;
	private JMenu narzedzia_menu;
	private JMenu ksztalty_menu;
	private JMenu kolory_menu;
	private JMenuItem zaznacz_item;
	private static JMenuItem wytnij_item;
	private static JMenuItem kopiuj_item;
	private static JMenuItem wklej_item;
	private JMenuItem obroc_w_prawo_item;
	private JMenuItem obroc_w_lewo_item;
	private JMenuItem obroc_o_180_item;
	private JMenuItem olowek_item;
	private JMenuItem wypelnienie_item;
	private JMenuItem tekst_item;
	private JMenuItem gumka_item;
	private JMenuItem owal_item;
	private JMenuItem prostokat_item;
	private JMenuItem trojkat_item;
	private JMenuItem czarny_item;
	private JMenuItem czerwony_item;
	private JMenuItem niebieski_item;
	private JMenuItem green_item;
	private JMenuItem open_item;
	private JMenuItem save_item;
	private JMenuItem saveas_item;
	private JMenuItem selektor_kolorow_item;
	private MainPanel5 containerPanel;
	private int x = 1500,y = 500;
	
	public MainFrame() {
		containerPanel = new MainPanel5();
		containerPanel.setBounds(0, 0, getPreferredSize().width,getPreferredSize().height);
		add(containerPanel);
		
		mb = new JMenuBar();
		setLayout(null);
		
		Plik();
		
		Opcje();
		
		Obroc();
		
		Narzedzia();
		
		Ksztalty();
		
		Kolory();
		
		mb.add(plik_menu);
		mb.add(opcje_menu);
		mb.add(obroc_menu);
		mb.add(narzedzia_menu);
		mb.add(ksztalty_menu);
		mb.add(kolory_menu);
		
		setJMenuBar(mb);
	    addKeyListener(this);
	}
	
	public void Plik() {
		plik_menu = new JMenu("Plik");
		open_item = new JMenuItem("Otwórz");
		save_item = new JMenuItem("Zapisz");
		saveas_item = new JMenuItem("Zapisz jako");
		try {
			open_item.setIcon(new ImageIcon(ImageIO.read(new File("open.png"))));
			save_item.setIcon(new ImageIcon(ImageIO.read(new File("save.png"))));
			saveas_item.setIcon(new ImageIcon(ImageIO.read(new File("saveas.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		open_item.addActionListener(listener);
		save_item.addActionListener(listener);
		saveas_item.addActionListener(listener);
		
		plik_menu.add(open_item);
		plik_menu.add(save_item);
		plik_menu.add(saveas_item);
	}
	
	public void Opcje() {
		opcje_menu = new JMenu("Opcje");
		zaznacz_item = new JMenuItem("Zaznacz");
		wytnij_item = new JMenuItem("Wytnij");
		kopiuj_item = new JMenuItem("Kopiuj");
		wklej_item = new JMenuItem("Wklej");
		try {
			zaznacz_item.setIcon(new ImageIcon(ImageIO.read(new File("zaznacz.png"))));
			wytnij_item.setIcon(new ImageIcon(ImageIO.read(new File("wytnij.png"))));
			kopiuj_item.setIcon(new ImageIcon(ImageIO.read(new File("kopiuj.png"))));
			wklej_item.setIcon(new ImageIcon(ImageIO.read(new File("wklej.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		zaznacz_item.addActionListener(listener);
		wytnij_item.addActionListener(listener);
		kopiuj_item.addActionListener(listener);
		wklej_item.addActionListener(listener);
		opcje_menu.add(zaznacz_item);
		opcje_menu.add(wytnij_item);
		opcje_menu.add(kopiuj_item);
		opcje_menu.add(wklej_item);
		wytnij_item.setEnabled(false);
		kopiuj_item.setEnabled(false);
		wklej_item.setEnabled(false);
	}
	
	public void Obroc() {
		obroc_menu = new JMenu("Obróæ");
		obroc_w_prawo_item = new JMenuItem("Obróæ w prawo");
		obroc_w_lewo_item = new JMenuItem("Obróæ w lewo");
		obroc_o_180_item = new JMenuItem("Obróæ o 180");
		try {
			obroc_w_prawo_item.setIcon(new ImageIcon(ImageIO.read(new File("rotate_right.png"))));
			obroc_w_lewo_item.setIcon(new ImageIcon(ImageIO.read(new File("rotate_left.png"))));
			obroc_o_180_item.setIcon(new ImageIcon(ImageIO.read(new File("rotate_180.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		obroc_w_prawo_item.addActionListener(listener);
		obroc_w_lewo_item.addActionListener(listener);
		obroc_o_180_item.addActionListener(listener);
		obroc_menu.add(obroc_w_prawo_item);
		obroc_menu.add(obroc_w_lewo_item);
		obroc_menu.add(obroc_o_180_item);
	}
	
	public void Narzedzia() {
		narzedzia_menu = new JMenu("Narzêdzia");
		olowek_item = new JMenuItem("O³ówek");
		wypelnienie_item = new JMenuItem("Wype³nienie");
		tekst_item = new JMenuItem("Tekst");
		gumka_item = new JMenuItem("Gumka");
		selektor_kolorow_item = new JMenuItem("Selektor kolorów");
		try {
			olowek_item.setIcon(new ImageIcon(ImageIO.read(new File("olowek.png"))));
			wypelnienie_item.setIcon(new ImageIcon(ImageIO.read(new File("wypelnienie.png"))));
			tekst_item.setIcon(new ImageIcon(ImageIO.read(new File("czcionka.png"))));
			gumka_item.setIcon(new ImageIcon(ImageIO.read(new File("gumka.png"))));
			selektor_kolorow_item.setIcon(new ImageIcon(ImageIO.read(new File("selektor_kolorow.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		olowek_item.addActionListener(listener);
		wypelnienie_item.addActionListener(listener);
		tekst_item.addActionListener(listener);
		gumka_item.addActionListener(listener);
		selektor_kolorow_item.addActionListener(listener);
		narzedzia_menu.add(olowek_item);
		narzedzia_menu.add(wypelnienie_item);
		narzedzia_menu.add(tekst_item);
		narzedzia_menu.add(gumka_item);
		narzedzia_menu.add(selektor_kolorow_item);
	}
	
	public void Ksztalty() {
		ksztalty_menu = new JMenu("Kszta³ty");
		owal_item = new JMenuItem("Owal");
		prostokat_item = new JMenuItem("Prostok¹t");
		trojkat_item = new JMenuItem("Trójk¹t");
		try {
			owal_item.setIcon(new ImageIcon(ImageIO.read(new File("owal.png"))));
			prostokat_item.setIcon(new ImageIcon(ImageIO.read(new File("prostokat.png"))));
			trojkat_item.setIcon(new ImageIcon(ImageIO.read(new File("trojkat.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		owal_item.addActionListener(listener);
		prostokat_item.addActionListener(listener);
		trojkat_item.addActionListener(listener);
		ksztalty_menu.add(owal_item);
		ksztalty_menu.add(prostokat_item);
		ksztalty_menu.add(trojkat_item);
	}
	
	public void Kolory() {
		kolory_menu = new JMenu("Kolory");
		czarny_item = new JMenuItem("Czarny");
		czerwony_item = new JMenuItem("Czerwony");
		niebieski_item = new JMenuItem("Niebieski");
		green_item = new JMenuItem("Zielony");
		try {
			czarny_item.setIcon(new ImageIcon(ImageIO.read(new File("czarny.png"))));
			czerwony_item.setIcon(new ImageIcon(ImageIO.read(new File("czerwony.png"))));
			niebieski_item.setIcon(new ImageIcon(ImageIO.read(new File("niebieski.png"))));
			green_item.setIcon(new ImageIcon(ImageIO.read(new File("zielony.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		czarny_item.addActionListener(listener);
		czerwony_item.addActionListener(listener);
		niebieski_item.addActionListener(listener);
		green_item.addActionListener(listener);
		kolory_menu.add(czarny_item);
		kolory_menu.add(czerwony_item);
		kolory_menu.add(niebieski_item);
		kolory_menu.add(green_item);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(x,y);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

//			Plik
//			Otwórz
			if (e.getSource() == open_item) {
				MainPanel5.Open();
				repaint();
			}
//			Zapisz
			else if (e.getSource() == save_item) {
				MainPanel5.Save();
			}
//			Zapisz jako
			else if (e.getSource() == saveas_item) {
				MainPanel5.SaveAs();
			}
			
//			Opcje
			
//			Zaznacz
			if (e.getSource() == zaznacz_item) {
				MainPanel5.Setindex(8);
			}
//			Wytnij
			else if (e.getSource() == wytnij_item) {
				MainPanel5.Cut();
				repaint();
			}
//			Kopiuj
			else if (e.getSource() == kopiuj_item) {
				MainPanel5.Copy();
			}
//			Wklej
			else if (e.getSource() == wklej_item) {
				MainPanel5.Setindex(9);
			}
			
//			Obroc
			
//			Obroc w prawo
			if (e.getSource() == obroc_w_prawo_item) {
				MainPanel5.ObrocWPrawo();
			}
//			Obroc w lewo
			if (e.getSource() == obroc_w_lewo_item) {
				MainPanel5.ObrocWLewo();
			}
//			Obroc o 180
			if (e.getSource() == obroc_o_180_item) {
				MainPanel5.ObrocO180();
			}
			
			
//			Narzedzia

//			O³ówek
			if (e.getSource() == olowek_item) {
				MainPanel5.Setindex(1);
			}
//			Wype³nienie
			else if (e.getSource() == wypelnienie_item) {
				MainPanel5.Setindex(5);
			}
//			Tekst
			else if (e.getSource() == tekst_item) {

			}
//			Gumka
			else if (e.getSource() == gumka_item) {
				MainPanel5.Setindex(6);
			}
//			Selektor kolorow
			else if (e.getSource() == selektor_kolorow_item) {
				MainPanel5.Setindex(7);
			}
		
//			Ksztalty
			
//			Owal
			if (e.getSource() == owal_item) {
				MainPanel5.Setindex(2);
			}
//			prostok¹t
			else if (e.getSource() == prostokat_item) {
				MainPanel5.Setindex(3);
			}
//			trojk¹t
			else if (e.getSource() == trojkat_item) {
				MainPanel5.Setindex(4);
			}
			
//			Kolory
			
//			czarny
			if (e.getSource() == czarny_item) {
				MainPanel5.black();
			}
//			czerwony
			else if (e.getSource() == czerwony_item) {
				MainPanel5.red();
			}
//			niebieski
			else if (e.getSource() == niebieski_item) {
				MainPanel5.blue();
			}
//			zielony
			else if (e.getSource() == green_item) {
				MainPanel5.green();
			}
		}
	};

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			MainPanel5.Setrubbersize(5);
		}
		if (key == KeyEvent.VK_DOWN) {
			MainPanel5.Setrubbersize(-5);
		}
		if ( (e.isControlDown()) && (key == KeyEvent.VK_O) ){
			MainPanel5.Open();
		}
		if ( (e.isControlDown()) && (key == KeyEvent.VK_S) ){
			MainPanel5.Save();
		}
		if (key == KeyEvent.VK_Q) {
			x=500;
			y=1500;
			pack();
		}
		
	}
	
//	stackoverflow -> wrzuæ pytanie na stackoverflow
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
	
	public static void wytnij_itemEnable() {
		wytnij_item.setEnabled(true);
	}
	
	public static void kopiuj_itemEnable() {
		kopiuj_item.setEnabled(true);
	}
	
	public static void wklej_itemEnable() {
		wklej_item.setEnabled(true);
	}
}

class MainPanel5 extends JPanel implements MouseListener, MouseMotionListener{
	
	
	private static BufferedImage imageBuffer;
	private static BufferedImage imageCopy = null;
	private static int index = 1;
	private int oldx, oldy;
	private int px, py;
	private static Graphics2D g2;
	private static Color colorbeforrubber;
	private static Color colorbeforselect;
	private static int rubbersize = 25;
	private static String StrSavePath = null;
	private static int returnVal;
	private static JFileChooser chooser;
	private static int zaznacz_px, zaznacz_py, zaznacz_x, zaznacz_y;
	
	public MainPanel5() {
	    addMouseMotionListener(this);
	    addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imageBuffer==null) {
		    imageBuffer = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
		    g2 = (Graphics2D) imageBuffer.getGraphics();
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    clear();
		}
		g.drawImage(imageBuffer, 0, 0, null);

	}
	
	public void clear() {
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();
	}
	
	public static void Open() {
		JFileChooser chooser = new JFileChooser("C:\\Users\\Predator\\Downloads");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("png",
	            "png");
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setFileFilter(filter);
	    returnVal = chooser.showOpenDialog(new JFrame());
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			java.io.File file = chooser.getSelectedFile();
			StrSavePath = file.getAbsolutePath();
			File outputfile = new File(StrSavePath);
			try {
				imageBuffer=ImageIO.read(outputfile);
				g2 = (Graphics2D) imageBuffer.getGraphics();
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			    black();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static void Save() {
		if (StrSavePath.equals(null)) {
			SaveAs();
		}
		else {
			File outputfile = new File(StrSavePath);
			try {
				ImageIO.write(imageBuffer, "PNG", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void SaveAs(){
		chooser = new JFileChooser("C:\\Users\\Predator\\Downloads");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("png",
	            "png");
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setFileFilter(filter);
	    returnVal = chooser.showSaveDialog(new JFrame());
		if (returnVal==JFileChooser.APPROVE_OPTION) {
			java.io.File file = chooser.getSelectedFile();
			StrSavePath = file.getAbsolutePath();
			if (!StrSavePath.contains(".png")){
				StrSavePath+=".png";
			}
			File outputfile = new File(StrSavePath);
			try {
				ImageIO.write(imageBuffer, "PNG", outputfile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void Select(int oldx, int oldy, int x, int y) {

		zaznacz_px = oldx;
		zaznacz_py = oldy;
		zaznacz_x = x;
		zaznacz_y = y;
		//wybranie prostok¹ta do kopiowania, wklejania, wycinania
		
	}
	
	public static void Cut() {
		colorbeforselect = (Color) g2.getPaint();
		imageCopy=CopyOfImage();
		g2.setColor(Color.white);
		g2.fillRect(zaznacz_px, zaznacz_py, zaznacz_x - zaznacz_px, zaznacz_y - zaznacz_py);
		g2.setPaint(colorbeforselect);
		MainFrame.wklej_itemEnable();
	}
	
	public static void Copy() {
		imageCopy=CopyOfImage();
		MainFrame.wklej_itemEnable();
	}
	
	public static BufferedImage CopyOfImage() {
		BufferedImage img  = imageBuffer.getSubimage(zaznacz_px, zaznacz_py, zaznacz_x - zaznacz_px, zaznacz_y - zaznacz_py);
		BufferedImage CopyOfImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g3 = CopyOfImage.createGraphics();
		g3.drawImage(img,0,0,null);
		return CopyOfImage;
	}
	
	public void Paste(int x, int y) {
		//Jakiœ panel który skopiuje pixele zaznaczonego obszaru???
		if (imageCopy!=null) {
			g2.drawImage(imageCopy, null, x, y);
		}
		repaint();
	}
	
	public static void ObrocWPrawo() {
		//Obrot w prawo
	}
	
	public static void ObrocWLewo() {
		//Obrot w lewo
	}
	
	public static void ObrocO180() {
		//Obrot o 180
	}
	
	public static void Setrubbersize(int i) {
		if (rubbersize+i>0) {
			rubbersize+=i;
		}
	}
	
	public void rubber(int x, int y) {
		colorbeforrubber = (Color) g2.getPaint();
		g2.setPaint(Color.white);
		g2.fillRect(x, y, rubbersize, rubbersize);
		g2.setPaint(colorbeforrubber);
		repaint();
	}
	
	public static void Setindex(int i) {
		index=i;
	}
	
	
	public static void black() {
		g2.setPaint(Color.black);
	}
	
	public static void red() {
		g2.setPaint(Color.red);
	}
	
	public static void blue() {
		g2.setPaint(Color.blue);
	}
	
	public static void green() {
		g2.setPaint(Color.green);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(getParent().getWidth(),getParent().getHeight());
	}
	
	public void mousePressed(MouseEvent e) {
		oldx = e.getX();
		oldy = e.getY();
			if (index==5) {
				px = e.getX();
				py = e.getY();
				Fill(imageBuffer, px, py, g2.getColor());
	    		repaint();
			}
			else if (index==7) {
				px = e.getX();
				py = e.getY();
				Color color = getColorAt((JFrame) SwingUtilities.getWindowAncestor(this), new Point(px,py));
				g2.setPaint(color);
			}
			else if (index==9) {
				px = e.getX();
				py = e.getY();
				try {
					Paste(px,py);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}

	public void mouseDragged(MouseEvent e) {
		
			if (index==1) {
				px = e.getX();
				py = e.getY();
				g2.drawLine(oldx, oldy, px, py);
				repaint();
				oldx = px;
				oldy = py;
			}
			if (index==6) {
				px = e.getX();
				py = e.getY();
				rubber(px,py);
				repaint();
			}
			
	}

	public void mouseReleased(MouseEvent e) {

			if (index==2) {
				px = e.getX();
				py = e.getY();
				g2.drawOval(oldx, oldy, px-oldx, py-oldy);
				repaint();
			}
			else if (index==3) {
				px = e.getX();
				py = e.getY();
				g2.drawRect(oldx, oldy, px-oldx, py-oldy);
				repaint();
			}
			else if (index==4) {
				px = e.getX();
				py = e.getY();
				g2.drawPolygon(new int[]{oldx,oldx+((px-oldx)/2),px},new int[]{py,oldy,py},3);
				repaint();
			}
			else if (index==8) {
				px = e.getX();
				py = e.getY();
				Select(oldx,oldy,px,py);
				MainFrame.wytnij_itemEnable();
				MainFrame.kopiuj_itemEnable();
			}
			
	}
	
	private static Color getColorAt(JFrame frm, Point p) {
        Rectangle rect = frm.getContentPane().getBounds();
        BufferedImage img = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
        frm.getContentPane().paintAll(img.createGraphics());
        return new Color(img.getRGB(p.x, p.y), true);
    }
    
    private void validate(BufferedImage bm, Stack<Point> sp, int x, int y, Color old_color, Color new_color) {
    	Color cx  = getColorAt((JFrame) SwingUtilities.getWindowAncestor(this), new Point(x,y));
    	if (cx.getRGB()==old_color.getRGB()) {
    		sp.push(new Point(x,y));
    		bm.setRGB(x, y, new_color.getRGB());
    	}
    }
    
    public void Fill (BufferedImage bm, int x, int y, Color new_clr) {
    	Color old_color = getColorAt((JFrame) SwingUtilities.getWindowAncestor(this), new Point(x,y));
    	Stack<Point> pixel = new Stack<Point>();
    	pixel.push(new Point(x,y));
    	bm.setRGB(x, y, new_clr.getRGB());
    	if (old_color==new_clr) return;
    	
    	while(pixel.size()>0) {
    		Point pt = (Point)pixel.pop();
    		if (pt.x>0 && pt.y>0 && pt.x<bm.getWidth()-1 && pt.y<bm.getHeight()-1) {
    			validate(bm, pixel, pt.x-1, pt.y,old_color, new_clr);
    			validate(bm, pixel, pt.x, pt.y-1,old_color, new_clr);
    			validate(bm, pixel, pt.x+1, pt.y,old_color, new_clr);
    			validate(bm, pixel, pt.x, pt.y+1,old_color, new_clr);
    		}
    	}
    	
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

public class Lekcja17_18_19_20 {
	
	private MainFrame frame;
	private MainPanel5 containerPanel;

	public static void main(String[] args) {
		//pi¹tek 20:00 niedziela 8:00
		//Paint
		//Lekcja17, Lekcja18, Lekcja19, Lekcja20
		//
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Lekcja17_18_19_20().createAndShowGUI();
			}
		});
	}
	
	public void createAndShowGUI() {
		frame = new MainFrame();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
	}
	
}
//kliknij zaznacz -> opcje kopiuj, wytnij przestaj¹ byæ wyszarzone
//Opcja kopiuj lub wytnij -> opcja wklej przestaje byæ wyszarzona
//o³ówek -> draw line
//wype³nienie-> wype³niaj danym kolorem dopóki???
//tekst -> zaznacz pole w którym bêdziesz wpisywaæ tekst
//gumka -> ma³y bia³y kwadrat, który wyrysowuje bia³y kwadrat
//kszta³ty -> draw na podstawie danego koloru
//kolor -> setColor