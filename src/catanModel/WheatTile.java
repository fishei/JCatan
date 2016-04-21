package catanModel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WheatTile extends ResourceTile{
	public static BufferedImage image = null;
	public WheatTile(CatanLocation loc, int num){
		super(loc, num);
	}
	public int cropY0(){return 250;}
	public Image getImage(){
		if (image == null){
			try {
				image = ImageIO.read(getClass().getResource("wheat.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}
}