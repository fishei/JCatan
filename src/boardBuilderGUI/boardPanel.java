package boardBuilderGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

import catanModel.*;

public class boardPanel extends JPanel{
	public static final Color WATER = Color.BLUE;
	public static final Color TAN = new Color(210,180,140);
	private CatanBoard board;
	private BoardBuilder bob;
	private static final double y_pos_factor = .5;
	private static final double x_pos_factor = Math.sqrt(2.0) / 2.0;
	private double maxX = 0;
	private double maxY = 0;
	private double sideLen;
	private ArrayList<CatanTile> tiles;
	public boardPanel(BoardBuilder bob){
		super();
		this.bob = bob;
		this.board = bob.buildBoard();
		tiles = board.tileList();
		for(CatanTile tile: tiles){
			maxX = Math.max(maxX, tile.getLocation().getX());
			maxY = Math.max(maxY, tile.getLocation().getY());
		}
		maxX += 1;
		maxY += 2;
		setPreferredSize(new Dimension(500,500));
		setBackground(WATER);
		setVisible(true);
	}
	public void newBoard(){
		board = bob.buildBoard();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		sideLen = Math.min(((double)getWidth()) / (maxX * x_pos_factor), ((double)getHeight()) / (maxY * y_pos_factor));
		for(CatanTile tile : tiles){
			double x = (double) tile.getLocation().getX();
			double y = (double) tile.getLocation().getY();
			Polygon tileHex = new Polygon();
			tileHex.addPoint((int)((x - 1.0) * x_pos_factor*sideLen), (int) ((y - 1.0)*y_pos_factor*sideLen));
			tileHex.addPoint((int)((x) * x_pos_factor*sideLen), (int) ((y - 2.0)*y_pos_factor*sideLen));
			tileHex.addPoint((int)((x + 1.0) * x_pos_factor*sideLen), (int) ((y - 1.0)*y_pos_factor*sideLen));
			tileHex.addPoint((int)((x + 1.0) * x_pos_factor*sideLen), (int) ((y + 1.0)*y_pos_factor*sideLen));
			tileHex.addPoint((int)((x) * x_pos_factor*sideLen), (int) ((y + 2.0)*y_pos_factor*sideLen));
			tileHex.addPoint((int)((x - 1.0) * x_pos_factor*sideLen), (int) ((y + 1.0)*y_pos_factor*sideLen));
			g.drawPolygon(tileHex);
			Shape shape = g.getClip();
			g.setClip(tileHex);
			g.drawImage(tile.getImage(), (int)(x*sideLen*x_pos_factor- 2*sideLen), (int)(y*sideLen*y_pos_factor-2*sideLen), (int)(x*sideLen*x_pos_factor+2*sideLen), (int)(y*sideLen*y_pos_factor+2*sideLen), tile.cropX0(), tile.cropY0(), tile.cropX(), tile.cropY(), null);
			g.setClip(shape);
			if(tile instanceof ResourceTile){
				Color lastColor = g.getColor();
				g.setColor(TAN);
				int r = (int)sideLen / 2;
				g.fillOval((int)(x*sideLen*x_pos_factor)-r/2,(int)(sideLen*y_pos_factor*y)-r/2,r,r);
				Font lastFont = g.getFont();
				g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(sideLen / 3)));
				g.setColor(Color.BLACK);
				String numStr = String.valueOf(((ResourceTile)tile).getNum());
				if(numStr.length() > 1){
					g.drawString(numStr, (int)(x*sideLen*x_pos_factor-sideLen/6), (int)(y*sideLen*y_pos_factor+sideLen/8));
				}
				else{
					g.drawString(numStr, (int)(x*sideLen*x_pos_factor-sideLen/12), (int)(y*sideLen*y_pos_factor+sideLen/8));
				}
				g.drawString(numStr, 0,0);
				g.setColor(lastColor);
			}
			
		}
	}
}