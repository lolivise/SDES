import java.util.List;
/**
 * this class is for checking whether the two lists
 * contain same text
 * **/
public class MatchChecker {

	public MatchChecker(){}
	
	// input two String list and return whether two list is matched.
	public boolean isMatch(List<String> a, List<String> b){
		
		//if two list have different size, return false
		if(a.size()!=b.size()){
			return false;
		}
		
		//for every sting in list a
		for(int i = 0; i<a.size();i++){
			//if the ith string of a is not equal to ith string of b, return false
			if(!a.get(i).equals(b.get(i))){
				return false;
			}
		}
		
		//if all test are passed, return true
		return true;
	}
}
