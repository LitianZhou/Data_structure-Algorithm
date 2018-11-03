
/**
 *	Name		:litian
 *	Matric No.: A0174913B
 */

import java.util.*;

public class Passin {
	private int playerNum;
	private int roundNum;
	private int livesNum;
	private LinkedList<Player> players;

	public static void main(String[] args) {
		Passin newGame = new Passin();
		newGame.run();			
	}

	public void run(){	
		Scanner sc = new Scanner(System.in);
		playerNum = sc.nextInt();
		roundNum = sc.nextInt();
		livesNum = sc.nextInt();

		for(int i = 0; i<playerNum;i++){
			players.add(new Player(sc.next(), livesNum));
		}

		pass(roundNum,players);
	}
	public void pass(int passingNum, LinkedList<Player> players){
		int ballPosition = 0;//tracing the ball
		for(int j = 0; j<roundNum;j++){
			int startPosition = ballPosition;
			Scanner sc = new Scanner(System.in);
			passingNum = sc.nextInt();
			ballPosition+=passingNum;
			ballPosition = ballPosition%playerNum;
			players.get(ballPosition).deductLive();
			if(ballPosition ==startPosition) break;
			if(players.get(ballPosition).getLives()<livesNum){
				Player temp = new Player(players.get(ballPosition).getName(), players.get(ballPosition).getLives());
				players.set(ballPosition, players.get(startPosition));
				players.set(startPosition, temp);
			}
			else{
				System.out.println(players.remove(ballPosition).getName());
				if(ballPosition==players.size()-1) ballPosition = 0;
			}
		}
	}

	class Player{
		private String name;
		private int lives;
		public Player(String name, int lives){
			this.name = name;
			this.lives = lives;
		}
		public String getName(){
			return name;
		}
		public int getLives(){
			return lives;
		}
		public void deductLive(){
			lives--;
		}
	}
}
