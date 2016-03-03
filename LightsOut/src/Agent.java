import java.awt.List;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Agent {
	public Agent(Bulb[][] array){
		State state = new State(array, null);
		graphSearh(state);
	}

	private void graphSearh(State array) {
		Stack <State> frontier = new Stack<State>();
		Stack <State> explored = new Stack<State>();
		State path = new State(null, null);
		State s = new State(null, null);
		
		frontier.push(array);
		while(!frontier.empty()){
			path = frontier.pop();
			explored.push(path);
			System.out.println("wee");
			if (goalTest(path)){
				System.out.println("WIN");
				return;
			}
			for (int i=0; i<5; i++){
				for (int j=0; j<5; j++){
					State temp = new State(result(path,i,j).getArray(), result(path,i,j).getParent());
//					if((!contain(temp, frontier) && !contain(temp, explored)) || goalTest(temp)){
//						System.out.println("shit");
//						frontier.push(temp);
//					}
					frontier.push(temp);
//					drawBoard(temp.getArray());
				}
				return;
			}
		}
		
		
		
	}
	
	private boolean contain(State result, Stack<State> frontier) {
		for (int a=0; a<frontier.size(); a++){
			drawBoard(frontier.get(a).getArray());
			System.out.println("fuck");
			for (int i=0; i<5; i++){
				for (int j=0; j<5; j++){
					if (result.getArray()[i][j] != frontier.get(a).getArray()[i][j]) return false;
				}
			}
		}
//		System.out.println("fuck");
		return true;
	}

	private State result(State path, int i, int j){
		State resultPath = new State(path.getArray(), path.getParent());
		
		try{
			if (resultPath.getArray()[i][j].getFlag() == true){
				resultPath.getArray()[i][j].setFlag(false);
				resultPath.getArray()[i][j].setBackground(Color.BLACK);
			} else {
				resultPath.getArray()[i][j].setFlag(true);
				resultPath.getArray()[i][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (resultPath.getArray()[i-1][j].getFlag() == true){
				resultPath.getArray()[i-1][j].setFlag(false);
				resultPath.getArray()[i-1][j].setBackground(Color.BLACK);
			} else {
				resultPath.getArray()[i-1][j].setFlag(true);
				resultPath.getArray()[i-1][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (resultPath.getArray()[i][j-1].getFlag() == true){
				resultPath.getArray()[i][j-1].setFlag(false);
				resultPath.getArray()[i][j-1].setBackground(Color.BLACK);
			} else {
				resultPath.getArray()[i][j-1].setFlag(true);
				resultPath.getArray()[i][j-1].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (resultPath.getArray()[i+1][j].getFlag() == true){
				resultPath.getArray()[i+1][j].setFlag(false);
				resultPath.getArray()[i+1][j].setBackground(Color.BLACK);
			} else {
				resultPath.getArray()[i+1][j].setFlag(true);
				resultPath.getArray()[i+1][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (resultPath.getArray()[i][j+1].getFlag() == true){
				resultPath.getArray()[i][j+1].setFlag(false);
				resultPath.getArray()[i][j+1].setBackground(Color.BLACK);
			} else {
				resultPath.getArray()[i][j+1].setFlag(true);
				resultPath.getArray()[i][j+1].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		drawBoard(resultPath.getArray());
		return resultPath;
		
	}

	private boolean goalTest(State path) {
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if (path.getArray()[i][j].getFlag() == true) return false;
			}
		}
		return true;
	}

	private void drawBoard(Bulb[][] array) {
		for(int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if (array[i][j].getFlag() == true) System.out.print("1 ");
				else System.out.print("0 ");
			}
			System.out.println();
		}
		System.out.println();
		
	}
}
