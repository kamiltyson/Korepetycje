import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class Lekcja48_49_50_Test {
	private Dziecko[] dzieci1;
	private Dziecko[] dzieci2 = new Dziecko[15];
	
	@Test
	public void Junit4AssertionTest() throws FileNotFoundException{	
		
		Lekcja48_49_50 lekcja48_49_50 = new Lekcja48_49_50();
		
		dzieci1=lekcja48_49_50.getDzieci();
		
		int i = 0;
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\git\\repository\\Korepetycje\\DaneWyjsciowe.txt"));
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			dzieci2[i]= new Dziecko(linia.split(" ")[0] + " " + linia.split(" ")[1], linia.split(" ")[2]);
			i++;
		}
		scanner.close();
		
		i = 0;
		
    	for (Dziecko dziecko : dzieci1) {
    		assertTrue(dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2);
    		assertEquals(dziecko.getNazwaplac(), "Sypialnia");
    		assertEquals(dziecko.getImie(), dzieci2[i].getImie());
    		assertEquals(dziecko.getNazwaplac(), dzieci2[i].getNazwaplac());
    		i++;
    	}
        							
    }
}
