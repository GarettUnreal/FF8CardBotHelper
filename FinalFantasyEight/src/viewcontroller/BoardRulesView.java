package viewcontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ff8cardgame.Board.Elemental;

public class BoardRulesView extends JPanel {
	
	private JComboBox[][] elements;
	
	private JCheckBox sameRulesCheckbox;
	private JCheckBox plusRuleCheckbox;
	private JCheckBox sameWallCheckbox;

	private JRadioButton youStartButton;
	private JRadioButton computersStartsButton;

	public BoardRulesView() {
		super( new BorderLayout() );
		elements = new JComboBox[3][3];
		
		JPanel boardPanel = new JPanel( new GridLayout(3,3) );
		for( int row = 0; row < 3; row++ ) {
			for( int col = 0; col < 3; col++ ) {
				elements[row][col] = new JComboBox();
				boardPanel.add( elements[row][col] );
			}
		}
		
		JPanel elementPanel = new JPanel();
		elementPanel.setLayout( new BoxLayout( elementPanel, BoxLayout.Y_AXIS ) );
		JLabel boardElementLabel = new JLabel( "Choose Elements on the Board:" );
		boardElementLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		elementPanel.add(boardElementLabel);
		boardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		elementPanel.add(boardPanel);
		add( elementPanel, BorderLayout.WEST );
		
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
		JLabel rulesLabel = new JLabel( "Set Game Rules:" );
		rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		rulesPanel.add(rulesLabel);
		JPanel rulesCheckboxPanel = new JPanel();
		rulesCheckboxPanel.setLayout(new BoxLayout(rulesCheckboxPanel, BoxLayout.Y_AXIS));
		rulesCheckboxPanel.setBorder( BorderFactory.createLineBorder(new Color(0,0,0)));
		sameRulesCheckbox = new JCheckBox( "Same" );
		sameRulesCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		rulesCheckboxPanel.add(sameRulesCheckbox);
		plusRuleCheckbox = new JCheckBox( "Plus" );
		plusRuleCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		rulesCheckboxPanel.add(plusRuleCheckbox);
		sameWallCheckbox = new JCheckBox( "Same Wall" );
		sameWallCheckbox.setAlignmentX(Component.LEFT_ALIGNMENT);
		rulesCheckboxPanel.add(sameWallCheckbox);
		rulesCheckboxPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		rulesPanel.add(rulesCheckboxPanel);
		add( rulesPanel, BorderLayout.EAST);
		
		JPanel southPanel = new JPanel( new FlowLayout());
		JLabel whoStartsLabel = new JLabel( "Who Starts?    " );
		southPanel.add(whoStartsLabel);
		youStartButton = new JRadioButton( "You");
		southPanel.add(youStartButton);
		computersStartsButton = new JRadioButton( "Computer" );
		southPanel.add(computersStartsButton);
		add( southPanel, BorderLayout.SOUTH);
		
	}

	public JComboBox[][] getElements() {
		return elements;
	}

	public JCheckBox getSameRulesCheckbox() {
		return sameRulesCheckbox;
	}

	public JCheckBox getPlusRuleCheckbox() {
		return plusRuleCheckbox;
	}

	public JCheckBox getSameWallCheckbox() {
		return sameWallCheckbox;
	}

	public JRadioButton getYouStartButton() {
		return youStartButton;
	}

	public JRadioButton getComputersStartsButton() {
		return computersStartsButton;
	}
	
}
