/**
 * Name			:
 * Matric No.	:
 */
import java.util.*;

public class Love {
	private LinkedList<String> loveLetter;
	private HashMap<String,Integer> stringOccurrence;

	public Love(){
		loveLetter = new LinkedList<String>();
		stringOccurrence = new HashMap<String,Integer>();
	}

	public void run() {
		// treat this as your "main" method
		Scanner sc = new Scanner(System.in);
		int stringNum = sc.nextInt();
		for(int i=0;i<stringNum;i++){
			String oneWord = sc.next();
			loveLetter.add(oneWord);
			if(stringOccurrence.keySet().contains(oneWord)){
				int occurrence = stringOccurrence.get(oneWord);
				stringOccurrence.put(oneWord,++occurrence);
			}
			if(!stringOccurrence.keySet().contains(oneWord)){
				stringOccurrence.put(oneWord,1);
			}
		}
		//deep copy loveletter to loveLetter2:
		LinkedList<String> loveLetter2 = new LinkedList<String>(loveLetter);
		//deep copy stringOccurrence to stringOccurrence2:
		HashMap<String,Integer> stringOccurrence2 = new HashMap<String,Integer>(stringOccurrence);

		//caculate front trim first:
		//front trim
		int index = 0;
		while(stringOccurrence.get(loveLetter.get(index)) > 1){
			String rem = loveLetter.removeFirst();
			int occurrence = stringOccurrence.get(rem);
			stringOccurrence.put(rem,--occurrence);
		}

		//backward trim
		index = loveLetter.size()-1;
		while(stringOccurrence.get(loveLetter.get(index)) > 1){
			String rem = loveLetter.removeLast();
			int occurrence = stringOccurrence.get(rem);
			stringOccurrence.put(rem,--occurrence);
			index--;
		}
		int minLength1=0;
		for(String s:loveLetter){
			minLength1 += s.length();
		}


		//caculate backward trim first:
		//backward trim
		index = loveLetter2.size()-1;
		while(stringOccurrence2.get(loveLetter2.get(index)) > 1){
			String rem = loveLetter2.removeLast();
			int occurrence = stringOccurrence2.get(rem);
			stringOccurrence2.put(rem,--occurrence);
			index--;
		}

		index = 0;
		while(stringOccurrence2.get(loveLetter2.get(index)) > 1){
			String rem = loveLetter2.removeFirst();
			int occurrence = stringOccurrence2.get(rem);
			stringOccurrence2.put(rem,--occurrence);
		}

		int minLength2=0;
		for(String s:loveLetter2){
			minLength2 += s.length();
		}

		System.out.println(Math.min(minLength1,minLength2));
	}

	public static void main(String[] args) {
		Love myLove = new Love();
		myLove.run();
	}

}
