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
}