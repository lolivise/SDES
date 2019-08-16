import java.util.BitSet;
/**
 * this class is use to process seitch function,
 * which exchange the first four bits of the input BitSet
 * to its last four bits
 * **/
public class SwitchFunction {
	public SwitchFunction(){}
	
	//input a 8 bits BitSets and return the exchanged Bitset
	public BitSet sw(BitSet input){
		
		//initial a 8 bits BitSet to Store inverse result
		BitSet inverse = new BitSet(8);
		
		//for the first four bits of input set to last four bit of inverse
		for(int i=0; i<4;i++){
			if(input.get(i)){
				inverse.set(i+4);
			}
		}
		
		//for the last four bit of input, set to first four bit of inverse 
		for(int i=4; i<8;i++){
			if(input.get(i)){
				inverse.set(i-4);
			}
		}
		
		//return the result inverse
		return inverse;
	}
}
