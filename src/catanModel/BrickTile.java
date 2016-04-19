package catanModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BrickTile extends ResourceTile{
	private static BufferedImage image = null;
	public BrickTile(CatanLocation loc, int num){
		super(loc, num);
	}
	public Image getImage(){
		if (image == null){
			try {
				image = ImageIO.read(new File("src/catanModel/brick.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
}