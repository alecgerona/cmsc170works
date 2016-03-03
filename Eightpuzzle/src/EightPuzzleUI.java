import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EightPuzzleUI {
	public static int position = 0;
	public static int pos = 1;
	public static int[][] mainArray = new int[3][3];
	public static void main (String args[]){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Eight Puzzle");
		JFrame solutionFrame = new JFrame("Solution");
		frame.setPreferredSize(new Dimension(500,500));
		solutionFrame.setPreferredSize(new Dimension(500,500));
		Agent.personalizeArray(mainArray);
		Stage stage = new Stage(mainArray);
		JButton solve = new JButton("Solve");
		JButton randomize = new JButton("Randomize");
		JButton next = new JButton("Next");
		JButton prev = new JButton("Previous");
		
		
		
		
		Container con = new Container();
		con.setLayout(new BorderLayout());
		con.add(stage, BorderLayout.CENTER);
		
		Container conButtons = new Container();
		conButtons.setLayout(new GridLayout(1,2,0,0));
		conButtons.add(solve);
		conButtons.add(randomize);
		
		Container solButtons = new Container();
		solButtons.setLayout(new GridLayout(1,2,0,0));
		solButtons.add(prev);
		solButtons.add(next);
		
		Container solutionCon = new Container();
		solutionCon.setLayout(new BorderLayout());
		
		
		solve.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int[][] array = new int[3][3];
				
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						array[i][j] = stage.stageArray[i][j].getValue();
					}
				}
				
				Agent agent = new Agent(array);
				Stage solStage = new Stage(mainArray);
				solutionCon.add(solStage);
				solutionFrame.add(solutionCon, BorderLayout.CENTER); 
				solutionFrame.add(solButtons, BorderLayout.PAGE_END);
				solutionFrame.pack();
				solutionFrame.setVisible(true);
				solutionFrame.setLocation(500, 0);
			
				
				
				
			}
			
		});
		
		randomize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				
				
				
				
				
			}
			
		});
		
		prev.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if (position == 1){
						position = 0;
						pos++;
					}
					Stack <EPuzzleState> steps = new Stack <EPuzzleState>();
					EPuzzleState finish = Agent.finish;
					steps.push(finish);
					while (finish.getParent() != null){
						steps.push(finish.getParent());
						finish = finish.getParent();
					}
					Agent.drawArray(steps.firstElement().getArray());
					for (int i=0; i<3; i++){
						for (int j=0; j<3; j++){
							if (Stage.stageArray[i][j].getValue() == 9) Stage.stageArray[i][j].setEnabled(true);
							Stage.stageArray[i][j].setValue(steps.get(pos).getArray(i, j));
							Stage.stageArray[i][j].setText(Integer.toString(steps.get(pos).getArray(i, j)));
							if (Stage.stageArray[i][j].getValue() == 9){
								Stage.stageArray[i][j].setText("");
								Stage.stageArray[i][j].setEnabled(false);
							} 
						}
					}
					System.out.println(Agent.size);
					pos++;
				} catch (Exception e1){}
			}
			
		});
		
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if (position==0){
						position=1;
						pos--;
					}
					Stack <EPuzzleState> steps = new Stack <EPuzzleState>();
					EPuzzleState finish = Agent.finish;
					steps.push(finish);
					while (finish.getParent() != null){
						steps.push(finish.getParent());
						finish = finish.getParent();
					}
					for (int i=0; i<3; i++){
						for (int j=0; j<3; j++){
							if (Stage.stageArray[i][j].getValue() == 9) Stage.stageArray[i][j].setEnabled(true);
							Stage.stageArray[i][j].setValue(steps.get(pos-1).getArray(i, j));
							Stage.stageArray[i][j].setText(Integer.toString(steps.get(pos-1).getArray(i, j)));
							if (Stage.stageArray[i][j].getValue() == 9){
								Stage.stageArray[i][j].setText("");
								Stage.stageArray[i][j].setEnabled(false);
							} 
						}
					}
					pos--;
				} catch (Exception e1){}
			}
			
		});
		
		
		
		frame.add(con, BorderLayout.CENTER);
		frame.add(conButtons, BorderLayout.PAGE_END);
		//frame.add(button);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
