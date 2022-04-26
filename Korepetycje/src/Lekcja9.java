import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Zwierze{
	// pole
		private String nazwa;
		private String kategoria;
		private boolean plec;
		private double waga;
		private int dlugosc;
		
		// konstruktor

		public Zwierze(String nazwa, String kategoria, boolean plec, double waga, int dlugosc) {
			this.nazwa = nazwa;
			this.kategoria = kategoria;
			this.plec = plec;
			this.waga = waga;
			this.dlugosc = dlugosc;
		}
		
		// metoda
		
		public String getNazwa() {
			return nazwa;
		}

		public void setNazwa(String nazwa) {
			this.nazwa = nazwa;
		}
		
		public String getKategoria() {
			return kategoria;
		}

		public void setKategoria(String kategoria) {
			this.kategoria = kategoria;
		}
		
		public boolean getPlec() {
			return plec;
		}

		public void setPlec(boolean plec) {
			this.plec = plec;
		}
		
		public double getWaga() {
			return waga;
		}

		public void setWaga(double waga) {
			this.waga = waga;
		}
		
		public int getDlugosc() {
			return dlugosc;
		}

		public void setDlugosc(int dlugosc) {
			this.dlugosc = dlugosc;
		}
		
}

public class Lekcja9 {

	public static void main(String[] args) throws Exception {
		//jpanel, jframe,paintComponent, Graphics
		//Stworz klase zwierz z 4 ró¿nymi typami zmiennych string, boolean, double, integer
		//Posortuj zwierzêta po nazwach. W przypadku tej samej nazwy po wadze. Nastêpnie wska¿ najl¿ejsze zwierz¹tko.
		//Której kategorii jest wiêcej. Ssaków czy ryb.
		//Œrednia d³ugoœæ zwiêrzêcia to.
		
		Zwierze[] zwierzeta = new Zwierze[5];
		
		//odczytujesz je z pliku i wstawiasz do tablicy
		
		//Create
		
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\zwierzeta.txt"));
		
		int x = 0;
		
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			if (!linia.split(" ")[0].equals("null")) {
				zwierzeta[x]= new Zwierze(linia.split(" ")[0],linia.split(" ")[1],Boolean.valueOf(linia.split(" ")[2]),Double.valueOf(linia.split(" ")[3]),Integer.valueOf(linia.split(" ")[4]));
				x++;
			}
		}
		
		x--;
		
		scanner.close();
		
		Scanner scanner2 = new Scanner(System.in);
		
		int zwint = 0;
		
		for (Zwierze zwierze : zwierzeta) {
			if (zwint<=x) {
				zwint++;
				System.out.print(zwierze.getNazwa() + " ");
				System.out.print(zwierze.getKategoria() + " ");
				System.out.print(zwierze.getPlec() + " ");
				System.out.print(zwierze.getWaga() + " ");
				System.out.println(zwierze.getDlugosc());
			}
		}
		
		//Update
		System.out.println("Czy chcesz edytowaæ jakieœ zwierzê?(T/N)");
		String y = scanner2.nextLine();
		
		String check = y;
		while (check.equals("T")) {
			System.out.println("Które zwierzê chcesz edytowaæ?(0-"+x+")");
			y = scanner2.nextLine();
			System.out.println("Podaj nazwa");
			String str = scanner2.nextLine();
			zwierzeta[Integer.valueOf(y)].setNazwa(str);
			System.out.println("Podaj kategoria");
			str = scanner2.nextLine();
			zwierzeta[Integer.valueOf(y)].setKategoria(str);
			System.out.println("Podaj plec");
			str = scanner2.nextLine();
			zwierzeta[Integer.valueOf(y)].setPlec(Boolean.valueOf(str));
			System.out.println("Podaj waga");
			str = scanner2.nextLine();
			zwierzeta[Integer.valueOf(y)].setWaga(Double.valueOf(str));
			System.out.println("Podaj dlugosc");
			str = scanner2.nextLine();
			zwierzeta[Integer.valueOf(y)].setDlugosc(Integer.valueOf(str));
			System.out.println("Czy chcesz edytowaæ jeszcze jakieœ zwierzê?(T/N)");
			y = scanner2.nextLine();
			check = y;
		}
		
		//Add
		if (x!=4) {
			System.out.println("Czy chcesz dodaæ jakieœ zwierzê?(T/N)");
			y = scanner2.nextLine();
			String check2 = y;
			
			
			while (check2.equals("T")) {
				x++;
				zwierzeta[x]= new Zwierze("","",true,0,0);
				System.out.println("Podaj nazwa");
				String str = scanner2.nextLine();
				System.out.println(x);
				zwierzeta[x].setNazwa(str);
				System.out.println("Podaj kategoria");
				str = scanner2.nextLine();
				zwierzeta[x].setKategoria(str);
				System.out.println("Podaj plec");
				str = scanner2.nextLine();
				zwierzeta[x].setPlec(Boolean.valueOf(str));
				System.out.println("Podaj waga");
				str = scanner2.nextLine();
				zwierzeta[x].setWaga(Double.valueOf(str));
				System.out.println("Podaj dlugosc");
				str = scanner2.nextLine();
				zwierzeta[x].setDlugosc(Integer.valueOf(str));
				System.out.println("Czy chcesz dodaæ jeszcze jakieœ zwierzê?(T/N)");
				y = scanner2.nextLine();
				check2 = y;
			}
		}
		//Delete
		
		System.out.println();
		
		zwint = 0;
		
		for (Zwierze zwierze : zwierzeta) {
			if (zwint<=x) {
				zwint++;
				System.out.print(zwierze.getNazwa() + " ");
				System.out.print(zwierze.getKategoria() + " ");
				System.out.print(zwierze.getPlec() + " ");
				System.out.print(zwierze.getWaga() + " ");
				System.out.println(zwierze.getDlugosc());
			}
		}
		
		ArrayList<Integer> deletelist = new ArrayList<Integer>();
		System.out.println("Czy chcesz usun¹æ jakieœ zwierzê?(T/N)");
		y = scanner2.nextLine();
		String check3 = y;

		while (check3.equals("T")) {
			System.out.println("Które zwierzê chcesz usun¹æ?(0-"+x+")");
			y = scanner2.nextLine();
			deletelist.add(Integer.valueOf(y));
			System.out.println("Czy chcesz usun¹æ jeszcze jakieœ zwierzê?(T/N)");
			y = scanner2.nextLine();
			check3 = y;
		}
		
		scanner2.close();
		
//		boolean czyBylaZamiana = true;
//		
//		while (czyBylaZamiana) {
//			czyBylaZamiana = false;
//			for(int i = 0; i < zwierzeta.length - 1; i++) {
//				if(zwierzeta[i].getNazwa().compareTo(zwierzeta[i+1].getNazwa())>0) {
//					Zwierze r = zwierzeta[i];
//					zwierzeta[i] = zwierzeta[i+1];
//					zwierzeta[i+1] =  r;
//					czyBylaZamiana = true;
//				}
//				if(zwierzeta[i].getNazwa().equals(zwierzeta[i+1].getNazwa())) {
//					if(zwierzeta[i].getWaga() > zwierzeta[i+1].getWaga()) {
//						Zwierze r = zwierzeta[i];
//						zwierzeta[i] = zwierzeta[i+1];
//						zwierzeta[i+1] =  r;
//						czyBylaZamiana = true;
//					}
//				}
//			}
//		}
//		
//		for (Zwierze zwierze : zwierzeta) {
//			System.out.println(zwierze.getNazwa());
//			System.out.println(zwierze.getWaga());
//		}
//		
//		int liczbassakow = 0;
//		int liczbaryb = 0;
//		
//		for (Zwierze zwierze : zwierzeta) {
//			if(zwierze.getKategoria().equals("ssak")) {
//				liczbassakow++;
//			}
//			else if(zwierze.getKategoria().equals("ryba")) {
//				liczbaryb++;
//			}
//		}
//		
//		if (liczbassakow>liczbaryb) {
//			System.out.println("Wiêcej jest ssaków: "+liczbassakow);
//		}
//		else if (liczbassakow<liczbaryb) {
//			System.out.println("Wiêcej jest ryb: "+liczbaryb);
//		}
//		
//		double sredniadlugosc = 0;
//		
//		for (Zwierze zwierze : zwierzeta) {
//			sredniadlugosc+=zwierze.getDlugosc();
//		}
//		
//		sredniadlugosc=sredniadlugosc/zwierzeta.length;
//		
//		System.out.println("Œrednia d³ugoœæ zwierzêcia to "+sredniadlugosc);
		
//		Mechanizm zapisu do pliku
		
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\zwierzeta.txt"));
		
		int z = 0;
		
		for (Zwierze zwierze : zwierzeta) {
			if(!deletelist.contains(z) && z<=x){
				pw.print(zwierze.getNazwa() + " ");
				pw.print(zwierze.getKategoria() + " ");
				pw.print(zwierze.getPlec() + " ");
				pw.print(zwierze.getWaga() + " ");
				pw.print(zwierze.getDlugosc() + "\n");
			}
			else if(deletelist.contains(z)){
				pw.print("null" + "\n");
			}
			z++;
		}
		
		pw.close();
		
		
	}
	
}
