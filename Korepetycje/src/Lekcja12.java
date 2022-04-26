import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Lekcja12 extends JPanel {
	int x = 300;
	int rozmiar = 200-(Strnapis.split(" ").length*15); 
	int y = (550/Strnapis.split(" ").length);
	Color kolor = Color.black;
	Color kolor2 = Color.white;
	Color kolor3 = Color.gray;
	static String Strnapis;
	static String [] StrTabnapis;
	
	
	public Lekcja12(){
		setLayout(null);
		StrTabnapis = new String[Strnapis.split(" ").length];
		for(int i = 0; i < Strnapis.split(" ").length; i++) {
			StrTabnapis [i]=Strnapis.split(" ")[i];
		}
		x+=(Strnapis.split(" ").length*10);
		if (Strnapis.split(" ").length==1) {
			y=300;
		}
	}
	
	public Dimension getPreferredSize() {
		return  new Dimension(1000,500);
	}
	
	protected void paintComponent(Graphics  g) {
		super.paintComponent(g);
		g.setColor(kolor2);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(int i = 0; i < Strnapis.split(" ").length; i++) {
			g.setColor(kolor3);
			g.setFont(new Font("Gotham Medium", Font.ITALIC, rozmiar));
			g.drawString(StrTabnapis [i], x+5, y+5+(i*(50+(rozmiar/(Strnapis.split(" ").length+2)))));
			g.setColor(kolor);
			g.drawString(StrTabnapis [i], x, y+(i*(50+(rozmiar/(Strnapis.split(" ").length+2)))));
		}
	}
	
	public void animacja() {
		
		boolean b =true;
		
		while(b) {
			int rand_val = new Random().nextInt(5);
			Color rand_color = Color.black;
			Color rand_color2 = Color.white;
			
			switch (rand_val) {
			case 0:
				rand_color = Color.black;
				rand_color2 = Color.white;
				break;
				
			case 1:
				rand_color = Color.yellow;
				rand_color2 = new Color(192, 0, 255);
				break;
				
			case 2:
				rand_color = Color.red;
				rand_color2 = Color.green;
				break;
				
			case 3:
				rand_color = Color.blue;
				rand_color2 = Color.orange;
				break;
				
			case 4:
				rand_color = Color.green;
				rand_color2 = Color.red;
				break;
			}
			
			kolor = rand_color;
			kolor2 = rand_color2;
			
			try {
				Thread.sleep(300);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Napis migaj¹cy
		JFrame window  = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Scanner scanner = new Scanner(System.in);
		System.out.println("Podaj napis: ");
		Lekcja12.Strnapis = scanner.nextLine();
		scanner.close();
		Lekcja12 napis = new Lekcja12();
		window.add(napis);
		window.setVisible(true);
		window.pack();
		
		napis.animacja();
		

		
		// Napis migaj¹cy w zale¿noœci od wprowadzonego stringu przez u¿ytkownika
		//.png
	}

}
