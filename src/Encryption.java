import java.util.BitSet;
import java.util.List;

/**
 * this class process the encryption, which input a character
 * and convert it to binary String and compute the process of 
 * encryption then return the encrypted character.
 * **/
public class Encryption {
	
	private KeyGeneration keyGeneration;
	private FKfunction fkfunction;
	private SwitchFunction switchFunction;
	private IPhandler iPhandler;
	
	public Encryption(String givenKey){
		//pass the given key to process key generation
		keyGeneration = new KeyGeneration(givenKey);
		
		iPhandler = new IPhandler();
		fkfunction = new FKfunction();
		switchFunction = new SwitchFunction();
	}
	
	//input the character to encrypt and return the encrypted character
	public char encrypt(char c){
		//get the sub-Keys from key generation function
		List<BitSet> keys = keyGeneration.generateKeys();
		
		//assign the sub-keys to key1 and key2
		BitSet key1 = keys.get(0);
		BitSet key2 = keys.get(1);
		
		//convert input character to binary string
		String binary = Integer.toBinaryString((int)c);	
		
		//initial a 8 bits BitSet to the restore binary string
		BitSet b = new BitSet(8);
		
		//insure the 0 is at the front if the binary string is less than 8 bits
		int index = 7;
		for(int i = binary.length()-1; i >= 0; i--){
			if (binary.charAt(i) == '1'){
				b.set(index);
			}
			index--;
		}
		
		//process initial permutation 
		BitSet ip = iPhandler.ip(b);
		
		//process fk function with ip and key1 as parameters
		BitSet fk1 = fkfunction.fK(ip, key1);
		
		//process switch function with parameter fk1
		BitSet switchedBits = switchFunction.sw(fk1);
		
		//process fk function with parameters switched bits and key2
		BitSet fk2 = fkfunction.fK(switchedBits, key2);
		
		//process inverse permutation with parameters fk2
		BitSet enc = iPhandler.rip(fk2);
		
		//initial a StringBuilder res to restore the encrypted bits
		StringBuilder result = new StringBuilder();
		for (int i=0; i<8; i++){
			if (enc.get(i)){
				result.append("1");
			}else{
				result.append("0");
			}
		}
		//convert binary result to integer
		int i = Integer.parseInt(result.toString(), 2);
		
		//convert integer to char and return it
		return (char)i;
	}

}
