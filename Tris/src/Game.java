import java.util.Scanner;

public class Game {
	
	public static void main (String[] args) {
		
		Tris gameTris = new Tris();
		int choice, cont;
		Scanner in = new Scanner(System.in);
		do {
			gameTris.resetMatrice();
			int winner = 0;
			cont = 0;
			Player a = new Player();
			Player b = new Player();
			System.out.println("Inserire username 1° giocatore");
			a.setUsername(in.next());
			System.out.println("Inserire simbolo");
			a.setSymbol(in.next().charAt(0));
			System.out.println("Inserire username 2° giocatore");
			b.setUsername(in.next());
			if (a.getSymbol() == 'X') {
				System.out.println("Il tuo simolo è O");
				b.setSymbol('O');
			}
			else {
				System.out.println("Il tuo simolo è X");
				b.setSymbol('X');
			}
			if (Math.random() < 0.5) {
				System.out.println(a.getUsername() + " inizia la partita");
				a.changeRound();
			}
			else {
				System.out.println(b.getUsername() + " inizia la partita");
				b.changeRound();
			}
			do {
				gameTris.printTris();
				if (a.isRound()) {
					System.out.println(a.getUsername() + "inserisci le coordinate (riga,colonna)");
					gameTris.setMatrice(in.nextInt(), in.nextInt(), a);
					cont++;
					winner = gameTris.isWinner();
				}
				else if(b.isRound()) {
					System.out.println(b.getUsername() + "inserisci le coordinate (riga,colonna)");
					gameTris.setMatrice(in.nextInt(), in.nextInt(), b);
					cont++;
					winner = gameTris.isWinner();
				}
				a.changeRound();
				b.changeRound();
				
			}while(winner == 0 && cont<9);
			
			gameTris.printTris();
			if (winner == 1) {
				if(!a.round) {
					System.out.println(a.getUsername() + " è il vincitore");
				}
				else {
					System.out.println(b.getUsername() + " è il vincitore");
				}
			}
			else {
				System.out.println("Pareggio");
			}
			
			System.out.println("Vuoi iniziare un'altra partita? 1) si 0) no");
			choice = in.nextInt();
			
				
			
		}while(choice != 0);
		
	}

}
