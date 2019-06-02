//import java.io.*;
public class CheckOperator {
	
	String sentence;
	
	public CheckOperator(String sentence) {
		
		this.sentence = sentence;
	}
	
	public boolean isMatching() {
		
		//String test = new String("(9 ∗ [3 ∗ {[(3 + 3)/5] ∗ 7}])");
		// (9 ∗ [3 ∗ {[(3 + 3)/5] ∗ 7}])
		//{3 ∗ (2 + [3 − [4/[6/9]]]})
		//{2 − {3 ∗ {6/[[[(((9 − 0)))]]]}}/7}
		
		myStack s1 = new myStack();
		
		String opening = new String("{[(");
		String closing = new String("}])");
		char array[] = sentence.toCharArray();
		for(char c: array) {
			if(opening.indexOf(c) != -1) {
				
				s1.push(c);
				
			}else if(closing.indexOf(c)!= -1) {
				
				if(s1.isEmpty()) {
					
					return false;
				}
				
				if(closing.indexOf(c) != opening.indexOf(s1.pop())) {
					
					return false;
					
				}
			}
			
			
		}
		
		return s1.isEmpty();
	}

}
