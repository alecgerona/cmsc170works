
public class EPuzzleState {
	public int array[][] = new int[3][3];
	public EPuzzleState parent;
	public int g,h,f;
	
	public EPuzzleState(int[][] array, int g, int h, int f, EPuzzleState parent){
//		array = this.array;
//		g = this.g;
//		h = this.h;
//		f = this.f;
//		parent = this.parent;
		this.g = g;
		this.h= h;
		this.f = f;
		this.parent = parent;
		Agent.copyMyArray(this.array, array);
	}
	
	public void setArray(int i, int j, int value){
//		array = this.array;
		this.array[i][j] = value;
	}
	
	public void setG(int g){
//		g = this.g;
		this.g = g;
	}
	
	public void setH(int h){
//		h = this.h;
		this.h = h;
	}
	public void setF(int f){
//		f = this.f;
		this.f = f;
	}
	
	public void setParent(EPuzzleState parent){
//		parent = this.parent;
		this.parent = parent;
	}
	
	public int[][] getArray(){
		return this.array;
	}
	
	public int getArray(int i, int j){
		return this.array[i][j];
	}
	
	public int getG(){
		return this.g;
	}
	
	public int getH(){
		return this.h;
	}
	public int getF(){
		return this.f;
	}
	public EPuzzleState getParent(){
		return this.parent;
	}
	
}
