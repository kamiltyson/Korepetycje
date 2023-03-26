import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

class DbConnectorLogowanie3{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	
	public static Connection connect(String USER, String PASSWORD) {
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(URL, USER, PASSWORD);
			
			JOptionPane.showMessageDialog(null, "Zalogowano poprawnie.");
			JFrame frame = new JFrame();
			if (USER.equals("postgres")) {
				DbAdminGUI dbAdminGUI = new DbAdminGUI(USER, PASSWORD);
				frame.add(dbAdminGUI);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
			} 
			else {
				DbUserGUI dbUserGUI = new DbUserGUI(USER, PASSWORD);
				frame.add(dbUserGUI);
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

class DbConnectorRejestracja3{
	
	private static String query;
	
	public static void executeQuery(String USER, String PASSWORD){
		
		query = "CREATE USER " + USER + " PASSWORD '" + PASSWORD + "'";
		
		try {
			Connection connection  = DbConnector.connect();
			Statement statement = connection.createStatement();
			statement.execute(query);
			query = "ALTER USER " + USER + " WITH SUPERUSER";
			statement.execute(query);
			JOptionPane.showMessageDialog(null, "Zarejestrowano poprawnie");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login powtarza siê.");
		}
	}
}

class DbConnector2{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	private static String USER;
	private static String PASSWORD;
	
	public DbConnector2(String USER, String PASSWORD) {
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


class QueryExecutor2{
	
	private static String USER;
	private static String PASSWORD;
	
	public QueryExecutor2(String USER, String PASSWORD) {
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

class DBlogowanie3 extends JPanel implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private boolean zalogowano = false;
	
	public DBlogowanie3(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setFont(new Font("Tahoma", Font.BOLD, 18));
		ll.setBounds(130, 175, 140, 25);
		ll.setForeground(Color.black);
		add(ll);
		
		tl = new JTextField();
		tl.setFont(new Font("Tahoma", Font.BOLD, 18));
		tl.setBounds(130, 200, 240, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh.setBounds(130, 228, 140, 25);
		lh.setForeground(Color.black);
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
		bl.setForeground(Color.black);
		add(bl);
		
		JButton br = new JButton("Rejestruj");
		br.setFont(new Font("Tahoma", Font.BOLD, 18));
		br.setBounds(250, 275, 120, 50);
		br.setOpaque(false);
		br.setContentAreaFilled(false);
		br.setForeground(Color.black);
		add(br);
		
		bl.setActionCommand("Loguj");
		bl.addActionListener(this);
		
		br.setActionCommand("Rejestruj");
		br.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if ("Loguj".equals(e.getActionCommand())) {
			
			DbConnectorLogowanie3.connect(tl.getText(), String.valueOf(ph.getPassword()));
			
		}
		
		if ("Rejestruj".equals(e.getActionCommand())) {
			JFrame frame2 = new JFrame();
			frame2.add(new DBrejestracja3());
			frame2.setVisible(true);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.pack();
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
}

class DBrejestracja3 extends JPanel implements ActionListener{
	
	private JTextField tl;
	private JPasswordField ph;
	private JPasswordField ph2;
	private boolean loginpowtarzasie = false;
	
	public DBrejestracja3(){
		
		setLayout(null);
		
		JLabel ll = new JLabel("Login");
		ll.setFont(new Font("Tahoma", Font.BOLD, 18));
		ll.setForeground(Color.black);
		ll.setBounds(130, 150, 230, 25);
		add(ll);
		
		tl = new JTextField();
		tl.setFont(new Font("Tahoma", Font.BOLD, 18));
		tl.setBounds(130, 175, 230, 25);
		add(tl);
		
		JLabel lh = new JLabel("Has³o");
		lh.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh.setForeground(Color.black);
		lh.setBounds(130, 200, 230, 25);
		add(lh);
		
		ph = new JPasswordField();
		ph.setFont(new Font("Tahoma", Font.BOLD, 18));
		ph.setBounds(130, 225, 230, 25);
		add(ph);
		
		JLabel lh2 = new JLabel("Powtórz has³o");
		lh2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lh2.setForeground(Color.black);
		lh2.setBounds(130, 250, 230, 25);
		add(lh2);
		
		ph2 = new JPasswordField();
		ph2.setFont(new Font("Tahoma", Font.BOLD, 18));
		ph2.setBounds(130, 275, 230, 25);
		add(ph2);
		
		JButton br = new JButton("Rejestruj");
		br.setFont(new Font("Tahoma", Font.BOLD, 18));
		br.setContentAreaFilled(false);
		br.setForeground(Color.black);
		br.setBounds(130, 300, 230, 50);
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
					DbConnectorRejestracja3.executeQuery(String.valueOf(tl.getText()), String.valueOf(ph.getPassword()));
					
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

class DbAdminGUI extends JPanel{
	
	private JTable table1;
	private static DefaultTableModel Model1 = new DefaultTableModel();
	private static String Dzial1;
	private JScrollPane scrollPane1;
	private JTable table2;
	private JScrollPane scrollPane2;
	private JLabel lblDzial;
	private JTextField jtxtDzial;
	private JLabel lblTemat;
	private JTextField jtxtTemat;
	private JLabel lblTresc;
	private JTextField jtxtTresc;
	private JLabel lblAdd;
	private JLabel lblDelete;
	private JLabel lblSend;
	private static String login;
	private static String password;
	
	
	public DbAdminGUI(String login, String password) {
		
		super(new GridLayout(1, 1));
		
		this.login = login;
		this.password = password;
//		initialize();
		
		
		
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = PanelDzialy();
        tabbedPane.add("Dzialy", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JComponent panel2 = PanelWiadomosci();
        tabbedPane.add("Wiadomosci", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
				
		        if (sourceTabbedPane.getTitleAt(index).equals("Dzialy")) {
		        	Object col[] = {"Dzial"};
		    		Model1.setColumnIdentifiers(col);
		    		Model1.setRowCount(0);
		    		Read();
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Wiadomosci")) {
		        	Object col[] = {"Dzial"};
		    		Model1.setColumnIdentifiers(col);
		    		Model1.setRowCount(0);
		    		Read();
		        }
		        
			}
		});
        
		Object col[] = {"Dzial"};
		Model1.setColumnIdentifiers(col);
		Model1.setRowCount(0);
		Read();
		
    }
	
	
	private static void Read() {
		try {
			ResultSet result = new QueryExecutor2(login, password).executeSelect("SELECT * From public.dzialy");
			Object[] columnData = new Object[1];
			while(result.next()){
				
				Dzial1 = result.getString("Nazwa");
				columnData[0] = Dzial1;
				Model1.addRow(columnData);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private JComponent PanelDzialy() {
		JPanel panel = new JPanel(false);
		
		panel.setLayout(null);
		
		PanelDzialyTopLeftCorner(panel);
		
		PanelDzialyButtons(panel) ;
		
		Table1(panel);
		
        return panel;
	}
	
	private JComponent PanelWiadomosci() {
		
		JPanel panel = new JPanel(false);
		
		panel.setLayout(null);
		
		PanelWiadomosciTopRightCorner(panel);
		
		PanelWiadomosciButtons(panel) ;
		
		Table1(panel);
		
        return panel;
		
	}
    
	private void PanelDzialyTopLeftCorner(JPanel panel) {
		lblDzial = new JLabel("Dzial");
		lblDzial.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDzial.setBounds(53, 82, 129, 22);
		lblDzial.setForeground(Color.black);
		panel.add(lblDzial);
		
		jtxtDzial = new JTextField();
		jtxtDzial.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDzial.setBounds(200, 79, 337, 40);
		panel.add(jtxtDzial);
		
	}
	
	private void PanelDzialyButtons(JPanel panel) {
		
		lblAdd = new JLabel("Add");
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdd.setBounds(53, 155, 130, 57);
		lblAdd.setOpaque(false);
		lblAdd.setForeground(Color.black);
		lblAdd.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblAdd);
		
		lblAdd.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				if(!jtxtDzial.getText().isBlank()) {
					
					String query = "INSERT INTO public.Dzialy (Nazwa) "
							+ "VALUES ('" + jtxtDzial.getText() + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
		        	Model1.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Dzial jest pusty");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});
		
		lblDelete = new JLabel("Delete");
		lblDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDelete.setBounds(407, 155, 130, 57);
		lblDelete.setOpaque(false);
		lblDelete.setForeground(Color.black);
		lblDelete.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblDelete);
		lblDelete.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				
				if(!table1.getSelectionModel().isSelectionEmpty()) {
					
					for (int i = 0; i < table1.getRowCount(); i++) {
						if (table1.isRowSelected(i)) {
							
							String query = "DELETE FROM public.dzialy WHERE Dzial = " + table1.getValueAt(i, 0);
							new SerwisQueryExecutor(login, password).executeQuery(query);

						}
					}
					
		        	Model1.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Co najmniej jeden z wierszy tabeli nie jest zaznaczony");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});

	}
	
	private void Table1(JPanel panel){
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(605, 50, 300, 212);
		Border empty = new EmptyBorder(0,0,0,0);
		scrollPane1.setBorder(empty);
		panel.add(scrollPane1);
		
		table1 = new JTable();
		table1.setModel(Model1);
		table1.setFont(new Font("Tahoma", Font.BOLD, 18));
		table1.setShowGrid(false);
		table1.setOpaque(false);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane1.setViewportView(table1);
		scrollPane1.setOpaque(false);
		scrollPane1.getViewport().setOpaque(false);
	}
	
	private void PanelWiadomosciTopRightCorner(JPanel panel) {
		lblTemat = new JLabel("Temat");
		lblTemat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTemat.setBounds(53, 82, 129, 22);
		lblTemat.setForeground(Color.black);
		panel.add(lblTemat);
		
		jtxtTemat = new JTextField();
		jtxtTemat.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtTemat.setBounds(200, 79, 337, 40);
		panel.add(jtxtTemat);
		
		lblTresc = new JLabel("Tresc");
		lblTresc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTresc.setBounds(53, 132, 129, 22);
		lblTresc.setForeground(Color.black);
		panel.add(lblTresc);
		
		jtxtDzial = new JTextField();
		jtxtDzial.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDzial.setBounds(200, 129, 337, 40);
		panel.add(jtxtDzial);
		
		
	}
	
    private void PanelWiadomosciButtons(JPanel panel) {
    	
		lblSend = new JLabel("Send");
		lblSend.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSend.setBounds(53, 155, 130, 57);
		lblSend.setOpaque(false);
		lblSend.setForeground(Color.black);
		lblSend.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblSend);
		lblSend.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				if(!table2.getSelectionModel().isSelectionEmpty()) {
					
					for (int i = 0; i < table2.getRowCount(); i++) {
						if (table2.isRowSelected(i)) {
							
							try {
								ResultSet result = new QueryExecutor2(login, password).executeSelect("SELECT * From public.dzialy WHERE Dzial = " + table2.getValueAt(i, 0));
								Object[] columnData = new Object[1];
								while(result.next()){
									
									
									String query = "INSERT INTO public.wiadomosci (Dzial , Uzytkownik , Temat , Tresc) "
											+ "VALUES ('" + table2.getValueAt(i, 0) + "', '" + 
											result.getString("Uzytkownik") + "', '" + 
											jtxtTemat.getText() + "', '" + 
											jtxtTresc.getText() + "')";
									new SerwisQueryExecutor(login, password).executeQuery(query);
									
					            }
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Co najmniej jeden z wierszy tabeli nie jest zaznaczony");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});
    	
    }

	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbUserGUI extends JPanel{
	
	private JTable table1;
	private static DefaultTableModel Model1 = new DefaultTableModel();
	private static String Dzial1;
	private JScrollPane scrollPane1;
	private JTable table2;
	private static String Dzial2;
	private JScrollPane scrollPane2;
	private static DefaultTableModel Model2 = new DefaultTableModel();
	private static String Dzial3;
	private static String Temat3;
	private JScrollPane scrollPane3;
	private JTable table3;
	private static DefaultTableModel Model3 = new DefaultTableModel();
	private JLabel lblDzial;
	private JTextField jtxtDzial;
	private JLabel lblTemat;
	private JTextField jtxtTemat;
	private JLabel lblTresc;
	private JTextField jtxtTresc;
	private JLabel lblAdd;
	private JLabel lblDelete1;
	private JLabel lblDelete2;
	private static String login;
	private static String password;
	
	private static void Read() {
		try {
			ResultSet result = new QueryExecutor2(login, password).executeSelect("SELECT * From public.dzialy");
			Object[] columnData = new Object[1];
			while(result.next()){
				
				Dzial1 = result.getString("Nazwa");
				columnData[0] = Dzial1;
				Model1.addRow(columnData);
				
            }
			result = new QueryExecutor2(login, password).executeSelect("SELECT * From public.subskrypcje WHERE ID = " + login);
			columnData = new Object[1];
			while(result.next()){
				
				Dzial2 = result.getString("Dzial");
				columnData[0] = Dzial1;
				Model2.addRow(columnData);
				
            }
			result = new QueryExecutor2(login, password).executeSelect("SELECT * From public.wiadomosci WHERE ID = " + login);
			columnData = new Object[2];
			while(result.next()){
				
				Dzial3 = result.getString("Dzial");
				Temat3 = result.getString("Temat");
				columnData[0] = Dzial3;
				Model3.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DbUserGUI(String login, String password) {
		super(new GridLayout(1, 1));
		this.login = login;
		this.password = password;
		
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = PanelSubskrypcje();
        tabbedPane.add("Dzialy", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JComponent panel2 = PanelWiadomosci();
        tabbedPane.add("Wiadomosci", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        
        tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
		        int index = sourceTabbedPane.getSelectedIndex();
		        System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
				
		        if (sourceTabbedPane.getTitleAt(index).equals("Dzialy")) {
		        	Object col[] = {"Dzial"};
		    		Model1.setColumnIdentifiers(col);
		    		Model1.setRowCount(0);
		    		Read();
		        }
		        else if(sourceTabbedPane.getTitleAt(index).equals("Wiadomosci")) {
		        	Object col[] = {"Dzial"};
		    		Model1.setColumnIdentifiers(col);
		    		Model1.setRowCount(0);
		    		Read();
		        }
		        
			}
		});
		
		Object col[] = {"Dzial"};
		Model1.setColumnIdentifiers(col);
		Model1.setRowCount(0);
		Read();
	}
	
	private JComponent PanelSubskrypcje() {
		JPanel panel = new JPanel(false);
		
		panel.setLayout(null);
		
		Table1(panel);
		
		PanelSubksrypcjeButtons(panel) ;
		
		Table2(panel);
		
		return panel;
	}
	
	private JComponent PanelWiadomosci() {
		JPanel panel = new JPanel(false);
		
		panel.setLayout(null);
		
		Table3(panel);
		
		PanelWiadomosciButtons(panel) ;
		
		
		return panel;
	}

	private void PanelSubksrypcjeButtons(JPanel panel) {
		lblAdd = new JLabel("Add");
		lblAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdd.setBounds(53, 655, 130, 57);
		lblAdd.setOpaque(false);
		lblAdd.setForeground(Color.white);
		lblAdd.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblAdd);
		
		lblAdd.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				if(!table1.getSelectionModel().isSelectionEmpty()) {
					
					String query = "INSERT INTO public.subskrypcje (Uzytkownik, Dzial) "
							+ "VALUES ('" + 1 + "', '" + 
							1 + "')";
					new SerwisQueryExecutor(login, password).executeQuery(query);
		        	Model2.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});
		
		lblDelete1 = new JLabel("Delete");
		lblDelete1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDelete1.setBounds(407, 655, 130, 57);
		lblDelete1.setOpaque(false);
		lblDelete1.setForeground(Color.white);
		lblDelete1.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblDelete1);
		
		lblDelete1.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				if(!table2.getSelectionModel().isSelectionEmpty()) {
					
					String query = "DELETE FROM public.subskrypcje WHERE Uzytkownik = " + login + " AND Dzial =" + jtxtDzial.getText();
					new SerwisQueryExecutor(login, password).executeQuery(query);
		        	Model2.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID jest puste");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});
		
	}

	private void PanelWiadomosciButtons(JPanel panel) {
		
		lblDelete2 = new JLabel("Delete");
		lblDelete2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDelete2.setBounds(407, 655, 130, 57);
		lblDelete2.setOpaque(false);
		lblDelete2.setForeground(Color.white);
		lblDelete2.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblDelete2);
		lblDelete2.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				if(jtxtDzial.getText().isBlank() && jtxtTemat.getText().isBlank()) {
					
					String query = "DELETE FROM public.wiadomosci WHERE Dzial = " + jtxtDzial.getText() + " AND Temat = " + jtxtTemat.getText() ;
					new SerwisQueryExecutor(login, password).executeQuery(query);
					jtxtDzial.setText("");
					jtxtTemat.setText("");
					jtxtTresc.setText("");
		        	Model3.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "Nie wybra³eœ wiadomoœci do usuniêcia");
		        }
			}
			
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
			}
			
			public void mouseEntered(MouseEvent e) {
			}
			
		});
	}
	
	private void Table1(JPanel panel){
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(5, 50, 300, 212);
		Border empty = new EmptyBorder(0,0,0,0);
		scrollPane1.setBorder(empty);
		panel.add(scrollPane1);
		
		table1 = new JTable();
		table1.setModel(Model1);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table1.setShowGrid(false);
		table1.setOpaque(false);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane1.setViewportView(table1);
		scrollPane1.setOpaque(false);
		scrollPane1.getViewport().setOpaque(false);
	}
	
	private void Table2(JPanel panel){
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(750, 50, 300, 212);
		Border empty = new EmptyBorder(0,0,0,0);
		scrollPane2.setBorder(empty);
		panel.add(scrollPane2);
		
		table2 = new JTable();
		table2.setModel(Model2);
		table2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table2.setShowGrid(false);
		table2.setOpaque(false);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane2.setViewportView(table2);
		scrollPane2.setOpaque(false);
		scrollPane2.getViewport().setOpaque(false);
	}
	
	private void Table3(JPanel panel){
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(750, 50, 300, 212);
		Border empty = new EmptyBorder(0,0,0,0);
		scrollPane3.setBorder(empty);
		add(scrollPane3);
		
		table3 = new JTable();
		table3.setModel(Model3);
		table3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table3.setShowGrid(false);
		table3.setOpaque(false);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane3.setViewportView(table3);
		scrollPane3.setOpaque(false);
		scrollPane3.getViewport().setOpaque(false);
	}
	
	private void Panel2TopRightCorner() {
		// TODO Auto-generated method stub
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}


public class Lekcja46_47 {
	
	private JFrame frame;
	private DBlogowanie3 dblogowanie3;

	public static void main(String[] args) {
		
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE dzialy (Nazwa varchar(255))");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE subskrypcje (Uzytkownik varchar(255), Dzial varchar(255))");
//		new SerwisQueryExecutor("postgres", "12345").executeQuery("CREATE TABLE wiadomosci (Dzial varchar(255), Uzytkownik varchar(255), Temat varchar(255), Tresc varchar(255))");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
					try {
						new Lekcja46_47().createAndShowGUI();
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
		dblogowanie3 = new DBlogowanie3();
		frame.add(dblogowanie3);
		frame.setVisible(true);
		frame.pack();
		
	}
}
//user1 9}e_Nl-(
// W¹tki
//Symulator panela mailingowego:
//
//- Rejestracja uzytkownika, pod warunkiem ze taki nick nie istnieje, a haslo spelnia zasady zlozonosci
//- konto administratora ktory dodaje nowe dzialy 
//- User ktory widzi wszystkie dzialy i moze zaznaczyc ktore chce subskrybowac 
//- administrator tworzy wiadomosc i zaznacza jakiego dzialu lub kilku ona dotyczy, wysyla ja 
//- userzy subskrybujacy dostaja ta wiadomosc w swoim panelu z historia wiadomosci
//- wiadomosc moga archiwizowac (domyslnie) lub usunac 
//- Aplikacja wymaga pe³nego GUI oraz testów do poszczegolnych funckjonalnosci 
// Przypadki pozytywne ? Przypadki negatywne ?
// Test logowani - pobranie logowania i hasla
// Assert True
// Assert False