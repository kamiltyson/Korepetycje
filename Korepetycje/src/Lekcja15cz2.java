import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class LewyPanelGorny extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(185,122,87));
		g.fillRect(0, 0, 400, 100);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 100);
	}

}

class PrawyPanel2 extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(239,228,176));
		g.fillRect(0, 0, 100, 500);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 500);
	}

}

class SrodkowyPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(255,242,0));
		g.fillRect(0, 0, 300, 400);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 400);
	}

}

class LewyPanel2 extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(163,73,164));
		g.fillRect(0, 0, 100, 400);

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(100, 400);
	}

}

class MainPanel2 extends JPanel {

	public MainPanel2() {
		LewyPanelGorny lpg = new LewyPanelGorny();
		setLayout(null);
		lpg.setBounds(0, 0, 400, 100);
		add(lpg);
		
		PrawyPanel2 pp = new PrawyPanel2();
		pp.setBounds(400, 0, 100, 500);
		add(pp);
		
		SrodkowyPanel sp = new SrodkowyPanel();
		sp.setBounds(100, 100, 300, 400);
		add(sp);
		
		LewyPanel2 lp = new LewyPanel2();
		lp.setBounds(0, 100, 100, 400);
		add(lp);
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

public class Lekcja15cz2 {
	
	private JFrame frame;
	private MainPanel2 containerPane;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Lekcja15cz2().createAndShowGui();
			}
		});
	}
	public void createAndShowGui() {
		frame = new JFrame("Jframe");
		containerPane = new MainPanel2();

		frame.add(containerPane);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
	}
}
