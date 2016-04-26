package boardBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import catanModel.CatanLocation;
import catanModel.CatanTile;

public class RandomNumBuilder implements NumBuilder{
	public HashMap<Integer, Integer> numsToAdd;
	public HashMap<CatanLocation, Integer> numMap;
	public RandomNumBuilder(HashMap<Integer, Integer> numsToAdd){
		this.numsToAdd = numsToAdd;
	}
	public HashMap<CatanLocation, Integer> buildNums(HashMap<CatanLocation, Integer> tiles){
		numMap = new HashMap<CatanLocation, Integer>();
		Random rand = new Random();
		int numTiles = 0;
		for(Map.Entry<Integer, Integer> entry: numsToAdd.entrySet()) numTiles += entry.getValue();
		numTiles += 1;
		CatanLocation[] locArr = tiles.keySet().toArray(new CatanLocation[numTiles]);
		for(int i = 6; i<9; i+=2){
			for(int j = 0; j < numsToAdd.get(i); j++){
				CatanLocation loc = locArr[rand.nextInt(numTiles)];
				while(numMap.containsKey(loc) || hasAdjHighNum(loc) || !BoardFactory.isNumTileType(tiles.get(loc))) loc = locArr[rand.nextInt(numTiles)];
				numMap.put(loc, i);
			}
		}
		for(Map.Entry<Integer, Integer> entry: numsToAdd.entrySet()){
			if(entry.getKey() != 6 && entry.getKey() != 8){
				for(int i = 0; i<entry.getValue(); i++){
					CatanLocation loc = locArr[rand.nextInt(numTiles)];
					while(numMap.containsKey(loc) || !BoardFactory.isNumTileType(tiles.get(loc))) loc = locArr[rand.nextInt(numTiles)];
					numMap.put(loc, entry.getKey());
				}
			}
		}
		return numMap;
	}
	private boolean hasAdjHighNum(CatanLocation loc){
		for(Integer i: getAdjTiles(loc))
			if(i == 6 || i == 8) return true;
		return false;
	}
	private ArrayList<Integer> getAdjTiles(CatanLocation loc){
		int x = loc.getX();
		int y = loc.getY();
		ArrayList<Integer> adjTiles = new ArrayList<Integer>();
		if(numMap.containsKey(CatanLocation.getInstance(x-1, y-3)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x-1,y-3)));
		if(numMap.containsKey(CatanLocation.getInstance(x+1, y-3)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x+1,y-3)));
		if(numMap.containsKey(CatanLocation.getInstance(x-2, y)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x-2,y)));
		if(numMap.containsKey(CatanLocation.getInstance(x-1, y+3)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x-1,y+3)));
		if(numMap.containsKey(CatanLocation.getInstance(x+1, y+3)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x+1,y+3)));
		if(numMap.containsKey(CatanLocation.getInstance(x+2, y)))
			adjTiles.add(numMap.get(CatanLocation.getInstance(x+2,y)));
		return adjTiles;
	}
}