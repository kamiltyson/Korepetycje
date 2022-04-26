import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Osoba1 {

	// pole
	private String imie;
	private int wiek;
	private boolean plec;// true=mê¿czyzna, false=kobieta
	
	// konstruktor

	public Osoba1(String imie, int wiek, boolean plec) {
		this.imie = imie;
		this.wiek = wiek;
		this.plec = plec;
	}

	// metoda

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

	public boolean getPlec() {
		return plec;
	}

	public void setPlec(boolean plec) {
		this.plec = plec;
	}
	
}

class Samochod {

	// pole
	private String marka;
	private int pojemnosc;
	private boolean rodzaj;//true - osobowy, false - cie¿arowy
	
	// konstruktor

	public Samochod(String marka, int pojemnosc,boolean rodzaj) {
		this.marka = marka;
		this.pojemnosc = pojemnosc;
		this.rodzaj = rodzaj;
	}

	// metoda

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
	
	public boolean getRodzaj() {
		return rodzaj;
	}

	public void setRodzaj(boolean rodzaj) {
		this.rodzaj = rodzaj;
	}
	
}

class Osoba1zSamochod{
	
	// pole
	private Osoba1 osoba;
	private ArrayList<Samochod> list;
	
	// konstruktor

	public Osoba1zSamochod(Osoba1 osoba,ArrayList<Samochod> list) {
		this.osoba = osoba;
		this.list = list;
	}
	
	public String getOsoba() {
		String str = osoba.getImie() + " " + String.valueOf(osoba.getWiek()) + " " + String.valueOf(osoba.getPlec());
		return str;
	}

	public void setOsoba(Osoba1 osoba) {
		this.osoba = osoba;
	}
	
	public String getList() {
		String str="";
		for (Samochod e : list) {
			str = str + e.getMarka() + " " + String.valueOf(e.getPojemnosc()) + " " + String.valueOf(e.getRodzaj()) + "\n";
		}
		return str;
	}

	public void setList(ArrayList<Samochod> list) {
		this.list = list;
	}
}

public class Lekcja10 {

	public static void main(String[] args) throws Exception {
		// œroda 17:00
		// 5 osób z pliku odczytane
		// 10 samochodów
		Osoba1[] osoby = new Osoba1[5];
		Samochod[] samochody = new Samochod[10];
		
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\osoby1.txt"));
		
		int x = 0;
		
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			if (!linia.split(" ")[0].equals("null")) {
				osoby[x]= new Osoba1(linia.split(" ")[0],Integer.valueOf(linia.split(" ")[1]),Boolean.valueOf(linia.split(" ")[2]));
				x++;
			}
		}
		
		x--;
		
		scanner.close();
		
		System.out.println("Osoby");
		System.out.println();
		
		for (Osoba1 osoba : osoby) {
			System.out.print(osoba.getImie() + " ");
			System.out.print(osoba.getWiek() + " ");
			System.out.println(osoba.getPlec());
		}
		
		scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\samochody.txt"));
		
		x = 0;
		
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			if (!linia.split(" ")[0].equals("null")) {
				samochody[x]= new Samochod(linia.split(" ")[0],Integer.valueOf(linia.split(" ")[1]),Boolean.valueOf(linia.split(" ")[2]));
				x++;
			}
		}
		
		x--;
		
		scanner.close();
		
		System.out.println();
		System.out.println("Samochody");
		System.out.println();
		
		for (Samochod samochod : samochody) {
			System.out.print(samochod.getMarka() + " ");
			System.out.print(samochod.getPojemnosc() + " ");
			System.out.println(samochod.getRodzaj());
		}
		
		System.out.println();
		
		Scanner scanner2 = new Scanner(System.in);
		
		
		
		Osoba1zSamochod[] osobyzsamochodami = new Osoba1zSamochod[5];
		int z = 0;
		for (Osoba1 osoba : osoby) {
			ArrayList<Samochod> list=new ArrayList<Samochod>();
			String check = "T";
			while (check.equals("T")){
				System.out.println(osoba.getImie() + " który masz samochód?(0-9): ");
				String y = scanner2.nextLine();
				list.add(samochody[Integer.valueOf(y)]);    
				System.out.println("Czy masz jeszcze jakiœ samochód?(T/N)");
				y = scanner2.nextLine();
				check = y;
			}
			osobyzsamochodami[z]=new Osoba1zSamochod(osoba, list);
			z++;
		}
		
		scanner2.close();
		
//		for (Osoba1zSamochod osobazsamochodami : osobyzsamochodami) {			
//			System.out.println(osobazsamochodami.getOsoba());
//			System.out.println(osobazsamochodami.getList());
//		}
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\osobyzsamochodami.txt"));
		
		for (Osoba1zSamochod osobazsamochodami : osobyzsamochodami) {
			pw.print(osobazsamochodami.getOsoba() + "\n");
			pw.print(osobazsamochodami.getList() + "\n");
		}
		
		pw.close();
		
//		osoby[0] = new Osoba1("Marek",20,true);
//		osoby[1] = new Osoba1("Darek",23,true);
//		osoby[2] = new Osoba1("Arek",15,true);
//		osoby[3] = new Osoba1("Aneta",27,false);
//		osoby[4] = new Osoba1("Kasia",35,false);
//		
//		samochody[0] = new Samochod("Pasat",10,true);
//		samochody[1] = new Samochod("Golf",15,true);
//		samochody[2] = new Samochod("Tesla",25,false);
//		samochody[3] = new Samochod("Volkswagen",17,false);
//		samochody[4] = new Samochod("Mercedes-Benz",34,false);
//		samochody[5] = new Samochod("Ford",25,false);
//		samochody[6] = new Samochod("Skoda",19,false);
//		samochody[7] = new Samochod("Peugot",13,true);
//		samochody[8] = new Samochod("Toyota",11,true);
//		samochody[9] = new Samochod("Lexus",14,true);
//		
//		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\osoby1.txt"));
//		
//		for (Osoba1 osoba : osoby) {
//			pw.print(osoba.getImie() + " ");
//			pw.print(osoba.getWiek() + " ");
//			pw.print(osoba.getPlec() + "\n");
//		}
//		
//		pw.close();
//		
//		pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\samochody.txt"));
//		
//		for (Samochod samochod : samochody) {
//			pw.print(samochod.getMarka() + " ");
//			pw.print(samochod.getPojemnosc() + " ");
//			pw.print(samochod.getRodzaj() + "\n");
//		}
//		
//		pw.close();
	}

}
