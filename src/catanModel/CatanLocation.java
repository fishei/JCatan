package catanModel;

import java.util.HashMap;

public class CatanLocation{
	private int x;
	private int y;
	private CatanLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){ return x;}
	public int getY(){ return y;}
	
	private static HashMap<Integer, CatanLocation> instances = new HashMap<Integer, CatanLocation>();
	
	public static CatanLocation getInstance(int x, int y){
		int index = 100 * x + y;
		CatanLocation loc = instances.get(index);
		if(loc == null){
			loc = new CatanLocation(x,y);
			instances.put(index, loc);
		}
		return loc;
	}
}