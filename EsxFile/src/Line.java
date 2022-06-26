
public class Line {
	
	String line;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public int compareLine (String line) {
		if (this.line.equals(line)) {
			return 1;
		}
		return 0;
	}

	
	

	
}
