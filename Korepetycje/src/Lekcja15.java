import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

class LewyPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 100, 500);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 500);
	}

}

class PrawyPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, 400, 500);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 500);
	}

}

class MainPanel extends JPanel {

	public MainPanel() {
		LewyPanel lp = new LewyPanel();
		setLayout(null);
		lp.setBounds(0, 0, 100, 500);
		add(lp);
		
		PrawyPanel pp = new PrawyPanel();
		pp.setBounds(100, 0, 400, 500);
		add(pp);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

}

public class Lekcja15 {

	private JFrame frame;
	private MainPanel containerPane;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// oddzielne 2 okienka rejestracja, logowanie
		// stworzyæ jframe i jeden jpanel na czerwono
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Lekcja15().createAndShowGui();
			}
		});
	}

	public void createAndShowGui() {
		frame = new JFrame("Jframe");
		containerPane = new MainPanel();

		frame.add(containerPane);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
}
