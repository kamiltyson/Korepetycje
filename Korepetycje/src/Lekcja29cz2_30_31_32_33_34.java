import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

class DbConnectorLogowanie2{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	private static Image Wrench;
	
	public static void plikiGraficzne() {
		try {
			Wrench = ImageIO.read(new File("wrench.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connect(String USER, String PASSWORD) {
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(URL, USER, PASSWORD);
			ResultSet result = new SerwisQueryExecutor(USER, PASSWORD).executeSelect("SELECT * From public.users WHERE Uzytkownik = '" + USER + "'");
			if (result.next()==true) {
				if (result.getBoolean("Banned")==true) {
					JOptionPane.showMessageDialog(null, "U¿ytkownik jest zbanowany. Proszê skontaktuj siê ze swoim administratorem.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Zalogowano poprawnie.");
					plikiGraficzne();
					JFrame frame = new JFrame();
					DbSerwisGUI dbSerwisGui = new DbSerwisGUI();
					dbSerwisGui.SetLogin(USER);
					dbSerwisGui.SetPassword(PASSWORD);
					frame.setIconImage(Wrench);
					frame.add(dbSerwisGui);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Zalogowano poprawnie.");
				plikiGraficzne();
				JFrame frame = new JFrame();
				DbSerwisGUI dbSerwisGui = new DbSerwisGUI();
				dbSerwisGui.SetLogin(USER);
				dbSerwisGui.SetPassword(PASSWORD);
				frame.setIconImage(Wrench);
				frame.add(dbSerwisGui);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login lub has³o jest niepoprawne.");
		}
		return connection;
	}
}

class DbConnectorRejestracja2{
	
	private static String query;
	
	public static void executeQuery(String USER, String PASSWORD){
		
		query = "CREATE USER " + USER + " PASSWORD '" + PASSWORD + "'";
		
		try {
			Connection connection  = DbConnector.connect();
			Statement statement = connection.createStatement();
			statement.execute(query);
			query = "ALTER USER " + USER + " WITH SUPERUSER";
			statement.execute(query);
			new SerwisQueryExecutor(USER, PASSWORD).executeQuery("INSERT INTO public.users (Uzytkownik, Banned) VALUES ('" + USER + "', 'false')");
			JOptionPane.showMessageDialog(null, "Zarejestrowano poprawnie");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login powtarza siê.");
		}
	}
}

class SerwisDbConnector{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	private static String USER;
	private static String PASSWORD;
	
	public SerwisDbConnector(String USER, String PASSWORD) {
		this.USER = USER;
		this.PASSWORD = PASSWORD;
	}
	
	public static Connection connect() {
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Po³¹czono");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}


class SerwisQueryExecutor{
	
	private static String USER;
	private static String PASSWORD;
	
	public SerwisQueryExecutor(String USER, String PASSWORD) {
		this.USER = USER;
		this.PASSWORD = PASSWORD;
	}
	
	public static ResultSet executeSelect(String selectQuery){

		try {
			Connection connection  = new SerwisDbConnector(USER, PASSWORD).connect();
			Statement statement = connection.createStatement();
			return statement.executeQuery(selectQuery);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public static void executeQuery(String query){
		
		try {
			Connection connection  = new SerwisDbConnector(USER, PASSWORD).connect();
			Statement statement = connection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

class DBlogowanie2 extends JPanel implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private boolean zalogowano = false;
	private Graphics2D g2;
	private Image Car_service;
	private Image Wrench;
	
	public DBlogowanie2(){
		
		plikiGraficzne();
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setFont(new Font("Tahoma", Font.BOLD, 18));
		ll.setBounds(130, 175, 140, 25);
		ll.setForeground(Color.white);
		add(ll);
		
		tl = new JTextField();
		tl.setFont(new Font("Tahoma", Font.BOLD, 18));
		tl.setBounds(130, 200, 240, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh.setBounds(130, 228, 140, 25);
		lh.setForeground(Color.white);
		add(lh);
		
		ph = new JPasswordField();
		ph.setFont(new Font("Tahoma", Font.BOLD, 18));
		ph.setBounds(130, 250, 240, 25);
		add(ph);
		
		JButton bl = new JButton("Loguj");
		bl.setFont(new Font("Tahoma", Font.BOLD, 18));
		bl.setBounds(130, 275, 120, 50);
		bl.setOpaque(false);
		bl.setContentAreaFilled(false);
		bl.setForeground(Color.white);
		add(bl);
		
		JButton br = new JButton("Rejestruj");
		br.setFont(new Font("Tahoma", Font.BOLD, 18));
		br.setBounds(250, 275, 120, 50);
		br.setOpaque(false);
		br.setContentAreaFilled(false);
		br.setForeground(Color.white);
		add(br);
		
		bl.setActionCommand("Loguj");
		bl.addActionListener(this);
		
		br.setActionCommand("Rejestruj");
		br.addActionListener(this);
		
	}
	
	public void plikiGraficzne() {
		try {
			Car_service = ImageIO.read(new File("car_service.jpg"));
			Wrench = ImageIO.read(new File("wrench.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Car_service, 0, 0, 500, 500, null);
	}

	public void actionPerformed(ActionEvent e) {
		
		if ("Loguj".equals(e.getActionCommand())) {
			
			DbConnectorLogowanie2.connect(tl.getText(), String.valueOf(ph.getPassword()));
			
		}
		
		if ("Rejestruj".equals(e.getActionCommand())) {
			JFrame frame2 = new JFrame();
			frame2.setIconImage(Wrench);
			frame2.add(new DBrejestracja2());
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.pack();
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
}

class DBrejestracja2 extends JPanel implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private JPasswordField ph2;
	private boolean loginpowtarzasie = false;
	private Graphics2D g2;
	private Image Car_service;
	
	public DBrejestracja2(){
		
		plikiGraficzne();
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setFont(new Font("Tahoma", Font.BOLD, 18));
		ll.setForeground(Color.white);
		ll.setBounds(130, 150, 230, 25);
		add(ll);
		
		tl = new JTextField();
		tl.setFont(new Font("Tahoma", Font.BOLD, 18));
		tl.setBounds(130, 175, 230, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh.setForeground(Color.white);
		lh.setBounds(130, 200, 230, 25);
		add(lh);
		
		ph = new JPasswordField();
		ph.setFont(new Font("Tahoma", Font.BOLD, 18));
		ph.setBounds(130, 225, 230, 25);
		add(ph);
		
		JLabel lh2 = new JLabel("Powtórz has³o");
		lh2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh2.setForeground(Color.white);
		lh2.setBounds(130, 250, 230, 25);
		add(lh2);
		
		ph2 = new JPasswordField();
		ph2.setFont(new Font("Tahoma", Font.BOLD, 18));
		ph2.setBounds(130, 275, 230, 25);
		add(ph2);
		
		JButton br = new JButton("Rejestruj");
		br.setFont(new Font("Tahoma", Font.BOLD, 18));
		br.setContentAreaFilled(false);
		br.setForeground(Color.white);
		br.setBounds(130, 300, 230, 50);
		add(br);
		
		br.setActionCommand("Rejestruj");
		br.addActionListener(this);
		
	}
	
	public void plikiGraficzne() {
		try {
			Car_service = ImageIO.read(new File("car_service.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Car_service, 0, 0, 500, 500, null);
	}
	

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	public void actionPerformed(ActionEvent e) {
		if ("Rejestruj".equals(e.getActionCommand())) {
			if (String.valueOf(ph.getPassword()).equals(String.valueOf(ph2.getPassword()))) {
				
				if (isValidPassword(String.valueOf(ph.getPassword()))) {
					
					DbConnectorRejestracja2.executeQuery(String.valueOf(tl.getText()), String.valueOf(ph.getPassword()));
					
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

class DbSerwisGUI extends JPanel{

	private JButton btnSamochody;
	private JButton btnStanowiska;
	private JButton btnPracownicy;
	private JButton btnZlecenia;
	private JButton btnPanelAdmina;
	private Graphics2D g2;
	private Image Wrench, Car_service, Cars, Workshop_stations, Employees, Orders, AdminPanelPicture;
	private JFrame nextframe;
	private DbSamochody dbsamochody;
	private DbStanowiska dbstanowiska;
	private DbPracownicy dbpracownicy;
	private DbZlecenia dbzlecenia;
	private DbPanelAdmina dbpaneladmina;
	private String login;
	private String password;
	
	
	public void SetLogin(String login) {
		this.login = login;
	}
	
	public void SetPassword(String password) {
		this.password = password;
	}
	
	public String GetLogin() {
		return login;
	}
	
	public String GetPassword() {
		return password;
	}
	
	public DbSerwisGUI() {
		plikiGraficzne();
		initialize();

	}
	
	public void plikiGraficzne() {
		try {
			Wrench = ImageIO.read(new File("wrench.png"));
			Car_service = ImageIO.read(new File("car_service.jpg"));
			Cars = ImageIO.read(new File("cars.jpg"));
			Workshop_stations = ImageIO.read(new File("workshop.jpg"));
			Employees = ImageIO.read(new File("employees.jpg"));
			Orders = ImageIO.read(new File("orders.jpg"));
			AdminPanelPicture = ImageIO.read(new File("adminpanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Car_service, 0, 0, 520, 800, null);
	}
	
	private void initialize() {
		
		setLayout(null);
		
		ButtonSamochody();
		
		ButtonStanowiska();
	
		ButtonPracownicy();
		
		ButtonZlecenia();
		
		ButtonPanelAdmina();
		
	}
	
	private void ButtonSamochody() {
		btnSamochody = new JButton("Samochody", new ImageIcon(new ImageIcon(Cars).getImage().getScaledInstance(320, 100, Image.SCALE_DEFAULT)));
		btnSamochody.setForeground(Color.white);
		btnSamochody.setHorizontalTextPosition(JButton.CENTER);
		btnSamochody.setVerticalTextPosition(JButton.CENTER);
		btnSamochody.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnSamochody.setBackground(Color.white);
		btnSamochody.setBorder(BorderFactory.createLineBorder(Color.black));
		btnSamochody.setBounds(100, 50, 320, 100);
		add(btnSamochody);
		btnSamochody.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbsamochody = new DbSamochody(login, password);
				nextframe.add(dbsamochody);
				nextframe.setVisible(true);
				nextframe.pack();
				
			}
		});
	}
	
	private void ButtonStanowiska() {
		btnStanowiska = new JButton("Stanowiska", new ImageIcon(new ImageIcon(Workshop_stations).getImage().getScaledInstance(320, 100, Image.SCALE_DEFAULT)));
		btnStanowiska.setForeground(Color.white);
		btnStanowiska.setHorizontalTextPosition(JButton.CENTER);
		btnStanowiska.setVerticalTextPosition(JButton.CENTER);
		btnStanowiska.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnStanowiska.setBorder(BorderFactory.createLineBorder(Color.black));
		btnStanowiska.setBounds(100, 200, 320, 100);
		add(btnStanowiska);
		btnStanowiska.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   DbStanowiska.SetExistFalse();
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbstanowiska = new DbStanowiska(login, password);
				nextframe.add(dbstanowiska);
				nextframe.setVisible(true);
				nextframe.pack();
				
			}
		});
	}
	
	private void ButtonPracownicy() {
		btnPracownicy = new JButton("Pracownicy", new ImageIcon(new ImageIcon(Employees).getImage().getScaledInstance(320, 100, Image.SCALE_DEFAULT)));
		btnPracownicy.setForeground(Color.white);
		btnPracownicy.setHorizontalTextPosition(JButton.CENTER);
		btnPracownicy.setVerticalTextPosition(JButton.CENTER);
		btnPracownicy.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnPracownicy.setBorder(BorderFactory.createLineBorder(Color.black));
		btnPracownicy.setBounds(100, 350, 320, 100);
		add(btnPracownicy);
		btnPracownicy.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   DbPracownicy.SetExistFalse();
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbpracownicy = new DbPracownicy(login, password);
				nextframe.add(dbpracownicy);
				nextframe.setVisible(true);
				nextframe.pack();
				
			}
		});
	}
	
	private void ButtonZlecenia() {
		btnZlecenia = new JButton("Zlecenia", new ImageIcon(new ImageIcon(Orders).getImage().getScaledInstance(320, 100, Image.SCALE_DEFAULT)));
		btnZlecenia.setForeground(Color.white);
		btnZlecenia.setHorizontalTextPosition(JButton.CENTER);
		btnZlecenia.setVerticalTextPosition(JButton.CENTER);
		btnZlecenia.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnZlecenia.setBackground(Color.white);
		btnZlecenia.setBorder(BorderFactory.createLineBorder(Color.black));
		btnZlecenia.setBounds(100, 500, 320, 100);
		add(btnZlecenia);
		btnZlecenia.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbzlecenia = new DbZlecenia(login, password);
				nextframe.add(dbzlecenia);
				nextframe.setVisible(true);
				nextframe.pack();
				
			}
		});
	}
	
	private void ButtonPanelAdmina() {
		btnPanelAdmina = new JButton("Panel Admina", new ImageIcon(new ImageIcon(AdminPanelPicture).getImage().getScaledInstance(320, 100, Image.SCALE_DEFAULT)));
		btnPanelAdmina.setForeground(Color.white);
		btnPanelAdmina.setHorizontalTextPosition(JButton.CENTER);
		btnPanelAdmina.setVerticalTextPosition(JButton.CENTER);
		btnPanelAdmina.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnPanelAdmina.setBackground(Color.white);
		btnPanelAdmina.setBorder(BorderFactory.createLineBorder(Color.black));
		btnPanelAdmina.setBounds(100, 650, 320, 100);
		add(btnPanelAdmina);
		btnPanelAdmina.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbpaneladmina = new DbPanelAdmina(login, password);
				nextframe.add(dbpaneladmina);
				nextframe.setVisible(true);
				nextframe.pack();
				
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(520,800);
	}
	
}



class DbSamochody extends JPanel{
	
	private static String ID;
	private static String Nazwa;
	private static Boolean WymianaOleju;
	private static Boolean WymianaFiltru;
	private static Boolean WymianaWycieraczek;
	private static Boolean KontrolaHamulcow;
	private static Boolean KontrolaZawieszenia;
	private static Boolean KontrolaOpon;
	private static Boolean KontrolaPlynow;
	private static Boolean KontrolaWyciekow;
	private static Boolean KontrolaSwiec;
	private static Boolean KontrolaPaskow;
	private static Boolean KonserwacjaZaciskowHamulocowych;
	private static Boolean KontrolaZamkow;
	private static Boolean KontrolaZawiasow;
	private static Boolean RegulacjaHamulcaPostojowego;
	private static Boolean KontrolaLuzowZaworowych;
	private JLabel lblSamochody;
	private JLabel lblID;
	private JLabel lblNazwa;
	private JLabel lblWymianaOleju;
	private JLabel lblWymianaFiltru;
	private JLabel lblWymianaWycieraczek;
	private JLabel lblKontrolaHamulcow;
	private JLabel lblKontrolaZawieszenia;
	private JLabel lblKontrolaOpon;
	private JLabel lblKontrolaPlynow;
	private JLabel lblKontrolaWyciekow;
	private JLabel lblKontrolaSwiec;
	private JLabel lblKontrolaPaskow;
	private JLabel lblKonserwacjaZaciskowHamulocowych;
	private JLabel lblKontrolaZamkow;
	private JLabel lblKontrolaZawiasow;
	private JLabel lblRegulacjaHamulcaPostojowego;
	private JLabel lblKontrolaLuzowZaworowych;
	private JTextField jtxtID;
	private JTextField jtxtNazwa;
	private JTextField jtxtWymianaOleju;
	private JTextField jtxtWymianaFiltru;
	private JTextField jtxtWymianaWycieraczek;
	private JTextField jtxtKontrolaHamulcow;
	private JTextField jtxtKontrolaZawieszenia;
	private JTextField jtxtKontrolaOpon;
	private JTextField jtxtKontrolaPlynow;
	private JTextField jtxtKontrolaWyciekow;
	private JTextField jtxtKontrolaSwiec;
	private JTextField jtxtKontrolaPaskow;
	private JTextField jtxtKonserwacjaZaciskowHamulocowych;
	private JTextField jtxtKontrolaZamkow;
	private JTextField jtxtKontrolaZawiasow;
	private JTextField jtxtRegulacjaHamulcaPostojowego;
	private JTextField jtxtKontrolaLuzowZaworowych;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JScrollPane scrollChoice;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private JPanel panelChoice;
	private Graphics2D g2;
	private Image Cars;
	private static String login;
	private static String password;
	
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.samochody");
			Object[] columnData = new Object[17];
			while(result.next()){
				ID = result.getString("ID");
				Nazwa = result.getString("Nazwa");
				WymianaOleju = result.getBoolean("WymianaOleju");
				WymianaFiltru = result.getBoolean("WymianaFiltru");
				WymianaWycieraczek = result.getBoolean("WymianaWycieraczek");
				KontrolaHamulcow = result.getBoolean("KontrolaHamulcow");
				KontrolaZawieszenia = result.getBoolean("KontrolaZawieszenia");
				KontrolaOpon = result.getBoolean("KontrolaOpon");
				KontrolaPlynow = result.getBoolean("KontrolaPlynow");
				KontrolaWyciekow = result.getBoolean("KontrolaWyciekow");
				KontrolaSwiec = result.getBoolean("KontrolaSwiec");
				KontrolaPaskow = result.getBoolean("KontrolaPaskow");
				KonserwacjaZaciskowHamulocowych = result.getBoolean("KonserwacjaZaciskowHamulocowych");
				KontrolaZamkow = result.getBoolean("KontrolaZamkow");
				KontrolaZawiasow = result.getBoolean("KontrolaZawiasow");
				RegulacjaHamulcaPostojowego = result.getBoolean("RegulacjaHamulcaPostojowego");
				KontrolaLuzowZaworowych = result.getBoolean("KontrolaLuzowZaworowych");
				columnData[0] = ID;
				columnData[1] = Nazwa;
				columnData[2] = WymianaOleju;
				columnData[3] = WymianaFiltru;
				columnData[4] = WymianaWycieraczek;
				columnData[5] = KontrolaHamulcow;
				columnData[6] = KontrolaZawieszenia;
				columnData[7] = KontrolaOpon;
				columnData[8] = KontrolaPlynow;
				columnData[9] = KontrolaWyciekow;
				columnData[10] = KontrolaSwiec;
				columnData[11] = KontrolaPaskow;
				columnData[12] = KonserwacjaZaciskowHamulocowych;
				columnData[13] = KontrolaZamkow;
				columnData[14] = KontrolaZawiasow;
				columnData[15] = RegulacjaHamulcaPostojowego;
				columnData[16] = KontrolaLuzowZaworowych;
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DbSamochody(String login, String password) {
		this.login = login;
		this.password = password;
		plikiGraficzne();
		initialize();
		Object col[] = {"ID", "Nazwa", "WymianaOleju", "WymianaFiltru", "WymianaWycieraczek", "KontrolaHamulcow", "KontrolaZawieszenia", "KontrolaOpon", "KontrolaPlynow", "KontrolaWyciekow", "KontrolaSwiec", "KontrolaPaskow", "KonserwacjaZaciskowHamulocowych", "KontrolaZamkow", "KontrolaZawiasow", "RegulacjaHamulcaPostojowego", "KontrolaLuzowZaworowych"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	public void plikiGraficzne() {
		try {
			Cars = ImageIO.read(new File("cars.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Cars, 0, 0, 1500, 800, null);
		g2.setColor(Color.black);
		g2.fillRect(678, 36, 164, 40);
	}
	
	private void initialize() {
		
		setLayout(null);
		
		TopLeftCorner();
		
		ChoiceList();
		
		Buttons();
		
		Table();
		
	}
	
	private void TopLeftCorner() {
		lblSamochody = new JLabel("Samochody");
		lblSamochody.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblSamochody.setHorizontalAlignment(SwingConstants.CENTER);
		lblSamochody.setBounds(91, 36, 1337, 40);
		lblSamochody.setForeground(Color.white);
		add(lblSamochody);
		
		lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblID.setBounds(53, 142, 349, 22);
		lblID.setForeground(Color.white);
		add(lblID);
		
		jtxtID = new JTextField();
		jtxtID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtID.setBounds(420, 139, 117, 40);
		add(jtxtID);
		jtxtID.setColumns(10);
		jtxtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!jtxtID.getText().isBlank()) {
					try {
						
						String query = "SELECT * From public.samochody WHERE ID = " + jtxtID.getText();					
						ResultSet result = new SerwisQueryExecutor(login, password).executeSelect(query);
						
			            if(result.next()==true){
			            	
			            	jtxtNazwa.setText(result.getString("Nazwa"));
			            	jtxtWymianaOleju.setText(Boolean.toString(result.getBoolean("WymianaOleju")));
			            	jtxtWymianaFiltru.setText(Boolean.toString(result.getBoolean("WymianaFiltru")));
			            	jtxtWymianaWycieraczek.setText(Boolean.toString(result.getBoolean("WymianaWycieraczek")));
			            	jtxtKontrolaHamulcow.setText(Boolean.toString(result.getBoolean("KontrolaHamulcow")));
			            	jtxtKontrolaZawieszenia.setText(Boolean.toString(result.getBoolean("KontrolaZawieszenia")));
			            	jtxtKontrolaOpon.setText(Boolean.toString(result.getBoolean("KontrolaOpon")));
			            	jtxtKontrolaPlynow.setText(Boolean.toString(result.getBoolean("KontrolaPlynow")));
			            	jtxtKontrolaWyciekow.setText(Boolean.toString(result.getBoolean("KontrolaWyciekow")));
			            	jtxtKontrolaSwiec.setText(Boolean.toString(result.getBoolean("KontrolaSwiec")));
			            	jtxtKontrolaPaskow.setText(Boolean.toString(result.getBoolean("KontrolaPaskow")));
			            	jtxtKonserwacjaZaciskowHamulocowych.setText(Boolean.toString(result.getBoolean("KonserwacjaZaciskowHamulocowych")));
			            	jtxtKontrolaZamkow.setText(Boolean.toString(result.getBoolean("KontrolaZamkow")));
			            	jtxtKontrolaZawiasow.setText(Boolean.toString(result.getBoolean("KontrolaZawiasow")));
			            	jtxtRegulacjaHamulcaPostojowego.setText(Boolean.toString(result.getBoolean("RegulacjaHamulcaPostojowego")));
			            	jtxtKontrolaLuzowZaworowych.setText(Boolean.toString(result.getBoolean("KontrolaLuzowZaworowych")));
			                
			            }  
			            else{
			            	
			            	jtxtNazwa.setText("");
			            	jtxtWymianaOleju.setText("false");
			            	jtxtWymianaFiltru.setText("false");
			            	jtxtWymianaWycieraczek.setText("false");
			            	jtxtKontrolaHamulcow.setText("false");
			            	jtxtKontrolaZawieszenia.setText("false");
			            	jtxtKontrolaOpon.setText("false");
			            	jtxtKontrolaPlynow.setText("false");
			            	jtxtKontrolaWyciekow.setText("false");
			            	jtxtKontrolaSwiec.setText("false");
			            	jtxtKontrolaPaskow.setText("false");
			            	jtxtKonserwacjaZaciskowHamulocowych.setText("false");
			            	jtxtKontrolaZamkow.setText("false");
			            	jtxtKontrolaZawiasow.setText("false");
			            	jtxtRegulacjaHamulcaPostojowego.setText("false");
			            	jtxtKontrolaLuzowZaworowych.setText("false");
			            	
			            }
			            
			        }
					catch (SQLException e1) {
					}
				}
				else{
					
					jtxtNazwa.setText("");
	            	jtxtWymianaOleju.setText("false");
	            	jtxtWymianaFiltru.setText("false");
	            	jtxtWymianaWycieraczek.setText("false");
	            	jtxtKontrolaHamulcow.setText("false");
	            	jtxtKontrolaZawieszenia.setText("false");
	            	jtxtKontrolaOpon.setText("false");
	            	jtxtKontrolaPlynow.setText("false");
	            	jtxtKontrolaWyciekow.setText("false");
	            	jtxtKontrolaSwiec.setText("false");
	            	jtxtKontrolaPaskow.setText("");
	            	jtxtKonserwacjaZaciskowHamulocowych.setText("false");
	            	jtxtKontrolaZamkow.setText("false");
	            	jtxtKontrolaZawiasow.setText("false");
	            	jtxtRegulacjaHamulcaPostojowego.setText("false");
	            	jtxtKontrolaLuzowZaworowych.setText("false");
		        }
			}
		});
		
		lblNazwa = new JLabel("Nazwa");
		lblNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNazwa.setBounds(53, 232, 349, 22);
		lblNazwa.setForeground(Color.white);
		add(lblNazwa);
		
		jtxtNazwa = new JTextField();
		jtxtNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNazwa.setBounds(420, 229, 117, 40);
		add(jtxtNazwa);
		jtxtNazwa.setColumns(10);
	}
	
	private void ChoiceList(){
		scrollChoice = new JScrollPane();
		scrollChoice.setBounds(53, 322, 501, 333);
		
		panelChoice = new JPanel();
		panelChoice.setLayout(null);
		panelChoice.setPreferredSize(new Dimension(484,1300));
		panelChoice.setOpaque(false);
		
		lblWymianaOleju = new JLabel("WymianaOleju");
		lblWymianaOleju.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWymianaOleju.setBounds(0, 3, 349, 22);
		lblWymianaOleju.setForeground(Color.white);
		panelChoice.add(lblWymianaOleju);
		
		jtxtWymianaOleju = new JTextField();
		jtxtWymianaOleju.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtWymianaOleju.setBounds(367, 0, 117, 40);
		panelChoice.add(jtxtWymianaOleju);
		jtxtWymianaOleju.setColumns(10);
		
		lblWymianaFiltru = new JLabel("WymianaFiltru");
		lblWymianaFiltru.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWymianaFiltru.setBounds(0, 93, 349, 22);
		lblWymianaFiltru.setForeground(Color.white);
		panelChoice.add(lblWymianaFiltru);
		
		jtxtWymianaFiltru = new JTextField();
		jtxtWymianaFiltru.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtWymianaFiltru.setBounds(367, 90, 117, 40);
		panelChoice.add(jtxtWymianaFiltru);
		jtxtWymianaFiltru.setColumns(10);
		
		lblWymianaWycieraczek = new JLabel("WymianaWycieraczek");
		lblWymianaWycieraczek.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWymianaWycieraczek.setBounds(0, 183, 349, 22);
		lblWymianaWycieraczek.setForeground(Color.white);
		panelChoice.add(lblWymianaWycieraczek);
		
		jtxtWymianaWycieraczek = new JTextField();
		jtxtWymianaWycieraczek.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtWymianaWycieraczek.setBounds(367, 180, 117, 40);
		panelChoice.add(jtxtWymianaWycieraczek);
		jtxtWymianaWycieraczek.setColumns(10);
		
		lblKontrolaHamulcow = new JLabel("KontrolaHamulcow");
		lblKontrolaHamulcow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaHamulcow.setBounds(0, 273, 349, 22);
		lblKontrolaHamulcow.setForeground(Color.white);
		panelChoice.add(lblKontrolaHamulcow);
		
		jtxtKontrolaHamulcow = new JTextField();
		jtxtKontrolaHamulcow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaHamulcow.setBounds(367, 270, 117, 40);
		panelChoice.add(jtxtKontrolaHamulcow);
		jtxtKontrolaHamulcow.setColumns(10);
		
		lblKontrolaZawieszenia = new JLabel("KontrolaZawieszenia");
		lblKontrolaZawieszenia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaZawieszenia.setBounds(0, 363, 349, 22);
		lblKontrolaZawieszenia.setForeground(Color.white);
		panelChoice.add(lblKontrolaZawieszenia);
		
		jtxtKontrolaZawieszenia = new JTextField();
		jtxtKontrolaZawieszenia.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaZawieszenia.setBounds(367, 360, 117, 40);
		panelChoice.add(jtxtKontrolaZawieszenia);
		jtxtKontrolaZawieszenia.setColumns(10);
		
		lblKontrolaOpon = new JLabel("KontrolaOpon");
		lblKontrolaOpon.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaOpon.setBounds(0, 453, 349, 22);
		lblKontrolaOpon.setForeground(Color.white);
		panelChoice.add(lblKontrolaOpon);
		
		jtxtKontrolaOpon = new JTextField();
		jtxtKontrolaOpon.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaOpon.setBounds(367, 450, 117, 40);
		panelChoice.add(jtxtKontrolaOpon);
		jtxtKontrolaOpon.setColumns(10);
		
		lblKontrolaPlynow = new JLabel("KontrolaPlynow");
		lblKontrolaPlynow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaPlynow.setBounds(0, 543, 349, 22);
		lblKontrolaPlynow.setForeground(Color.white);
		panelChoice.add(lblKontrolaPlynow);
		
		jtxtKontrolaPlynow = new JTextField();
		jtxtKontrolaPlynow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaPlynow.setBounds(367, 540, 117, 40);
		panelChoice.add(jtxtKontrolaPlynow);
		jtxtKontrolaPlynow.setColumns(10);
		
		lblKontrolaWyciekow = new JLabel("KontrolaWyciekow");
		lblKontrolaWyciekow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaWyciekow.setBounds(0, 633, 349, 22);
		lblKontrolaWyciekow.setForeground(Color.white);
		panelChoice.add(lblKontrolaWyciekow);
		
		jtxtKontrolaWyciekow = new JTextField();
		jtxtKontrolaWyciekow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaWyciekow.setBounds(367, 630, 117, 40);
		panelChoice.add(jtxtKontrolaWyciekow);
		jtxtKontrolaWyciekow.setColumns(10);
		
		lblKontrolaSwiec = new JLabel("KontrolaSwiec");
		lblKontrolaSwiec.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaSwiec.setBounds(0, 723, 349, 22);
		lblKontrolaSwiec.setForeground(Color.white);
		panelChoice.add(lblKontrolaSwiec);
		
		jtxtKontrolaSwiec = new JTextField();
		jtxtKontrolaSwiec.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaSwiec.setBounds(367, 720, 117, 40);
		panelChoice.add(jtxtKontrolaSwiec);
		jtxtKontrolaSwiec.setColumns(10);
		
		lblKontrolaPaskow = new JLabel("KontrolaPaskow");
		lblKontrolaPaskow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaPaskow.setBounds(0, 813, 349, 22);
		lblKontrolaPaskow.setForeground(Color.white);
		panelChoice.add(lblKontrolaPaskow);
		
		jtxtKontrolaPaskow = new JTextField();
		jtxtKontrolaPaskow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaPaskow.setBounds(367, 810, 117, 40);
		panelChoice.add(jtxtKontrolaPaskow);
		jtxtKontrolaPaskow.setColumns(10);
		
		lblKonserwacjaZaciskowHamulocowych = new JLabel("KonserwacjaZaciskowHamulocowych");
		lblKonserwacjaZaciskowHamulocowych.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKonserwacjaZaciskowHamulocowych.setBounds(0, 903, 349, 22);
		lblKonserwacjaZaciskowHamulocowych.setForeground(Color.white);
		panelChoice.add(lblKonserwacjaZaciskowHamulocowych);
		
		jtxtKonserwacjaZaciskowHamulocowych = new JTextField();
		jtxtKonserwacjaZaciskowHamulocowych.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKonserwacjaZaciskowHamulocowych.setBounds(367, 900, 117, 40);
		panelChoice.add(jtxtKonserwacjaZaciskowHamulocowych);
		jtxtKonserwacjaZaciskowHamulocowych.setColumns(10);
		
		lblKontrolaZamkow = new JLabel("KontrolaZamkow");
		lblKontrolaZamkow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaZamkow.setBounds(0, 993, 349, 22);
		lblKontrolaZamkow.setForeground(Color.white);
		panelChoice.add(lblKontrolaZamkow);
		
		jtxtKontrolaZamkow = new JTextField();
		jtxtKontrolaZamkow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaZamkow.setBounds(367, 990, 117, 40);
		panelChoice.add(jtxtKontrolaZamkow);
		jtxtKontrolaZamkow.setColumns(10);
		
		lblKontrolaZawiasow = new JLabel("KontrolaZawiasow");
		lblKontrolaZawiasow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaZawiasow.setBounds(0, 1083, 349, 22);
		lblKontrolaZawiasow.setForeground(Color.white);
		panelChoice.add(lblKontrolaZawiasow);
		
		jtxtKontrolaZawiasow = new JTextField();
		jtxtKontrolaZawiasow.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaZawiasow.setBounds(367, 1080, 117, 40);
		panelChoice.add(jtxtKontrolaZawiasow);
		jtxtKontrolaZawiasow.setColumns(10);
		
		lblRegulacjaHamulcaPostojowego = new JLabel("RegulacjaHamulcaPostojowego");
		lblRegulacjaHamulcaPostojowego.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegulacjaHamulcaPostojowego.setBounds(0, 1173, 349, 22);
		lblRegulacjaHamulcaPostojowego.setForeground(Color.white);
		panelChoice.add(lblRegulacjaHamulcaPostojowego);
		
		jtxtRegulacjaHamulcaPostojowego = new JTextField();
		jtxtRegulacjaHamulcaPostojowego.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtRegulacjaHamulcaPostojowego.setBounds(367, 1170, 117, 40);
		panelChoice.add(jtxtRegulacjaHamulcaPostojowego);
		jtxtRegulacjaHamulcaPostojowego.setColumns(10);
		
		lblKontrolaLuzowZaworowych = new JLabel("KontrolaLuzowZaworowych");
		lblKontrolaLuzowZaworowych.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblKontrolaLuzowZaworowych.setBounds(0, 1263, 349, 22);
		lblKontrolaLuzowZaworowych.setForeground(Color.white);
		panelChoice.add(lblKontrolaLuzowZaworowych);
		
		jtxtKontrolaLuzowZaworowych = new JTextField();
		jtxtKontrolaLuzowZaworowych.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtKontrolaLuzowZaworowych.setBounds(367, 1260, 117, 40);
		panelChoice.add(jtxtKontrolaLuzowZaworowych);
		jtxtKontrolaLuzowZaworowych.setColumns(10);
		
		add(scrollChoice);
		scrollChoice.getViewport().add(panelChoice);
		scrollChoice.setOpaque(false);
		scrollChoice.getViewport().setOpaque(false);
	}
	
	private void Buttons() {
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(53, 655, 130, 57);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.white);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "INSERT INTO public.samochody (ID, Nazwa, WymianaOleju, WymianaFiltru, WymianaWycieraczek, KontrolaHamulcow, KontrolaZawieszenia,  KontrolaOpon, KontrolaPlynow, KontrolaWyciekow, KontrolaSwiec, KontrolaPaskow, KonserwacjaZaciskowHamulocowych, KontrolaZamkow, KontrolaZawiasow, RegulacjaHamulcaPostojowego, KontrolaLuzowZaworowych) "
							+ "VALUES ('" + jtxtID.getText() + "', '" + 
							jtxtNazwa.getText() + "', '" + 
							jtxtWymianaOleju.getText() + "', '" + 
							jtxtWymianaFiltru.getText() + "', '" + 
							jtxtWymianaWycieraczek.getText() + "', '" + 
							jtxtKontrolaHamulcow.getText() + "', '" + 
							jtxtKontrolaZawieszenia.getText() + "', '" + 
							jtxtKontrolaOpon.getText() + "', '" + 
							jtxtKontrolaPlynow.getText() + "', '" + 
							jtxtKontrolaWyciekow.getText() + "', '" + 
							jtxtKontrolaSwiec.getText() + "', '" + 
							jtxtKontrolaPaskow.getText() + "', '" + 
							jtxtKonserwacjaZaciskowHamulocowych.getText() + "', '" + 
							jtxtKontrolaZamkow.getText() + "', '" + 
							jtxtKontrolaZawiasow.getText() + "', '" + 
							jtxtRegulacjaHamulcaPostojowego.getText() + "', '" + 
							jtxtKontrolaLuzowZaworowych.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "INSERT INTO public.zlecenia (IDSamochodu, Nazwa) "
							+ "VALUES ('" + jtxtID.getText() + "', '" + 
							jtxtNazwa.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
	            	jtxtWymianaOleju.setText("false");
	            	jtxtWymianaFiltru.setText("false");
	            	jtxtWymianaWycieraczek.setText("false");
	            	jtxtKontrolaHamulcow.setText("false");
	            	jtxtKontrolaZawieszenia.setText("false");
	            	jtxtKontrolaOpon.setText("false");
	            	jtxtKontrolaPlynow.setText("false");
	            	jtxtKontrolaWyciekow.setText("false");
	            	jtxtKontrolaSwiec.setText("false");
	            	jtxtKontrolaPaskow.setText("false");
	            	jtxtKonserwacjaZaciskowHamulocowych.setText("false");
	            	jtxtKontrolaZamkow.setText("false");
	            	jtxtKontrolaZawiasow.setText("false");
	            	jtxtRegulacjaHamulcaPostojowego.setText("false");
	            	jtxtKontrolaLuzowZaworowych.setText("false");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(238, 655, 130, 57);
		btnUpdate.setOpaque(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setForeground(Color.white);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "UPDATE public.samochody"
							+ " SET"
							+ " Nazwa = "  + "'" + jtxtNazwa.getText() + "',"
							+ " WymianaOleju = "  + "'" + jtxtWymianaOleju.getText() + "',"
							+ " WymianaFiltru = "  + "'" + jtxtWymianaFiltru.getText() + "',"
							+ " WymianaWycieraczek = "  + "'" + jtxtWymianaWycieraczek.getText() + "',"
							+ " KontrolaHamulcow = "  + "'" + jtxtKontrolaHamulcow.getText() + "',"
							+ " KontrolaZawieszenia = "  + "'" + jtxtKontrolaZawieszenia.getText() + "',"
							+ " KontrolaOpon = "  + "'" + jtxtKontrolaOpon.getText() + "',"
							+ " KontrolaPlynow = "  + "'" + jtxtKontrolaPlynow.getText() + "',"
							+ " KontrolaWyciekow = "  + "'" + jtxtKontrolaWyciekow.getText() + "',"
							+ " KontrolaSwiec = "  + "'" + jtxtKontrolaSwiec.getText() + "',"
							+ " KontrolaPaskow = "  + "'" + jtxtKontrolaPaskow.getText() + "',"
							+ " KonserwacjaZaciskowHamulocowych = "  + "'" + jtxtKonserwacjaZaciskowHamulocowych.getText() + "',"
							+ " KontrolaZamkow = "  + "'" + jtxtKontrolaZamkow.getText() + "',"
							+ " KontrolaZawiasow = "  + "'" + jtxtKontrolaZawiasow.getText() + "',"
							+ " RegulacjaHamulcaPostojowego = "  + "'" + jtxtRegulacjaHamulcaPostojowego.getText() + "',"
							+ " KontrolaLuzowZaworowych = "  + "'" + jtxtKontrolaLuzowZaworowych.getText() + "'"
							+ " WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "UPDATE public.zlecenia"
					+ " SET"
					+ " Nazwa = " + "'" + jtxtNazwa.getText() + "'"
					+ " WHERE IDSamochodu = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
	            	jtxtWymianaOleju.setText("false");
	            	jtxtWymianaFiltru.setText("false");
	            	jtxtWymianaWycieraczek.setText("false");
	            	jtxtKontrolaHamulcow.setText("false");
	            	jtxtKontrolaZawieszenia.setText("false");
	            	jtxtKontrolaOpon.setText("false");
	            	jtxtKontrolaPlynow.setText("false");
	            	jtxtKontrolaWyciekow.setText("false");
	            	jtxtKontrolaSwiec.setText("false");
	            	jtxtKontrolaPaskow.setText("");
	            	jtxtKonserwacjaZaciskowHamulocowych.setText("false");
	            	jtxtKontrolaZamkow.setText("false");
	            	jtxtKontrolaZawiasow.setText("false");
	            	jtxtRegulacjaHamulcaPostojowego.setText("false");
	            	jtxtKontrolaLuzowZaworowych.setText("false");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(407, 655, 130, 57);
		btnDelete.setOpaque(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setForeground(Color.white);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "DELETE FROM public.samochody WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "DELETE FROM public.zlecenia WHERE IDSamochodu = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
	            	jtxtWymianaOleju.setText("false");
	            	jtxtWymianaFiltru.setText("false");
	            	jtxtWymianaWycieraczek.setText("false");
	            	jtxtKontrolaHamulcow.setText("false");
	            	jtxtKontrolaZawieszenia.setText("false");
	            	jtxtKontrolaOpon.setText("false");
	            	jtxtKontrolaPlynow.setText("false");
	            	jtxtKontrolaWyciekow.setText("false");
	            	jtxtKontrolaSwiec.setText("false");
	            	jtxtKontrolaPaskow.setText("");
	            	jtxtKonserwacjaZaciskowHamulocowych.setText("false");
	            	jtxtKontrolaZamkow.setText("false");
	            	jtxtKontrolaZawiasow.setText("false");
	            	jtxtRegulacjaHamulcaPostojowego.setText("false");
	            	jtxtKontrolaLuzowZaworowych.setText("false");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
	}
	
	private void Table(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 100, 830, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbStanowiska extends JPanel{
	
	private static String ID;
	private static String Nazwa;
	private static int IDSamochodu;
	private static boolean Exist;
	private JLabel lblStanowiska;
	private JLabel lblID;
	private JLabel lblNazwa;
	private JLabel lblIDSamochodu;
	private JTextField jtxtID;
	private JTextField jtxtNazwa;
	private static JTextField jtxtIDSamochodu;
	private JButton btnCarSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private JFrame nextframe;
	private DbSamochodySearch dbsamochodysearch;
	private Graphics2D g2;
	private Image Wrench, Workshop_stations;
	private static String login;
	private static String password;
			
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.stanowiska");
			Object[] columnData = new Object[3];
			while(result.next()){
				ID = result.getString("ID");
				Nazwa = result.getString("nazwa");
				IDSamochodu = result.getInt("IDSamochodu");
				columnData[0] = ID;
				columnData[1] = Nazwa;
				columnData[2] = IDSamochodu;
				
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public DbStanowiska(String login, String password) {
		this.login = login;
		this.password = password;
		Exist  = true;
		plikiGraficzne();
		initialize();
		Object col[] = {"ID", "Nazwa", "IDSamochodu"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	public void plikiGraficzne() {
		try {
			Wrench = ImageIO.read(new File("wrench.png"));
			Workshop_stations = ImageIO.read(new File("workshop.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Workshop_stations, 0, 0, 1500, 800, null);
	}
	
	public static boolean IsExist() {
		return Exist;
	}
	
	public static void SetExistFalse() {
		Exist = false;
	}
	
	public static void SetIDSamochodu(String IDSamochodu) {
		jtxtIDSamochodu.setText(IDSamochodu);
	}
	
	private void initialize() {
		
		setLayout(null);
		
		LeftSide();
		
		Buttons();
		
		Table();
		
	}
	
	private void LeftSide() {
		lblStanowiska = new JLabel("Stanowiska");
		lblStanowiska.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblStanowiska.setHorizontalAlignment(SwingConstants.CENTER);
		lblStanowiska.setBounds(91, 36, 1337, 40);
		lblStanowiska.setForeground(Color.white);
		this.add(lblStanowiska);
		
		lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblID.setBounds(53, 142, 129, 22);
		lblID.setForeground(Color.white);
		add(lblID);
		
		jtxtID = new JTextField();
		jtxtID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtID.setBounds(200, 139, 337, 40);
		add(jtxtID);
		jtxtID.setColumns(10);
		jtxtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!jtxtID.getText().isBlank()) {
					try {
						
						String query = "SELECT * From public.stanowiska WHERE ID = " + jtxtID.getText();					
						ResultSet result = new SerwisQueryExecutor(login, password).executeSelect(query);
						
			            if(result.next()==true){
			            	
			            	jtxtNazwa.setText(result.getString("Nazwa"));
			            	jtxtIDSamochodu.setText(result.getString("IDSamochodu"));
			                
			            }  
			            else{
			            	
			            	jtxtNazwa.setText("");
			            	jtxtIDSamochodu.setText("");
			                
			            }
			            
			        }
					catch (SQLException e1) {
					}
				}
				else{
					
					jtxtNazwa.setText("");
		        	jtxtIDSamochodu.setText("");
		
		        }
			}
		});
		
		lblNazwa = new JLabel("nazwa");
		lblNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNazwa.setBounds(53, 232, 129, 22);
		lblNazwa.setForeground(Color.white);
		add(lblNazwa);
		
		jtxtNazwa = new JTextField();
		jtxtNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNazwa.setBounds(200, 229, 337, 40);
		add(jtxtNazwa);
		jtxtNazwa.setColumns(10);
		
		lblIDSamochodu = new JLabel("IDSamochodu");
		lblIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIDSamochodu.setBounds(53, 322, 129, 22);
		lblIDSamochodu.setForeground(Color.white);
		add(lblIDSamochodu);
		
		jtxtIDSamochodu = new JTextField();
		jtxtIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtIDSamochodu.setBounds(200, 319, 337, 40);
		add(jtxtIDSamochodu);
		jtxtIDSamochodu.setColumns(10);
	}
	
	private void Buttons() {
		
		btnCarSearch = new JButton("+");
		btnCarSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCarSearch.setBounds(543, 319, 49, 40);
		btnCarSearch.setOpaque(false);
		btnCarSearch.setContentAreaFilled(false);
		btnCarSearch.setForeground(Color.white);
		add(btnCarSearch);
		btnCarSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbsamochodysearch = new DbSamochodySearch(login, password);
				nextframe.add(dbsamochodysearch);
				nextframe.setVisible(true);
				nextframe.pack();
			}
		});
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(53, 655, 130, 57);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.white);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "INSERT INTO public.stanowiska (ID, Nazwa, IDSamochodu) "
							+ "VALUES ('" + jtxtID.getText() + "', '" + 
									jtxtNazwa.getText() + "', '" + 
									jtxtIDSamochodu.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(238, 655, 130, 57);
		btnUpdate.setOpaque(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setForeground(Color.white);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "UPDATE public.stanowiska"
							+ " SET"
							+ " Nazwa = "  + "'" + jtxtNazwa.getText() + "',"
							+ " IDSamochodu = " + "'" + jtxtIDSamochodu.getText() + "'"
							+ " WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(407, 655, 130, 57);
		btnDelete.setOpaque(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setForeground(Color.white);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "DELETE FROM public.stanowiska WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtNazwa.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
	}
	
	private void Table() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 100, 830, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setBackground(new Color(0,0,0,0));
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbPracownicy extends JPanel{
	
	private static String ID;
	private static String Imie;
	private static String Nazwisko;
	private static boolean Plec;
	private static int Wiek;
	private static String IDSamochodu;
	private static boolean Exist;
	private JLabel lblPracownicy;
	private JLabel lblID;
	private JLabel lblImie;
	private JLabel lblNazwisko;
	private JLabel lblPlec;
	private JLabel lblWiek;
	private JLabel lblIDSamochodu;
	private JTextField jtxtID;
	private JTextField jtxtImie;
	private JTextField jtxtNazwisko;
	private JTextField jtxtPlec;
	private JTextField jtxtWiek;
	private static JTextField jtxtIDSamochodu;
	private JButton btnCarSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private JFrame nextframe;
	private DbSamochodySearch dbsamochodysearch;
	private Graphics2D g2;
	private Image Wrench, Employees;
	private static String login;
	private static String password;
	
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.pracownicy");
			Object[] columnData = new Object[6];
			while(result.next()){
				ID = result.getString("ID");
				Imie = result.getString("Imie");
				Nazwisko = result.getString("Nazwisko");
				Plec = result.getBoolean("Plec");
				Wiek = result.getInt("Wiek");
				IDSamochodu = result.getString("IDSamochodu");
				columnData[0] = ID;
				columnData[1] = Imie;
				columnData[2] = Nazwisko;
				columnData[3] = Plec;
				columnData[4] = Wiek;
				columnData[5] = IDSamochodu;
				
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DbPracownicy(String login, String password) {
		this.login = login;
		this.password = password;
		Exist = true;
		plikiGraficzne();
		initialize();
		Object col[] = {"ID", "Imie", "Nazwisko", "Plec", "Wiek", "IDSamochodu"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	public void plikiGraficzne() {
		try {
			Wrench = ImageIO.read(new File("wrench.png"));
			Employees = ImageIO.read(new File("employees.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Employees, 0, 0, 1500, 800, null);
	}
	
	public static boolean IsExist() {
		return Exist;
	}
	
	public static void SetExistFalse() {
		Exist = false;
	}
	
	public static void SetIDSamochodu(String IDSamochodu) {
		jtxtIDSamochodu.setText(IDSamochodu);
	}
	
	private void initialize() {
		
		setLayout(null);
		
		LeftSide();

		Buttons();
		
		Table();
		
	}
	
	private void LeftSide() {
		lblPracownicy = new JLabel("Pracownicy");
		lblPracownicy.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPracownicy.setHorizontalAlignment(SwingConstants.CENTER);
		lblPracownicy.setBounds(91, 36, 1337, 40);
		lblPracownicy.setForeground(Color.white);
		add(lblPracownicy);
		
		lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblID.setBounds(53, 142, 129, 22);
		lblID.setForeground(Color.white);
		add(lblID);
		
		jtxtID = new JTextField();
		jtxtID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtID.setBounds(200, 139, 337, 40);
		add(jtxtID);
		jtxtID.setColumns(10);
		jtxtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!jtxtID.getText().isBlank()) {
					try {
						
						String query = "SELECT * From public.pracownicy WHERE ID = " + jtxtID.getText();					
						ResultSet result = new SerwisQueryExecutor(login, password).executeSelect(query);
						
			            if(result.next()==true){
			            	
			            	jtxtImie.setText(result.getString("Imie"));
			            	jtxtNazwisko.setText(result.getString("Nazwisko"));
			            	jtxtPlec.setText(Boolean.toString(result.getBoolean("Plec")));
			            	jtxtWiek.setText(result.getString("Wiek"));
			            	jtxtIDSamochodu.setText(result.getString("IDSamochodu"));
			                
			            }  
			            else{
			            	
			            	jtxtImie.setText("");
			            	jtxtNazwisko.setText("");
			            	jtxtPlec.setText("");
			            	jtxtWiek.setText("");
			            	jtxtIDSamochodu.setText("");
			                
			            }
			            
			        }
					catch (SQLException e1) {
					}
				}
				else{
					
					jtxtImie.setText("");
					jtxtNazwisko.setText("");
					jtxtPlec.setText("");
		        	jtxtWiek.setText("");
		        	jtxtIDSamochodu.setText("");
		
		        }
			}
		});
		
		lblImie = new JLabel("Imie");
		lblImie.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblImie.setBounds(53, 232, 129, 22);
		lblImie.setForeground(Color.white);
		add(lblImie);
		
		jtxtImie = new JTextField();
		jtxtImie.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtImie.setBounds(200, 229, 337, 40);
		add(jtxtImie);
		jtxtImie.setColumns(10);
		
		lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNazwisko.setBounds(53, 322, 129, 22);
		lblNazwisko.setForeground(Color.white);
		add(lblNazwisko);
		
		jtxtNazwisko = new JTextField();
		jtxtNazwisko.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNazwisko.setBounds(200, 319, 337, 40);
		add(jtxtNazwisko);
		jtxtNazwisko.setColumns(10);
		
		lblPlec = new JLabel("Plec");
		lblPlec.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlec.setBounds(53, 412, 129, 22);
		lblPlec.setForeground(Color.white);
		add(lblPlec);
		
		jtxtPlec = new JTextField();
		jtxtPlec.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtPlec.setBounds(200, 409, 337, 40);
		add(jtxtPlec);
		jtxtPlec.setColumns(10);
		
		lblWiek = new JLabel("Wiek");
		lblWiek.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWiek.setBounds(53, 502, 129, 22);
		lblWiek.setForeground(Color.white);
		add(lblWiek);
		
		jtxtWiek = new JTextField();
		jtxtWiek.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtWiek.setBounds(200, 499, 337, 40);
		add(jtxtWiek);
		jtxtWiek.setColumns(10);
		
		lblIDSamochodu = new JLabel("IDSamochodu");
		lblIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIDSamochodu.setBounds(53, 586, 129, 34);
		lblIDSamochodu.setForeground(Color.white);
		add(lblIDSamochodu);
		
		jtxtIDSamochodu = new JTextField();
		jtxtIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtIDSamochodu.setBounds(200, 583, 337, 40);
		add(jtxtIDSamochodu);
		jtxtIDSamochodu.setColumns(10);
	}
	
	private void Buttons() {
		
		btnCarSearch = new JButton("+");
		btnCarSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCarSearch.setBounds(543, 583, 49, 40);
		btnCarSearch.setOpaque(false);
		btnCarSearch.setContentAreaFilled(false);
		btnCarSearch.setForeground(Color.white);
		add(btnCarSearch);
		btnCarSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				nextframe = new JFrame();
				nextframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				nextframe.addWindowListener(new WindowAdapter() {
				   public void windowClosing(WindowEvent evt) {
					   nextframe.dispose();
				   }
				});
				nextframe.setResizable(false);
				nextframe.setIconImage(Wrench);
				dbsamochodysearch = new DbSamochodySearch(login, password);
				nextframe.add(dbsamochodysearch);
				nextframe.setVisible(true);
				nextframe.pack();
			}
		});
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(53, 655, 130, 57);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.white);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "INSERT INTO public.pracownicy (ID, Imie, Nazwisko, Plec, Wiek, IDSamochodu) "
							+ "VALUES ('" + jtxtID.getText() + "', '" + 
									jtxtImie.getText() + "', '" + 
									jtxtNazwisko.getText() + "', '" + 
									jtxtPlec.getText() + "', '" + 
									jtxtWiek.getText() + "', '" + 
									jtxtIDSamochodu.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtImie.setText("");
					jtxtNazwisko.setText("");
		        	jtxtPlec.setText("");
		        	jtxtWiek.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(238, 655, 130, 57);
		btnUpdate.setOpaque(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setForeground(Color.white);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "UPDATE public.pracownicy"
							+ " SET"
							+ " Imie = "  + "'" + jtxtImie.getText() + "',"
							+ " Nazwisko = " + "'" + jtxtNazwisko.getText() + "',"
							+ " Plec = " + "'" + jtxtPlec.getText() + "',"
							+ " Wiek = " + "'" + jtxtWiek.getText() + "',"
							+ " IDSamochodu = " + "'" + jtxtIDSamochodu.getText() + "'"
							+ " WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtImie.setText("");
					jtxtNazwisko.setText("");
		        	jtxtPlec.setText("");
		        	jtxtWiek.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(407, 655, 130, 57);
		btnDelete.setOpaque(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setForeground(Color.white);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "DELETE FROM public.pracownicy WHERE ID = " + jtxtID.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtID.setText("");
					jtxtImie.setText("");
					jtxtNazwisko.setText("");
		        	jtxtPlec.setText("");
		        	jtxtWiek.setText("");
		        	jtxtIDSamochodu.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
	}
	
	private void Table() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 100, 830, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setBackground(new Color(0,0,0,0));
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}


class DbSamochodySearch extends JPanel{
	
	private JLabel lblSamochody;
	private static String ID;
	private static String Nazwa;
	private static Boolean WymianaOleju;
	private static Boolean WymianaFiltru;
	private static Boolean WymianaWycieraczek;
	private static Boolean KontrolaHamulcow;
	private static Boolean KontrolaZawieszenia;
	private static Boolean KontrolaOpon;
	private static Boolean KontrolaPlynow;
	private static Boolean KontrolaWyciekow;
	private static Boolean KontrolaSwiec;
	private static Boolean KontrolaPaskow;
	private static Boolean KonserwacjaZaciskowHamulocowych;
	private static Boolean KontrolaZamkow;
	private static Boolean KontrolaZawiasow;
	private static Boolean RegulacjaHamulcaPostojowego;
	private static Boolean KontrolaLuzowZaworowych;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private Graphics2D g2;
	private Image Cars;
	private static String login;
	private static String password;
			
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.samochody");
			Object[] columnData = new Object[17];
			while(result.next()){
				ID = result.getString("ID");
				Nazwa = result.getString("Nazwa");
				WymianaOleju = result.getBoolean("WymianaOleju");
				WymianaFiltru = result.getBoolean("WymianaFiltru");
				WymianaWycieraczek = result.getBoolean("WymianaWycieraczek");
				KontrolaHamulcow = result.getBoolean("KontrolaHamulcow");
				KontrolaZawieszenia = result.getBoolean("KontrolaZawieszenia");
				KontrolaOpon = result.getBoolean("KontrolaOpon");
				KontrolaPlynow = result.getBoolean("KontrolaPlynow");
				KontrolaWyciekow = result.getBoolean("KontrolaWyciekow");
				KontrolaSwiec = result.getBoolean("KontrolaSwiec");
				KontrolaPaskow = result.getBoolean("KontrolaPaskow");
				KonserwacjaZaciskowHamulocowych = result.getBoolean("KonserwacjaZaciskowHamulocowych");
				KontrolaZamkow = result.getBoolean("KontrolaZamkow");
				KontrolaZawiasow = result.getBoolean("KontrolaZawiasow");
				RegulacjaHamulcaPostojowego = result.getBoolean("RegulacjaHamulcaPostojowego");
				KontrolaLuzowZaworowych = result.getBoolean("KontrolaLuzowZaworowych");
				columnData[0] = ID;
				columnData[1] = Nazwa;
				columnData[2] = WymianaOleju;
				columnData[3] = WymianaFiltru;
				columnData[4] = WymianaWycieraczek;
				columnData[5] = KontrolaHamulcow;
				columnData[6] = KontrolaZawieszenia;
				columnData[7] = KontrolaOpon;
				columnData[8] = KontrolaPlynow;
				columnData[9] = KontrolaWyciekow;
				columnData[10] = KontrolaSwiec;
				columnData[11] = KontrolaPaskow;
				columnData[12] = KonserwacjaZaciskowHamulocowych;
				columnData[13] = KontrolaZamkow;
				columnData[14] = KontrolaZawiasow;
				columnData[15] = RegulacjaHamulcaPostojowego;
				columnData[16] = KontrolaLuzowZaworowych;
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public DbSamochodySearch(String login, String password) {
		this.login = login;
		this.password = password;
		plikiGraficzne();
		initialize();
		Object col[] = {"ID", "Nazwa", "WymianaOleju", "WymianaFiltru", "WymianaWycieraczek", "KontrolaHamulcow", "KontrolaZawieszenia", "KontrolaOpon", "KontrolaPlynow", "KontrolaWyciekow", "KontrolaSwiec", "KontrolaPaskow", "KonserwacjaZaciskowHamulocowych", "KontrolaZamkow", "KontrolaZawiasow", "RegulacjaHamulcaPostojowego", "KontrolaLuzowZaworowych"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	public void plikiGraficzne() {
		try {
			Cars = ImageIO.read(new File("cars.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Cars, 0, 0, 1500, 800, null);
		g2.setColor(Color.black);
		g2.fillRect(678, 36, 164, 40);
	}
	
	private void initialize() {
		
		setLayout(null);
		lblSamochody = new JLabel("Samochody");
		lblSamochody.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblSamochody.setHorizontalAlignment(SwingConstants.CENTER);
		lblSamochody.setBounds(91, 36, 1337, 40);
		lblSamochody.setForeground(Color.white);
		add(lblSamochody);
		Buttons();
		Table();
		
	}
	
	
	private void Buttons() {
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(1200, 343, 130, 57);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.white);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!table.getSelectionModel().isSelectionEmpty()) {
					String IDSamochodow = "";
					int x = 0;
					for (int i = 0; i < table.getRowCount(); i++) {
						if (table.isRowSelected(i)) {
							if (x!=0) {
								IDSamochodow += "," + table.getValueAt(i, 0);
							}
							else{
								IDSamochodow += table.getValueAt(i, 0);
							}
							x++;
						}
					}
					if (DbStanowiska.IsExist()) {
						if (x==1) {
							DbStanowiska.SetIDSamochodu(IDSamochodow);
							((Window) getRootPane().getParent()).dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "Tylko jeden wiersz tabeli powinien byæ wybrany ");
						}
					}
					else if (DbPracownicy.IsExist()) {
						DbPracownicy.SetIDSamochodu(IDSamochodow);
						((Window) getRootPane().getParent()).dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Co najmniej jeden z wierszy tabeli nie jest zaznaczony");
				}
			}
		});
	}

	private void Table(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 100, 1100, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setBackground(new Color(0,0,0,0));
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbZlecenia extends JPanel{
	
	private JLabel lblZlecenia;
	private JLabel lblIDSamochodu;
	private JLabel lblNazwa;
	private JLabel lblPrzyjecieZlecenia;
	private JLabel lblDataRealizacji;
	private JLabel lblDataOdbioru;
	private JLabel lblOplataZaSpoznienie;
	private JTextField jtxtIDSamochodu;
	private JTextField jtxtNazwa;
	private JDateChooser jdcPrzyjecieZlecenia;
	private JDateChooser jdcDataRealizacji;
	private JDateChooser jdcDataOdbioru;
	private JTextField jtxtOplataZaSpoznienie;
	private static int IDSamochodu;
	private static String Nazwa;
	private static Date PrzyjecieZlecenia;
	private static Date DataRealizacji;
	private static Date DataOdbioru;
	private static double OplataZaSpoznienie;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private Graphics2D g2;
	private Image Orders;
	private static String login;
	private static String password;
	
	public DbZlecenia(String login, String password) {
		this.login = login;
		this.password = password;
		
		plikiGraficzne();
		initialize();
		
		Object col[] = {"IDSamochodu", "Nazwa", "PrzyjecieZlecenia", "DataRealizacji", "DataOdbioru", "OplataZaSpoznienie"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.zlecenia");
			Object[] columnData = new Object[6];
			while(result.next()){
				IDSamochodu = result.getInt("IDSamochodu");
				Nazwa = result.getString("Nazwa");
				PrzyjecieZlecenia = result.getDate("PrzyjecieZlecenia");
				DataRealizacji = result.getDate("DataRealizacji");
				DataOdbioru = result.getDate("DataOdbioru");
				OplataZaSpoznienie = result.getDouble("OplataZaSpoznienie");
				columnData[0] = IDSamochodu;
				columnData[1] = Nazwa;
				columnData[2] = PrzyjecieZlecenia;
				columnData[3] = DataRealizacji;
				columnData[4] = DataOdbioru;
				columnData[5] = OplataZaSpoznienie;
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void plikiGraficzne() {
		try {
			Orders = ImageIO.read(new File("orders.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(Orders, 0, 0, 1500, 800, null);
	}
	
	private void initialize(){
		
		setLayout(null);
		
		TopLeftCorner();
		
		Buttons();
		
		Table();
		
	}
	
	private void TopLeftCorner() {
		lblZlecenia = new JLabel("Zlecenia");
		lblZlecenia.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblZlecenia.setHorizontalAlignment(SwingConstants.CENTER);
		lblZlecenia.setBounds(91, 36, 1337, 40);
		lblZlecenia.setForeground(Color.white);
		add(lblZlecenia);
		
		lblIDSamochodu = new JLabel("IDSamochodu");
		lblIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblIDSamochodu.setBounds(53, 142, 349, 22);
		lblIDSamochodu.setForeground(Color.white);
		add(lblIDSamochodu);
		
		jtxtIDSamochodu = new JTextField();
		jtxtIDSamochodu.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtIDSamochodu.setBounds(280, 139, 257, 40);
		add(jtxtIDSamochodu);
		jtxtIDSamochodu.setColumns(10);
		jtxtIDSamochodu.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!jtxtIDSamochodu.getText().isBlank()) {
					try {
						
						String query = "SELECT * From public.zlecenia WHERE IDSamochodu = " + jtxtIDSamochodu.getText();					
						ResultSet result = new SerwisQueryExecutor(login, password).executeSelect(query);
						
			            if(result.next()==true){
			            	
							jtxtNazwa.setText(result.getString("Nazwa"));
							jdcPrzyjecieZlecenia.setDate(result.getDate("PrzyjecieZlecenia"));
							jdcDataRealizacji.setDate(result.getDate("DataRealizacji"));
							jdcDataOdbioru.setDate(result.getDate("DataOdbioru"));
							jtxtOplataZaSpoznienie.setText(result.getString("OplataZaSpoznienie"));
			      
			                
			            }  
			            else{
			            	
							jtxtNazwa.setText("");
							jdcPrzyjecieZlecenia.setDate(null);
							jdcDataRealizacji.setDate(null);
							jdcDataOdbioru.setDate(null);
							jtxtOplataZaSpoznienie.setText("");
			            	
			            	
			            }
			            
			        }
					catch (SQLException e1) {
					}
				}
				else{
					
					jtxtNazwa.setText("");
					jdcPrzyjecieZlecenia.setDate(null);
					jdcDataRealizacji.setDate(null);
					jdcDataOdbioru.setDate(null);
					jtxtOplataZaSpoznienie.setText("");
	            	
		        }
			}
		});
		
		lblNazwa = new JLabel("Nazwa");
		lblNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNazwa.setBounds(53, 232, 349, 22);
		lblNazwa.setForeground(Color.white);
		add(lblNazwa);
		
		jtxtNazwa = new JTextField();
		jtxtNazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtNazwa.setBounds(280, 229, 257, 40);
		add(jtxtNazwa);
		jtxtNazwa.setColumns(10);
		
		lblPrzyjecieZlecenia = new JLabel("PrzyjecieZlecenia");
		lblPrzyjecieZlecenia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrzyjecieZlecenia.setBounds(53, 322, 349, 22);
		lblPrzyjecieZlecenia.setForeground(Color.white);
		add(lblPrzyjecieZlecenia);
		
		jdcPrzyjecieZlecenia = new JDateChooser();
		jdcPrzyjecieZlecenia.setFont(new Font("Tahoma", Font.BOLD, 18));
		jdcPrzyjecieZlecenia.setBounds(280, 319, 257, 40);
		jdcPrzyjecieZlecenia.setDateFormatString("yyyy-MM-dd");
		add(jdcPrzyjecieZlecenia);
		
		lblDataRealizacji = new JLabel("DataRealizacji");
		lblDataRealizacji.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDataRealizacji.setBounds(53, 412, 349, 22);
		lblDataRealizacji.setForeground(Color.white);
		add(lblDataRealizacji);
		
		jdcDataRealizacji = new JDateChooser();
		jdcDataRealizacji.setFont(new Font("Tahoma", Font.BOLD, 18));
		jdcDataRealizacji.setBounds(280, 409, 257, 40);
		jdcDataRealizacji.setDateFormatString("yyyy-MM-dd");
		add(jdcDataRealizacji);
		
		lblDataOdbioru = new JLabel("DataOdbioru");
		lblDataOdbioru.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDataOdbioru.setBounds(53, 502, 349, 22);
		lblDataOdbioru.setForeground(Color.white);
		add(lblDataOdbioru);
		
		jdcDataOdbioru = new JDateChooser();
		jdcDataOdbioru.setFont(new Font("Tahoma", Font.BOLD, 18));
		jdcDataOdbioru.setBounds(280, 499, 257, 40);
		jdcDataOdbioru.setDateFormatString("yyyy-MM-dd");
		add(jdcDataOdbioru);
		
		lblOplataZaSpoznienie = new JLabel("OplataZaSpoznienie");
		lblOplataZaSpoznienie.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOplataZaSpoznienie.setBounds(53, 592, 349, 22);
		lblOplataZaSpoznienie.setForeground(Color.white);
		add(lblOplataZaSpoznienie);
		
		jtxtOplataZaSpoznienie = new JTextField();
		jtxtOplataZaSpoznienie.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtOplataZaSpoznienie.setBounds(280, 589, 257, 40);
		add(jtxtOplataZaSpoznienie);
		jtxtOplataZaSpoznienie.setColumns(10);
		
	}
	
	private void Buttons() {
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(53, 655, 130, 57);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setForeground(Color.white);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtIDSamochodu.getText().isBlank()) {
					
					String query = "INSERT INTO public.zlecenia (IDSamochodu, Nazwa, PrzyjecieZlecenia, DataRealizacji, DataOdbioru, OplataZaSpoznienie) "
							+ "VALUES ('" + jtxtIDSamochodu.getText() + "', '" + 
							jtxtNazwa.getText() + "', '" + 
							jdcPrzyjecieZlecenia.getDate() + "', '" + 
							jdcDataRealizacji.getDate() + "', '" + 
							jdcDataOdbioru.getDate() + "', '" + 
							jtxtOplataZaSpoznienie.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "INSERT INTO public.samochody (ID, Nazwa) "
							+ "VALUES ('" + jtxtIDSamochodu.getText() + "', '" + 
							jtxtNazwa.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtIDSamochodu.setText("");
					jtxtNazwa.setText("");
					jdcPrzyjecieZlecenia.setDate(null);
					jdcDataRealizacji.setDate(null);
					jdcDataOdbioru.setDate(null);
					jtxtOplataZaSpoznienie.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(238, 655, 130, 57);
		btnUpdate.setOpaque(false);
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setForeground(Color.white);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtIDSamochodu.getText().isBlank()) {
					
					String query = "UPDATE public.zlecenia"
							+ " SET"
							+ " Nazwa = " + "'" + jtxtNazwa.getText() + "',"
							+ " PrzyjecieZlecenia = " + "'" + jdcPrzyjecieZlecenia.getDate() + "',"
							+ " DataRealizacji = " + "'" + jdcDataRealizacji.getDate() + "',"
							+ " DataOdbioru = " + "'" + jdcDataOdbioru.getDate() + "',"
							+ " OplataZaSpoznienie = " + "'" + jtxtOplataZaSpoznienie.getText() + "'"
							+ " WHERE IDSamochodu = " + jtxtIDSamochodu.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "UPDATE public.samochody"
							+ " SET"
							+ " Nazwa = "  + "'" + jtxtNazwa.getText() + "'"
							+ " WHERE ID = " + jtxtIDSamochodu.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtIDSamochodu.setText("");
					jtxtNazwa.setText("");
					jdcPrzyjecieZlecenia.setDate(null);
					jdcDataRealizacji.setDate(null);
					jdcDataOdbioru.setDate(null);
					jtxtOplataZaSpoznienie.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(407, 655, 130, 57);
		btnDelete.setOpaque(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setForeground(Color.white);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtIDSamochodu.getText().isBlank()) {
					
					String query = "DELETE FROM public.zlecenia WHERE IDSamochodu = " + jtxtIDSamochodu.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					query = "DELETE FROM public.samochody WHERE ID = " + jtxtIDSamochodu.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtIDSamochodu.setText("");
					jtxtNazwa.setText("");
					jdcPrzyjecieZlecenia.setDate(null);
					jdcDataRealizacji.setDate(null);
					jdcDataOdbioru.setDate(null);
					jtxtOplataZaSpoznienie.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
		});
	}
	
	private void Table(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 100, 830, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setBackground(new Color(0,0,0,0));
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
	
}

class DbPanelAdmina extends JPanel{
	
	
	private JLabel lblPanelAdmina;
	private JLabel lblUzytkownik;
	private JLabel lblHaslo;
	private JTextField jtxtUzytkownik;
	private JPasswordField jpHaslo;
	private JButton btnChangePassword;
	private JButton btnBan;
	private JButton btnUnban;
	private static String Uzytkownik;
	private static boolean Banned;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
	private Graphics2D g2;
	private Image AdminPanelPicture;
	private static String login;
	private static String password;
	
	public DbPanelAdmina(String login, String password) {
		this.login = login;
		this.password = password;
		
		plikiGraficzne();
		initialize();
		
		Object col[] = {"Uzytkownik", "Banned"};
		Model.setColumnIdentifiers(col);
		Model.setRowCount(0);
		Read();
	}
	
	private static void Read() {
		try {
			ResultSet result = new SerwisQueryExecutor(login, password).executeSelect("SELECT * From public.users");
			Object[] columnData = new Object[2];
			while(result.next()){
				Uzytkownik = result.getString("Uzytkownik");
				Banned = result.getBoolean("Banned");
				columnData[0] = Uzytkownik;
				columnData[1] = Banned;
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void plikiGraficzne() {
		try {
			AdminPanelPicture = ImageIO.read(new File("adminpanel.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.drawImage(AdminPanelPicture, 0, 0, 1500, 800, null);
	}
	
	private void initialize(){
		
		setLayout(null);
		
		TopLeftCorner();
		
		Buttons();
		
		Table();
		
	}
	
	private void TopLeftCorner() {
		lblPanelAdmina = new JLabel("Panel Admina");
		lblPanelAdmina.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPanelAdmina.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelAdmina.setBounds(91, 36, 1337, 40);
		lblPanelAdmina.setForeground(Color.white);
		add(lblPanelAdmina);
		
		lblUzytkownik = new JLabel("Uzytkownik");
		lblUzytkownik.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUzytkownik.setBounds(53, 142, 349, 22);
		lblUzytkownik.setForeground(Color.white);
		add(lblUzytkownik);
		
		jtxtUzytkownik = new JTextField();
		jtxtUzytkownik.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtUzytkownik.setBounds(420, 139, 287, 40);
		add(jtxtUzytkownik);
		jtxtUzytkownik.setColumns(10);
		
		lblHaslo = new JLabel("Haslo");
		lblHaslo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHaslo.setBounds(53, 232, 349, 22);
		lblHaslo.setForeground(Color.white);
		add(lblHaslo);
		
		jpHaslo = new JPasswordField();
		jpHaslo.setFont(new Font("Tahoma", Font.BOLD, 18));
		jpHaslo.setBounds(420, 229, 287, 40);
		add(jpHaslo);
		jpHaslo.setColumns(10);
	}
	
	private void Buttons() {
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChangePassword.setBounds(53, 655, 200, 57);
		btnChangePassword.setOpaque(false);
		btnChangePassword.setContentAreaFilled(false);
		btnChangePassword.setForeground(Color.white);
		add(btnChangePassword);
		btnChangePassword.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtUzytkownik.getText().isBlank() && !String.valueOf(jpHaslo.getPassword()).isBlank()) {
					
					String query = "ALTER USER " + jtxtUzytkownik.getText() + " WITH PASSWORD '" + String.valueOf(jpHaslo.getPassword()) + "'";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					if (login.equals(jtxtUzytkownik.getText())) {
						password = String.valueOf(jpHaslo.getPassword());
					}
					jtxtUzytkownik.setText("");
					jpHaslo.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Uzytkownik lub Haslo jest puste");
		        }
				
			}
		});
		
		btnBan = new JButton("Ban");
		btnBan.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBan.setBounds(288, 655, 200, 57);
		btnBan.setOpaque(false);
		btnBan.setContentAreaFilled(false);
		btnBan.setForeground(Color.white);
		add(btnBan);
		btnBan.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtUzytkownik.getText().isBlank()) {
					
					String query = "UPDATE public.users"
							+ " SET"
							+ " Banned = " + "'" + true + "'"
							+ " WHERE Uzytkownik = " + "'" + jtxtUzytkownik.getText() + "'";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtUzytkownik.setText("");
					jpHaslo.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Uzytkownik jest pusty");
		        }
				
			}
		});
		
		btnUnban = new JButton("Unban");
		btnUnban.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUnban.setBounds(507, 655, 200, 57);
		btnUnban.setOpaque(false);
		btnUnban.setContentAreaFilled(false);
		btnUnban.setForeground(Color.white);
		add(btnUnban);
		btnUnban.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!jtxtUzytkownik.getText().isBlank()) {
					
					String query = "UPDATE public.users"
							+ " SET"
							+ " Banned = " + "'" + false + "'"
							+ " WHERE Uzytkownik = " + "'" + jtxtUzytkownik.getText() + "'";
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtUzytkownik.setText("");
					jpHaslo.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Uzytkownik jest pusty");
		        }
			}
		});
	}
	
	private void Table(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(748, 100, 680, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setShowGrid(false);
		table.setOpaque(false);
		table.setBackground(new Color(0,0,0,0));
		table.setForeground(Color.white);
		scrollPane.setViewportView(table);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
	
}

public class Lekcja29cz2_30_31_32_33_34 {
	
	private JFrame frame;
	private DBlogowanie2 dblogowanie2;
	private Image Wrench;
	
	Lekcja29cz2_30_31_32_33_34(){
		plikiGraficzne();
	}
	
	public void plikiGraficzne() {
		try {
			Wrench = ImageIO.read(new File("wrench.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//3 tabele: pracownicy, stanowiska, samochody

//wymianê: oleju silnikowego wraz z filtrem, filtra kabinowego uk³adu klimatyzacji, filtra paliwa (w samochodach z silnikiem Diesla), a tak¿e wycieraczek przedniej szyby;
//kontrolê: hamulców, zawieszenia, opon, stanu p³ynów eksploatacyjnych i ewentualnych wycieków, œwiec zap³onowych, pasków rozrz¹du;
//konserwacjê: zacisków hamulcowych, zamków i zawiasów;
//regulacjê: hamulca postojowego lub luzów zaworowych.

//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE samochody (ID int, Nazwa varchar(255), WymianaOleju BOOLEAN, WymianaFiltru BOOLEAN, WymianaWycieraczek BOOLEAN, KontrolaHamulcow BOOLEAN, KontrolaZawieszenia BOOLEAN, KontrolaOpon BOOLEAN, KontrolaPlynow BOOLEAN, KontrolaWyciekow BOOLEAN, KontrolaSwiec BOOLEAN, KontrolaPaskow BOOLEAN, KonserwacjaZaciskowHamulocowych BOOLEAN, KontrolaZamkow BOOLEAN, KontrolaZawiasow BOOLEAN, RegulacjaHamulcaPostojowego BOOLEAN, KontrolaLuzowZaworowych BOOLEAN)");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE stanowiska (ID int, Nazwa varchar(255), IDSamochodu int)");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE pracownicy (ID int, Imie varchar(255), Nazwisko varchar(255), Plec BOOLEAN, Wiek int, IDSamochodu varchar(255))");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE zlecenia (IDSamochodu int, Nazwa varchar(255), PrzyjecieZlecenia DATE, DataRealizacji DATE, DataOdbioru DATE, OplataZaSpoznienie DOUBLE PRECISION)");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE users (Uzytkownik varchar(255), Banned BOOLEAN, PrzyjecieZlecenia DATE, DataRealizacji DATE, DataOdbioru DATE, OplataZaSpoznienie DOUBLE PRECISION)");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
					try {
						new Lekcja29cz2_30_31_32_33_34().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
	}
	
	public void createAndShowGUI() throws Exception {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setIconImage(Wrench);
		dblogowanie2 = new DBlogowanie2();
		frame.add(dblogowanie2);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
