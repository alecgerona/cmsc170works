import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Eightpuzzle extends JFrame{
	public static int array[][] = new int[3][3];
	public static int cost=0;


	public static void main (String args[]){
		drawStage();
		EPuzzleState state = new EPuzzleState(array, cost,getManhattan(array),0);
		System.out.println(getManhattan(array));
		System.out.println(findEmptyTile(array).getRow()+","+findEmptyTile(array).getCol());
	}
	public static int getManhattan(int [][] array){
		int totalcost=0;
		int num;
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
	
	public static void result(int [][] array, int actionTile){
		
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

	public static void drawStage(){
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

	public static void initializeArray(int [][] array){
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				
				
				array[i][j]=0;
			}
		}
	}

	public static class EPuzzleState{
		public int[][] array;
		public int g,h,f;
		
		public EPuzzleState(int[][] array, int g, int h, int f){
			array = this.array;
			g = this.g;
			h = this.h;
			f = this.f;
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
	}
}