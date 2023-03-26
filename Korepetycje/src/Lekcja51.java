import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Lekcja51 {
	
	private static double r = 0;
	private static double g = 0;
	private static double b = 0;
	private static double rPrawdopodobienstwo = 0.33;
	private static double gPrawdopodobienstwo = 0.33;
	private static double bPrawdopodobienstwo = 0.33;
	private static double i;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runnable timerRunnable = new Runnable() {
		    public void run() {
		    	
		    	i = Math.random();
		    	
		    	System.out.println("Prawdopodobienstwo przed losowaniem wynosi red " + (double) Math.round(rPrawdopodobienstwo * 10000)/100.00 + "%, green " + (double) Math.round(gPrawdopodobienstwo * 10000)/100.00 + "%, blue " + (double) Math.round(bPrawdopodobienstwo * 10000)/100.00 + "%");
		    	
		    	if (i <= rPrawdopodobienstwo) {
		    		r++;
		    		System.out.println("red " + r);
		    	}
		    	else if(i <= (rPrawdopodobienstwo + gPrawdopodobienstwo)) {
		    		g++;
		    		System.out.println("green " + g);
		    	}
		    	else if(i <= (rPrawdopodobienstwo + gPrawdopodobienstwo + bPrawdopodobienstwo)) {
		    		b++;
		    		System.out.println("blue " + b);
		    	}
		    	
		    	//gdy wszystko jest równe nie sprawdzaj dalej
		    	rPrawdopodobienstwo = r/(r+b+g);
		    	gPrawdopodobienstwo = g/(r+b+g);
		    	bPrawdopodobienstwo = b/(r+b+g);
		    	
		    	//3 przypadki gdy jedno prawdopowobienstwo jest wieksze od pozosta³ych dwóch, 3 przypadki gdy dwa s¹ wieksze od jednego
		    	if ( (rPrawdopodobienstwo>(gPrawdopodobienstwo+bPrawdopodobienstwo)) || (gPrawdopodobienstwo>(rPrawdopodobienstwo+bPrawdopodobienstwo)) || (bPrawdopodobienstwo>(rPrawdopodobienstwo+gPrawdopodobienstwo))) {
		    		System.out.println("tak");
		    		if ( (rPrawdopodobienstwo>gPrawdopodobienstwo) && (rPrawdopodobienstwo>bPrawdopodobienstwo) ) {
		    			
				    	gPrawdopodobienstwo += 0.25 * rPrawdopodobienstwo;
				    	bPrawdopodobienstwo += 0.25 * rPrawdopodobienstwo;
				    	rPrawdopodobienstwo -= 0.5 * rPrawdopodobienstwo;
				    	
			    	}
		    		else if ( (gPrawdopodobienstwo>rPrawdopodobienstwo) && (gPrawdopodobienstwo>bPrawdopodobienstwo) ) {
		    			
				    	rPrawdopodobienstwo += 0.25 * gPrawdopodobienstwo;
				    	bPrawdopodobienstwo += 0.25 * gPrawdopodobienstwo;
				    	gPrawdopodobienstwo -= 0.5 * gPrawdopodobienstwo;
				    	
			    	}
		    		else if ( (bPrawdopodobienstwo>rPrawdopodobienstwo) && (bPrawdopodobienstwo>gPrawdopodobienstwo) ) {
		    			
				    	rPrawdopodobienstwo += 0.25 * bPrawdopodobienstwo;
				    	gPrawdopodobienstwo += 0.25 * bPrawdopodobienstwo;
				    	bPrawdopodobienstwo -= 0.5 * bPrawdopodobienstwo;
				    	
			    	}
		    		
		    	}
		    	else if ( (rPrawdopodobienstwo >= gPrawdopodobienstwo) && (gPrawdopodobienstwo > bPrawdopodobienstwo) ) {
		    		System.out.println("1");
			    	bPrawdopodobienstwo += 0.25 * rPrawdopodobienstwo;
			    	bPrawdopodobienstwo += 0.25 * gPrawdopodobienstwo;
			    	rPrawdopodobienstwo -= 0.25 * rPrawdopodobienstwo;
			    	gPrawdopodobienstwo -= 0.25 * gPrawdopodobienstwo;
			    	
		    	}
		    	else if ( (bPrawdopodobienstwo >= rPrawdopodobienstwo) && (rPrawdopodobienstwo > gPrawdopodobienstwo) ) {
		    		System.out.println("2");
			    	gPrawdopodobienstwo += 0.25 * rPrawdopodobienstwo;
			    	gPrawdopodobienstwo += 0.25 * bPrawdopodobienstwo;
			    	rPrawdopodobienstwo -= 0.25 * rPrawdopodobienstwo;
			    	bPrawdopodobienstwo -= 0.25 * bPrawdopodobienstwo;
			    	
		    	}
		    	else if ( (gPrawdopodobienstwo >= bPrawdopodobienstwo) && (gPrawdopodobienstwo > rPrawdopodobienstwo)) {
		    		System.out.println("3");
			    	rPrawdopodobienstwo += 0.25 * gPrawdopodobienstwo;
			    	rPrawdopodobienstwo += 0.25 * bPrawdopodobienstwo;
			    	gPrawdopodobienstwo -= 0.25 * gPrawdopodobienstwo;
			    	bPrawdopodobienstwo -= 0.25 * bPrawdopodobienstwo;
			    	
		    	}
		    	
		    	System.out.println("Prawdopodobienstwo po losowaniu wynosi red " + (double) Math.round(rPrawdopodobienstwo * 10000)/100.00 + "%, green " + (double) Math.round(gPrawdopodobienstwo * 10000)/100.00 + "%, blue " + (double) Math.round(bPrawdopodobienstwo * 10000)/100.00 + "%");
		    
		    }
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(timerRunnable, 0, 1, TimeUnit.SECONDS);

	}

}



// co 1 sekunde losuj inny kolor (red, green, blue), wypisuj go na ekranie, 
// w losowaniu zawrzyj mechanzim prawdopodobienstwa z wykorzystaniem proporcji (3, 2, 1) (50%, 33.33%, 16,67%) -> (25%, 33,33%, 41,67%)