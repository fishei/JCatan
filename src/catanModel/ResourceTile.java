package catanModel;

public abstract class ResourceTile extends CatanTile{
	private int num;
	public ResourceTile(CatanLocation loc, int num){
		super(loc);
		this.num = num;
	}
	public int getNum(){
		return num;
	}
	public int numDots(){
		for(int i = 1; i < 6; i++){
			if(7 - 6 + i == num || 7 + 6 - i == num) return i;
		}
		return -1;
	}
	public abstract Integer getType();
}