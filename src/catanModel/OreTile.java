package catanModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OreTile extends ResourceTile{
	private static BufferedImage image = null;
	public OreTile(CatanLocation loc, int num){
		super(loc, num);
	}
	public int cropY0(){return 100;}
	public int cropX(){return 1000;}
	public int cropY(){return 1000;}
	public Image getImage(){
		if (image == null){
			try {
				image = ImageIO.read(getClass().getResource("ore.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
}