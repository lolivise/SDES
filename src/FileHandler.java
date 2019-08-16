import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * this class is use to handle file processing
 * **/
public class FileHandler {

	private final String fileName = "testfile-SDES.txt";
	
	public FileHandler(){
		
	}
	
	//return the text in the file
	public List<String> readFile(){
			
		List<String> lines = new ArrayList<>();;
		String line = null;
		try {
            // FileReader reads text file.
            FileReader fileReader = new FileReader(fileName);

            // Wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
            //read every line in the file
            while((line = bufferedReader.readLine()) != null) {
            	//append text in the list lines
            	lines.add(line);
            }   

            //Close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
        	ex.printStackTrace();                
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
			
		//return text
		return lines;
	}
		
	//write a String list to a file
	public void writeFile(String filename, List<String> lines) throws IOException{
		//use BufferWiter wrap with FileWriter
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		// i indicate the index of list lines
		for(int i = 0; i<lines.size();i++){
			if(i!=0){
				//if the current line is not the first line
				//shift to a new line before write the text
				writer.newLine();
				writer.write(lines.get(i));
			}else{
				//else write the text to the file
				writer.write(lines.get(i));
			}
			
		}
		//close file
		writer.close();
	}
}
