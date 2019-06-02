
public class myStack {

	private char[] array = new char[50];
	
	private int top = -1;
	
	public boolean isEmpty() {
		
		if(top != -1)
			return false;
		else
			return true;	
		
	}
	
	public int size() {
		return top + 1;
	}
	
	public char push(char n) {
		if( (top+1) != array.length)
			array[++top] = n;
		
		return n;
	}
	
	public char pop() {
		
		if(!isEmpty()) {
			
			char value = array[top];
			array[top--] = '\0';
			return value;
		}else {
			
			return '\0';
		}
		
	}
}
