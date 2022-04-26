import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


class DbGUI2 extends JPanel{
	
	private static String IDWlasciciel;
	private static String imieWlasciciel;
	private static String miastoWlasciciel;
	private static boolean plecWlasciciel;
	private static int wiekWlasciciel;
	private static String ID;
	private static String nazwa;
	private static String kategoria;
	private static boolean plec;
	private static double waga;
	private static int dlugosc;
	private JLabel lblJoin;
	private JButton btnJoin;
	private JScrollPane scrollPaneWlasciciel;
	private JScrollPane scrollPaneZwierze;
	private JScrollPane scrollPane3;
	private static JTable tableWlasciciel;
	private JTable tableZwierze;
	private JTable table3;
	private static DefaultTableModel ModelWlasciciel = new DefaultTableModel();
	private static DefaultTableModel ModelZwierze = new DefaultTableModel();
	private static DefaultTableModel Model3 = new DefaultTableModel();
	
	public DbGUI2() {
		initialize();
		Object colWlasciciel[] = {"ID", "imie", "miasto", "plec", "wiek"};
		ModelWlasciciel.setColumnIdentifiers(colWlasciciel);
		ReadWlasciciel();
		Object colZwierze[] = {"ID", "nazwa", "kategoria", "plec", "waga", "dlugosc"};
		ModelZwierze.setColumnIdentifiers(colZwierze);
		ReadZwierze();
		Object col3[] = {"IDWlasciciel", "imieWlasciciel", "IDZwierze", "nazwaZwierze",};
		Model3.setColumnIdentifiers(col3);
		Read3();
	}
	
	private static void ReadWlasciciel() {
		try {
			ResultSet result = QueryExecutor.executeSelect("SELECT * From public.wlasciciel");
			Object[] columnData = new Object[5];
			while(result.next()){
				IDWlasciciel = result.getString("ID");
				imieWlasciciel = result.getString("imie");
				miastoWlasciciel = result.getString("miasto");
				plecWlasciciel = result.getBoolean("plec");
				wiekWlasciciel = result.getInt("wiek");
				columnData[0] = IDWlasciciel;
				columnData[1] = imieWlasciciel;
				columnData[2] = miastoWlasciciel;
				columnData[3] = plecWlasciciel;
				columnData[4] = wiekWlasciciel;
				
				ModelWlasciciel.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void ReadZwierze() {
		try {
			ResultSet result = QueryExecutor.executeSelect("SELECT * From public.zwierze");
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
				
				ModelZwierze.addRow(columnData);
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void Read3() {
		try {
			ResultSet result = QueryExecutor.executeSelect("SELECT * From public.zwierze");
			Object[] columnData = new Object[4];
			while(result.next()){
				ID = result.getString("ID");
				nazwa = result.getString("nazwa");
				IDWlasciciel = result.getString("IDWlasciciel");
				
				if (IDWlasciciel != null) {
					
					String linia = IDWlasciciel;
					for (int j = 0;  j < linia.split(",").length;j++) {
						for (int i = 0; i < tableWlasciciel.getRowCount(); i++) {
							if (linia.split(",")[j].equals(tableWlasciciel.getValueAt(i, 0))) {
								columnData[0] = linia.split(",")[j];
								columnData[1] = tableWlasciciel.getValueAt(i, 1);
								columnData[2] = ID;
								columnData[3] = nazwa;
								Model3.addRow(columnData);
							}
						 }
					}
					
				}
				
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void initialize() {
		
		setLayout(null);
		
		scrollPaneWlasciciel = new JScrollPane();
		scrollPaneWlasciciel.setBounds(98, 100, 430, 612);
		add(scrollPaneWlasciciel);
		
		tableWlasciciel = new JTable();
		tableWlasciciel.setModel(ModelWlasciciel);
		tableWlasciciel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneWlasciciel.setViewportView(tableWlasciciel);
		
		scrollPaneZwierze = new JScrollPane();
		scrollPaneZwierze.setBounds(898, 100, 430, 612);
		add(scrollPaneZwierze);
		
		lblJoin = new JLabel("Join");
		lblJoin.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblJoin.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoin.setBounds(548, 100, 330, 40);
		this.add(lblJoin);
		
		btnJoin = new JButton("Join");
		btnJoin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnJoin.setBounds(548, 200, 330, 40);
		add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (!tableZwierze.getSelectionModel().isSelectionEmpty() && !tableWlasciciel.getSelectionModel().isSelectionEmpty()) {
					ID = tableZwierze.getModel().getValueAt(tableZwierze.getSelectedRow(), 0).toString();
					String Wlasciciele = "";
					int x = 0;
					for (int i = 0; i < tableWlasciciel.getRowCount(); i++) {
						if (tableWlasciciel.isRowSelected(i)) {
							if (x!=0) {
								Wlasciciele += "," + tableWlasciciel.getValueAt(i, 0);
							}
							else{
								Wlasciciele += tableWlasciciel.getValueAt(i, 0);
							}
							x++;
						}
					 }
					IDWlasciciel = tableWlasciciel.getModel().getValueAt(tableWlasciciel.getSelectedRow(), 0).toString();
					String query = "UPDATE public.zwierze"
							+ " SET"
							+ " IDWlasciciel = " + "'" + Wlasciciele + "'"
							+ " WHERE ID = " + ID;
					QueryExecutor.executeQuery(query);
		        	Model3.setRowCount(0);
					Read3();
				}
				else {
					JOptionPane.showMessageDialog(null, "One of rows of table is not selected");
				}
			}
		});
		
		tableZwierze = new JTable();
		tableZwierze.setModel(ModelZwierze);
		tableZwierze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneZwierze.setViewportView(tableZwierze);
		
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(548, 300, 330, 462);
		add(scrollPane3);
		
		table3 = new JTable();
		table3.setModel(Model3);
		table3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane3.setViewportView(table3);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(1500,800);
	}
	
}

public class Lekcja28 {

	private JFrame frame;
	private DbGUI2 dbgui;
	
	public static void main(String[] args) {
		//Relacja  miêdzy obiektami Zwierze-w³aœciciel
		//Wielu opiekunów
//		QueryExecutor.executeQuery("CREATE TABLE wlasciciel (ID int, imie varchar(255), miasto varchar(255), plec BOOLEAN, wiek int)");
//		QueryExecutor.executeQuery("INSERT INTO wlasciciel (ID, imie , miasto, plec, wiek) VALUES ('1', 'Kamil', 'Katowice', 'true', '26')");
//		QueryExecutor.executeQuery("INSERT INTO wlasciciel (ID, imie , miasto, plec, wiek) VALUES ('2', 'Patryk', 'Kraków', 'true', '28')");
//		QueryExecutor.executeQuery("INSERT INTO wlasciciel (ID, imie , miasto, plec, wiek) VALUES ('3', 'Patrycja', 'Poznañ', 'false', '23')");
//		QueryExecutor.executeQuery("INSERT INTO wlasciciel (ID, imie , miasto, plec, wiek) VALUES ('4', 'Kasia', 'Wroc³aw', 'false', '29')");
//		QueryExecutor.executeQuery("INSERT INTO wlasciciel (ID, imie , miasto, plec, wiek) VALUES ('5', 'Piotr', 'Warszawa', 'true', '24')");
//		QueryExecutor.executeQuery("ALTER TABLE zwierze ADD COLUMN IDWlasciciel int");
//		Usuniecie kolumny typ int i zastapienie Stringiem
//		QueryExecutor.executeQuery("ALTER TABLE zwierze DROP COLUMN IDWlasciciel");
//		QueryExecutor.executeQuery("ALTER TABLE zwierze ADD COLUMN IDWlasciciel varchar(255)");
//		Pojedyñczo w³aœciciel - zwierze
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
					try {
						new Lekcja28().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}

	public void createAndShowGUI() throws Exception {
		frame = new JFrame();
		frame.setBounds(0, 0, 1500, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dbgui = new DbGUI2();
		frame.add(dbgui);
		frame.setVisible(true);
		frame.pack();
		
	}
	
}
