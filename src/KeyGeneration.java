import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * this class is for S-DES key generation, it is able to determine
 * whether the key is eligible, and return the sub-key for further
 * computation
 * **/
public class KeyGeneration {
	
	// 10 bits key
	private BitSet originalKey;
	
	//table for P10 permutation
	private final int[] P10_data = {3,5,2,7,4,10,1,9,8,6};
	
	//table for P8 permutation
	private final int[] P8_data = {6,3,7,4,8,5,10,9};
	
	
	public KeyGeneration(String givenKey){
		// Check the validity of the key by regular expression
		String regex = "[0,1]{10}";
		if (!givenKey.matches(regex)){
			throw new IllegalArgumentException("The key must be a String containing only 10 0's or 1's");
		}
		
		// Convert the string key to a BitSet
		BitSet key = new BitSet(10);
		for(int i=0; i < 10; i++){
			if(givenKey.charAt(i) == '1') key.set(i);
		}
		
		//assign the key to field originalKey for further computation
		originalKey = key;
	}
	
	
	// Process of S-DES Key generation and return the two sub-key key1 and key2
	public List<BitSet> generateKeys(){
		
		//permuted the order of the key
		BitSet perKey = p10(originalKey);
		
		//left shift permuted key by 1
		BitSet ls1 = leftShift(perKey, 1);
		
		//permuted the ls1 to 8 bits and assign the result to key1
		BitSet key1 = p8(ls1);
		
		//left shift 2 bits of ls1
		BitSet ls2 = leftShift(ls1,2);
		
		////permuted the ls2 to 8 bits and assign the result to key1
		BitSet key2 = p8(ls2);
		
		//initial a BitSet list to store key1 and key2
		List<BitSet> keys = new ArrayList<>();
		keys.add(key1);
		keys.add(key2);
		
		//return the key
		return keys;
	}
	
	//process 10 bits permutation with table P10_data
	private BitSet p10(final BitSet key){
		
		//initial a BitSet permutedKey for storing return result
		BitSet permutedKey = new BitSet(10);
		
		int i = 0;
		
		//for every index in table P10_data
		for(int index : P10_data){
			
			//set the value of ith bit of permutedKey to the value of relative bit of intput key
			permutedKey.set(i, key.get(index-1));
			i++;
		}
		
		//return result
		return permutedKey;
	}
    
	//process of circular left shifting
	private BitSet leftShift(final BitSet key, int shift){
		
		//initial a BitSet s for storing return result
		BitSet s = new BitSet(10);
		
		// for the first five bits of the key
		for (int i=0; i < 5; i++){
			//get the boolean value of current bit
			boolean value = key.get(i);
			
			//calculate the index of target bit that the value need to set to
			int shiftedIndex = i - shift;
			
			//if index is less than 0, add index with 5
			if (shiftedIndex < 0){
				//set the value to target bit
				if(value){
					s.set(5 + shiftedIndex);
				}else{
					s.clear(5 + shiftedIndex);
				}
			}else{
				if(value){
					s.set(shiftedIndex);
				}else{
					s.clear(shiftedIndex);
				}
			}
		}
		
		// for the last five bits of the key
		for (int i=5; i < 10; i++){
			
			//get the boolean value of current bit
			boolean value = key.get(i);
			
			//calculate the index of target bit that the value need to set to
			int shiftedIndex = i - shift;
			
			//if index is less than 5, add index with 5
			if (shiftedIndex < 5){
				if(value){
					s.set(5+shiftedIndex);
				}else{
					s.clear(5+shiftedIndex);
				}
			}else{
				if(value){
					s.set(shiftedIndex);
				}else{
					s.clear(shiftedIndex);
				}
			}
		}
		
		//return the result
		return s;
	}
	
	//process 10 bits permutation with table P8_data
	private BitSet p8(final BitSet key){
		
		//initial a BitSet permutedKey for storing return result
		BitSet permutedKey = new BitSet(8);
		
		int i = 0;
		
		//for every index in table P10_data
		for(int index : P8_data){
			
			//set the value of ith bit of permutedKey to the value of relative bit of intput key
			permutedKey.set(i, key.get(index-1));
			i++;
		}
		
		//return result
		return permutedKey;
	}
	
}
