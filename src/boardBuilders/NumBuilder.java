package boardBuilders;

import java.util.HashMap;
import java.util.HashSet;

import catanModel.CatanLocation;

public interface NumBuilder{
	public HashMap<CatanLocation, Integer> buildNums(HashMap<CatanLocation, Integer> tiles);
}