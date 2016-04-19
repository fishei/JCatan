package catanModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DesertTile extends CatanTile{
	private static BufferedImage image = null;
	public DesertTile(CatanLocation loc){
		super(loc);
	}
	public Image getImage(){
		if (image == null){
			try {
				image = ImageIO.read(new File("src/catanModel/desert.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
}