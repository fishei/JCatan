package boardBuilders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import catanModel.*;

public abstract class BoardFactory{
	public static final Integer WATER = 0;
	public static final Integer DESERT = 1;
	public static final Integer BRICK = 2;
	public static final Integer WOOD = 3;
	public static final Integer SHEEP = 4;
	public static final Integer WHEAT = 5;
	public static final Integer ORE = 6;
	public static final Integer GOLD = 7;
	protected LocationBuilder locB;
	protected NumBuilder numB;
	protected TileBuilder tileB;
	public CatanBoard buildBoard(){
		HashSet<CatanLocation> locs = locB.buildLocations();
		HashMap<CatanLocation, Integer> tiles = tileB.buildTiles(locs);
		HashMap<CatanLocation, Integer> nums = numB.buildNums(tiles);
		HashMap<CatanLocation, CatanTile> boardMap = new HashMap<CatanLocation, CatanTile>();
		for(Map.Entry<CatanLocation, Integer> entry: tiles.entrySet()){
			Integer tileType = entry.getValue();
			CatanLocation loc = entry.getKey();
			if(isNumTileType(tileType)){
				boardMap.put(loc, buildTile(loc, tileType, nums.get(loc)));
			}else boardMap.put(loc, buildTile(loc, tileType, null));
		}
		assert(boardMap.size() > 0);
		return new CatanBoard(boardMap);
	}
	public CatanTile buildTile(CatanLocation loc, Integer type, Integer num){
		if(type == DESERT) return new DesertTile(loc);
		else if(type == BRICK) return new BrickTile(loc, num);
		else if(type == WOOD) return new WoodTile(loc, num);
		else if(type == SHEEP) return new SheepTile(loc, num);
		else if(type == WHEAT) return new WheatTile(loc, num);
		else if(type == ORE) return new OreTile(loc, num);
		else return null;
	}
	public static boolean isNumTileType(Integer type){
		return (type != DESERT && type != WATER);
	}
}