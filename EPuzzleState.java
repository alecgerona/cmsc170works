
public class EPuzzleState {
	public int array[][] = new int[3][3];
	public int parent[][] = new int[3][3];
	public int g,h,f;
	
	public EPuzzleState(int[][] array, int g, int h, int f, int [][] parent){
//		array = this.array;
//		g = this.g;
//		h = this.h;
//		f = this.f;
//		parent = this.parent;
		this.g = g;
		this.h= h;
		this.f = f;
		Eightpuzzle.copyMyArray(this.parent,parent);
		Eightpuzzle.copyMyArray(this.array, array);
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
	
	public void setParent(int[][] parent){
//		parent = this.parent;
		this.parent = parent;
	}
	
	public int[][] getArray(){
		return this.array;
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
	public int[][] getParent(){
		return this.parent;
	}
	
}
