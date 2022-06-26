
import java.util.Random;

public class Main {
	
	public static void main (String [] args) {
		
		boolean sorted = false;
		int rCompare;
		int swap = 0;
		int counter = 0;
		Random r = new Random();
		Persona[] listPersona = new Persona[5];
		for (int i=0; i<5; i++) {
			listPersona [i] = new Persona();
			listPersona[i].setEta(r.nextInt(20));
		}
		for (int i=0; i<listPersona.length; i++) {
			System.out.println(listPersona[i].getEta());
		}
		while (sorted == false) {
			sorted = true;
			for (int i=0; i<listPersona.length - 1 - counter; i++) {
				rCompare = listPersona[i].compare(listPersona[i+1]);
				if (rCompare == 1) {
					swap = listPersona[i].getEta();
					listPersona[i].setEta(listPersona[i+1].getEta());
					listPersona[i+1].setEta(swap);
					sorted = false;
				}
			}
			counter++;
		}
		
		for (int i=0; i<listPersona.length; i++) {
			System.out.println(listPersona[i].getEta());
		}
		
		
		
	}

	}
