import java.util.Random;

public class Lekcja3 {

	public static void main(String[] args) {
//		1 wymiarowa tablica  losowanie i zapêtlenie. Ile trzeba zagraæ ¿eby wygraæ.
//		Sortowanie np. b¹belkowe.
//		wylosuj tablicê 10 liczb z zakresu od 0 do 40 i od -40 do -20. Nastêpnie posortuj tablicê.
		final Random random = new Random();
//		int tab [] = new int[10];
//		int x;
//
//		for(int i = 0; i < 10; i++)  {
//			x = random.nextInt(2);
//			if (x==0)
//		    	tab [i]=(random.nextInt(41));
//			else
//		    	tab [i]=(-random.nextInt(21)-20);
//	    }
//		boolean czyBylaZamiana = true;
//
//		while (czyBylaZamiana) {
//			czyBylaZamiana = false;
//			for(int i = 0; i < tab.length - 1; i++) {
//				if(tab[i] < tab[i+1]) {
//					x = tab[i];
//					tab[i] = tab[i+1];
//					tab[i+1] = x;
//					czyBylaZamiana = true;
//				}
//			}
//		}
//		for(int i = 0; i < 10; i++)  {
//	    	System.out.println(tab [i]);
//	    }
//		Tworzymy tablice 10x10 od 0,1,2. Wyœwietlamy w formie kwadratu.
		int tab2 [][] = new int[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				tab2[i][j] = random.nextInt(3);
				System.out.print(tab2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
//		Wskazaæ wiersz z najwiêksz¹ sum¹ i kolumnê z najwiêksz¹ sum¹.
//		System.out.println("wiersz z najwiêksz¹ sum¹ ma nr "+wierszznajsum(tab2));
//		System.out.println("kolumna z najwiêksz¹ sum¹ ma nr "+kolumnaznajsum(tab2));
//		Posortowaæ ka¿dy wiersz.
		int x;
		boolean czyBylaZamiana;
		for(int i = 0; i < 10; i++)  {
			for(int j = 0; j < 10; j++) {
				czyBylaZamiana = true;
				while (czyBylaZamiana) {
					czyBylaZamiana = false;
					for(int k = 0; k < tab2.length - 1; k++) {
						if(tab2[i][k] > tab2[i][k+1]) {
							x = tab2[i][k];
							tab2[i][k] = tab2[i][k+1];
							tab2[i][k+1] = x;
							czyBylaZamiana = true;
						}
					}
				}
			}
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(tab2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		for(int j = 0; j < 10; j++)  {
			for(int i = 0; i < 10; i++) {
				czyBylaZamiana = true;
				while (czyBylaZamiana) {
					czyBylaZamiana = false;
					for(int k = 0; k < tab2.length - 1; k++) {
						if(tab2[k][j] > tab2[k+1][j]) {
							x = tab2[k][j];
							tab2[k][j] = tab2[k+1][j];
							tab2[k+1][j] = x;
							czyBylaZamiana = true;
						}
					}
				}
			}
		}
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				System.out.print(tab2[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		x=0;
		int j = 9;
			while (tab2[0][j]!=0) {
				if (tab2[0][j]!=0) {
					j-=1;
					x=j+1;
				}	
			}
		int i = 1;
		while(i < 10) {
			while (tab2[i][j]!=0 && j>0) {
				if (tab2[i][j]!=0) {
					j-=1;
				}	
			}
			if (tab2[i][j]==0) {
				x+=(j+1);
				System.out.println(x);
				i+=1;
			}
		}
		System.out.println("Liczba zer wynosi "+x);
		//œroda  18:00 sobota 20:00
		//operacje na plikach odczyt zapis, rekurencje
	}
//	public static int wierszznajsum(int tab2[][]) {
//		int x;
//		int y;
//		int tab3 [] = new int[10];
//		for(int i = 0; i < 10; i++) 
//			tab3 [i] = 0;
//		
//		for(int i = 0; i < 10; i++) {
//			for(int j = 0; j < 10; j++) {
//				tab3 [i] = tab3 [i] + tab2 [i][j];
//			}
//		}
//		x=1;
//		y=tab3 [0];
//		for(int i = 1; i < 10; i++) {
//			if(tab3 [i]>y) {
//				x=i+1;
//				y=tab3 [i];
//			}
//		}
//		return x;
//	}
//	public static int kolumnaznajsum(int tab2[][]) {
//		int x;
//		int y;
//		int tab3 [] = new int[10];
//		for(int i = 0; i < 10; i++) 
//			tab3 [i] = 0;
//		
//		for(int j = 0; j < 10; j++) {
//			for(int i = 0; i < 10; i++) {
//				tab3 [j] = tab3 [j] + tab2 [i][j];
//			}
//		}
//		x=1;
//		y=tab3 [0];
//		for(int i = 1; i < 10; i++) {
//			if(tab3 [i]>y) {
//				x=i+1;
//				y=tab3 [i];
//			}
//		}
//		return x;
//	}
}
