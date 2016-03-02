import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Panel extends JButton implements ActionListener {
	public int x,y,value;
	public Panel(int x, int y, int value){
		this.x = x;
		this.y = y;
		this.value = value;
		
		this.setText(Integer.toString(this.value));
		if (this.value == 9){
			this.setText("");
			this.setEnabled(false);
		}
		
		this.addActionListener(this);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Stage.click(this.getX(), this.getY());
		
	}
}
