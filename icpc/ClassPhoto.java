/*name :litian Zhou
 * Matric. No :A0174913B
 * PLab Acct. :n.a.
 */
import java.util.*;
public class ClassPhoto {
	private LinkedList<Stu> stu;
	private int queryNum;

	public ClassPhoto() { //constructor
		stu = new LinkedList<Stu>();
		queryNum = 0;
	}


	private void run() {//implement your "main" method here
		Scanner sc = new Scanner(System.in);
		queryNum = sc.nextInt();
		for(int q=0;q<queryNum;q++){
			String query = sc.next();
			if(query.equals("arrive")){
				Stu temp = new Stu(sc.next(),sc.nextInt());
				if(stu.size()==0) stu.add(temp);
				else{
					boolean unrivalled = true;
					for(Stu s: stu){
						if(temp.height<=s.height) {
							stu.add(stu.indexOf(s), temp);
							unrivalled = false;
							break;
						}
						if(unrivalled == true) stu.add(temp);
					}
				}
				System.out.println(temp.name+" is added at position "+stu.indexOf(temp)+1);
			}
			if(query.equals("leave")){
				String temp = sc.next();
				boolean exist = false;
				for(Stu s: stu){
				 if(s.name.equals(temp)){
				 	int position = stu.indexOf(s)+1;
					stu.remove(s);
					System.out.println(temp+" has left position "+position);
				 	exist = true;
				 }
				}
				if(exist == true) System.out.println("No student with name "+temp);
			}
			if(query.equals("shortest")){
				int position = sc.nextInt();
				int shortest=stu.getFirst().height;
				int count =1;
				String list="";
				for(int i=1;i<stu.size();i++){
					if(stu.get(i).height!=shortest){
						shortest = stu.get(i).height;
						count++;
						if(count==position) break;
					}
					//
				}
				for(Stu s:stu){
					if(s.height==shortest) list+=(" "+s.name);
				}
				System.out.println(list);
			}
			if(query.equals("count")){
				int count = 0;
				int low = sc.nextInt();
				int high = sc.nextInt();
				for(Stu s:stu){
					if(s.height<=high&&s.height>=low) count++;
				}
				System.out.println(count);
			}
		}

	}
	public static void main(String[] args) {
		 ClassPhoto newClassPhoto = new ClassPhoto(); newClassPhoto.run();
	}
}

class Stu{
	public String name;
	public int height;

	public Stu(String name, int height){
		this.name = name;
		this.height = height;
	}
}
