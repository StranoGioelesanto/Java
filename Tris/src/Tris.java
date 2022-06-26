

public class Tris {
	
	char [][] matrice = new char [][] {
		{' ', ' ', ' '},
		{' ', ' ', ' '},
		{' ', ' ', ' '},
		
	};
	
	
	public void setMatrice(int riga, int colonna, Player p){
		matrice[riga][colonna] = p.symbol;
		
	}
	
	public void printTris () {
		System.out.println(matrice[0][0] + " | " + matrice[0][1] + " | " + matrice[0][2]);
		System.out.println("----------");
		System.out.println(matrice[1][0] + " | " + matrice[1][1] + " | " + matrice[1][2]);
		System.out.println("----------");
		System.out.println(matrice[2][0] + " | " + matrice[2][1] + " | " + matrice[2][2]);
	}
	
	public int isWinner() {
		if ((matrice[0][0] == 'X' && matrice[1][1] == 'X' && matrice[2][2] == 'X') || (matrice[0][0] == 'O' && matrice[1][1] == 'O' && matrice[2][2] == 'O')) {
			return 1;
		}
		if ((matrice[0][2] == 'X' && matrice[1][1] == 'X' && matrice[2][0] == 'X') || (matrice[0][2] == 'X' && matrice[1][1] == 'X' && matrice[2][0] == 'X')) {
			return 1;
		}
		for (int i=0; i<3; i++) {
			if ((matrice[i][0] == 'X' && matrice[i][1] == 'X' && matrice[i][2] == 'X') || (matrice[i][0] == 'O' && matrice[i][1] == 'O' && matrice[i][2] == 'O')) {
				return 1;
			}
			if ((matrice[0][i] == 'X' && matrice[1][i] == 'X' && matrice[2][i] == 'X') || (matrice[0][i] == 'O' && matrice[1][i] == 'O' && matrice[2][i] == 'O')) {
				return 1;
			}
		}
		return 0;
		
	}
	
	public void resetMatrice () {
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				this.matrice[i][j] = ' ';
			}
		}

}
}
