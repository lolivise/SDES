import java.util.BitSet;

/**
 * this class is use to process fk function
 * **/
public class FKfunction {
	
	//table of permutation p4
	private final int[] P4_data = {2, 4, 3, 1};
	
	//table of expend permutation
	private final int[] EP_data = {4, 1, 2, 3, 
			   					   2, 3, 4, 1};
	
	//table of S-box 0
	private final int[][] S0_data = {{1, 0, 3, 2}
									,{3, 2, 1, 0}
									,{0, 2, 1, 3}
									,{3, 1, 3, 2}};
	//table of S-box 1
	private final int[][] S1_data = {{0, 1, 2, 3}
									,{2, 0, 1, 3}
									,{3, 0, 1, 0}
									,{2, 1, 0, 3}};
	
	

	public FKfunction(){
		
	}
	
	//fk function
	public BitSet fK(BitSet msgBits, BitSet key){
		
		//send the key and last four bits of megBit to f function
		BitSet fVal = f(key,msgBits.get(4,8));
		
		//clone first four bit of megBit to BitSet x
		BitSet x = (BitSet)msgBits.get(0,4).clone();
		
		//compute XOR with x and fVal, the n assign to x 
		x.xor(fVal);
		
		//initial a 8 bits BitSet to restore return value
		BitSet returnVal = new BitSet(8);
		
		//set first four bits as x
		for(int i=0; i<4;i++){
			if(x.get(i)){
				returnVal.set(i);
			}
		}
		
		//set last four bit as the last four bits of msgBits
		for(int i=4; i<8;i++){
			if(msgBits.get(i)){
				returnVal.set(i);
			}
		}
		
		//return the result
		return returnVal;
	}
	
	// process f(SK,R)
	private BitSet f(BitSet sk,BitSet r){
		
		//expend r to 8 bit with expend permute function
		BitSet ep = expendPermute(r);
		
		//Clone ep to xor
		BitSet xor = (BitSet)ep.clone();
		
		//compute XOR operation with xor and sk
		xor.xor(sk);
		
		BitSet s0 = sandbox(xor.get(0, 4), S0_data);
		BitSet s1 = sandbox(xor.get(4, 8), S1_data);
		
		//process p4 permutation with s0 and s1 and return the result
		return p4(s0,s1);
	}
	
	//Expend a 4 bits input BitSet to 8 bits BitSet
	private BitSet expendPermute(final BitSet input){
		
		//initial a 8 bit BitSet ep to restore the result
		BitSet ep = new BitSet(8);
		
		int i = 0;
		//for every permutation index in EP_data table
		for(int index: EP_data){
			
			//set the ith bit of ep to relative value of input
			ep.set(i, input.get(index-1));
			i++;
		}
		
		//return the result
		return ep;
	}
	
	//process sandbox transformation
	private BitSet sandbox(final BitSet input, final int[][] s){
		
		// Store every bits of input BitSet
		boolean p0, p1, p2, p3;
		p0 = input.get(0);
		p1 = input.get(1);
		p2 = input.get(2);
		p3 = input.get(3);
		
		//use first and last bit to determine the row number
		int row=0;
		if (!p0 && !p3){
			row = 0;
		}else if (!p0 && p3){
			row = 1;
		}else if (p0 && !p3){
			row = 2;
		}else{
			row = 3;
		} 
		
		//use second and third bits to determine the column number
		int col=0;
		if (!p1 && !p2){
			col = 0;
		}else if (!p1 && p2){
			col = 1;
		}else if (p1 && !p2){
			col = 2;
		} else{ 
			col = 3;
		}
		
		//get the value on SBox with the row and column, 
		//and convert it to binary string
		String binary = Integer.toBinaryString(s[row][col]);
		
		//initial a 2 bits BitSet to store the return value
		BitSet result = new BitSet(2);
		
		//insure the 0 is at the front if the binary string is less than 2 bits
		int index = 1;
		for(int i = binary.length()-1; i >= 0; i--){
			if (binary.charAt(i) == '1'){
				result.set(index);
			}
			index--;
		}
		
		//return the result
		return result;
	}
	
	
	//process the p4 permutation
	private BitSet p4(final BitSet s0, final BitSet s1){
		
		//initial a 4 bits BitSet originalP4 to store the input s0 and s1
		BitSet originalP4 = new BitSet(4);
		originalP4.set(0,s0.get(0));
		originalP4.set(1,s0.get(1));
		originalP4.set(2,s1.get(0));
		originalP4.set(3,s1.get(1));
		
		//initial a 4 bits BitSet p4 to store the final result of permutation
		BitSet p4 = new BitSet(4);
		
		int i = 0;
		
		//for every index in table P4_data
		for(int index: P4_data){
			//set the value of ith bit of p4 to the value of relative bit of originalP4
			p4.set(i, originalP4.get(index-1));
			i++;
		}
		
		//return final result
		return p4;
	}
	
	
	
	
	
}
