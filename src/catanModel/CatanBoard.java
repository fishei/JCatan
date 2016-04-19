package catanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatanBoard{
	public static final int DESERT = 0;
	public static final int WOOD = 1;
	public static final int WHEAT = 2;
	public static final int SHEEP = 3;
	public static final int BRICK = 4;
	public static final int ORE = 5;
	
	private HashMap<CatanLocation, CatanTile> tileMap;
	private HashMap<Integer, ArrayList<CatanTile>> rollMap;
	public CatanBoard(HashMap<CatanLocation, CatanTile> tileMap){
		this.tileMap = tileMap;
		HashMap<Integer, ArrayList<CatanTile>> rollMap = new HashMap<Integer, ArrayList<CatanTile>>();
		for(int i = 2; i<=12; i++){
			if(i != 7) rollMap.put(i, new ArrayList<CatanTile>());
		}
		for(Map.Entry<CatanLocation, CatanTile> entry : tileMap.entrySet()){
			CatanTile tl = entry.getValue();
			if(tl instanceof ResourceTile){
				rollMap.get(((ResourceTile) tl).getNum()).add(tl);
			}
		}
	}
	public CatanTile getTile(int x, int y){
		return tileMap.get(CatanLocation.getInstance(x,y));
	}
	public ArrayList<CatanTile> tileList(){
		ArrayList<CatanTile> tlst = new ArrayList<CatanTile>();
		for(CatanLocation loc : tileMap.keySet()) tlst.add(tileMap.get(loc));
		return tlst;
	}
}