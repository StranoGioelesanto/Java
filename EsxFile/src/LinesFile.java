import java.util.ArrayList;

public class LinesFile extends Line{
	
	ArrayList<Line> lines = new ArrayList<Line>();

	public ArrayList<Line> getLines() {
		return lines;
	}

	public void setLines(ArrayList<Line> lines) {
		this.lines = lines;
	}
	public void addLine(Line line) {
		lines.add(line);
	}
	public void printLines() {
		for (Line line : lines ) {
			System.out.println(line.getLine());		
		}
	}
	public void compareAllLine () {
		for (int i=0; i<lines.size(); i++) {
			int cont = 0;
			for (int j=0; j<lines.size(); j++) {
			
				int val = lines.get(i).compareLine(lines.get(j).getLine());
				
				if (val == 1) {
					
					cont++;	
				}
				
				if (cont > 1) {
					
					lines.remove(j);
				}	
				
			}
	   }
		
	}


	
}
