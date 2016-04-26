package boardBuilders;

import java.util.HashMap;

public abstract class RandomBoardFactory extends BoardFactory{
	public RandomBoardFactory(){
		super();
		this.numB = new RandomNumBuilder(numsToAdd());
		this.tileB = new RandomTileBuilder(tileNums());
	}
	protected abstract HashMap<Integer, Integer> numsToAdd();
	protected abstract HashMap<Integer, Integer> tileNums();
}