/*name         :zhou
* Matric No.   :A0174913B
* PLab Acct.   :xx
*/

import java.util.*;

public class ICPC {

    // define your own attributes, constructor, and methods here
    private HashMap<String,Team> teams;
    private Scanner sc;
    private int teamNum;
    private int proNum;
    private int queryNum;
    private ArrayList<Problem> problems;

    public ICPC(){

    }

    private void run() {
        sc = new Scanner(System.in);
        teamNum = sc.nextInt();
        teams = new HashMap<String,Team>(teamNum);
        proNum = sc.nextInt();
        queryNum = sc.nextInt();
        problems = new ArrayList<Problem>(proNum);
        for(int i=0; i<teamNum; i++){
            String name = sc.next();
            teams.put(name,new Team(name));
        }
        for(int i = 0;i<proNum;i++){
            problems.add(i, new Problem(i+1));
        }
        for(int i=0;i<queryNum;i++){
        String query = sc.next();
        if(query.equals("SUBMIT")){
            String teamName = sc.next();
            int problemIndex = sc.nextInt();
            int time = sc.nextInt();
            String verdict = sc.next();
            // System.out.println(problemIndex);
            // System.out.println(problems.size());
            // System.out.println(teams.get(teamName));
            teams.get(teamName).submit(problems.get(problemIndex-1),time, verdict);
        }
        if(query.equals("DETAILS")){
            teams.get(sc.next()).details();
        }
        if(query.equals("FIRST")){
            problems.get(sc.nextInt()-1).first();
        }
        if(query.equals("UNSOLVED")){
            ArrayList<Integer> unsolvedProblem = new ArrayList<Integer>();
            for(Problem p : problems){
                if(p.getSolvedBy().size()==0)
                    unsolvedProblem.add(p.getIndex());
            }
            System.out.println(unsolvedProblem.toString().replace("[","").replace("]","").replace(",",""));
        }
        if(query.equals("TOP")){
            int highest = 0;
            for(Team t : teams.values()){
                if(t.getProblemSolved()>highest){
                    highest = t.getProblemSolved();
                }
            }
            ArrayList<Team> tops = new ArrayList<Team>();
            for(Team t : teams.values()){
                if(t.getProblemSolved()==highest)
                    tops.add(t);
            }
            int lowest = 9999;
            for(Team t: tops){
                if(t.penalty()>lowest){
                    lowest = t.penalty();
                }
            }

            for(Team t: tops){
                if(t.penalty()==lowest)
                    t.details();
            }
        }
      }
    }

    public static void main(String[] args) {
        ICPC competition = new ICPC();
        competition.run();
    }
}

class Problem {
    // define your own attributes, constructor, and methods here
    private int index;
    private ArrayList<String> solvedBy;
    private ArrayList<Integer> solvedTime;

    public Problem(int problemIndex){
        this.index = problemIndex;
        solvedBy = new ArrayList<String>();
        solvedTime = new ArrayList<Integer>();
    }

    public void newSolver(String solver, int time){
        solvedBy.add(solver);
        solvedTime.add(time);
    }

    public int getIndex(){
        return index;
    }

    public ArrayList<String> getSolvedBy(){
        return solvedBy;
    }

    public void first(){
        if(solvedBy.size()==0) System.out.println("problem "+index+" has not been solved");
        else System.out.println(solvedBy.get(0)+" "+solvedTime.get(0));
    }

}

class Team {
    // define your own attributes, constructor, and methods here
    private String name;
    private int penaltyPoint;
    private int problemSolved;
    private HashMap<Integer,Integer> subTime;//second integer is the submission times done before, not included the duplicated AC submission
                                            //first integer is the problem index
    public Team(String name){
        this.name = name;
        penaltyPoint = 0;
    }


    public void submit(Problem problem, int time, String verdict){
        if(problem.getSolvedBy().contains(this.name)) {
            System.out.println("problem already solved");
            return;
         }
        if(!subTime.containsValue(problem.getIndex())){
            subTime.put(problem.getIndex(),1);
        }
        if(!verdict.equals("AC")){
            subTime.put(problem.getIndex(),subTime.get(problem.getIndex())+1);
        }
        problem.newSolver(this.name,time);
        problemSolved++;
        System.out.println(this.name+" "+verdict+" "+problem.getIndex()+" "+subTime.get(problem.getIndex()));
    }
    public void details(){
        System.out.println(this.name+" "+problemSolved+" "+penaltyPoint);
    }

    public int getProblemSolved(){
        return problemSolved;
    }

    public String getName(){
        return this.name;
    }

    public int penalty(){
        return 0;
    }
}
