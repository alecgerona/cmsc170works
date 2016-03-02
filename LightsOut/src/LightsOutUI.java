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

public class LightsOutUI {
	public static void main(String args[]){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Lights Out");
		frame.setPreferredSize(new Dimension(500,500));
		JButton solve = new JButton("Solve");
		JButton randomize = new JButton("Randomize");
		
		Stage stage = new Stage();
		Container con = new Container();
		con.setLayout(new BorderLayout());
		con.add(stage);
		
		Container conButtons = new Container();
		conButtons.setLayout(new BorderLayout());
		conButtons.add(solve);
		conButtons.add(randomize);
		
		randomize.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Random rand = new Random();
				for (int i=0; i<5; i++){
					for (int j=0; j<5; j++){
						if (rand.nextInt()%2==0) Stage.toggle(i, j);
						
					}
				}
				
				
			}
			
		});
		
		frame.add(con, BorderLayout.CENTER);
		frame.add(conButtons, BorderLayout.PAGE_END);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
