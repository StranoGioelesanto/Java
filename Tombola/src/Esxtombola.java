import java.util.Random;

public class Esxtombola {
	
	


public static void main (String [] args) {
	
	Random r = new Random();
	int total = 10;
	int [] numbers = new int [total];
	int temp; 
	
	for (int i=0; i<total; i++) {
		numbers[i] = i+1;
	}
	
	for (int i=total-1; i >= 0; i--) {
		
		int estratto = r.nextInt(i+1);
		System.out.println(numbers[estratto]);
		if (numbers[estratto] != numbers[i]) {
			
			temp = numbers[estratto];
			numbers[estratto] = numbers [i];
			numbers[i] = temp;
		
		}
	}
}

}
