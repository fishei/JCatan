package catanModel;

import java.util.HashMap;

public class Intersection{
	private static HashMap<CatanLocation, Intersection> instances = new HashMap<CatanLocation, Intersection>();
	private CatanLocation loc;
	private Intersection(CatanLocation loc){
		this.loc = loc;
	}
	public static Intersection getInstance(CatanLocation loc){
		if(!instances.containsKey(loc)) instances.put(loc, new Intersection(loc));
		return instances.get(loc);
	}
	public CatanLocation getLocation(){
		return loc;
	}
}