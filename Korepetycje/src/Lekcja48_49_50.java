import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class Dziecko{
	
	private String imie;
	private String nazwaplac;
	private int iloscplac1=0;
	private int iloscplac2=0;
	private int iloscplac3=0;
	
	public Dziecko(String imie, String nazwaplac) {
		this.imie = imie;
		this.nazwaplac = nazwaplac;
		if (nazwaplac.equals("Plac1")) {
			iloscplac1++;
		}
		else if(nazwaplac.equals("Plac2")) {
			iloscplac2++;
		}
		else if(nazwaplac.equals("Plac3")) {
			iloscplac3++;
		}
	}
	
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public String getNazwaplac() {
		return nazwaplac;
	}

	public void setNazwaplac(String nazwaplac) {
		this.nazwaplac = nazwaplac;
		if (nazwaplac.equals("Plac1")) {
			iloscplac1++;
		}
		else if(nazwaplac.equals("Plac2")) {
			iloscplac2++;
		}
		else if(nazwaplac.equals("Plac3")) {
			iloscplac3++;
		}
	}
	
	public int getIloscplac1() {
		return iloscplac1;
	}
	
	public int getIloscplac2() {
		return iloscplac2;
	}
	
	public int getIloscplac3() {
		return iloscplac3;
	}
	
}

		
class Tunel12awprawo{
	
	private String imie;
	
	public Tunel12awprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel12awprawo a = (Tunel12awprawo) o;
        return this.imie.equals(a.imie);
    }
	
}
	
class Tunel12awlewo{
	private String imie;
	
	public Tunel12awlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel12awlewo a = (Tunel12awlewo) o;
        return this.imie.equals(a.imie);
    }
}
	
class Tunel12bwprawo{
	private String imie;
	
	public Tunel12bwprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel12bwprawo a = (Tunel12bwprawo) o;
        return this.imie.equals(a.imie);
    }
}
	
class Tunel12bwlewo{
	private String imie;
	
	public Tunel12bwlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel12bwlewo a = (Tunel12bwlewo) o;
        return this.imie.equals(a.imie);
    }
}

class Tunel13awprawo{
	private String imie;
	
	public Tunel13awprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel13awprawo a = (Tunel13awprawo) o;
        return this.imie.equals(a.imie);
    }
}
		
class Tunel13awlewo{
	private String imie;
	
	public Tunel13awlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel13awlewo a = (Tunel13awlewo) o;
        return this.imie.equals(a.imie);
    }
}

class Tunel13bwprawo{
	private String imie;
	
	public Tunel13bwprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel13bwprawo a = (Tunel13bwprawo) o;
        return this.imie.equals(a.imie);
    }
}
		
class Tunel13bwlewo{
	private String imie;
	
	public Tunel13bwlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel13bwlewo a = (Tunel13bwlewo) o;
        return this.imie.equals(a.imie);
    }
}

class Tunel23awprawo{
	private String imie;
	
	public Tunel23awprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel23awprawo a = (Tunel23awprawo) o;
        return this.imie.equals(a.imie);
    }
}
		
class Tunel23awlewo{
	private String imie;
	
	public Tunel23awlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel23awlewo a = (Tunel23awlewo) o;
        return this.imie.equals(a.imie);
    }
}

class Tunel23bwprawo{
	private String imie;
	
	public Tunel23bwprawo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel23bwprawo a = (Tunel23bwprawo) o;
        return this.imie.equals(a.imie);
    }
}
		
class Tunel23bwlewo{
	private String imie;
	
	public Tunel23bwlewo(String imie) {
		this.imie = imie;
	}


	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
        if (o == this) return true; //if both pointing towards same object on heap

        Tunel23bwlewo a = (Tunel23bwlewo) o;
        return this.imie.equals(a.imie);
    }
}

public class Lekcja48_49_50 {
	
	public static Dziecko[] dzieci;
	private static boolean czywszystkiedziecilezakuja = false;
	final static Random random = new Random();
	private static int i;

	public static void main(String[] args) throws FileNotFoundException {
		
		dzieci = new Dziecko[15];
		int i = 0;
		
		Scanner scanner = new Scanner(new File("C:\\Users\\Predator\\git\\repository\\Korepetycje\\DaneWejscie.txt"));
		while(scanner.hasNextLine()) {
			String linia = scanner.nextLine();
			dzieci[i]= new Dziecko(linia.split(" ")[0] + " " + linia.split(" ")[1], linia.split(" ")[2]);
			i++;
		}
		scanner.close();
		
		ArrayList<Tunel12awprawo> tunel12awprawo = new ArrayList<Tunel12awprawo>();
		ArrayList<Tunel12awlewo> tunel12awlewo = new ArrayList<Tunel12awlewo>();
		ArrayList<Tunel12bwprawo> tunel12bwprawo = new ArrayList<Tunel12bwprawo>();
		ArrayList<Tunel12bwlewo> tunel12bwlewo = new ArrayList<Tunel12bwlewo>();
		
		ArrayList<Tunel13awprawo> tunel13awprawo = new ArrayList<Tunel13awprawo>();
		ArrayList<Tunel13awlewo> tunel13awlewo = new ArrayList<Tunel13awlewo>();
		ArrayList<Tunel13bwprawo> tunel13bwprawo = new ArrayList<Tunel13bwprawo>();
		ArrayList<Tunel13bwlewo> tunel13bwlewo = new ArrayList<Tunel13bwlewo>();
		
		ArrayList<Tunel23awprawo> tunel23awprawo = new ArrayList<Tunel23awprawo>();
		ArrayList<Tunel23awlewo> tunel23awlewo = new ArrayList<Tunel23awlewo>();
		ArrayList<Tunel23bwprawo> tunel23bwprawo = new ArrayList<Tunel23bwprawo>();
		ArrayList<Tunel23bwlewo> tunel23bwlewo = new ArrayList<Tunel23bwlewo>();
		
		while (!czywszystkiedziecilezakuja) {
			
			czywszystkiedziecilezakuja = true;
			
			for (Dziecko dziecko : dzieci) {
				if (dziecko.getIloscplac1()!=2 || dziecko.getIloscplac2()!=2 || dziecko.getIloscplac3()!=2) {
					i = random.nextInt(2);
					if (dziecko.getNazwaplac().equals("Plac1")){
						if ( (i==0 && dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==1 && dziecko.getIloscplac3()==0) || (i==0 && dziecko.getIloscplac2()==2) ) {
							i=1;
						}
						else if ( (i==1 && dziecko.getIloscplac1()==2 && dziecko.getIloscplac3()==1 && dziecko.getIloscplac2()==0) || (i==1 && dziecko.getIloscplac3()==2) ){
							i=0;
						}
						if (i==0) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac2 tunelem A");
								tunel12awprawo.add(new Tunel12awprawo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac2 tunelem B");
								tunel12bwprawo.add(new Tunel12bwprawo(dziecko.getImie()));
							}
						}
						else if(i==1) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac3 tunelem A");;
								tunel13awprawo.add(new Tunel13awprawo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac3 tunelem B");
								tunel13bwprawo.add(new Tunel13bwprawo(dziecko.getImie()));
							}
						}
					}
					else if(dziecko.getNazwaplac().equals("Plac2")){
						if ( (i==0 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac1()==1 && dziecko.getIloscplac3()==0) || (i==0 && dziecko.getIloscplac1()==2) ) {
							i=1;
						}
						else if ( (i==1 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==1 && dziecko.getIloscplac1()==0) || (i==1 && dziecko.getIloscplac3()==2)){
							i=0;
						}
						if (i==0) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac1 tunelem A");
								tunel12awlewo.add(new Tunel12awlewo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac1 tunelem B");
								tunel12bwlewo.add(new Tunel12bwlewo(dziecko.getImie()));
							}
						}
						else if(i==1) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac3 tunelem A");
								tunel23awprawo.add(new Tunel23awprawo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac3 tunelem B");
								tunel23bwprawo.add(new Tunel23bwprawo(dziecko.getImie()));
							}
						}
					}
					else if(dziecko.getNazwaplac().equals("Plac3")){
						if ( (i==0 && dziecko.getIloscplac3()==2 && dziecko.getIloscplac1()==1 && dziecko.getIloscplac2()==0) || (i==0 && dziecko.getIloscplac1()==2)){
							i=1;
						}
						else if ( (i==1 && dziecko.getIloscplac3()==2 && dziecko.getIloscplac2()==1 && dziecko.getIloscplac1()==0) || (i==1 && dziecko.getIloscplac2()==2)){
							i=0;
						}
						if (i==0) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac1 tunelem A");
								tunel13awlewo.add(new Tunel13awlewo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac1 tunelem B");
								tunel13bwlewo.add(new Tunel13bwlewo(dziecko.getImie()));
							}
						}
						else if(i==1) {
							i = random.nextInt(2);
							if (i==0) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac2 tunelem A");
								tunel23awlewo.add(new Tunel23awlewo(dziecko.getImie()));
							}
							else if(i==1) {
								System.out.println(dziecko.getImie() +" idzie z " + dziecko.getNazwaplac() + " w stronê Plac2 tunelem B");
								tunel23bwlewo.add(new Tunel23bwlewo(dziecko.getImie()));
							}
						}
					}
				}
			}
			
			if(tunel12awprawo.size()>tunel12awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac1 (" + tunel12awprawo.size() + ") do Plac2 tunelem A(" + tunel12awlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel12awprawo.contains(new Tunel12awprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac2");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac2");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel12awprawo.size()<tunel12awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac2 (" + tunel12awlewo.size() + ") do Plac1 tunelem A(" + tunel12awprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel12awlewo.contains(new Tunel12awlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac1");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac1");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			if(tunel12bwprawo.size()>tunel12bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac1 (" + tunel12bwprawo.size() + ") do Plac2 tunelem B(" + tunel12bwlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel12bwprawo.contains(new Tunel12bwprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac2");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac2");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel12bwprawo.size()<tunel12bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac2 (" + tunel12bwlewo.size() + ") do Plac1 tunelem B(" + tunel12bwprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel12bwlewo.contains(new Tunel12bwlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac1");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac1");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			if(tunel13awprawo.size()>tunel13awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac1 (" + tunel13awprawo.size() + ") do Plac3 tunelem A(" + tunel13awlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel13awprawo.contains(new Tunel13awprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac3");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac3");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel13awprawo.size()<tunel13awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac3 (" + tunel13awlewo.size() + ")  do Plac1 tunelem A(" + tunel13awprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel13awlewo.contains(new Tunel13awlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac1");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac1");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			if(tunel13bwprawo.size()>tunel13bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac1 (" + tunel13bwprawo.size() + ") do Plac3 tunelem B(" + tunel13bwlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel13bwprawo.contains(new Tunel13bwprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac3");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac3");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel13bwprawo.size()<tunel13bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac3 (" + tunel13bwlewo.size() + ") do Plac1 tunelem B(" + tunel13bwprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel13bwlewo.contains(new Tunel13bwlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac1");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac1");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			if(tunel23awprawo.size()>tunel23awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac2 (" + tunel23awprawo.size() + ") do Plac3 tunelem A(" + tunel23awlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel23awprawo.contains(new Tunel23awprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac3");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac3");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel23awprawo.size()<tunel23awlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac3 (" + tunel23awlewo.size() + ") do Plac2 tunelem A(" + tunel23awprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel23awlewo.contains(new Tunel23awlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac2");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac2");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			if(tunel23bwprawo.size()>tunel23bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac2 (" + tunel23bwprawo.size() + ") do Plac3 tunelem B(" + tunel23bwlewo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel23bwprawo.contains(new Tunel23bwprawo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac3");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac3");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			else if(tunel23bwprawo.size()<tunel23bwlewo.size()) {
				System.out.println("Wiêcej dzieci dotar³o z Plac3 (" + tunel23bwlewo.size() + ")  do Plac2 tunelem B(" + tunel23bwprawo.size() + ")");
				for (Dziecko dziecko : dzieci) {
					if(tunel23bwlewo.contains(new Tunel23bwlewo(dziecko.getImie()))) {
						dziecko.setNazwaplac("Plac2");
						System.out.println(dziecko.getImie() +" dziecko dotar³o do Plac2");
						System.out.println(dziecko.getImie() +" by³o " + dziecko.getIloscplac1() + " na Placu 1, by³o " + dziecko.getIloscplac2() + " na Placu 2, by³o "  + dziecko.getIloscplac3() + " na Placu 3");
						if (dziecko.getIloscplac1()==2 && dziecko.getIloscplac2()==2 && dziecko.getIloscplac3()==2) {
							dziecko.setNazwaplac("Sypialnia");
							System.out.println(dziecko.getImie() + " idzie le¿akowaæ");
						}
						else {
							czywszystkiedziecilezakuja=false;
						}
					}
				}
			}
			
			System.out.println(czywszystkiedziecilezakuja);
			
			tunel12awprawo.removeAll(tunel12awprawo);
			tunel12awlewo.removeAll(tunel12awlewo);
			tunel12bwprawo.removeAll(tunel12bwprawo);
			tunel12bwlewo.removeAll(tunel12bwlewo);
			
			tunel13awprawo.removeAll(tunel13awprawo);
			tunel13awlewo.removeAll(tunel13awlewo);
			tunel13bwprawo.removeAll(tunel13bwprawo);
			tunel13bwlewo.removeAll(tunel13bwlewo);
			
			tunel23awprawo.removeAll(tunel23awprawo);
			tunel23awlewo.removeAll(tunel23awlewo);
			tunel23bwprawo.removeAll(tunel23bwprawo);
			tunel23bwlewo.removeAll(tunel23bwlewo);
			
		}
		
	Result result = JUnitCore.runClasses(Lekcja48_49_50_Test.class);	
	
	for (Failure failure : result.getFailures()) {							
		System.out.println(failure.toString());					
	}
	
	System.out.println("Result=="+result.wasSuccessful());
	
	}
	
	public Dziecko[] getDzieci() {
		return dzieci;
	}
	
}
//Przedszkole. W przedszkolu s¹ 3 place zabaw, a ¿eby przejœæ z jednego placu na inny trzeba przejœæ przez jeden z dwóch tuneli. Ka¿dy plac jest po³¹czony z innym placem dwoma tunelami. 
//Dziecko jak chce przejœæ z jednego placu na drugi to wybiera któryœ tunel i idzie. Jak dziecko jest w œrodku to przechodzi na drug¹ stronê. 
//Chyba ¿e w przeciwnym kierunku te¿ id¹ dzieci. Je¿eli dzieci siê spotkaj¹ w tunelu to negocjuj¹, kto komu ma ust¹piæ. Wygrywa to dziecko (tak na prawdê grupa dzieci), których jest wiêcej. 
//Jak 4 dzieciaki id¹ w lewo a 5 dzieciaków idzie w prawo, to dzieciaki id¹ce w prawo wygrywaj¹ i te 4 dzieciaki, musz¹ siê wycofaæ, a potem próbowaæ raz jeszcze. 
//Ka¿de dziecko musi odwiedziæ ka¿dy plac zabaw dwukrotnie, a potem idzie na le¿akowanie.

//Wpuszczamy dziecko na dany plac zabaw. Nazwa placu zabaw na którym siê znajduje zwiêksza liczbê odwiedzenia danego placu zabaw o jeden.
//Dziecko wybiera miêdzy max dwoma placami zabaw dla których liczba odwiedzin < 2. Je¿eli wybiera miêdzy dwoma kierunkami to szansa wyboru danego kierunku wynosi 50%.
//50% wynosi szansa wyboru tunelem A a tunelem B.
//Je¿eli liczba dzieci w tunelu w obu kierunkach jest taka sama to nastêpuje losowanie.
//w¹tki - przeczytaæ


//testy prezentuj¹ce rozwi¹zania
//test w oparciu o plik. Jeden Plik dane wejœciowe, Drugi plik dane wyjœciowe.