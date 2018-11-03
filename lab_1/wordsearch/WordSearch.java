/*
 Name: Zhou Litian
 * Matric No. : A0174913B
  * Plab Acct. : ???
 */
import java.util.*;
/*
 pre: word is the input word in the first line;
      wordlength is the number of letters in the word;
      size is the length and width of the grid;
 post:count is the max number that the word appears
*/
public class WordSearch {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String word=input.nextLine();//identify the word need to be searched

        char[] arrayword=word.toCharArray();
        int wordlength=arrayword.length;//put the word's charcter into arrayword
        input.nextLine();

        int size=input.nextInt();//judge the size of the grid
        input.nextLine();
        char[][] array=new char[size][size];//create a 2D array to represent grid

        for(int i=0;i<size;i++){
            String string=input.nextLine();
            char[] array1=string.toCharArray();
            System.arraycopy(array1, 0, array[i], 0, size);
        }

        int count=0;//initialize the count number
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(array[i][j]==arrayword[0]){//to find the location of the first letter of the word
                    int a;
                    for(a=1;a<wordlength;a++){
                        if(i+a>=size || array[i+a][j]!=arrayword[a]) break;
                        if(a==wordlength-1){
                            count++;
                        }
                    }//check the direction of forward horizontal
                    for(a=1;a<wordlength;a++){
                        if(j+a>=size || array[i][j+a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of forward vertical

                    for(a=1;a<wordlength;a++){
                        if(j-a<0 || array[i][j-a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of backward vertical

                    for(a=1;a<wordlength;a++){
                        if(i-a<0 || array[i-a][j]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of backward horizontal

                    for(a=1;a<wordlength;a++){
                        if(i-a<0 || j-a<0 || array[i-a][j-a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of backward diagonal

                    for(a=1;a<wordlength;a++){
                        if(i+a>=size || j+a>=size || array[i+a][j+a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of forward diagonal

                    for(a=1;a<wordlength;a++){
                        if(i-a<0 || j+a>=size || array[i-a][j+a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of backward diagonal

                    for(a=1;a<wordlength;a++){
                        if(i+a>=size || j-a<0 || array[i+a][j-a]!=arrayword[a]) break;
                        if(a==wordlength-1) count++;
                    }//check the direction of forward diagonal
                }
            }
        }
        System.out.println(count);
    }

}
