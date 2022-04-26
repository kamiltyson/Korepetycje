import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

class SerwisDbConnector{
	
	private static String URL = "jdbc:postgresql://localhost/postgres";
	private static String USER = "postgres";
	private static String PASSWORD = "12345";
	
	public static Connection connect() {
		
		Connection connection = null;
		try {
			connection  = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Po³¹czono");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}


class SerwisQueryExecutor{
	
	public static ResultSet executeSelect(String selectQuery){

		try {
			Connection connection  = DbConnector.connect();
			Statement statement = connection.createStatement();
			return statement.executeQuery(selectQuery);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	public static void executeQuery(String query){
		
		try {
			Connection connection  = DbConnector.connect();
			Statement statement = connection.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

class DbSamochody extends JPanel{
	
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbStanowiska extends JPanel{
	
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbPracownicy extends JPanel{
	
	private static String ID;
	private static String nazwa;
	private static String kategoria;
	private static boolean plec;
	private static double waga;
	private static int dlugosc;
	private JLabel lblzwierze;
	private JLabel lblID;
	private JLabel lblnazwa;
	private JLabel lblkategoria;
	private JLabel lblplec;
	private JLabel lblwaga;
	private JLabel lbldlugosc;
	private JTextField jtxtID;
	private JTextField jtxtnazwa;
	private JTextField jtxtkategoria;
	private JTextField jtxtplec;
	private JTextField jtxtwaga;
	private JTextField jtxtdlugosc;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel Model = new DefaultTableModel();
			
	
	private static void Read() {
		try {
			ResultSet result = SerwisQueryExecutor.executeSelect("SELECT * From public.pracownicy");
			Object[] columnData = new Object[6];
			while(result.next()){
				ID = result.getString("ID");
				nazwa = result.getString("nazwa");
				kategoria = result.getString("kategoria");
				plec = result.getBoolean("plec");
				waga = result.getDouble("waga");
				dlugosc = result.getInt("dlugosc");
				columnData[0] = ID;
				columnData[1] = nazwa;
				columnData[2] = kategoria;
				columnData[3] = plec;
				columnData[4] = waga;
				columnData[5] = dlugosc;
				
				Model.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public DbPracownicy() {
		initialize();
		Object col[] = {"ID", "nazwa", "kategoria", "plec", "waga", "dlugosc"};
		Model.setColumnIdentifiers(col);
		Read();
	}
	
	private void initialize() {
		
		setLayout(null);
		
		lblzwierze = new JLabel("Zwierze");
		lblzwierze.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblzwierze.setHorizontalAlignment(SwingConstants.CENTER);
		lblzwierze.setBounds(91, 36, 1337, 40);
		this.add(lblzwierze);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblID.setBounds(53, 142, 94, 22);
		add(lblID);
		
		jtxtID = new JTextField();
		jtxtID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtID.setBounds(157, 133, 380, 40);
		add(jtxtID);
		jtxtID.setColumns(10);
		jtxtID.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(!jtxtID.getText().isBlank()) {
					try {
						
						String query = "SELECT * From public.pracownicy WHERE ID = " + jtxtID.getText();					
						ResultSet result = SerwisQueryExecutor.executeSelect(query);
						
			            if(result.next()==true){
			            	
			            	jtxtnazwa.setText(result.getString("nazwa"));
			            	jtxtkategoria.setText(result.getString("kategoria"));
			            	jtxtplec.setText(Boolean.toString(result.getBoolean("plec")));
			            	jtxtwaga.setText(result.getString("waga"));
			            	jtxtdlugosc.setText(result.getString("dlugosc"));
			                
			            }  
			            else{
			            	
			            	jtxtnazwa.setText("");
			            	jtxtkategoria.setText("");
			            	jtxtplec.setText("");
			            	jtxtwaga.setText("");
			            	jtxtdlugosc.setText("");
			                
			            }
			            
			        }
					catch (SQLException e1) {
					}
				}
				else{
					
		        	jtxtnazwa.setText("");
		        	jtxtkategoria.setText("");
		        	jtxtplec.setText("");
		        	jtxtwaga.setText("");
		        	jtxtdlugosc.setText("");
		
		        }
			}
		});
		
		lblnazwa = new JLabel("nazwa");
		lblnazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblnazwa.setBounds(53, 232, 59, 22);
		add(lblnazwa);
		
		jtxtnazwa = new JTextField();
		jtxtnazwa.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtnazwa.setBounds(157, 223, 380, 40);
		add(jtxtnazwa);
		jtxtnazwa.setColumns(10);
		
		lblkategoria = new JLabel("kategoria");
		lblkategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblkategoria.setBounds(53, 322, 86, 22);
		add(lblkategoria);
		
		jtxtkategoria = new JTextField();
		jtxtkategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtkategoria.setBounds(157, 313, 380, 40);
		add(jtxtkategoria);
		jtxtkategoria.setColumns(10);
		
		lblplec = new JLabel("plec");
		lblplec.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblplec.setBounds(53, 412, 37, 22);
		add(lblplec);
		
		jtxtplec = new JTextField();
		jtxtplec.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtplec.setBounds(157, 403, 380, 40);
		add(jtxtplec);
		jtxtplec.setColumns(10);
		
		lblwaga = new JLabel("waga");
		lblwaga.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblwaga.setBounds(53, 502, 73, 22);
		add(lblwaga);
		
		jtxtwaga = new JTextField();
		jtxtwaga.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtwaga.setBounds(157, 493, 380, 40);
		add(jtxtwaga);
		jtxtwaga.setColumns(10);
		
		lbldlugosc = new JLabel("dlugosc");
		lbldlugosc.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbldlugosc.setBounds(53, 586, 73, 34);
		add(lbldlugosc);
		
		jtxtdlugosc = new JTextField();
		jtxtdlugosc.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtdlugosc.setBounds(157, 583, 380, 40);
		add(jtxtdlugosc);
		jtxtdlugosc.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAdd.setBounds(53, 655, 130, 57);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "INSERT INTO public.pracownicy (ID, nazwa, kategoria, plec, waga, dlugosc) "
							+ "VALUES ('" + jtxtID.getText() + "', '" + 
									jtxtnazwa.getText() + "', '" + 
									jtxtkategoria.getText() + "', '" + 
									jtxtplec.getText() + "', '" + 
									jtxtwaga.getText() + "', '" + 
									jtxtdlugosc.getText() + "')";
					SerwisQueryExecutor.executeQuery(query);
					jtxtID.setText("");
					jtxtnazwa.setText("");
		        	jtxtkategoria.setText("");
		        	jtxtplec.setText("");
		        	jtxtwaga.setText("");
		        	jtxtdlugosc.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID is blank");
		        }
			}
		});
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdate.setBounds(238, 655, 130, 57);
		add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "UPDATE public.pracownicy"
							+ " SET"
							+ " nazwa = "  + "'" + jtxtnazwa.getText() + "',"
							+ " kategoria = " + "'" + jtxtkategoria.getText() + "',"
							+ " plec = " + "'" + jtxtplec.getText() + "',"
							+ " waga = " + "'" + jtxtwaga.getText() + "',"
							+ " dlugosc = " + "'" + jtxtdlugosc.getText() + "'"
							+ " WHERE ID = " + jtxtID.getText();
					SerwisQueryExecutor.executeQuery(query);
					jtxtID.setText("");
					jtxtnazwa.setText("");
		        	jtxtkategoria.setText("");
		        	jtxtplec.setText("");
		        	jtxtwaga.setText("");
		        	jtxtdlugosc.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID is blank");
		        }
			}
		});
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(407, 655, 130, 57);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(!jtxtID.getText().isBlank()) {
					
					String query = "DELETE FROM public.pracownicy WHERE ID = " + jtxtID.getText();
					SerwisQueryExecutor.executeQuery(query);
					jtxtID.setText("");
					jtxtnazwa.setText("");
		        	jtxtkategoria.setText("");
		        	jtxtplec.setText("");
		        	jtxtwaga.setText("");
		        	jtxtdlugosc.setText("");
		        	Model.setRowCount(0);
		        	Read();
				}
				else{
					JOptionPane.showMessageDialog(null, "ID is blank");
		        }
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(598, 100, 830, 612);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(Model);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
}

class DbSerwisGUI extends JPanel{

	private JButton btnSamochody;
	private JButton btnStanowiska;
	private JButton btnPracownicy;
	
	public DbSerwisGUI() {
		initialize();

	}
	
	private void initialize() {
		
		setLayout(null);
		
		btnSamochody = new JButton("Samochody");
		btnSamochody.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSamochody.setBounds(100, 50, 300, 100);
		add(btnSamochody);
		btnSamochody.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		btnStanowiska = new JButton("Stanowiska");
		btnStanowiska.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStanowiska.setBounds(100, 200, 300, 100);
		add(btnStanowiska);
		btnStanowiska.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		btnPracownicy = new JButton("Pracownicy");
		btnPracownicy.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPracownicy.setBounds(100, 350, 300, 100);
		add(btnPracownicy);
		btnPracownicy.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}
	
}


public class Lekcja29cz2 {
	
	private JFrame frame;
	private DbSerwisGUI dbserwisgui;

	public static void main(String[] args) {
//3 tabele: pracownicy, stanowiska, samochody

//wymianê: oleju silnikowego wraz z filtrem, filtra kabinowego uk³adu klimatyzacji, filtra paliwa (w samochodach z silnikiem Diesla), a tak¿e wycieraczek przedniej szyby;
//kontrolê: hamulców, zawieszenia, opon, stanu p³ynów eksploatacyjnych i ewentualnych wycieków, œwiec zap³onowych, pasków rozrz¹du;
//konserwacjê: zacisków hamulcowych, zamków i zawiasów;
//regulacjê: hamulca postojowego lub luzów zaworowych.
//		SerwisQueryExecutor.executeQuery("CREATE TABLE pracownicy (ID int, imie varchar(255), nazwisko varchar(255), plec BOOLEAN, wiek int)");
//		SerwisQueryExecutor.executeQuery("ALTER TABLE pracownicy ADD COLUMN IDSamochodu varchar(255)");
//		SerwisQueryExecutor.executeQuery("CREATE TABLE stanowiska (ID int, nazwa varchar(255), IDSamochodu int)");
//		SerwisQueryExecutor.executeQuery("CREATE TABLE samochody (ID int, nazwa varchar(255), wymianaoleju BOOLEAN, wymianafiltru BOOLEAN, wymianawycieraczek BOOLEAN, kontrolahamulców BOOLEAN, kontrolazawieszenia BOOLEAN, kontrolaopon BOOLEAN, kontrolaplynow BOOLEAN, kontrolawyciekow BOOLEAN, kontrolaswiec BOOLEAN, kontrolapaskow BOOLEAN, konserwacjazaciskowhamulocowych BOOLEAN, kontrolazamkow BOOLEAN, kontrolazawiasow BOOLEAN, regulacjahamulcapostojowego BOOLEAN, kontrolaluzowzaworowych BOOLEAN)");
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
					try {
						new Lekcja29cz2().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
	}
	
	public void createAndShowGUI() throws Exception {
		frame = new JFrame();
		frame.setBounds(0, 0, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dbserwisgui = new DbSerwisGUI();
		frame.add(dbserwisgui);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
