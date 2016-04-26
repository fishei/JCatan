package boardBuilders;

import java.util.HashMap;

public class BasicRandomBoardFactory extends RandomBoardFactory{
	private static BasicRandomBoardFactory instance = null;
	private BasicRandomBoardFactory(){
		super();
		locB = BasicLocationBuilder.getInstance();
	}
	public static BasicRandomBoardFactory getInstance(){
		if(instance == null) instance = new BasicRandomBoardFactory();
		return instance;
	}
	protected HashMap<Integer, Integer> numsToAdd(){
		HashMap<Integer, Integer> ret = new HashMap<Integer, Integer>();
		ret.put(2,1);
		ret.put(12,1);
		for(int i = 3; i<12; i++){
			if(i != 7) ret.put(i, 2);
		}
		return ret;
	}
	protected HashMap<Integer, Integer> tileNums(){
		HashMap<Integer, Integer> ret = new HashMap<Integer, Integer>();
		ret.put(BoardFactory.DESERT, 1);
		ret.put(BoardFactory.WOOD, 4);
		ret.put(BoardFactory.SHEEP, 4);
		ret.put(BoardFactory.WHEAT, 4);
		ret.put(BoardFactory.BRICK, 3);
		ret.put(BoardFactory.ORE, 3);
		return ret;
	}
}