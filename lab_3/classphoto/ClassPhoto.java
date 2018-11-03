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
					boolean shortest = true;
					for(int j=0;j<stu.size();j++){
						if(temp.height>=stu.get(j).height) {
							stu.add(j, temp);
							shortest = false;
							break;
						}
					}
					if(shortest == true){
						 stu.addLast(temp);
					}
				}
				System.out.println(temp.name+" added at "+(stu.indexOf(temp)+1));
			}
			if(query.equals("leave")){
				String temp = sc.next();
				boolean exist = false;
				for(int k=0;k<stu.size();k++){
				 if(stu.get(k).name.equals(temp)){
				 	int position = k+1;
					stu.remove(k);
					System.out.println(temp+" has left position "+position);
				 	exist = true;
				 }
				}
				if(exist == false) System.out.println("No student with name "+temp);
			}
			if(query.equals("shortest")){
				int position = sc.nextInt();
				if(position>stu.size()) System.out.println("No such student");
				else{
					int shortest=stu.getLast().height;
					int count =1;
					String list="";
					for(int i=stu.size()-2;i>=0;i--){
						if(count==position) break;
						if(stu.get(i).height!=shortest){
							shortest = stu.get(i).height;
							count++;
						}
					}
					if(count<position){
						System.out.println("No such student");
					}
					else{
						String gap = "";
						for(Stu s:stu){
							if(s.height==shortest){
								 list+=(gap+s.name);
								gap=" ";
							}
						}
					System.out.println(list);
					}
				}
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
