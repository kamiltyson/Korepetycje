import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class Osoba {

	// pole
	private String imie;
	private int wiek;
	private int wzrost;
	private boolean plec;// true=mê¿czyzna, false=kobieta
	private String miejscowosc;
	public static Osoba[] obywatele;
	public static ArrayList<Integer> deletelist = new ArrayList<Integer>();
	public static int x;
	
	// konstruktor

	public Osoba(String imie, int wiek, int wzrost, boolean plec, String miejscowosc) {
		this.imie = imie;
		this.wiek = wiek;
		this.wzrost = wzrost;
		this.plec = plec;
		this.miejscowosc = miejscowosc;
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


	public static void create() throws Exception{
		x = 0;
		obywatele = new Osoba[10];
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\obywatele.txt"));
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			if (!linia.split(" ")[0].equals("null")) {
				obywatele[x]= new Osoba(linia.split(";")[0],Integer.valueOf(linia.split(" ")[1]),Integer.valueOf(linia.split(" ")[2]),Boolean.valueOf(linia.split(" ")[3]),linia.split(" ")[4]);
				x++;
			}
		}
		x--;
		scanner.close();
		Osoba.read();
	}

	public static void read() throws Exception{
		int obint = 0;
		for (Osoba osoba : obywatele) {
			if (obint<=x) {
				obint++;
				System.out.print(osoba.getImie() + " ");
				System.out.print(osoba.getWiek() + " ");
				System.out.print(osoba.getWzrost() + " ");
				System.out.print(osoba.getPlec() + " ");
				System.out.println(osoba.getMiejscowosc());
			}
		}
		Osoba.update();
	}

	public static void update() throws Exception{
		Scanner scanner = new Scanner(System.in);
		//Update
		System.out.println("Czy chcesz edytowaæ jak¹œ osobê?(T/N)");
		String y = scanner.nextLine();
		
		String check = y;
		while (check.equals("T")) {
			System.out.println("Któr¹ osobê chcesz edytowaæ?(0-"+x+")");
			y = scanner.nextLine();
			System.out.println("Podaj imie");
			String str = scanner.nextLine();
			obywatele[Integer.valueOf(y)].setImie(str);
			System.out.println("Podaj wiek");
			str = scanner.nextLine();
			obywatele[Integer.valueOf(y)].setWiek(Integer.valueOf(str));
			System.out.println("Podaj wzrost");
			str = scanner.nextLine();
			obywatele[Integer.valueOf(y)].setWzrost(Integer.valueOf(str));
			System.out.println("Podaj plec");
			str = scanner.nextLine();
			obywatele[Integer.valueOf(y)].setPlec(Boolean.valueOf(str));
			System.out.println("Podaj miejscowosc");
			str = scanner.nextLine();
			obywatele[Integer.valueOf(y)].setMiejscowosc(str);
			System.out.println("Czy chcesz edytowaæ jeszcze jak¹œ osobê?(T/N)");
			y = scanner.nextLine();
			check = y;
		}
		
		//Add
		if (x!=9) {
			System.out.println("Czy chcesz dodaæ jak¹œ osobê?(T/N)");
			y = scanner.nextLine();
			String check2 = y;
			
			
			while (check2.equals("T")) {
				x++;
				obywatele[x]= new Osoba("",0,0,true,"");
				System.out.println("Podaj imie");
				String str = scanner.nextLine();
				System.out.println(x);
				obywatele[x].setImie(str);
				System.out.println("Podaj wiek");
				str = scanner.nextLine();
				obywatele[x].setWiek(Integer.valueOf(str));
				System.out.println("Podaj wzrost");
				str = scanner.nextLine();
				obywatele[x].setWzrost(Integer.valueOf(str));
				System.out.println("Podaj plec");
				str = scanner.nextLine();
				obywatele[x].setPlec(Boolean.valueOf(str));
				System.out.println("Podaj miejscowosc");
				str = scanner.nextLine();
				obywatele[x].setMiejscowosc(str);
				System.out.println("Czy chcesz dodaæ jeszcze jak¹œ osobê?(T/N)");
				y = scanner.nextLine();
				check2 = y;
			}
		}
		Osoba.delete();
	}


	public static void delete() throws Exception{
		
		System.out.println("Czy chcesz usun¹æ jak¹œ osobê?(T/N)");
		Scanner scanner = new Scanner(System.in);;
		String y = scanner.nextLine();
		String check3 = y;

		while (check3.equals("T")) {
			System.out.println("Któr¹ osobê chcesz usun¹æ?(0-"+x+")");
			y = scanner.nextLine();
			deletelist.add(Integer.valueOf(y));
			System.out.println("Czy chcesz usun¹æ jeszcze jak¹œ osobê?(T/N)");
			y = scanner.nextLine();
			check3 = y;
		}
		
		scanner.close();
		
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\obywatele.txt"));
		
		int z = 0;
		
		for (Osoba osoba : obywatele) {
			if(!deletelist.contains(z) && z<=x){
				pw.print(osoba.getImie() + " ");
				pw.print(osoba.getWiek() + " ");
				pw.print(osoba.getWzrost() + " ");
				pw.print(osoba.getPlec() + " ");
				pw.print(osoba.getMiejscowosc() + "\n");
			}
			else if(deletelist.contains(z)){
				pw.print("null" + "\n");
			}
			z++;
		}
		
		pw.close();
	}
}

public class Lekcja8 {

	public static void main(String[] args) throws Exception {
		// Konstruktor
		// stworzyæ klasê np osoba 5 ró¿nych pól imie, wiek, wzrost, waga, miejscowoœæ
		// Tworzymy tablice 10 elementow¹ z ró¿nymi informacjami. Zapisujemy do pliku. Dajemy mo¿liwoœæ odczytu z pliku.
		
		Osoba[] obywatele = new Osoba[10];
		
//		obywatele[0] = new Osoba("Kamil",26,180,true,"Katowice");
//		obywatele[1] = new Osoba("Maciek",35,170,true,"Warszawa");
//		obywatele[2] = new Osoba("Monika",27,150,false,"£ódz");
//		obywatele[3] = new Osoba("Karolina",29,154,false,"Kraków");
//		obywatele[4] = new Osoba("Tomek",35,200,true,"Gdañsk");
//		obywatele[5] = new Osoba("Piotr",26,155,true,"Warszawa");
//		obywatele[6] = new Osoba("Jaros³aw",45,165,true,"Warszawa");
//		obywatele[7] = new Osoba("Grzegorz",37,195,true,"Czêstochowa");
//		obywatele[8] = new Osoba("Aneta",31,160,false,"Zakopane");
//		obywatele[9] = new Osoba("Alicja",41,167,false,"Bieszczady");
		
//		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\obywatele.txt"));
//		
//		for (Osoba osoba : obywatele) {
//			pw.print(osoba.getImie() + " ");
//			pw.print(osoba.getWiek() + " ");
//			pw.print(osoba.getWzrost() + " ");
//			pw.print(osoba.getPlec() + " ");
//			pw.print(osoba.getMiejscowosc() + "\n");
//		}
//		
//		pw.close();
		
		//Zczytaj z pliku txt i zapisz do klasy
		//CRUD
		
//		int i = 0;
//		
//		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\obywatele.txt"));
//		while(scanner.hasNextLine()) {
//			String linia = scanner.nextLine();
//			obywatele[i]= new Osoba(linia.split(";")[0],Integer.valueOf(linia.split(" ")[1]),Integer.valueOf(linia.split(" ")[2]),Boolean.valueOf(linia.split(" ")[3]),linia.split(" ")[4]);
//			i++;
//		}
//		scanner.close();
//		
//		for (Osoba osoba : obywatele) {
//			System.out.print(osoba.getImie() + " ");
//			System.out.print(osoba.getWiek() + " ");
//			System.out.print(osoba.getWzrost() + " ");
//			System.out.print(osoba.getPlec() + " ");
//			System.out.println(osoba.getMiejscowosc());
//		}
		
		Osoba.create();
		
//		static, scanner
	}

}
