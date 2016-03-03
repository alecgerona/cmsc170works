import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
public class Bulb extends JButton implements ActionListener{
	public int i, j;
	public boolean flag;
	public Bulb(int i, int j, boolean flag){
		
		this.i = i;
		this.j = j;
		this.flag = flag;
		if (this.flag == true){
			this.setBackground(Color.YELLOW);
		} else this.setBackground(Color.BLACK);
		this.addActionListener(this);
	}
	
	public int getI(){
		return this.i;
	}
	
	public int getJ(){
		return this.j;
	}
	
	public boolean getFlag(){
		return this.flag;
	}
	
	public void setI(int i){
		this.i = i;
	}
	
	public void setJ(int j){
		this.j = j;
	}
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Stage.toggle(this.getI(), this.getJ());
		
	}
}
