import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lekcja11 extends JPanel{
	
	Color kolor1 = Color.BLACK;
	Color kolor2 = Color.RED;
	int xwym = 0;
	int ywym = 0;
	int xkwa = 0;
	int ykwa = 0;
	final Random random = new Random();
	int xcze = 0;
	int ycze = 0;
	
	public  Lekcja11 () {
		xcze = (random.nextInt(8));
		if (xcze%2==0) {
			ycze = (random.nextInt(8));
			while (ycze%2==0) {
				ycze = (random.nextInt(8));
			}
		}
		else if (xcze%2!=0) {
			ycze = (random.nextInt(8));
			while (ycze%2!=0) {
				ycze = (random.nextInt(8));
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return  new Dimension(400,400);
	}
	
	protected void paintComponent(Graphics  g) {
		super.paintComponent(g);
		xwym = (int) (getSize().getWidth());
		ywym = (int) (getSize().getHeight());   
		xkwa = (int) (xwym/8);
		ykwa = (int) (ywym/8);
		g.setColor(kolor1);
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (i%2==0) {
					if (j%2!=0) {
						g.fillRect(j*xkwa, i*ykwa, xkwa, ykwa);
					}
				}
				else if (i%2!=0) {
					if (j%2==0) {
						g.fillRect(j*xkwa, i*ykwa, xkwa, ykwa);
					}
				}
			}
		}
		g.setColor(kolor2);
		g.fillRect(xcze*xkwa, ycze*ykwa, xkwa, ykwa);
		
	}

	public static void main(String[] args) {
//		okno ³aduj¹ce siê
		JFrame window  = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Lekcja11 okno = new Lekcja11();
		
		window.add(okno);
		
		window.setVisible(true);
		window.pack();
//		narysowaæ szachownice (czarny, bia³y)
//		jeden kwadrat czarny losowy zmienia³ kolor na czerwony
//		labirynt
//		animacja pi³ka odbijaj¹ca siê od œciany lewa prawa
	}

}
