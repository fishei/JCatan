package boardBuilders;

import java.util.HashSet;

import catanModel.CatanLocation;

public class BasicLocationBuilder implements LocationBuilder{
	private static BasicLocationBuilder instance = null;
	private BasicLocationBuilder(){}
	public static BasicLocationBuilder getInstance(){
		if(instance == null) instance = new BasicLocationBuilder();
		return instance;
	}
	
	public HashSet<CatanLocation> buildLocations(){
		HashSet<CatanLocation> locs = new HashSet<CatanLocation>();
		for(int i = 3; i< 8; i+=2)locs.add(CatanLocation.getInstance(i,2));
		for(int i = 2; i< 9; i+=2)locs.add(CatanLocation.getInstance(i,5));
		for(int i = 1; i< 10; i+=2)locs.add(CatanLocation.getInstance(i,8));
		for(int i = 2; i< 9; i+=2)locs.add(CatanLocation.getInstance(i,11));
		for(int i = 3; i< 8; i+=2)locs.add(CatanLocation.getInstance(i,14));
		return locs;
	}
}