import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;

public class Lekcja6 {

	public static void main(String[] args) throws FileNotFoundException {
		//Odczytaæ ca³¹ zawartoœæ z pliku i zapisaæ j¹ od ty³u
//		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\testA.txt"));
//		String str = "";
//		while(scanner.hasNextLine()) {
//			str += scanner.nextLine() + "\n";
//		}
//		
//		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\testA.txt"));
//		
//		for(int i = str.length()-2; i >= 0; i--) {
//			pw.print(str.charAt(i));
//			System.out.print(str.charAt(i));
//		}
//		pw.close();
//		scanner.close();
		//œci¹gn¹æ tekst tabularny z wikipedii
//		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\testA.txt"));
//		String str = "";
//		while(scanner.hasNextLine()) {
//			str += scanner.nextLine() + "\n";
//		}
//		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\testB.txt"));
//		for(int i = 0; i <= str.length()-2; i++) {
//			if (str.charAt(i)=='.' || str.charAt(i)=='?' || str.charAt(i)=='!') {
//				pw.print(str.charAt(i)+"\n");
//				pw.flush();
//			}
//			else {
//				pw.print(str.charAt(i));
//				pw.flush();
//			}
//		}
//		pw.close();
//		scanner.close();
//		Na podstawie tekstu liczba liter, zdañ, linijek
//		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\testB.txt"));
//		String str = "";
//		int liczbaliter =  0;
//		int liczbazdan =  0;
//		int liczbalinjek =  0;
//		while(scanner.hasNextLine()) {
//			str += scanner.nextLine() + "\n";
//			liczbalinjek++;
//		}
//		for(int i = 0; i <= str.length()-2; i++) {
//			if (str.charAt(i)=='.' || str.charAt(i)=='?' || str.charAt(i)=='!') {
//				liczbazdan++;
//			}
//			if((str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') ) {
//				liczbaliter++;
//			}
//		}
//		System.out.println("Liczba liter: "+liczbaliter);
//		System.out.println("Liczba zdañ: "+liczbazdan);
//		System.out.println("Liczba linijek: "+liczbalinjek);
//		scanner.close();
//		posortowany histogram (liczba wyœwietleñ danych liter, posortowana od najwy¿szej liczby wyst¹pieñ)
//		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\testB.txt"));
//		String str = "";
//		while(scanner.hasNextLine()) {
//			str += scanner.nextLine() + "\n";
//		}
//		str=str.toLowerCase();
//		char tab1 [] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//		int tab2 [] = new int[26];
//		for(int i = 0; i <= str.length()-2; i++) {
//			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
//				for(int j = 0; j < tab2.length; j++) {
//					if (tab1 [j]==str.charAt(i)) {
//						tab2 [j]++;
//					}
//				}
//			}
//		}
//		int tmp1;
//		char tmp2;
//		boolean czyBylaZamiana;
//		for(int i = 0; i < tab2.length; i++)  {
//				czyBylaZamiana = true;
//				while (czyBylaZamiana) {
//					czyBylaZamiana = false;
//					for(int j = 0; j < tab2.length - 1; j++) {
//						if(tab2[j] < tab2[j+1]) {
//							tmp1 = tab2[j];
//							tab2[j] = tab2[j+1];
//							tab2[j+1] = tmp1;
//							tmp2 = tab1[j];
//							tab1[j] = tab1[j+1];
//							tab1[j+1] = tmp2;
//							czyBylaZamiana = true;
//						}
//					}
//				}
//			}
//		for(int i = 0; i < tab2.length; i++)  {
//			System.out.println("Litera "+tab1 [i]+" wyst¹pi³a "+tab2 [i]+" razy");
//		}
//		scanner.close();
//		Szyfr cezara
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\Downloads\\testB.txt"));
		String str = "";
		int klucz = 1000;
		int x = 0;
		while(scanner.hasNextLine()) {
			str += scanner.nextLine() + "\n";
		}
		char tab1 [] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char tab2 [] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		PrintWriter pw = new PrintWriter(new File("C:\\Users\\Predator\\Downloads\\testC.txt"));
		for(int i = 0; i <= str.length()-2; i++) {
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				for(int j = 0; j < tab1.length; j++) {
					if (tab1 [j]==str.charAt(i)) {
						x=j+klucz+1;
						x%=26;
						if (x+1<tab1.length)
							pw.print(tab1[x+1]);
						else
							pw.print(tab1[x-25]);
					}
				}
			}
			else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				for(int j = 0; j < tab2.length; j++) {
					if (tab2 [j]==str.charAt(i)) {
						x=j+klucz+1;
						x%=26;
						if (x+1<tab2.length)
							pw.print(tab2[x+1]);
						else
							pw.print(tab2[x-25]);
					}
				}
			}
			else
				pw.print(str.charAt(i));
		}
		pw.close();
		scanner.close();
		//niedziela 17:00
	}
}
