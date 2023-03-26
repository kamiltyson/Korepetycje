import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;


class MyTask extends Thread {
	
	private boolean keepThread;
	private String threadName;
	private String str;
	private boolean checkOtherThreads;
	private boolean checkThisThread;
	private MyTask[] threadArray;
	private String fileContent;
	
	
	public MyTask(boolean keepThread, String threadName) {
		this.keepThread = keepThread;
		this.threadName = threadName;
		this.str = null;
		this.checkOtherThreads = true;
		this.checkThisThread = false;
		this.threadArray = null;
	}
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	
	public boolean getKeepThread() {
		return keepThread;
	}

	public void setKeepThread(boolean keepThread) {
		this.keepThread = keepThread;
	}
	
	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}
	
	public boolean getCheckOtherThreads() {
		return checkOtherThreads;
	}

	public void setCheckOtherThreads(boolean checkOtherThreads) {
		this.checkOtherThreads = checkOtherThreads;
	}
	
	public boolean getCheckThisThread() {
		return checkThisThread;
	}

	public void setCheckThisThread(boolean checkThisThread) {
		this.checkThisThread = checkThisThread;
	}
	
	public MyTask[] getThreadArray() {
		return threadArray;
	}

	public void setThreadArray(MyTask[] threadArray) {
		this.threadArray = threadArray;
	}
	
	public String getFileContent() {
		
		String threadName = getThreadName();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("C:\\Users\\Predator\\git\\repository\\Korepetycje\\Lekcja58_" + threadName + ".txt"));
			fileContent = scanner.nextLine();
			scanner.close();
			return fileContent;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public void setFileContent(String fileContent) {
		
		String threadName = getThreadName();
		PrintWriter pw1;
		try {
			pw1 = new PrintWriter(new File("C:\\Users\\Predator\\git\\repository\\Korepetycje\\Lekcja58_" + threadName + ".txt"));
			pw1.print(fileContent);
			pw1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
    public void run() {
    	
    	while(keepThread) {
    		if (checkThisThread) {
    			JOptionPane.showMessageDialog(null, sprawdz(str, checkOtherThreads));
    			checkOtherThreads=false;
    			checkThisThread=false;
    		}
    		try { this.sleep(1000); } catch(InterruptedException ie) {}
    	}
    	
    }
    
    public String sprawdz(String str, boolean checkOtherThreads) {
		
    	String threadName = Thread.currentThread().getName();
    	Scanner scanner;
		String linia1 = getFileContent();
		if (linia1.split(" ")[0].equals(str+":")) {
			return threadName + " " + getFileContent();
		}
		
		else if (threadArray!=null && checkOtherThreads==true) {
			for (MyTask thread : threadArray) {
				if (!thread.getThreadName().equals(thread.currentThread().getName())) {
					
					String linia2 = thread.getFileContent();
					if (linia2.split(" ")[0].equals(str+":")) {
						setFileContent(linia2);
						thread.setFileContent(linia1);
						return threadName + " " + linia2;
					}
				}
			}
		}
    	
		return ("Nie ma takich owoców nazywających się "+str);
		
    }
    

}

class ThreadsInterface extends JPanel{
	
	private JLabel lblQuestion1;
	private JLabel lblQuestion2;
	private JTextField jtxtQuestion1;
	private JTextField jtxtQuestion2;
	private JLabel lblSearch;
	private JFrame nextframe;
	private static Thread[] threads = new Thread[5];
	private static MyTask[] myTask = new MyTask[5];
	private String check1, check2;
	
	public ThreadsInterface() {
		
		setLayout(null);
		
		lblQuestion1 = new JLabel("Podaj Nazwe watku");
		lblQuestion1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuestion1.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion1.setBounds(15, 15, 150, 30);
		lblQuestion1.setForeground(Color.black);
		add(lblQuestion1);
		
		jtxtQuestion1 = new JTextField();
		jtxtQuestion1.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtxtQuestion1.setBounds(23, 40, 150, 30);
		add(jtxtQuestion1);
		jtxtQuestion1.setColumns(10);
		
		lblQuestion2 = new JLabel("Podaj Nazwe owoców");
		lblQuestion2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuestion2.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuestion2.setBounds(8, 70, 180, 30);
		lblQuestion2.setForeground(Color.black);
		add(lblQuestion2);
		
		jtxtQuestion2 = new JTextField();
		jtxtQuestion2.setFont(new Font("Tahoma", Font.BOLD, 14));
		jtxtQuestion2.setBounds(23, 95, 180, 30);
		add(jtxtQuestion2);
		jtxtQuestion2.setColumns(10);
		
		lblSearch = new JLabel("Szukaj");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSearch.setBounds(23, 125, 50, 30);
		lblSearch.setOpaque(false);
		lblSearch.setForeground(Color.black);
		lblSearch.setHorizontalAlignment(JLabel.CENTER);
		add(lblSearch);
		
		myTask[0] = new MyTask(true, "ThreadA");
		myTask[1] = new MyTask(true, "ThreadB");
		myTask[2] = new MyTask(true, "ThreadC");
		myTask[3] = new MyTask(true, "ThreadD");
		myTask[4] = new MyTask(true, "ThreadE");
		
		threads[0] = new Thread(myTask[0], "ThreadA");
		threads[1] = new Thread(myTask[1], "ThreadB");
		threads[2] = new Thread(myTask[2], "ThreadC");
		threads[3] = new Thread(myTask[3], "ThreadD");
		threads[4] = new Thread(myTask[4], "ThreadE");
		
		threads[0].start();
		threads[1].start();
		threads[2].start();
		threads[3].start();
		threads[4].start();
		
		lblSearch.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				check1 = jtxtQuestion1.getText();
				check2 = jtxtQuestion2.getText();
				if (check1.equals("ThreadA")) {
					
					myTask[0].setStr(check2);
					myTask[0].setThreadArray(myTask);
					myTask[0].setCheckOtherThreads(true);
					myTask[0].setCheckThisThread(true);
					try { Thread.sleep(1000); } catch(InterruptedException ie) {}
					
				}
				else if (check1.equals("ThreadB")) {
					
					myTask[1].setStr(check2);
					myTask[1].setThreadArray(myTask);
					myTask[1].setCheckOtherThreads(true);
					myTask[1].setCheckThisThread(true);
					try { Thread.sleep(1000); } catch(InterruptedException ie) {}
					
				}
				else if (check1.equals("ThreadC")) {
					
					myTask[2].setStr(check2);
					myTask[2].setThreadArray(myTask);
					myTask[2].setCheckOtherThreads(true);
					myTask[2].setCheckThisThread(true);
					try { Thread.sleep(1000); } catch(InterruptedException ie) {}
					
				}
				else if (check1.equals("ThreadD")) {
					
					myTask[3].setStr(check2);
					myTask[3].setThreadArray(myTask);
					myTask[3].setCheckOtherThreads(true);
					myTask[3].setCheckThisThread(true);
					try { Thread.sleep(1000); } catch(InterruptedException ie) {}
					
				}
				else if (check1.equals("ThreadE")) {
					
					myTask[4].setStr(check2);
					myTask[4].setThreadArray(myTask);
					myTask[4].setCheckOtherThreads(true);
					myTask[4].setCheckThisThread(true);
					try { Thread.sleep(1000); } catch(InterruptedException ie) {}
					
				}
				else {
					
					JOptionPane.showMessageDialog(null, "Nie ma wątku o nazwie " + check1);
					
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
	
	public void disposeThreads() {
		myTask[0].setKeepThread(false);
		myTask[1].setKeepThread(false);
		myTask[2].setKeepThread(false);
		myTask[3].setKeepThread(false);
		myTask[4].setKeepThread(false);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(300,200);
	}
	
}

public class Lekcja58_59_60_61 {
	
	static Thread[] threads = new Thread[5];
	private ThreadsInterface threadsInterface;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run(){
					try {
						new Lekcja58_59_60_61().createAndShowGUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
	}
		
		public void createAndShowGUI() throws Exception{
			JFrame window = new JFrame("Threads");
			window.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			window.addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                JFrame frame = new JFrame();
	                String[] options = new String[2];
	                options[0] = "Tak";
	                options[1] = "Nie";
	                int choose = JOptionPane.showOptionDialog(frame.getContentPane(), "Czy na pewno chcesz zamknąć aplikację?", "Potwierdź Zamknięcie", 0, JOptionPane.INFORMATION_MESSAGE, null, options, null);
	                if (choose == 0) {
	                	threadsInterface.disposeThreads();
	                    System.exit(0);
	                }
	            }
	        });
			window.setResizable(false);
			threadsInterface = new ThreadsInterface();
			window.add(threadsInterface);
			window.setVisible(true);
			window.pack();
		}
		


}
//Uruchamiamy dowolną ilość wątków, kaady watek ma swoj plik z zasobami w postaci nazwa zasobu: ilosc.
//Kazdy watek ma informacje o kilku innych watkach dzialajacych.
//Klient odpytuje dowolny watek o jego zasob. Jesli watek taka informacje posiada odpowiada nazwa zasobu i jego iloscia
//Jezli dany watek nie posiada informacji o zasobie wowczas odpytuje te watki o ktorych informacje ma o mozliwosc wymiany.
//Jezeli ktorys z watkow ma posidany zasob wowczas nastepuje wymiana (pomiedzy plikami) o zasob ktory watek nie ma informacji.