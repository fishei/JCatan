package catanModel;

import java.awt.Image;

public abstract class CatanTile{
	private CatanLocation loc;
	private boolean hasRobber;
	
	public CatanTile(CatanLocation loc){
		this.loc = loc;
		hasRobber = false;
	}
	public int cropX0(){return 0;}
	public int cropY0(){return 0;}
	public int cropX(){return 500;}
	public int cropY(){return 500;}
	public CatanLocation getLocation(){
		return loc;
	}
	public boolean equals(Object o){
		if(o instanceof CatanTile){
			CatanTile ct = (CatanTile) o;
			return ct.getLocation() == this.loc;
		}
		return false;
	}
	public boolean getHasRobber(){
		return hasRobber;
	}
	public abstract Image getImage();
	public void addRobber(){hasRobber = true;}
	public void removeRobber(){hasRobber = false;}
}