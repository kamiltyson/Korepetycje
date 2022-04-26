import java.util.Arrays;
import java.util.Random;

public class Lekcja4 {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static void main(String[] args) {
//		1 wymiarowa tablica wyœwietl najd³u¿sz¹ liczbê elementów wystêpuj¹cych po sobie które s¹ rosn¹ce
//		int tab [] = {1,1,1,1,1,1,1,1,1,1,2,7,8};
//		int j = 0;
//		int x = 1;
//		int tab2 [] = new int[tab.length];
//		for(int i = 0; i < tab.length-1; i++) {
//			if (tab[i+1] > tab[i])
//				x+=1;
//			else {
//				tab2[j]=x;
//				x=1;
//				j+=1;
//			}
//			if (tab[i+1] > tab[i] && i==tab.length-2)
//				tab2[j]=x;
//		}
//		Arrays.sort(tab2);
//		System.out.println("najd³u¿sza liczba elementów wystêpuj¹cych po sobie które s¹ rosn¹ce to "+ tab2[tab2.length-1]);
		//stwórz tablicê 2 wymiarow¹ 10x10, zape³nij j¹ wylosowanymi wartoœciami z zakresu od od 0 do 8. Rzut lotu ptaka. Wyznacz szczyty.
		int tab [][] = new int[10][10];
		final Random random = new Random();
		for(int i = 0; i < 10; i++)  {
			for(int j = 0; j < 10; j++) {
				tab[i][j] = random.nextInt(9);
				System.out.print(tab[i][j]+" ");
			}
			System.out.println();
		}
		int tab2 [] = new int[8];
		int x;
		int y;
		int z;
		for(int i = 1; i < 9; i++)  {
			for(int j = 1; j < 9; j++) {
				x=0;
				for(int k = i-1; k < i+2; k++)  {
					for(int l = j-1; l < j+2; l++)  {
						if (k==i && l==j)
							l+=1;
						tab2[x]=tab[k][l];
						x+=1;
					}
				}
				Arrays.sort(tab2);
				if (tab[i][j]>tab2[tab2.length-1]) {
					y=i+1;
					z=j+1;
					System.out.println("Szczyt wiersz "+y+".Szczyt kolumna "+z);
					}
			}
		}
		for(int i = 0; i < 10; i++)  {
			for(int j = 0; j < 10; j++) {
				if ((i>0&&i<9) && (j>0&&j<9)) {
					x=0;
					for(int k = i-1; k < i+2; k++)  {
						for(int l = j-1; l < j+2; l++)  {
							if (k==i && l==j)
								l+=1;
							tab2[x]=tab[k][l];
							x+=1;
						}
					}
					Arrays.sort(tab2);
					//sprawdziæ wype³nianie tab2
					if (tab[i][j]>tab2[tab2.length-1]) {
						System.out.print(ANSI_RED+tab[i][j]+" "+ ANSI_RESET);
					}
					else {
						System.out.print(tab[i][j]+" ");
					}
				}
				else {
					System.out.print(tab[i][j]+" ");
				}
			}
			System.out.println();
		}
	}
}
