/**
 * name	 Litian Zhou    :
 * matric no.: A0174913B
 */

import java.util.*;

class Grid {

    // declare the member field
		private int size;
		private int[][] newGrid;
		private int treeNum;
		private int[][] treeCoordinate;

    // declare the constructor
		public Grid(){
			Scanner input = new Scanner(System.in);
			size = input.nextInt();
			newGrid = new int[size][size];//create a newGrid with all 0
			treeNum = input.nextInt();
			treeCoordinate = new int[treeNum][2];
			for(int i =0; i <treeNum; i++){
				treeCoordinate[i][0] = input.nextInt();
				treeCoordinate[i][1] = input.nextInt();

			}
		}
	public int getSize(){
		return size;
	}

	/**
	 *	   checkNoTree: to check whether the (size x size) square with upper-left coordinate
	 *                     (x, y) contains a tree
	 *	   Pre-condition: there was no tree within the previous smalled square
	 *	   Post-condition: if no tree, than size + 1, if there is, record the previous size and compare to get the biggest
	 */
	public boolean checkNoTree(int x, int y, int squareSize) {
		for(int i = 0; i < treeNum; i++){
			if(x <= treeCoordinate[i][0] && treeCoordinate[i][0] <= (x+squareSize - 1)
				&& y <= treeCoordinate[i][1] && treeCoordinate[i][1] <= (y+squareSize -1)){
					// System.out.println(squareSize);
				return false;
			}
		}
    return true;
	}
	/**
	 *	   checkValidSize: to check whether it is possible to find a (size x size) square that contains
	 *                     no tree
	 *	   Pre-condition : get a new maxium square with no tree, use this to compare to the precious biggest square
	 *	   Post-condition: if bigger, return true and it will become the biggest square
	 */
	public int checkValidSize(int squareSize, int biggestSize) {//return the biggestSize of square after comparison
		if(squareSize > biggestSize) return squareSize;
    else return biggestSize;
	}



	/**
	 *	   solve         : use this reccusion method to find the largest size of a square with no trees
	 *	   Pre-condition : x, y, (left top point and the squareSize, start from 0)
	 *	   Post-condition: return the possible no tree square's size
	 */
	public int solve(int x, int y, int squareSize, int grSize) {
		if(x + squareSize - 1 <= grSize && y + squareSize - 1 <= grSize){

			if(checkNoTree(x, y, squareSize)) {
				squareSize++;
				return solve(x, y, squareSize, grSize);
			}
			else return --squareSize;
		}
		return --squareSize;

	}
}

class Land {

	public static void main(String[] args) {

		// declare the necessary variables
		int squareSize = 1;
		int biggestSize = 0;
		int x = 1;
		int y = 1;
		// create new object from class Result
		Grid gr = new Grid();
		// declare a Scanner object to read input

		// read input and process them accordingly
		for(x = 1; x < gr.getSize(); x++){
			for(y = 1; y < gr.getSize(); y++){
				int solvedSize = gr.solve(x,y,squareSize,gr.getSize());
				biggestSize = gr.checkValidSize(solvedSize, biggestSize);
			}
		}
		System.out.println(biggestSize);
	}
}
