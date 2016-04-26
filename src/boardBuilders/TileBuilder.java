package boardBuilders;

import java.util.HashMap;
import java.util.HashSet;

import catanModel.CatanLocation;

public interface TileBuilder{
	public HashMap<CatanLocation, Integer> buildTiles(HashSet<CatanLocation> locs);
}