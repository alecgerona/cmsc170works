import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Eightpuzzle extends JFrame{
	
	public static int cost=0;
	public static int count=0;


	/*public static void main (String args[]){
		int array[][] = new int[3][3];
		EPuzzleState state = new EPuzzleState(array, cost,getManhattan(array),0, null);
		personalizeArray(state.array);	
		//drawArray(carillon(state).array);
		printResult(carillon(state));
	}*/
	
	
	private static void printResult(EPuzzleState state) {
		Stack <EPuzzleState> steps = new Stack <EPuzzleState>();
		BufferedWriter bw = null;
		String answer = "";
		File file = new File("8puzzle.out");
		while (state.getParent() != null){
			steps.push(state.getParent());
			state = state.getParent();
		}
		
		try {
			bw = new BufferedWriter(new FileWriter(file));
			answer += "Initial \n";
			while(!steps.empty()){
				EPuzzleState temp = steps.pop();
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						answer += temp.getArray(i,j)+" ";
					}
					answer += "\n";
				}
				answer += "<<<>>>\n";
			}
			bw.write(answer, 0, answer.length());
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try{
				bw.close();
			}
			catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		
		
		
	}


	public static void copyMyArray(int[][] shitarray, int[][] array) {
		for (int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				shitarray[i][j] = array[i][j];
			}
		}
		
	}


	public static EPuzzleState carillon(EPuzzleState state) {
		int array[][] = new int[3][3];
		int[][] shitarray = new int[3][3];
		final Stack <EPuzzleState> openList = new Stack<EPuzzleState>();
		Iterator<EPuzzleState> iter = openList.iterator();
		final Stack <EPuzzleState> closedList = new Stack<EPuzzleState>();
		Stack <Integer> adjacentTiles = new Stack<Integer>();
		EPuzzleState bestNode = new EPuzzleState(array, cost, cost, cost, state);
		EPuzzleState duplicated = new EPuzzleState(array, cost, cost, cost, state);
		LinkedList <Integer> listOfF = new LinkedList<Integer>();
		int actionTile;
		int fuck=0;
		
		
		openList.push(state);
		while(!openList.empty()){
			
			cost++;
			for (int i=0; i<openList.size(); i++){
				listOfF.add(openList.get(i).getF());
			}
			Collections.sort(listOfF);
			for (int i=0; i<openList.size(); i++){
				if (openList.get(i).getF() == listOfF.getFirst()) {
					bestNode = openList.get(i);
					fuck = i;
					openList.remove(i);
					break;
				}
					
			}
			listOfF.clear();
			
			
			closedList.push(bestNode);
			if(goalTest(bestNode)) return bestNode;
			count++;
			
			if (count==5) return null;
			
			adjacentTiles = getAdjacentTiles(bestNode);
			
			while(!adjacentTiles.empty()){ //Expanding paths
				actionTile = adjacentTiles.pop();
				
				
				EPuzzleState temp = result(bestNode,actionTile);
				copyMyArray(shitarray,temp.getArray());
				
				if (!arrayCompare(openList, shitarray) || !arrayCompare(closedList, shitarray) ||
						(arrayCompare(openList, shitarray) || arrayCompare(closedList, shitarray)
						&& temp.getG() < duplicated.getG())){ 
					/*
					 * Checks whether the expanded path is not in the openList nor the closedList to include it. If it is, it checks
					 * if its duplicate's cost is lower or higher to make the decision to include it or not.
					 */
					
					temp.setParent(bestNode);	
					openList.add(temp);
					//drawArray(temp.getArray());
					//System.out.println(temp.getF());
				}
				
			}
			
		}
		return null;
		
	}



	private static boolean arrayCompare(Stack<EPuzzleState> openList, int[][] shitarray3) {
		Iterator<EPuzzleState> iter = openList.iterator();
		int [][]shitarray = new int[3][3];
		int [][]shitarray2 = new int[3][3];
		
		int compareArray[][] = new int[3][3];
		while (iter.hasNext()){
			//compareArray = iter.next().array;
			copyMyArray(shitarray, iter.next().getArray());
			copyMyArray(shitarray2, shitarray3);
			if (arrayOrder(shitarray,shitarray2)){
				return true;
			}
		}
		//System.out.println("damn");
		return false;
	}


	private static boolean arrayOrder(int[][] compareArray, int[][] array2) {
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if (compareArray[i][j] != array2[i][j]){
					return false;
				}
			}
		}
		return true;
	}



	public static boolean goalTest(EPuzzleState state){
		int num=1;
		for (int i=0;i<3;i++){
			for (int j=0; j<3; j++){
				//System.out.println(state.array[i][j]);
				if (state.getArray()[i][j]!=num){
					
					return false;
				}
				num++;
			}
		}
		return true;
	}
	
	public static Stack<Integer> getAdjacentTiles(EPuzzleState state){
		
		Stack <Integer> adjacentTiles = new Stack<Integer>();
		
		int i = findEmptyTile(state.getArray()).getRow();
		int j = findEmptyTile(state.getArray()).getCol();
		
		try{
			adjacentTiles.push(state.getArray()[i-1][j]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.getArray()[i][j-1]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.getArray()[i+1][j]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.getArray()[i][j+1]);
		}
		catch (Exception e){}
		
		
		return adjacentTiles;
	}
	
	public static int getManhattan(int [][] array){
		int totalcost=0;
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if (array[i][j]==1){
					totalcost += Math.abs(i-0)+Math.abs(j-0);
				} else if(array[i][j]==2){
					totalcost += Math.abs(i-0) + Math.abs(j-1);
				} else if(array[i][j]==3){
					totalcost += Math.abs(i-0) + Math.abs(j-2);
				} else if(array[i][j]==4){
					totalcost += Math.abs(i-1) + Math.abs(j-0);
				} else if(array[i][j]==5){
					totalcost += Math.abs(i-1) + Math.abs(j-1);
				} else if(array[i][j]==6){
					totalcost += Math.abs(i-1) + Math.abs(j-2);
				} else if(array[i][j]==7){
					totalcost += Math.abs(i-2) + Math.abs(j-0);
				} else if(array[i][j]==8){
					totalcost += Math.abs(i-2) + Math.abs(j-1);
				} else if(array[i][j]==9){
					//totalcost += Math.abs(i-2) + Math.abs(j-2);
				}
			}
		}
		return totalcost;

	}
	
	public static EPuzzleState result(EPuzzleState state, int actionTile){
		EPuzzleState newState = new EPuzzleState(state.getArray(), state.getG(), state.getH(), state.getF(), state.getParent());
		
		int emptyRow = findEmptyTile(newState.getArray()).getRow();
		int emptyCol = findEmptyTile(newState.getArray()).getCol();
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if(newState.getArray()[i][j]==actionTile){
					newState.getArray()[i][j]=9;
				}
			}
		}
		newState.setArray(emptyRow,emptyCol,actionTile);
		newState.setG(cost);
		newState.setH(getManhattan(newState.getArray()));
		newState.setF(newState.getG() + newState.getH());
		
		return newState;
		
	}
	
	
	
	public static Coordinate findEmptyTile(int [][] array){
		Coordinate coord = new Coordinate(0, 0);
		
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if(array[i][j]==9){
					
					coord.setRow(i);
					coord.setCol(j);
					break;
				}
			}
		}
		
		return coord;
	}
	
	public static class Coordinate{
		public int row;
		public int col;
		
		
		public Coordinate(int row, int col){
			row = this.row;
			col = this.col;
		}
		
				
		public void setRow(int row){
			this.row = row;
		}
		public void setCol(int col){
			this.col = col;
		}
		
		public int getRow(){
			return row;
		}
		public int getCol(){
			return col;
		}
		
	}

	public static void drawArray(int [][] array){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void initializeState(int[][] array){
		int num=0;
		Random rand = new Random();
		initializeArray(array);
		
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				
				num = rand.nextInt(9)+1;
				
				while (arrayContains(array, num)){
					num = rand.nextInt(9)+1;

				}


				array[i][j]=num;
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static boolean arrayContains(int [][] array, int num){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if (array[i][j] == num){
					return true;
				}
			}
		}
		return false;
	}

	public static void personalizeArray(int [][] array){
		array[0][0] = 1;
		array[0][1] = 2;
		array[0][2] = 3;
		
		array[1][0] = 4;
		array[1][1] = 6;
		array[1][2] = 9;
		
		array[2][0] = 7;
		array[2][1] = 5;
		array[2][2] = 8;
	}
	
	public static void initializeArray(int [][] array){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				
				
				array[i][j]=0;
			}
		}
	}

	
}