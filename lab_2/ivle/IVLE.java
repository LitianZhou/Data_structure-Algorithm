/**
* name : Zhou Litian
* Matric No. : A0174913B
* PLab Acct. : none
*/

import java.util.*;

public class IVLE {
    // define attributes, constructor, and methods here
    private static int highestMc = 0;
    private static int stuCounter = 0;
    private int modNum;
    private int queryNum;
    private HashMap<String,Module> moduleList;
    private HashMap<String,Student> studentList;

    private void run() {
      Scanner input = new Scanner(System.in);
      modNum = input.nextInt();
      queryNum = input.nextInt();
      moduleList = new HashMap<String,Module>(modNum);
      studentList = new HashMap<String,Student>();

      for(int i=0;i<modNum;i++){
        String modName = input.next();
        moduleList.put(modName,(new Module(modName,input.nextInt(),input.nextInt())));
      }
      for(int i=0;i<queryNum;i++){
        String query = input.next();

        if(query.equals("total")){
          int total = 0;
          for(String stu:studentList.keySet()){
              if(studentList.get(stu).getMc()>0){
                  total++;
              }
          }
          if(total!=0) {
            System.out.println(total);
          }
          else {
            System.out.println("No students registered for modules");
          }
        }

        if(query.equals("register")){
            String stuName = input.next();
            if(!studentList.containsKey(stuName)){
                studentList.put(stuName,(new Student(stuName)));
            }
            System.out.println(moduleList.get(input.next()).register(stuName,studentList.get(stuName)));
         }

        if(query.equals("details")){
            String stuName = input.next();
            if(!studentList.containsKey(stuName)){
                studentList.put(stuName,(new Student(stuName)));
            }
            System.out.println(studentList.get(stuName).details());
        }

        if(query.equals("remove")){
            String stuName = input.next();
            System.out.println(moduleList.get(input.next()).remove(stuName,studentList.get(stuName)));
        }

        if(query.equals("highest")){
            highestMc = 0;
            TreeSet<String> crazyStudents = new TreeSet<String>();
            for(String stu: studentList.keySet()){
                if(studentList.get(stu).getMc()>highestMc){
                    highestMc = studentList.get(stu).getMc();
               }
             }
             for(String stu: studentList.keySet()){
                if(studentList.get(stu).getMc()==highestMc){
                    crazyStudents.add(stu);
                }
             }
             if(highestMc == 0) System.out.println("No students registered for modules");
             else System.out.println(highestMc+" "+crazyStudents.toString().replace("[","").replace("]","").replace(",",""));//just wonderful
        }
      }
}
   /** public IVLE(){//default  constructor
        Scanner input = new Scanner(System.in);
        modNum = input.nextInt();
        queryNum = input.nextInt();
    }
    */
    public static void main(String[] args) {
        IVLE ivle = new IVLE();
        ivle.run();
    }
}

class Module {
   // define your own attributes, constructor, and methods here
   private String modName;
   private int mc;
   private int capacity;
   private int registerStuNum;
   private HashMap<String,Student> registerStu;
   public Module(String modName,int mc,int capacity){
        this.modName = modName;
        this.mc = mc;
        this.capacity = capacity;
        registerStuNum = 0;
        registerStu = new HashMap<String,Student>(capacity);
    }

    public String register(String stuName,Student stu){
        if(registerStu.containsKey(stuName)){
            return stuName+" is already registered into "+this.modName;
        }
        if(registerStuNum>=capacity){
            return this.modName+" is full";
        }
        else{
            registerStu.put(stuName,stu);
            registerStuNum++;
            stu.addMod(this.modName);
            stu.addMc(this.mc);
            return stuName+" successfully registered into "+this.modName;
        }
    }

    public String remove(String stuName, Student stu){
        if(registerStu.containsKey(stuName)){
            registerStu.remove(stuName, stu);
            registerStuNum--;
            stu.rmMod(this.modName);
            stu.deductMc(this.mc);
            return stuName+" has been removed from "+this.modName;
        }
        else return stuName+" is not registered into "+this.modName;
    }

    public int getRegisterStuNum(){
        return registerStuNum;
    }
}
class Student {
    // define your own attributes, constructor, and methods here
    private String stuName;
    private int mc;
    private ArrayList<String> registerMod = new ArrayList<String>();

    public Student(String stuName){
        this.stuName = stuName;
    }

    public void addMod(String modName){
        registerMod.add(modName);
    }
    public void rmMod(String modName){
        registerMod.remove(modName);
    }
    public int getMc(){
        return mc;
    }
    public void addMc(int modMc){
        this.mc += modMc;
        // System.out.println(modMc);
        // System.out.println(this.mc); Teddy: must create a new student!!! Genius！
    }
    public void deductMc(int modMc){
        this.mc -= modMc;
    }
    public String details(){
        if(this.mc<=0){
            return this.stuName+" has no modules";
        }
        Collections.sort(this.registerMod);
        return this.mc+" "+this.registerMod.toString().replace("[","").replace("]","").replace(",","");
    }
}
