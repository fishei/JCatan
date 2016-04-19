package catanModel;
public abstract class CatanTile{
	private CatanLocation loc;
	private boolean hasRobber;
	
	public CatanTile(CatanLocation loc){
		this.loc = loc;
		hasRobber = false;
	}
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
	public void addRobber(){hasRobber = true;}
	public void removeRobber(){hasRobber = false;}
}