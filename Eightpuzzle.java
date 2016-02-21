import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Eightpuzzle extends JFrame{
	public static int array[][] = new int[3][3];
	public static int cost=0;


	public static void main (String args[]){
		EPuzzleState state = new EPuzzleState(array, cost,getManhattan(array),0, array);
		Stack <Integer> sample = new Stack<Integer>();
		personalizeArray(state.getArray());
		System.out.println(getManhattan(state.getArray()));
		System.out.println(findEmptyTile(state.getArray()).getRow()+","+findEmptyTile(state.getArray()).getCol());
		
		drawArray(state.array);
		/*sample = getAdjacentTiles(state);
		while (!sample.empty()){
			System.out.println(sample.pop());
		}*/
		
		//goalTest(state);
		
		drawArray(carillon(state).array);
		
	}
	
	
	public static EPuzzleState carillon(EPuzzleState state) {
		
		Stack <EPuzzleState> openList = new Stack<EPuzzleState>();
		Stack <EPuzzleState> closedList = new Stack<EPuzzleState>();
		Stack <Integer> adjacentTiles = new Stack<Integer>();
		EPuzzleState bestNode = new EPuzzleState(array, cost, cost, cost, array);
		EPuzzleState duplicated = new EPuzzleState(array, cost, cost, cost, array);
		int actionTile;
		
		openList.push(state);
		while(!openList.empty()){
			cost++;
			bestNode = removeMinF(openList);
			//drawArray(bestNode.array);
			//System.out.println();
			closedList.push(bestNode);
			if(goalTest(bestNode)) return bestNode;
			adjacentTiles = getAdjacentTiles(bestNode);
			while(!adjacentTiles.empty()){ //Expanding paths
				actionTile = adjacentTiles.pop();
				
				
				if (arrayCompare(openList, result(bestNode, actionTile))){
					duplicated = result(bestNode, actionTile);
				}
				if (arrayCompare(closedList, result(bestNode, actionTile))){
					duplicated = result(bestNode, actionTile);
				}
				
				if (!arrayCompare(openList, result(bestNode, actionTile)) || !arrayCompare(closedList, result(bestNode, actionTile)) ||
						(arrayCompare(openList, result(bestNode, actionTile)) || arrayCompare(closedList, result(bestNode, actionTile))
						&& result(bestNode, actionTile).g < duplicated.g)){ 
					/*
					 * Checks whether the expanded path is not in the openList nor the closedList to include it. If it is, it checks
					 * if its duplicate's cost is lower or higher to make the decision to include it or not.
					 */
					
					
					result(bestNode, actionTile).setParent(bestNode.array);
					openList.add(result(bestNode, actionTile));
					
				}
			}
		}
		return null;
		
	}


	private static boolean arrayCompare(Stack<EPuzzleState> openList, EPuzzleState result) {
		Iterator<EPuzzleState> iter = openList.iterator();
		while (iter.hasNext()){
			if (iter.next().array == result.array){
				return true;
			}
		}
		return false;
	}


	private static EPuzzleState removeMinF(Stack<EPuzzleState> openList) {
		Iterator<EPuzzleState> iter = openList.iterator();
		LinkedList <Integer> listOfF = new LinkedList<Integer>();
		EPuzzleState minF = new EPuzzleState(array, cost, cost, cost, array);
		
		while (iter.hasNext()){
		    listOfF.add(iter.next().f); //Adds the F values of the openList's states.
		}
		Collections.sort(listOfF); //Sorts the Fs ascendingly.
		
		Iterator<EPuzzleState> iter2 = openList.iterator();
		while (iter2.hasNext()){
			//System.out.println(listOfF.getLast());
			minF = iter2.next();
			if(minF.f == listOfF.getLast()){ //Once the openList state with the highest F is found, return it.
				return minF;
			}
		}
		
		return null;
	}


	public static boolean goalTest(EPuzzleState state){
		int num=1;
		for (int i=0;i<3;i++){
			for (int j=0; j<3; j++){
				//System.out.println(state.array[i][j]);
				if (state.array[i][j]!=num){
					
					return false;
				}
				num++;
			}
		}
		return true;
	}
	
	public static Stack<Integer> getAdjacentTiles(EPuzzleState state){
		
		Stack <Integer> adjacentTiles = new Stack<Integer>();
		
		int i = findEmptyTile(state.array).getRow();
		int j = findEmptyTile(state.array).getCol();
		
		try{
			adjacentTiles.push(state.array[i-1][j]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.array[i][j-1]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.array[i+1][j]);
		}
		catch (Exception e){}
		
		try{
			adjacentTiles.push(state.array[i][j+1]);
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
					totalcost += Math.abs(i-2) + Math.abs(j-2);
				}
			}
		}
		return totalcost;

	}
	
	public static EPuzzleState result(EPuzzleState state, int actionTile){
		
		int emptyRow = findEmptyTile(state.array).getRow();
		int emptyCol = findEmptyTile(state.array).getCol();
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				if(state.getArray()[i][j]==actionTile){
					state.getArray()[i][j]=9;
				}
			}
		}
		state.array[emptyRow][emptyCol]=actionTile;
		state.g = cost;
		state.h = getManhattan(state.array);
		state.f = state.g + state.h;
		
		return state;
		
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
		array[1][1] = 5;
		array[1][2] = 6;
		
		array[2][0] = 7;
		array[2][1] = 9;
		array[2][2] = 8;
	}
	
	public static void initializeArray(int [][] array){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				
				
				array[i][j]=0;
			}
		}
	}

	public static class EPuzzleState{
		public int array[][] = new int[3][3];
		public int parent[][] = new int[3][3];
		public int g,h,f;
		
		public EPuzzleState(int[][] array, int g, int h, int f, int [][] parent){
			array = this.array;
			g = this.g;
			h = this.h;
			f = this.f;
			parent = this.parent;
		}
		
		public void setArray(int[][] array){
			array = this.array;
		}
		
		public void setG(int g){
			g = this.g;
		}
		
		public void setH(int h){
			h = this.h;
		}
		public void setF(int f){
			f = this.f;
		}
		
		public void setParent(int[][] parent){
			parent = this.parent;
		}
		
		public int[][] getArray(){
			return array;
		}
		
		public int getG(){
			return g;
		}
		
		public int getH(){
			return h;
		}
		public int getF(){
			return f;
		}
	}
}