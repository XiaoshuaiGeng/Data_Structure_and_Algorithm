
public class Tester {

	public static void main(String[] args) {
		
		CheckOperator n1 = new CheckOperator("(9 ∗ [3 ∗ {[(3 + 3)/5] ∗ 7}])");
		System.out.println(n1.isMatching());
		CheckOperator n2 = new CheckOperator("{3 ∗ (2 + [3 − [4/[6/9]]]})");
		System.out.println(n2.isMatching());
		CheckOperator n3 = new CheckOperator("((3 ∗ (9 − (4 ∗ (6 − 5))))");
		System.out.println(n3.isMatching());
		CheckOperator n4 = new CheckOperator("{2 − {3 ∗ {6/[[[(((9 − 0)))]]]}}/7}");
		System.out.println(n4.isMatching());
	}

}
