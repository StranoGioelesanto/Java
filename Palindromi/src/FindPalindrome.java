import java.util.Scanner;

public class FindPalindrome {
	
	public static String Reverse(String word) {
		char[] charWord = word.toCharArray();
		String wordReverse = "";
		for (int i=charWord.length-1; i>=0; i--) {
			wordReverse = wordReverse + charWord[i];
		}
		return wordReverse;
	}
	
	public static void main(String [] args) {
		
		Scanner in = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("Inserire parola");
			String word = in.next();
			String wordReverse = FindPalindrome.Reverse(word);
			if (word.equals(wordReverse)) {
				System.out.println("La parola inserita è un palindormo");
			}
			else {
				System.out.println("La parola non è un palindromo");
			}
			System.out.println("Vuoi inserirne un'altra? 1)si 0)no");
			choice = in.nextInt();
			
		}while(choice != 0);
		
	}

}
