/**
 * Name          :
 * Matric number :
 */

import java.util.*;

public class Cake {
	private Queue<String> cakeType;
	private Queue<Integer> choc;
	private int cake_num;
	private int maxRasin;
	private int maxChoc;
	private int currRasin;
	public Cake(){
		cakeType =new LinkedList<String>();
		choc =new LinkedList<Integer>();
	}
	public static void main(String[] args) {
		//implement your main method here
		Cake ca = new Cake();
		ca.run();			
	}

	private void run(){
		Scanner sc = new Scanner(System.in);
		cake_num = sc.nextInt();
		maxRasin = sc.nextInt();
		for(int c=0;c<cake_num;c++){
			String type = sc.next();
			int chocIn = sc.nextInt();
			int currChoc = 0;
			//check cakeType
			if(type.equals("C")){
				cakeType.offer("C");
				choc.offer(chocIn);
			}
			if(type.equals("R")){
				currRasin++;
				if(currRasin>maxRasin){
					takeOneRasin();
					currRasin--;
				}

				cakeType.offer("R");
				choc.offer(chocIn);
			}
			currChoc = getChocolate();
			if(currChoc>maxChoc) maxChoc = currChoc;
		}
		System.out.println(maxChoc);
	}

	public void takeOneRasin(){
		if(!cakeType.isEmpty()){
			while(cakeType.poll().equals("C"))
				choc.poll();
		choc.poll();
		}
	}

	public int getChocolate(){
		int sum = 0;
		for(Integer i:choc) sum+=i;
		return sum; 	
	}
}
