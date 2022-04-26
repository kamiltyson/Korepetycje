import java.util.Random;

public class Lekcja2 {

	public static void main(String[] args) {
//		funkcje/tablice 2 wymiarowe, rekurencje
//		Tworzymy tablice 5x10 od 0 do 3. 0 prawdopodobieñstwo 60%, 1 10%, 2 20%, 3 10%
		int tab [][] = new int[5][10];
		double x;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10; j++) {
				x = Math.random();
				if (x <= 0.6)
					tab [i][j] = 0;
				else if (x > 0.60 && x <= 0.7)
					tab [i][j] = 1;
				else if (x > 0.70 && x <= 0.9)
					tab [i][j] = 2;
				else if (x > 0.90 && x <= 1)
					tab [i][j] = 3;
				System.out.print(tab [i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
//		Wypisaæ prawdopodobieñstwo
		int tab2 [] = new int[4];
		for(int i = 0; i < 4; i++) 
			tab2 [i] = 0;

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10; j++) {
				tab2 [tab [i][j]] = tab2 [tab [i][j]] + 1;
			}
		}
		int y;
		for(int i = 0; i < 4; i++) {
			y = (int)(((double)(tab2 [i])/50)*100);
			System.out.println(i+" wyst¹pi³a "+y+"%");
		}
//		suma ka¿dej kolumny i ka¿dego wiersza
		System.out.println();
		int tab3 [] = new int[10];
		for(int i = 0; i < 10; i++) 
			tab3 [i] = 0;
		
		for(int j = 0; j < 10; j++) {
			for(int i = 0; i < 5; i++) {
				tab3 [j] = tab3 [j] + tab [i][j];
			}
		}
		
		for(int j = 0; j < 10; j++) {
			y = j+1;
			System.out.println(y+" kolumna ma sumê "+tab3 [j]);
		}
		System.out.println();
		int tab4 [] = new int[10];
		for(int i = 0; i < 5; i++) 
			tab4 [i] = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 10; j++) {
				tab4 [i] = tab4 [i] + tab [i][j];
			}
		}
		
		for(int j = 0; j < 5; j++) {
			y = j+1;
			System.out.println(y+" wiersz ma sumê "+tab4 [j]);
		}
//		funkcja która przyjmie dwie liczby i wyœwietli Najwiêksza wspólny dzielnik
		System.out.println();
		int a = 22;
		int b = 4;
		System.out.println("Najwiêkszy wspólny licznik liczb "+a+" i "+b+" to "+nwd(a,b));
//		funkcja która przyjmie dwie liczby i wyœwietli Namniejsza wspólna wielokrotnoœæ
		System.out.println("Namniejsza wspólna wielokrotnoœæ liczb "+a+" i "+b+" to "+nww(a,b));
	}
	public static int nwd(int a, int b) {
		while (a != b) {
			if (a > b)
			a -= b;
			else
			b -= a;
			}
			return a;
	}
	public static int nww(int a, int b) {
		int nww = 0;
    	if (a > b) {
    		nww=a;
    	    while (nww % b != 0) {
    	    	nww += a;
    	    }
    	}
		else {
			nww=b;
    	    while (nww % a != 0) {
    	    	nww += b;
    	    }
		}
		return nww;
	}
}