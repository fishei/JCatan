package boardBuilders;

import java.util.HashSet;

import catanModel.CatanLocation;

public interface LocationBuilder{
	public HashSet<CatanLocation> buildLocations();
}