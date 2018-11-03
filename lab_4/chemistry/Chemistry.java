/**
 * Name          :
 * Matric number :
 */

import java.util.*;

public class Chemistry {
	private int ele;
	private Stack<Integer> calc;
	private HashMap<Character,Integer> eleMap;

	public Chemistry(){
		ele = 0;
		calc = new Stack<Integer>();
		eleMap = new HashMap<Character,Integer>();
	}
	public static void main(String[] args) {
        //implement your main method here
    	Chemistry chem = new Chemistry();
	chem.run();
    }

    private void run(){
    	Scanner sc = new Scanner(System.in);
	ele = sc.nextInt();
	for(int e=0;e<ele;e++){
		eleMap.put(sc.next().charAt(0),sc.nextInt());
	}
	sc.nextLine();
	String nextLine = sc.nextLine();
	char[] formula = nextLine.toCharArray();
	process(formula);
	int total = accumulate();
	
	System.out.println(total);
	sc.close();
    }

    private void process(char[] formular){
	calc.push(-1);
	for(char c : formular){
		if(c=='('){
			calc.push(-1);
		}
		if(c==')'){
			calc.push(accumulate());
		}
		if(Character.isDigit(c)){
			calc.push(calc.pop()*Character.getNumericValue(c));
		}
		if(Character.isLetter(c)){
			calc.push(eleMap.get(c));
		}
	}
    }

    private int accumulate(){
    	int total = 0;
	while(calc.peek()!=-1){
		total += calc.pop();
	}
	calc.pop();
	return total;
    }
}
