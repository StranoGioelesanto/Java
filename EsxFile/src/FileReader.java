import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 
public class FileReader {

	    public static void main(String[] args) throws FileNotFoundException {
	    	
	            LinesFile lsFile = new LinesFile();
	            File file = new File("C:\\Users\\Gioele\\Desktop\\fileEsx.csv");
	            Scanner fileReader = new Scanner(file); 
	            while (fileReader.hasNextLine()) {
		            Line lineFile = new Line();
	            	lineFile.setLine(fileReader.nextLine());
	                lsFile.addLine(lineFile); 
	            }
	            lsFile.printLines();
	            fileReader.close();
	            lsFile.compareAllLine();
	            lsFile.printLines();
	            
	            

	    }
}

