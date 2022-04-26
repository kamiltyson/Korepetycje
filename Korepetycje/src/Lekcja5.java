import java.util.Arrays;

public class Lekcja5 {

	public static void main(String[] args) {
//		z wykorzystaniem rekurencji wypisz sum� cyfr danej liczby.
//		System.out.println(sumaCyfr(1420));
		// wypisz n-ty wyraz ci�gu Fibonaciego
//		System.out.println(fibonacciRekurencja(10));
//		z wykorzystaniem rekurencji zlicz liczb� dzielnik�w danej liczby
//		System.out.println(liczbaDzielnikow(2,1));
//		ilo�� cyfr danej liczby
//		System.out.println(iloscCyfr(110));
//		wypisz tablice za pomoc� rekurencji
		int [] tab = {8,7,9,6};
//		wypiszTablice(true,0,tab);
//		argument boolean kt�ry pozwoli wy�wietli� od przodu (true) lub od ty�u(false))
//		posortowa� tablic� rekurencyjnie, bez p�tli (wy�wietli�)
		sortujTablice(0,0,tab);
		wypiszTablice(true,0,tab);
	}
//	public static int sumaCyfr(int a) {
//	
//		if(a > 0) 
//			return a%10 + sumaCyfr(a/10);
//		return 0;
//	}
// 	public static int fibonacciRekurencja(int n) {
//		return n<2? n : fibonacciRekurencja(n - 1) + fibonacciRekurencja(n - 2);
//	}
//	public static int liczbaDzielnikow(int a, int b) {
//		
//		if(a != b)
//			return (a % b == 0? 1 : 0)  + liczbaDzielnikow(a, b+1);
//		return 1;
//	}
//	public static int iloscCyfr (int a) {
//		if(a > 0)
//			return (a / 10 > 0? 1 : 0) + iloscCyfr(a/10);
//		return 1;
//	}
	public static void wypiszTablice(boolean jak,int i,int... tab) {
		if (jak == true) {
			if(i != (tab.length)) {
				System.out.print(tab[i]+" ");
				wypiszTablice(jak,i+1,tab);
			}
		}
		else {
			if(i != (tab.length)) {
				System.out.print(tab[tab.length-1-i]+" ");
				wypiszTablice(jak,i+1,tab);
			}
		}
	}
	public static void sortujTablice(int i,int j,int... tab) {
		if(i != (tab.length)) {
			int temp;
			if(j<tab.length-1){
				if(tab[j]>tab[j+1]){
					temp = tab[j+1];
					tab[j+1] = tab[j];
					tab[j] = temp;
					}
				sortujTablice(i,j+1,tab);
			}
			sortujTablice(i+1,0,tab);
		}
	}
}
