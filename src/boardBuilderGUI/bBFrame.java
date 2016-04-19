package boardBuilderGUI;

import javax.swing.JFrame;

import catanModel.*;

public class bBFrame extends JFrame{
	public static final String appTitle = "Random Catan Board Generator";
	private boardPanel bPanel;
	public bBFrame(){
		super();
		setSize(500,500);
		bPanel = new boardPanel(BasicBoardBuilder.getInstance());
		add(bPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args){
		bBFrame fr = new bBFrame();
	}
}