import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SDES {	
	
	//Set the original key as provide in text book 1010011010
	private static final String key = "1111111111";
	    
	public static void main(String[] args) throws IOException {
		
		//init lists to store original text, encrypted text and decrypted text
		List<String> orgText = new ArrayList<>();
		List<String> enText = new ArrayList<>();
		List<String> deText = new ArrayList<>();
		
		FileHandler fileHandler = new FileHandler(){};
		
		//read the text from test file
		orgText = fileHandler.readFile();
		
		//pass the key to encryption process
    	Encryption encryption = new Encryption(key);
    	
    	//for every string in the original text list
    	for(String text: orgText){
    		
    		//initial a StringBuilder to store decrypted text
    		StringBuilder encMsg = new StringBuilder();
    		
    		// for every character in the current String
    		for (int i=0; i<text.length();i++){
    			
    			//encrypt every character in the string
        		encMsg.append(encryption.encrypt(text.charAt(i)));
        	}  
    		
    		//append encrypted string to the encrypted text list
    		enText.add(encMsg.toString());
    	}
    	
    	// write the encrypted text list to the file Encrypt_Msg.txt
    	fileHandler.writeFile("Encrypt_Msg.txt", enText);
    	
    	
    	//pass the key to decryption process
    	Decryption decryption = new Decryption(key);
    	
    	//for every string in the encrypted text list
    	for(String t: enText){
    		
    		//initial a StringBuilder to store decrypted text
    		StringBuilder decMsg = new StringBuilder();
    		
    		// for every character in the current String
    		for (int i=0; i<t.length();i++){
    			
    			//encrypt every character in the string, and append to decMsg
        		decMsg.append(decryption.decrypt(t.charAt(i)));
        	}
    		
    		//append encrypted string to the decrypted text list
    		deText.add(decMsg.toString());
    	}
    	
    	// write the decrypted text list to the file Decrypt_Msg.txt
    	fileHandler.writeFile("Decrypt_Msg.txt", deText);
    	
    	MatchChecker matchChecker = new MatchChecker();
    	
    	// complare the text in the original text list and decrypted text list
    	if(matchChecker.isMatch(orgText, deText)){
    		
    		//if match, print successful message
	       	System.out.println("Success: Decrypt text is matches to original text.\n");
	    }else{
	    	
	    	//if not match, print failure message
	       	System.out.println("Failure: Decrypt text is not matches to original text.\n");
    	}   	
    	
    	System.out.println("Done!");
	}

}
