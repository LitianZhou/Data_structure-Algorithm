import java.util.*;

public class CountingChange{

    private HashMap<Integer,Integer> coins;
    private int c;
    private int num_way;
    private int maxCoin;

    public CountingChange(){
    	coins = new HashMap<Integer, Integer>();
	c =0;
	num_way = 0;
	maxCoin = 0;
    }

    private void run(){
    	Scanner sc = new Scanner(System.in);
	c = sc.nextInt();
	int[] coinNum = new int[5];
	for(int v =0; v<4;v++){
		coinNum[v] =sc.nextInt();
		if(coinNum[v]>10) coinNum[v] = 10;
	}
	coins.put(5,coinNum[0]);
	coins.put(10,coinNum[1]);
	coins.put(20, coinNum[2]);
	coins.put(50, coinNum[3]);
	coins.put(100,coinNum[4]);

	int[] values ={5,10,20,50,100};
	
	
	maxCoin = counter(coins,values,0,0,0,0);
	System.out.println(maxCoin+" "+num_way); 
    }

    private int counter(HashMap coins,int[] values, int whichValue, int whichCoin, int coinCounter,int amass){
	
	if(whichValue >= coins.size()) return 0;
	if(whichCoin >(int) coins.get(values[whichValue])) return 0;
	if(amass==c) return coinCounter;
	if(amass > c) return 0;
	if(amass < c){
		int choose = counter(coins,values, whichValue, whichCoin+1,coinCounter+1,amass+values[whichValue]);
		int notChoose = counter(coins,values, whichValue+1, 0, coinCounter, amass);
		int temp = Math.max(choose,notChoose);
		
		System.out.println("Line48:"+temp+" "+maxCoin);
		if(temp>maxCoin){
			maxCoin = temp;
			num_way = 1;
			System.out.print("new max:" + temp);
		}
		else if(maxCoin!=0 && temp==maxCoin){
			num_way++;
			System.out.println("update ways:"+num_way);
		}
	}
	return maxCoin;
    }

    public static void main(String[] args){
        CountingChange myCountingChange = new CountingChange();
        myCountingChange.run();
    }

}
