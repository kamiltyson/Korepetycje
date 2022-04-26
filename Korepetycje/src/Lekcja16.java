import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class GeneratorHasla {
	//kod ASCII
	private static String MALE;
	private static String DUZE;
	private static String CYFRY;
	private static String ZNAKI_SPECJALNE = "!@#$%^&*()_+-=[]|,./><";
	private boolean uzyjMalych;
	private boolean uzyjDuzych;
	private boolean uzyjCyfry;
	private boolean uzyjZnakiSpecjalne;
	
	public GeneratorHasla(boolean uzyjMalych, boolean uzyjDuzych, boolean uzyjCyfry, boolean uzyjZnakiSpecjalne) {
		this.uzyjMalych = uzyjMalych;
		this.uzyjDuzych = uzyjDuzych;
		this.uzyjCyfry = uzyjCyfry;
		this.uzyjZnakiSpecjalne = uzyjZnakiSpecjalne;
	}
	
	public String generuj(int dlugosc) {
		
		StringBuilder haslo = new StringBuilder(dlugosc);
		Random random = new Random();
		
		List<String> KategorieZnakow = new ArrayList<>(4);
		if (uzyjMalych) {
			for(int i = 97; i <= 122; i++){
				MALE+=Character.toString((char)i);
			}
			KategorieZnakow.add(MALE);
		}
		if (uzyjDuzych) {
			for(int i = 65; i <= 90; i++){
				DUZE+=Character.toString((char)i);
			}
			KategorieZnakow.add(DUZE);
		}
		if (uzyjCyfry) {
			for(int i = 48; i <= 57; i++){
				CYFRY+=Character.toString((char)i);
			}
			KategorieZnakow.add(CYFRY);
		}
		if (uzyjZnakiSpecjalne) {
			for(int i = 33; i <= 47; i++){
				ZNAKI_SPECJALNE+=Character.toString((char)i);
			}
			for(int i = 58; i <= 64; i++){
				ZNAKI_SPECJALNE+=Character.toString((char)i);
			}
			for(int i = 91; i <= 96; i++){
				ZNAKI_SPECJALNE+=Character.toString((char)i);
			}
			for(int i = 123; i <= 126; i++){
				ZNAKI_SPECJALNE+=Character.toString((char)i);
			}
			KategorieZnakow.add(ZNAKI_SPECJALNE);
		}
		for (int i = 0; i<dlugosc; i++){
			String KategoriaZnakow = KategorieZnakow.get(random.nextInt(KategorieZnakow.size()));
			int pozycja = random.nextInt(KategoriaZnakow.length());
			haslo.append(KategoriaZnakow.charAt(pozycja));
		}
		return new String (haslo);
	}

}

class MainPanel4 extends JPanel implements ActionListener{
	
	private JButton buttonStart;
	
	public MainPanel4() {
		
		LewyDolnyPanel ldp = new LewyDolnyPanel();
		setLayout(null);
		ldp.setBounds(50, 300, 200, 100);
		add(ldp);
		
		PrawyDolnyPanel pdp = new PrawyDolnyPanel();
		pdp.setBounds(295, 250, 105, 200);
		add(pdp);
		
		buttonStart = new JButton("Start");
		buttonStart.setBounds(400, 400, 100, 50);
		buttonStart.setActionCommand("Start");
		buttonStart.addActionListener(this);
		add(buttonStart);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Start".equals(e.getActionCommand())) {
			if (PrawyDolnyPanel.getFirstButton() ||
					PrawyDolnyPanel.getSecondButton() ||
					PrawyDolnyPanel.getThirdButton() ||
					PrawyDolnyPanel.getFourthButton()) {
				GornyPanel2 gp = new GornyPanel2();
				gp.setBounds(100, 55, 205, 100);
				add(gp);
			}
		}
	}
	
}

class GornyPanel2 extends JPanel{
	
	private JTextField tf;
	GeneratorHasla gh;
	
	public GornyPanel2() {
		gh = new GeneratorHasla(PrawyDolnyPanel.getFirstButton(), PrawyDolnyPanel.getSecondButton(), PrawyDolnyPanel.getThirdButton(), PrawyDolnyPanel.getFourthButton());
		tf = new JTextField(gh.generuj(LewyDolnyPanel.getLength()));
		tf.setBounds(100, 55, 200, 50);
		add(tf);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(205,100);
	}
}

class LewyDolnyPanel extends JPanel implements ChangeListener{
	
	static final int LEN_MIN = 8;
    static final int LEN_MAX = 16;
    static final int LEN_INIT  = LEN_MIN;
    public static int length = LEN_MIN;
	
	public LewyDolnyPanel() {
		
		JSlider dlugoscHasla = new JSlider (JSlider.HORIZONTAL, LEN_MIN, LEN_MAX, LEN_INIT);
		dlugoscHasla.addChangeListener(this);
		dlugoscHasla.setMajorTickSpacing(1);
		dlugoscHasla.setPaintTicks(true);
		
		Hashtable labelTable = new Hashtable();
		labelTable.put( (Integer)( LEN_MIN ), new JLabel("8") );
		labelTable.put( (Integer)( LEN_MAX ), new JLabel("16") );
		dlugoscHasla.setLabelTable(labelTable);
		
		dlugoscHasla.setPaintLabels(true);
		add(dlugoscHasla);
		
	}
	
	public void stateChanged(ChangeEvent e) {
		length = (int)(((JSlider) e.getSource()).getValue());
	}
	
	static int getLength() {
		return length;
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200,100);
	}
	
}

class PrawyDolnyPanel extends JPanel{
	
	public static JRadioButton FirstButton;
	public static JRadioButton SecondButton;
	public static JRadioButton ThirdButton;
	public static JRadioButton FourthButton;
	
	public PrawyDolnyPanel() {
		
		FirstButton = new JRadioButton("ma³e litery");
		FirstButton.setBounds(0, 0, 105, 50);
		
		SecondButton = new JRadioButton("du¿e litery");
		SecondButton.setBounds(0, 50, 105, 50);
		
		ThirdButton = new JRadioButton("cyfry");
		ThirdButton.setBounds(0, 100, 105, 50);
		
		FourthButton = new JRadioButton("znaki specjalne");
		FourthButton.setBounds(0, 150, 105, 50);
		
		Box box1 = Box.createVerticalBox();
		box1.add(FirstButton);
		box1.add(SecondButton);
		box1.add(ThirdButton);
		box1.add(FourthButton);
		
		add(box1);
	}
	
	static boolean getFirstButton() {
		return FirstButton.isSelected();
	}
	
	static boolean getSecondButton() {
		return SecondButton.isSelected();
	}
	
	static boolean getThirdButton() {
		return ThirdButton.isSelected();
	}
	
	static boolean getFourthButton() {
		return FourthButton.isSelected();
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(105,200);
	}
}

public class Lekcja16 {

	private JFrame frame;
	private MainPanel4 containerPanel;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Lekcja16().createAndShowGUI();
			}
		});
		
	}

	public void createAndShowGUI() {
		frame = new JFrame("jframe");
		containerPanel = new MainPanel4();
		
		frame.add(containerPanel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
	}
	
}

//radio button
//jslider 8 - 16
//opcje wyboru cyfry, ma³e litery, du¿e litery, znaki specjalne