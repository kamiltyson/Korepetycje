import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class DbConnectorLogowanie{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	
	public static Connection connect(String USER, String PASSWORD) {
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(URL, USER, PASSWORD);
			JOptionPane.showMessageDialog(null, "Logged in correctly");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login or password is incorrect");
		}
		return connection;
	}
}

class DbConnectorRejestracja{
	
	private static String query;
	
	public static void executeQuery(String USER, String PASSWORD){
		
		query = "CREATE USER " + USER +" PASSWORD '" + PASSWORD + "'";
		
		try {
			Connection connection  = DbConnector.connect();
			Statement statement = connection.createStatement();
			statement.execute(query);
			JOptionPane.showMessageDialog(null, "Zarejestrowano poprawnie");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login powtarza siê.");
		}
	}
}

class DBlogowanie extends JFrame implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private boolean zalogowano = false;
	
	public DBlogowanie(){
		
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

	public void actionPerformed(ActionEvent e) {
		
		if ("Loguj".equals(e.getActionCommand())) {
			
			DbConnectorLogowanie.connect(tl.getText(), String.valueOf(ph.getPassword()));
			
		}
		
		if ("Rejestruj".equals(e.getActionCommand())) {
			DBrejestracja frame2 = new DBrejestracja();
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.pack();
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
}

class DBrejestracja extends JFrame implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private JPasswordField ph2;
	private boolean loginpowtarzasie = false;
	
	public DBrejestracja(){
		
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
					
					DbConnectorRejestracja.executeQuery(String.valueOf(tl.getText()), String.valueOf(ph.getPassword()));
					
				}
				else {

					JOptionPane.showMessageDialog(null, "Has³o nie spe³nia wymagañ(minimum 1 du¿a litera, 1 ma³a, 8-16 znaków).");
					
				}
			}
			else {
				
				JOptionPane.showMessageDialog(null, "Has³a ró¿ni¹ siê.");
				
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

public class Lekcja29 {
	
	private DBlogowanie dblogowanie;

	public static void main(String[] args) {
		//Logowanie i rejestracja do bazy danych
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
					try {
						new Lekcja29().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
	}
	
	public void createAndShowGUI() throws Exception {
		dblogowanie = new DBlogowanie();
		dblogowanie.setVisible(true);
		dblogowanie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dblogowanie.pack();
		
	}
}
