import java.util.*;

public class CountingChange{

	private void run(){
		Scanner sc = new Scanner(System.in);
		int target = sc.nextInt();
		int[] coinNum = new int[5];
		for(int v =0; v<5;v++){
			coinNum[v] =sc.nextInt();
			if(coinNum[v]>10) coinNum[v] = 10;
		}
		Coin[] coins = new Coin[5];
		coins[0] = new Coin(5,coinNum[0]);
		coins[1] = new Coin(10,coinNum[1]);
		coins[2] = new Coin(20,coinNum[2]);
		coins[3] = new Coin(50,coinNum[3]);
		coins[4] = new Coin(100,coinNum[4]);
		Result result = counter(target,0,coins,0,0,0);
		result.print();
	}
	
	public Result counter(int target, int amass, Coin[] coins, int currCoin, int sameValueCoinNum, int howManyCoin){
		if(currCoin >= coins.length) return new Result(0,0);
		if(amass>target) return new Result(0,0);
		if(amass == target)
			 return new Result(howManyCoin,1);
		Result choose = new Result(0,0);
		if(sameValueCoinNum < coins[currCoin].num)
			choose = counter(target, amass+coins[currCoin].denomination, coins, currCoin,sameValueCoinNum+1, howManyCoin+1);
		Result notChoose = counter(target, amass, coins, currCoin+1, 0, howManyCoin);
		//check point: System.out.println(choose.maxCoinNum+ " "+ notChoose.maxCoinNum);
		if(choose.maxCoinNum > notChoose.maxCoinNum) return choose;
		else if(choose.maxCoinNum < notChoose.maxCoinNum) return notChoose;
		//choose.maxCoinNum == notChoose.maxCoinNum:
		return new Result(choose.maxCoinNum, choose.maxCoinWay+notChoose.maxCoinWay);
	}

	public static void main(String[] args){
		CountingChange myCountingChange = new CountingChange();
		myCountingChange.run();
	}
}

class Coin{
	protected int denomination;
	protected int num;

	public Coin(int de, int num){
		this.denomination = de;
		this.num = num;
	}
}

class Result{
	protected int maxCoinNum;
	protected int maxCoinWay;

	public Result(int num, int way){
		this.maxCoinNum = num;
		this.maxCoinWay = way;
	}
	public void print(){
		System.out.println(maxCoinNum+" "+maxCoinWay);
	}
}
