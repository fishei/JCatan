package catanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class BasicBoardBuilder implements BoardBuilder{
	private static BasicBoardBuilder instance = null;
	private HashSet<CatanLocation> locs;
	private HashMap<CatanLocation, Integer> tiles;
	private HashMap<CatanLocation, CatanTile> tileMap;
	private BasicBoardBuilder(){
		locs = null;
		tiles = null;
		tileMap = null;
	}
	public static BasicBoardBuilder getInstance(){
		if(instance == null) instance = new BasicBoardBuilder();
		return instance;
	}
	public CatanBoard buildBoard(){
		locs = null;
		tiles = null;
		tileMap = null;
		initLocs();
		initTiles();
		initMap();
		return new CatanBoard(tileMap);
	}
	private HashSet<CatanLocation> initLocs(){
		locs = new HashSet<CatanLocation>();
		for(int i = 3; i< 8; i+=2)locs.add(CatanLocation.getInstance(i,2));
		for(int i = 2; i< 9; i+=2)locs.add(CatanLocation.getInstance(i,5));
		for(int i = 1; i< 10; i+=2)locs.add(CatanLocation.getInstance(i,8));
		for(int i = 2; i< 9; i+=2)locs.add(CatanLocation.getInstance(i,11));
		for(int i = 3; i< 8; i+=2)locs.add(CatanLocation.getInstance(i,14));
		return locs;
	}
	private void initTiles(){
		ArrayList<Integer> tileTypes = new ArrayList<Integer>();
		tileTypes.add(CatanBoard.DESERT);
		tileTypes.add(CatanBoard.WOOD);
		tileTypes.add(CatanBoard.SHEEP);
		tileTypes.add(CatanBoard.WHEAT);
		tileTypes.add(CatanBoard.BRICK);
		tileTypes.add(CatanBoard.ORE);
		HashMap<Integer, Integer> tilesRemaining = new HashMap<Integer, Integer>();
		tilesRemaining.put(CatanBoard.DESERT,1);
		tilesRemaining.put(CatanBoard.WOOD,4);
		tilesRemaining.put(CatanBoard.SHEEP,4);
		tilesRemaining.put(CatanBoard.WHEAT,4);
		tilesRemaining.put(CatanBoard.BRICK,3);
		tilesRemaining.put(CatanBoard.ORE,3);
		Random rand = new Random();
		tiles = new HashMap<CatanLocation, Integer>();
		for(CatanLocation loc : locs){
			int tileType = rand.nextInt(6);
			while(tilesRemaining.get(tileTypes.get(tileType)) < 1){
				tileType = rand.nextInt(6);
			}
			tiles.put(loc, tileType);
			tilesRemaining.put(tileType, tilesRemaining.get(tileTypes.get(tileType)) - 1);
		}
	}
	private void initMap(){
		Integer[] nums = {2,3,4,5,6,8,9,10,11,12};
		HashMap<Integer,Integer> numsRemaining = new HashMap<Integer,Integer>();
		Random rand = new Random();
		boolean good = false;
		while(!good){
			numsRemaining.put(2, 1);
			numsRemaining.put(12, 1);
			for(int i = 3; i<12; i++){
				if(i !=7) numsRemaining.put(i, 2);
			}
			good = true;
			tileMap = new HashMap<CatanLocation, CatanTile>();
			for(Map.Entry<CatanLocation, Integer> entry : tiles.entrySet()){
				Integer tileType = entry.getValue();
				CatanLocation loc = entry.getKey();
				if(tileType == CatanBoard.DESERT) tileMap.put(loc, new DesertTile(loc));
				else{
					assert(tileType == CatanBoard.WOOD || tileType == CatanBoard.BRICK || tileType == CatanBoard.SHEEP || tileType == CatanBoard.WHEAT || tileType == CatanBoard.ORE );
					boolean adjHighNum = false;
					for(CatanTile tl : getAdjTiles(loc)){
						if(tl instanceof ResourceTile && (((ResourceTile)tl).getNum() == 6 || ((ResourceTile)tl).getNum() == 8)) adjHighNum = true;
					}
					if(adjHighNum) System.out.println("test");
					if(adjHighNum && !hasLowNumRemaining(numsRemaining)){
						good = false;
						break;
					}
					Integer rollNum = nums[rand.nextInt(10)];
					while(numsRemaining.get(rollNum) < 1 || (adjHighNum && (rollNum == 6 || rollNum == 8))) rollNum = nums[rand.nextInt(10)];
					assert(!adjHighNum || (rollNum != 6 && rollNum != 8));
					tileMap.put(loc, buildTile(tileType, rollNum, loc));
					numsRemaining.put(rollNum, numsRemaining.get(rollNum) -1);
				}
			}
		}
	}
	private static ResourceTile buildTile(Integer type, Integer num, CatanLocation loc){
		if(type == CatanBoard.WOOD) return new WoodTile(loc,num);
		else if(type == CatanBoard.BRICK) return new BrickTile(loc,num); 
		else if(type == CatanBoard.SHEEP) return new SheepTile(loc,num);
		else if(type == CatanBoard.WHEAT) return new WheatTile(loc,num);
		else if(type == CatanBoard.ORE) return new OreTile(loc,num);
		else return null;
	}
	private static boolean hasLowNumRemaining(HashMap<Integer, Integer> numsRemaining){
		for(int i = 2; i<=12; i++){
			if(i != 6 && i!=8 && i!=7 && numsRemaining.get(i) > 0) return true;
		}
		return false;
	}
	private ArrayList<CatanTile> getAdjTiles(CatanLocation loc){
		int x = loc.getX();
		int y = loc.getY();
		ArrayList<CatanTile> adjTiles = new ArrayList<CatanTile>();
		if(tileMap.containsKey(CatanLocation.getInstance(x-1, y-3)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x-1,y-3)));
		if(tileMap.containsKey(CatanLocation.getInstance(x+1, y-3)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x+1,y-3)));
		if(tileMap.containsKey(CatanLocation.getInstance(x-2, y)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x-2,y)));
		if(tileMap.containsKey(CatanLocation.getInstance(x-1, y+3)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x-1,y+3)));
		if(tileMap.containsKey(CatanLocation.getInstance(x+1, y+3)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x+1,y+3)));
		if(tileMap.containsKey(CatanLocation.getInstance(x+2, y)))
			adjTiles.add(tileMap.get(CatanLocation.getInstance(x+2,y)));
		return adjTiles;
	}
}
