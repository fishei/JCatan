package boardBuilderGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boardBuilders.BasicRandomBoardFactory;

import catanModel.BasicBoardBuilder;
import catanModel.CatanBoard;
import catanModel.Intersection;

public class bBMainPanel extends JPanel{
	private boardPanel bPanel;
	private JButton newButton;
	private Intersection selected_int = null;
	private JLabel infoLabel;
	
	public bBMainPanel(){
		super();
		bPanel = new boardPanel(BasicRandomBoardFactory.getInstance());
		newButton = new JButton("New Board");
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bPanel.newBoard();
				bPanel.setIntersection(-1, -1);
				infoLabel.setText("Click an intersection to see production information");
				repaint();
			}
		});
		bPanel.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){
				bPanel.setIntersection(e.getX(), e.getY());
				infoLabel.setText(getProduction(bPanel.getIntsec()));
				repaint();
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
		});
		infoLabel = new JLabel("Click an intersection to see production information");
		setLayout(new BorderLayout());
		add(newButton, BorderLayout.EAST);
		add(bPanel, BorderLayout.CENTER);
		add(infoLabel, BorderLayout.SOUTH);
	}
	private String getProduction(Intersection intsec){
		if (intsec == null) return "Click an intersection to see production information";
		HashMap<Integer, Integer> prod = bPanel.getBoard().getProduction(intsec.getLocation().getX(), intsec.getLocation().getY());
		String ret = "Production per 36 rolls: ";
		if(prod.get(CatanBoard.BRICK)> 0) ret = ret.concat("\n     Brick: ").concat(prod.get(CatanBoard.BRICK).toString());
		if(prod.get(CatanBoard.ORE)> 0) ret = ret.concat("\n       Ore: ").concat(prod.get(CatanBoard.ORE).toString());
		if(prod.get(CatanBoard.SHEEP)> 0) ret = ret.concat("\n     Sheep: ").concat(prod.get(CatanBoard.SHEEP).toString());
		if(prod.get(CatanBoard.WHEAT)> 0) ret = ret.concat("\n     Wheat: ").concat(prod.get(CatanBoard.WHEAT).toString());
		if(prod.get(CatanBoard.WOOD)> 0) ret = ret.concat("\n      Wood: ").concat(prod.get(CatanBoard.WOOD).toString());
		return ret;
	}
	
}