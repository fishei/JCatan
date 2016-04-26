package boardBuilders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import catanModel.CatanLocation;

public class RandomTileBuilder implements TileBuilder{
	public HashMap<Integer, Integer> tileNums;
	public RandomTileBuilder(HashMap<Integer, Integer> tileNums){
		this.tileNums = tileNums;
	}
	public HashMap<CatanLocation, Integer> buildTiles(HashSet<CatanLocation> locs){
		Random rand = new Random();
		int numTiles = 0;
		for(Map.Entry<Integer, Integer> entry: tileNums.entrySet()) numTiles += entry.getValue();
		assert(numTiles == locs.size());
		CatanLocation[] locArr = locs.toArray(new CatanLocation[numTiles]);
		HashMap<CatanLocation, Integer> tiles = new HashMap<CatanLocation, Integer>();
		for(Map.Entry<Integer, Integer> entry: tileNums.entrySet()){
			Integer type = entry.getKey();
			Integer numTile = entry.getValue();
			for(int i = 0; i< numTile; i++){
				CatanLocation loc = locArr[rand.nextInt(numTiles)];
				while(tiles.containsKey(loc)) loc = locArr[rand.nextInt(numTiles)];
				tiles.put(loc, type);
			}
		}
		return tiles;
	}
}