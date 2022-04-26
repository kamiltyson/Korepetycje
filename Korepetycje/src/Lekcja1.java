import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Lekcja1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ctrl+a ctrl+shift+f
		// Znajdz wszyskie liczby pierwsze z zakresu 1 do 10
//		int liczbadzielnikow;
//		int liczbaliczb = 0;
//		for (int i = 1; i < 101; i++) {
//			liczbadzielnikow = 0;
//			for (int j = 1; j < i + 1; j++) {
//				if (i % j == 0)
//					liczbadzielnikow += 1;
//			}
//			if (liczbadzielnikow <= 2) {
//				System.out.println(i);
//				liczbaliczb += 1;
//			}
//		}
//		System.out.println("Liczb pierwszych z zakresu od 1 do 100 jest "+liczbaliczb);

//		int liczba = 123;
//		int sumacyfr = 0;
//		while (liczba>0) {
//			sumacyfr += liczba % 10;
//			liczba = liczba / 10;
//		}
//		System.out.println("Suma cyfr wynosi "+sumacyfr);
		// Sprawdz czy dana liczba jest palindromem
//		int liczba=1221;
//		String liczbastr=String.valueOf(liczba);
//		String odwliczbastr ="";
//		for(int i=liczbastr.length-1; i >= 0 ; i--) {
//			odwliczbastr = odwliczbastr + liczbastr.charAt(i);
//		}
//		System.out.println(liczbastr.equals(odwliczbastr));
		// Odwróciæ szyk tablicy jednowymiarowej
//		char tab[] = { 'A', 'B', 'C', 'D' };
//		int s = 0;
//		int k = 0;
//		char tmp;
//		while(s < k) {
//			tmp = tab[s];
//			tab[s] = tab[k];
//			tab[k] =tmp;
//			s++;
//			k--;
//	}
//		
//		for(int i=tab.length-1; i >= 0 ; i--) {
//		System.out.println(tab[i]);
//		}
		//Czy dana liczba sk³ada siê tylko z jednej cyfry
//		int liczba=222;
//		int tmp = liczba % 10;
//		boolean check =  true;
//		while (liczba>0) {
//			if (! (tmp == liczba % 10)) {
//				check = false;
//				liczba = 0;
//				System.out.println("NIE");
//			}
//		liczba = liczba / 10;
//		}
//		if (check) 
//			System.out.println("TAK");
		
		//tablica z elementami powtarzaj¹cymi siê
		
		int tab[] = { 1, 1, 2, 3, 4, 4, 4, 5 };
		
		Arrays.sort(tab);
		
		ArrayList<Integer> lista1 =  new  ArrayList<Integer>();
		ArrayList<Integer> lista2 =  new  ArrayList<Integer>();
		
		for (int i=0;i<tab.length;i++) {
			lista2.add(tab[i]);
			for (int j=i+1;j<tab.length;j++)
			    if (j!=i && tab[j] == tab[i]) {
			    	lista1.add(tab[i]);
			}
		}
		
		System.out.println("powtarza siê");
		
		System.out.println(new HashSet<Integer>(lista1));
		System.out.println(lista1);
		
		lista2.removeAll(lista1);
		
		System.out.println("nie powtarza siê");
		
		System.out.println(new HashSet<Integer>(lista2));

//		funkcje/tablice 2 wymiarowe, rekurencje
	}
}
