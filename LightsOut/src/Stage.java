import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JOptionPane;

public class Stage extends Container{
	public static Bulb [][] stageArray = new Bulb[5][5];
	public Stage(){
		Boolean flag = false;
		Random rand = new Random();
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){

				stageArray[i][j] = new Bulb(i, j, flag);
				add(stageArray[i][j]);
			}
		}
		setLayout(new GridLayout(5,5,0,0));
	}
	public static void toggle(int i, int j) {
		try{
			if (stageArray[i][j].getFlag() == true){
				stageArray[i][j].setFlag(false);
				stageArray[i][j].setBackground(Color.BLACK);
			} else {
				stageArray[i][j].setFlag(true);
				stageArray[i][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (stageArray[i-1][j].getFlag() == true){
				stageArray[i-1][j].setFlag(false);
				stageArray[i-1][j].setBackground(Color.BLACK);
			} else {
				stageArray[i-1][j].setFlag(true);
				stageArray[i-1][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (stageArray[i][j-1].getFlag() == true){
				stageArray[i][j-1].setFlag(false);
				stageArray[i][j-1].setBackground(Color.BLACK);
			} else {
				stageArray[i][j-1].setFlag(true);
				stageArray[i][j-1].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (stageArray[i+1][j].getFlag() == true){
				stageArray[i+1][j].setFlag(false);
				stageArray[i+1][j].setBackground(Color.BLACK);
			} else {
				stageArray[i+1][j].setFlag(true);
				stageArray[i+1][j].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		try{
			if (stageArray[i][j+1].getFlag() == true){
				stageArray[i][j+1].setFlag(false);
				stageArray[i][j+1].setBackground(Color.BLACK);
			} else {
				stageArray[i][j+1].setFlag(true);
				stageArray[i][j+1].setBackground(Color.YELLOW);
			}
		} catch (Exception e){}
		
		if (goalTest(stageArray)) infoBox("You win!", "CONGRATULATIONS");
	}
	private static boolean goalTest(Bulb[][] stageArray2) {
		for (int i=0; i<5; i++){
			for (int j=0; j<5; j++){
				if (stageArray[i][j].getFlag() == true) return false;
			}
		}
		return true;
	}
	
	private static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		
	}
}
