import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class Framelogowanie extends JFrame implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private boolean zalogowano = false;
	
	public Framelogowanie(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setBounds(100, 100, 100, 25);
		add(ll);
		
		tl = new JTextField();
		tl.setBounds(100, 125, 200, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setBounds(100, 150, 100, 25);
		add(lh);
		
		ph = new JPasswordField();
		ph.setBounds(100, 175, 200, 25);
		add(ph);
		
		JButton bl = new JButton("Loguj");
		bl.setBounds(100, 200, 100, 50);
		add(bl);
		
		JButton br = new JButton("Rejestruj");
		br.setBounds(200, 200, 100, 50);
		add(br);
		
		bl.setActionCommand("Loguj");
		bl.addActionListener(this);
		
		br.setActionCommand("Rejestruj");
		br.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if ("Loguj".equals(e.getActionCommand())) {
			String str = new String("C:\\Users\\Predator\\Downloads\\bazadanych.txt");
			Scanner scanner;
			try {
				scanner = new Scanner(new File(str));
				while (scanner.hasNextLine() && !zalogowano) {
					String linia = scanner.nextLine();
					if (String.valueOf(tl.getText()).equals(linia.split(" ")[0]) && String.valueOf(ph.getPassword()).equals(linia.split(" ")[1])){
						Framezalogowano frame3 = new Framezalogowano();
						frame3.setVisible(true);
						frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame3.pack();
						zalogowano = true;
					}
				}
				if (!zalogowano) {
					
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			

		}
		
		if ("Rejestruj".equals(e.getActionCommand())) {
			Framerejestracja frame2 = new Framerejestracja();
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.pack();
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
}

class Framezalogowano extends JFrame{
	
	public Framezalogowano(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Zalogowano poprawnie");
		ll.setBounds(100, 100, 150, 25);
		add(ll);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}

class Framerejestracja extends JFrame implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private JPasswordField ph2;
	private boolean loginpowtarzasie = false;
	
	public Framerejestracja(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setBounds(100, 100, 100, 25);
		add(ll);
		
		tl = new JTextField();
		tl.setBounds(100, 125, 200, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setBounds(100, 150, 100, 25);
		add(lh);
		
		ph = new JPasswordField();
		ph.setBounds(100, 175, 200, 25);
		add(ph);
		
		JLabel lh2 = new JLabel("Powtórz has³o");
		lh2.setBounds(100, 200, 100, 25);
		add(lh2);
		
		ph2 = new JPasswordField();
		ph2.setBounds(100, 225, 200, 25);
		add(ph2);
		
		JButton br = new JButton("Rejestruj");
		br.setBounds(100, 250, 100, 50);
		add(br);
		
		br.setActionCommand("Rejestruj");
		br.addActionListener(this);
		
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public void actionPerformed(ActionEvent e) {
		if ("Rejestruj".equals(e.getActionCommand())) {
			if (String.valueOf(ph.getPassword()).equals(String.valueOf(ph2.getPassword()))) {
				
				if (isValidPassword(String.valueOf(ph.getPassword()))) {
					String str = new String("C:\\Users\\Predator\\Downloads\\bazadanych.txt");
					Scanner scanner;
					try {
						String str2 = new String("");
						scanner = new Scanner(new File(str));
						while (scanner.hasNextLine()) {
							String linia = scanner.nextLine();
							str2 += linia.split(" ")[0] + " " + linia.split(" ")[1] + "\n";
							if (linia.split(" ")[0].equals(String.valueOf(tl.getText()))){
								Framehloginpowtarzasie frame5 = new Framehloginpowtarzasie();
								frame5.setVisible(true);
								frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								frame5.pack();
								loginpowtarzasie = true;
							}
						}
						if (!loginpowtarzasie) {
							PrintWriter pw = new PrintWriter(new File(str));
							
							pw.print(str2);
							pw.print(String.valueOf(tl.getText()) + " ");
							pw.print(String.valueOf(ph.getText()));
							pw.close();
							
							Framezarejestrowano frame7 = new Framezarejestrowano();
							frame7.setVisible(true);
							frame7.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame7.pack();
						}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
				else {
					Framehaslaniespelniawalidacji frame6 = new Framehaslaniespelniawalidacji();
					frame6.setVisible(true);
					frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame6.pack();
				}
			}
			else {
				Framehaslaniepasuja frame4 = new Framehaslaniepasuja();
				frame4.setVisible(true);
				frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame4.pack();
			}
			
		}
	}
	
	public boolean isValidPassword(String password)
    {
        String regex = "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=\\S+$).{8,16}$";
  
        Pattern p = Pattern.compile(regex);
  
        if (password == null) {
            return false;
        }
  
        Matcher m = p.matcher(password);
  
        return m.matches();
    }
	
}

class Framezarejestrowano extends JFrame{
	
	public Framezarejestrowano(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Zarejestrowano poprawnie");
		ll.setBounds(100, 100, 200, 25);
		add(ll);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}

class Framehaslaniepasuja extends JFrame{
	
	public Framehaslaniepasuja(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Has³a ró¿ni¹ siê.");
		ll.setBounds(100, 100, 150, 25);
		add(ll);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}

class Framehaslaniespelniawalidacji extends JFrame{
	
	public Framehaslaniespelniawalidacji(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Has³o nie spe³nia wymagañ(minimum 1 du¿a litera, 1 ma³a, 8-16 znaków).");
		ll.setBounds(50, 100, 450, 25);
		add(ll);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}

class Framehloginpowtarzasie extends JFrame{
	
	public Framehloginpowtarzasie(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login powtarza siê.");
		ll.setBounds(100, 100, 150, 25);
		add(ll);
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
}

public class Lekcja15cz3 {
	
	private Framelogowanie frame1;

	public static void main(String[] args) {
		// oddzielne 2 okienka rejestracja, logowanie
		// Walidacja z³o¿onoœci has³a, 1 du¿a, 1 ma³a, 8 znaków, maks 16, sprawdziæ czy login siê powtarza
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Lekcja15cz3().createAndShowGui();
			}
		});
	}

	public void createAndShowGui() {
		frame1 = new Framelogowanie();
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.pack();
	}
}
