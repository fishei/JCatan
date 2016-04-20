package boardBuilderGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import catanModel.BasicBoardBuilder;

public class bBMainPanel extends JPanel{
	public boardPanel bPanel;
	public JButton newButton;
	public bBMainPanel(){
		super();
		bPanel = new boardPanel(BasicBoardBuilder.getInstance());
		newButton = new JButton("New Board");
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bPanel.newBoard();
				repaint();
			}
		});
		setLayout(new BorderLayout());
		add(newButton, BorderLayout.EAST);
		add(bPanel, BorderLayout.CENTER);
	}
}