import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Stage extends Container{
	public static Panel [][] stageArray = new Panel[3][3];
	public Stage(int[][] array){
		
		for (int i=0; i<3; i++){
			for (int j=0; j<3; j++){
				stageArray[i][j] = new Panel(i, j, array[i][j]);
				add(stageArray[i][j]);
			}
		}
		setLayout(new GridLayout(3,3,0,0));
	}
	public static void click(int x, int y) {
		if (isAdjacent(x,y)){
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					if (stageArray[i][j].getValue() == 9){
						stageArray[i][j].setValue(stageArray[x][y].getValue());
						stageArray[i][j].setText(Integer.toString(stageArray[x][y].getValue()));
						stageArray[i][j].setEnabled(true);
						break;
					}
					
				}
			}
			stageArray[x][y].setValue(9);
			stageArray[x][y].setText("");
			stageArray[x][y].setEnabled(false);
			if (goalTest(stageArray)) infoBox("You win!", "CONGRATULATIONS");
		}
	}
	private static boolean goalTest(Panel[][] stageArray) {
		int num=1;
		for (int i=0;i<3;i++){
			for (int j=0; j<3; j++){
				if (stageArray[i][j].getValue()!=num){
					
					return false;
				}
				num++;
			}
		}
		return true;
		
	}
	private static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		
	}
	private static boolean isAdjacent(int x, int y) {
		try{
			if (stageArray[x-1][y].getValue() == 9) return true;
		} catch (Exception e){}
		
		try{
			if (stageArray[x][y-1].getValue() == 9) return true;
		} catch (Exception e){}
		
		try{
			if (stageArray[x+1][y].getValue() == 9) return true;
		} catch (Exception e){}
		
		try{
			if (stageArray[x][y+1].getValue() == 9) return true;
		} catch (Exception e){}
	
		
		
		return false;
	}
}
