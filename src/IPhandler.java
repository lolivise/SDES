import java.util.BitSet;

/**
 * this class is for processing permutation
 * which are IP and inverse IP process
 * **/
public class IPhandler {
	
	// the permutation table from text book
	private final int[] IP_data = {2, 6, 3, 1, 4, 8, 5, 7};
	private final int[] IPinv_data = {4, 1, 3, 5, 7, 2, 8, 6};
	
	public IPhandler(){
		
	}
	
	// IP processing, which input a 8 bits plaintext, 
	// and return a 8 bits permuted text
	public BitSet ip(final BitSet plainText){
		
		//initial a 8 bits BitSet ip as returning value
		BitSet ip = new BitSet(8);
		
		//i indicate the bit in ip
		int i = 0;
		
		//for every index in IP_data
		for(int index:IP_data){
			
			//Set the value to ip with permuted order
			ip.set(i, plainText.get(index-1));
			i++;
		}
		
		//return permuted BitSet
		return ip;
	}
	
	// inverse IP processing, which input a 8 bits permutedText, 
	// and return the permuted 8 bits text
	public BitSet rip(final BitSet permutedText){
		
		//initial a 8 bits BitSet rip as returning value
		BitSet rip = new BitSet(8);
		
		//i indicate the bit in rip
		int i = 0;
		
		//for every index in IPinv_data
		for(int index:IPinv_data){
			
			//Set the value to rip with permuted order
			rip.set(i, permutedText.get(index-1));
			i++;
		}
		
		//return permuted BitSet
		return rip;
	}
}
