import java.util.*;

public class Customs {
    private LinkedList<Integer> queue;
    private LinkedList<Integer> blockers;
    private LinkedList<Integer> canSeeHeight;
    public Customs(){
    	queue = new LinkedList<Integer>();
	blockers = new LinkedList<Integer>();
    	canSeeHeight = new LinkedList<Integer>();
	canSeeHeight.push(0);
    }

    private void run() {
    	Scanner sc = new Scanner(System.in);
	int query = sc.nextInt();
	for(int q = 0; q<query;q++){
		String operation = sc.next();
		if(operation.equals("join")){
			join(sc.nextInt());
			System.out.println(blockers.size());
		}
		if(operation.equals("leave")){
			leave(sc.nextInt());
			System.out.println(blockers.size());	
		}
	}
    }
    
    private void join(int height){
    	queue.offer(height);
	if(height>canSeeHeight.peek()){
		canSeeHeight.push(height);
		blockers.push(queue.size()-1);		
	}
    }

    private void leave(int people){
    	int lastIndex = queue.size()-1-people;
	while(lastIndex<blockers.peek()){
		blockers.pop();
		canSeeHeight.pop();
		if(blockers.size()==0)
			break;
	}
	for(int i=0;i<people;i++){
		queue.pollLast();
	}
    }


    public static void main(String[] args) {
        Customs newCustoms = new Customs();
        newCustoms.run();
    }
    
}
