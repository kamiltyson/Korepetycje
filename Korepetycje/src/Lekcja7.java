import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Lekcja7 {

	public static void main(String[] args) throws Exception {
		// pi¹tek 17:00 niedziela 7:00
		// Imiê i nazwisko od u¿ytkowanika. W wskazanej œcie¿ce tworzysz folder z jego
		// inicja³ami i kolejn¹ liczb¹ porz¹dkow¹. Wewn¹trze tego folderu stworzyæ plik
		// tekstowy z jego inicja³¹mi i kolejn¹ liczb¹ porz¹dkow¹ word, text, pdf.

//		String Imie="";
//		String Nazwisko;
//		String path;
//		
//		File folder;
//		String plikPath;
//		File f ;
//		int i = 0;
//		String stri;
//		Scanner scanner = new Scanner(System.in);
//		while (!Imie.equals("koniec")){
//			i++;
//			if (i<10)
//				stri="00"+i;
//			else if(i<100)
//				stri="0"+i;
//			else {
//				stri="";
//				stri=stri+i;
//			}	
//			System.out.println("Twoje imie:");
//			Imie = scanner.next();
//			if (!Imie.equals("koniec")){
//				System.out.println("Twoje nazwisko:");
//				Nazwisko = scanner.next();
//				path = "C:\\Users\\Predator\\Downloads\\" + Imie.charAt(0) + Nazwisko.charAt(0) + stri;
//				
//				folder = new File(path);
//				
//				folder.mkdir();
//				plikPath = path + "\\" + Imie.charAt(0) + Nazwisko.charAt(0) + stri + ".docx"; 
//				f = new File(plikPath);
//				f.createNewFile();
//				plikPath = path + "\\" + Imie.charAt(0) + Nazwisko.charAt(0) + stri + ".txt"; 
//				f = new File(plikPath);
//				f.createNewFile();
//				plikPath = path + "\\" + Imie.charAt(0) + Nazwisko.charAt(0) + stri + ".pdf"; 
//				f = new File(plikPath);
//				f.createNewFile();
//			}
//		}
//		podajesz u¿ytkownikowi nr dysku i zczytuje od u¿ytkownika fraze.
//		Wyœwietla ca³e œcie¿ki folderów z t¹ fraz¹.
//		String path = "C:\\Users\\Predator\\Downloads\\";
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Dysk nr: C. Podaj frazê:");
//		String FolderFraza = scanner.next();
//		File maindir = new File(path);
//		File arr[] = maindir.listFiles();
//		SzukajFolder(arr, 0, 0, FolderFraza);

//		podajesz u¿ytkownikowi nr dysku i zczytuje od u¿ytkownika fraze.
//		Wyœwietla ca³e œcie¿ki plików tekstowych .txt z t¹ fraz¹ w œrodku pliku.
		String path = "C:\\Users\\Predator\\Downloads\\";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Dysk nr: C. Podaj frazê:");
		String PlikTxtFraza = scanner.next();
		File maindir = new File(path);
		File arr[] = maindir.listFiles();
		SzukajWPlikuTxt(arr, 0, 0, PlikTxtFraza);
	}

	public static void SzukajFolder(File[] arr, int index, int level, String FolderFraza) {

		if (index == arr.length)
			return;

		if (arr[index].isDirectory()) {
			if (arr[index].getPath().contains(FolderFraza))
				System.out.println(arr[index]);

			SzukajFolder(arr[index].listFiles(), 0, level + 1, FolderFraza);
		}

		SzukajFolder(arr, ++index, level, FolderFraza);
	}

	public static void SzukajWPlikuTxt(File[] arr, int index, int level,String PlikTxtFraza) throws Exception {
	       
        if (index == arr.length)
            return;
        
        if (arr[index].isFile()) {
        	if (arr[index].getPath().contains(".txt")) {
        		Scanner scanner = new Scanner(arr[index]);
        		String str = "";
        		while(scanner.hasNextLine()) {
        			str += scanner.nextLine() + "\n";
        		}
        		if (str.contains(PlikTxtFraza)) {
    				System.out.println(arr[index].getName());
        		}
        		scanner.close();
        	}
        }
        if (arr[index].isDirectory()) {
        	SzukajWPlikuTxt(arr[index].listFiles(), 0,
                           level + 1,PlikTxtFraza);
        }
        SzukajWPlikuTxt(arr, ++index, level,PlikTxtFraza);
	}

}
