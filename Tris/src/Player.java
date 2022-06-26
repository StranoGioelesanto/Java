
public class Player {
	
	String Username;
	char symbol;
	boolean round = false;
	

	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	public void changeRound() {
		this.round = !round; 
	}
	public boolean isRound() {
		return round;
	}
	
	
		
	

	
}

