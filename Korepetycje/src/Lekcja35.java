import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;

class Osoba35 implements Ruch, Ciezar{
	
	private String imie;
	private int wiek;
	private int wzrost;
	private boolean plec;// true=m�czyzna, false=kobieta
	private String miejscowosc;
	private Point ruch;
	private int ciezar;
	
	public Osoba35(String imie, int wiek, int wzrost, boolean plec, String miejscowosc, Point ruch, int ciezar) {
		this.imie = imie;
		this.wiek = wiek;
		this.wzrost = wzrost;
		this.plec = plec;
		this.miejscowosc = miejscowosc;
		this.ruch = ruch;
		this.ciezar = ciezar;
	}
	
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		this.wiek = wiek;
	}

	public int getWzrost() {
		return wzrost;
	}

	public void setWzrost(int wzrost) {
		this.wzrost = wzrost;
	}

	public boolean getPlec() {
		return plec;
	}

	public void setPlec(boolean plec) {
		this.plec = plec;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public int getCiezar() {
		return ciezar;
	}

	public Point getRuch() {
		return ruch;
	}
}

class Samochod35 implements Ruch, Ciezar{
	
	private String marka;
	private int pojemnosc;
	private int wysokosc;
	private boolean typ;// true=osobowy, false=ciezarowy
	private String model;
	private Point ruch;
	private int ciezar;
	private static ArrayList<Point> ruchy = new ArrayList<Point>();
	private static ArrayList<Integer> ciezary = new ArrayList<Integer>();
	
	
	public Samochod35(String marka, int pojemnoscsc, int wysokosc, boolean typ, String model, Point ruch, int ciezar) {
		super();
		this.marka = marka;
		this.pojemnosc = pojemnosc;
		this.wysokosc= wysokosc;
		this.typ = typ;
		this.model = model;
		ruchy.add(ruch);
		ciezary.add(ciezar);
	}


	public String getMarka() {
		return marka;
	}


	public void setMarka(String marka) {
		this.marka = marka;
	}


	public int getPojemnosc() {
		return pojemnosc;
	}


	public void setPojemnosc(int pojemnosc) {
		this.pojemnosc = pojemnosc;
	}


	public int getWysokosc() {
		return wysokosc;
	}


	public void setWysokosc(int wysokosc) {
		this.wysokosc = wysokosc;
	}


	public boolean isTyp() {
		return typ;
	}


	public void setTyp(boolean typ) {
		this.typ = typ;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getCiezar() {
		sortujCiezar();
		return ciezary.get(ciezary.size()-1);
	}


	public Point getRuch() {
		sortujRuch();
		return ruchy.get(ruchy.size()-1);
	}
	
	public static void sortujCiezar() {
		ciezary.sort(Comparator.naturalOrder());
	}
	
	public static void sortujRuch() {
		for (int i=0; i<ruchy.size();i++) {
			for (int j=i+1; j<ruchy.size();j++) {
				Point temp = new Point();
				if( (Math.pow(ruchy.get(j).getX(), 2) + Math.pow(ruchy.get(j).getY(), 2)) 
						< (Math.pow(ruchy.get(i).getX(), 2) + Math.pow(ruchy.get(i).getY(), 2)) ) {
					temp = ruchy.get(i);
					ruchy.set(i, ruchy.get(j));
					ruchy.set(j, temp);
				}
			}
		}
	}
	
}

class Samolot35 implements Ruch, Ciezar{
	
	private String marka;
	private int pojemnosc;
	private int wysokosc;
	private boolean typ;// true=pasa�erskie, false=towarowe
	private String model;
	private Point ruch;
	private int ciezar;
	private static ArrayList<Point> ruchy = new ArrayList<Point>();
	private static ArrayList<Integer> ciezary = new ArrayList<Integer>();
	
	
	public Samolot35(String marka, int pojemnosc, int wysokosc, boolean typ, String model, Point ruch, int ciezar) {
		super();
		this.marka = marka;
		this.pojemnosc = pojemnosc;
		this.wysokosc = wysokosc;
		this.typ = typ;
		this.model = model;
		ruchy.add(ruch);
		ciezary.add(ciezar);
	}


	public String getMarka() {
		return marka;
	}


	public void setMarka(String marka) {
		this.marka = marka;
	}


	public int getPojemnosc() {
		return pojemnosc;
	}


	public void setPojemnosc(int pojemnosc) {
		this.pojemnosc = pojemnosc;
	}


	public int getWysokosc() {
		return wysokosc;
	}


	public void setWysokosc(int wysokosc) {
		this.wysokosc = wysokosc;
	}


	public boolean isTyp() {
		return typ;
	}


	public void setTyp(boolean typ) {
		this.typ = typ;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	@Override
	public int getCiezar() {
		int sum = 0;
		for (Integer c : ciezary) {
			sum += ciezar;
		}
		return sum/ciezary.size();
	}


	@Override
	public Point getRuch() {
		int x = 0;
		int y = 0;
		for (Point r : ruchy) {
			x += r.getX();
			y += r.getY();
		}
		return new Point(x/ruchy.size(), y/ruchy.size());
	}
	
}

interface Ruch{
	Point getRuch();
	
}

interface Ciezar{
	int getCiezar();
}

public class Lekcja35 {
	
	private static Osoba35[] osoby = new Osoba35[5];
	private static Samochod35[] samochody = new Samochod35[5];
	private static Samolot35[] samoloty = new Samolot35[5];

	public static void main(String[] args) {
		//rozbudowana klasa osoba, samochod, samolot
		//interface ruch
		//interface ciezar
		//zaimplementowa� na r�ne sposoby
		//posortowa� po ci�arze
		
		
		osoby[0] = new Osoba35("Kamil", 0, 0, true, "Katowice", new Point(0,1), 75);
		osoby[1] = new Osoba35("Jacek", 0, 0, true, "Katowice", new Point(2,1), 78);
		osoby[2] = new Osoba35("Adam", 0, 0, true, "Katowice", new Point(3,4), 82);
		osoby[3] = new Osoba35("Marek", 0, 0, true, "Katowice", new Point(4,5), 88);
		osoby[4] = new Osoba35("Kasia", 0, 0, false, "Katowice", new Point(6,7), 64);
		
		samochody[0] = new Samochod35("golf", 0, 0, false, "1", new Point(0,1), 75);
		samochody[1] = new Samochod35("opel", 0, 0, false, "2", new Point(0,1), 75);
		samochody[2] = new Samochod35("tir", 0, 0, false, "3", new Point(0,1), 75);
		samochody[3] = new Samochod35("brava", 0, 0, false, "4", new Point(0,1), 75);
		samochody[4] = new Samochod35("bmw", 0, 0, false, "5", new Point(0,1), 75);
		
	}

}
