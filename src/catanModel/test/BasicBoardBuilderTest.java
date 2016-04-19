package catanModel.test;
import catanModel.*;

public class BasicBoardBuilderTest{
	public static void main(String [] args){
		BoardBuilder bob = BasicBoardBuilder.getInstance();
		CatanBoard board = bob.buildBoard();
		System.out.println("Built Board");
		int x = 1;
		for(int i = 3; i< 8; i+=2){
			printTile(i,2,board, x);
			x++;
		}
		for(int i = 2; i< 9; i+=2){
			printTile(i,5,board, x);
			x++;
		}
		for(int i = 1; i< 10; i+=2){
			printTile(i,8,board, x);
			x++;
		}
		for(int i = 2; i< 9; i+=2){
			printTile(i,11,board, x);
			x++;
		}
		for(int i = 3; i< 8; i+=2){
			printTile(i,13,board, x);
			x++;
		}
	}
	public static void printTile(int x, int y, CatanBoard board, int num){
		CatanTile ct = board.getTile(x, y);
		if(ct == null){
			System.out.print(x);
			System.out.print(" , ");
			System.out.println(y);
		}
		assert(ct != null);
		System.out.print(num);
		System.out.print(": ");
		if(ct instanceof DesertTile) System.out.println("Desert");
		else{
			if(ct instanceof WoodTile) System.out.print("Wood");
			else if(ct instanceof BrickTile) System.out.print("Brick");
			else if(ct instanceof SheepTile) System.out.print("Sheep");
			else if(ct instanceof WheatTile) System.out.print("Wheat");
			else if(ct instanceof OreTile) System.out.print("Ore");
			else assert false;
			System.out.print(" : ");
			ResourceTile rt = (ResourceTile) ct;
			assert(rt != null);
			Integer k = rt.getNum();
			System.out.println(k);
		}
	}
}