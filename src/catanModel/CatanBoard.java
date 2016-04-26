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
	private HashMap<CatanLocation, Intersection> intMap;
	public CatanBoard(HashMap<CatanLocation, CatanTile> tileMap){
		this.tileMap = tileMap;
		intMap = new HashMap<CatanLocation, Intersection>();
		this.rollMap = new HashMap<Integer, ArrayList<CatanTile>>();
		for(int i = 2; i<=12; i++){
			if(i != 7) rollMap.put(i, new ArrayList<CatanTile>());
		}
		for(Map.Entry<CatanLocation, CatanTile> entry : tileMap.entrySet()){
			CatanTile tl = entry.getValue();
			if(tl instanceof ResourceTile){
				rollMap.get(((ResourceTile) tl).getNum()).add(tl);
			}
			addIntersections(tl);
		}
	}
	private void addIntersections(CatanTile tl){
		int x = tl.getLocation().getX();
		int y = tl.getLocation().getY();
		for(int i = -1; i<2; i+=2){
			for(int j = -1; j<2;j+=2){
				if(!intMap.containsKey(CatanLocation.getInstance(x+j, y+i))) intMap.put(CatanLocation.getInstance(x+j,y+i), Intersection.getInstance(CatanLocation.getInstance(x+j,y+i)));
			}
			if(!intMap.containsKey(CatanLocation.getInstance(x, y+2*i))) intMap.put(CatanLocation.getInstance(x, y+2*i), Intersection.getInstance(CatanLocation.getInstance(x, y+2*i)));
		}
	}
	public CatanTile getTile(int x, int y){
		if(!tileMap.containsKey(CatanLocation.getInstance(x,y))) return null;
		return tileMap.get(CatanLocation.getInstance(x,y));
	}
	public ArrayList<CatanTile> tileList(){
		ArrayList<CatanTile> tlst = new ArrayList<CatanTile>();
		for(CatanLocation loc : tileMap.keySet()) tlst.add(tileMap.get(loc));
		return tlst;
	}
	public Intersection getIntersection(int x, int y){
		if(!intMap.containsKey(CatanLocation.getInstance(x,y)))return null;
		return intMap.get(CatanLocation.getInstance(x,y));
	}
	public ArrayList<CatanTile> getSurroundingTiles(Intersection intsec){
		ArrayList<CatanTile> ret = new ArrayList<CatanTile>();
		int x = intsec.getLocation().getX();
		int y = intsec.getLocation().getY();
		for(int i = -1; i<2; i+=2){
			for(int j = -1; j<2;j+=2){
				if(getTile(x+j,y+i) != null) ret.add(getTile(x+j,y+i));
			}
			if(getTile(x,y+2*i) != null) ret.add(getTile(x,y+2*i));
		}
		return ret;
	}
	public HashMap<Integer, Integer> getProduction(int x, int y){
		CatanLocation loc = CatanLocation.getInstance(x,y);
		if(!intMap.containsKey(loc)) return null;
		HashMap<Integer,Integer> ret = new HashMap<Integer, Integer>();
		ret.put(WOOD,0);
		ret.put(BRICK,0);
		ret.put(ORE,0);
		ret.put(WHEAT,0);
		ret.put(SHEEP,0);
		for(CatanTile tl: getSurroundingTiles(getIntersection(x,y))){
			if(tl instanceof ResourceTile){
				ResourceTile rt = (ResourceTile) tl;
				ret.put(rt.getType(), ret.get(rt.getType()) + rt.numDots());
			}
		}
		return ret;
	}
}