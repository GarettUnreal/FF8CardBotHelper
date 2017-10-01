package viewcontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ff8cardgame.Board;

public class SimulationView extends JPanel {
	
	private Color humanColor;
	private Color computerColor;
	private Color noPlayerColor;
	
	private JBoardCell[][] boardCells;
	private JList computersCardsList;
	private DefaultListModel<String> computersCards;
	private JList yourCardsList;
	private DefaultListModel<String> yourCards;
	private JLabel statisticValues;
	
	private JCheckBox sameRulesCheckbox;
	private JCheckBox plusRuleCheckbox;
	private JCheckBox sameWallCheckbox;
	
	private JFrame mainWindow;
	
	public static final String DROP_TEXT = "&#60;Drop Here&#62;";

	public SimulationView(JFrame mainWindow, Color humanColor, Color computerColor ) {
		super( new BorderLayout() );
		this.setPreferredSize(new Dimension(1000,800));
		this.humanColor = humanColor;
		this.computerColor = computerColor;
		this.noPlayerColor = this.getBackground();
		this.mainWindow = mainWindow;
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel,BoxLayout.Y_AXIS));
		JLabel boardLabel = new JLabel( "Board:" );
		boardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		boardPanel.add(boardLabel);
		JPanel boardGrid = new JPanel( new GridLayout(3,3));
		boardGrid.setBorder( BorderFactory.createLineBorder(new Color(0,0,0)));
		boardGrid.setPreferredSize(new Dimension(300,300));
		boardCells = new JBoardCell[3][3];
		for( int row = 0; row < 3; row++ ){
			for( int col = 0; col < 3; col++ ) {
				boardCells[row][col] = new JBoardCell( row, col, SimulationView.DROP_TEXT );
				boardCells[row][col].setOpaque(true);
				boardCells[row][col].setBackground(this.noPlayerColor);
				boardCells[row][col].setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));
				boardGrid.add(boardCells[row][col]);
			}
		}
		boardPanel.add(boardGrid);
		add( boardPanel, BorderLayout.CENTER);
		
		JPanel computerCardsPanel = new JPanel();
		computerCardsPanel.setLayout( new BoxLayout(computerCardsPanel,BoxLayout.Y_AXIS));
		JLabel computerCardsLabel = new JLabel( "Computer's Cards" );
		computerCardsLabel.setBorder( new CompoundBorder(
				computerCardsLabel.getBorder(), new EmptyBorder( 100,0,0,0)));
		computerCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		computerCardsPanel.add(computerCardsLabel);
		computersCards = new DefaultListModel<String>();
		computersCardsList = new JList(computersCards);
		computersCardsList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		computersCardsList.setVisibleRowCount(10);
		computersCardsList.setFixedCellWidth(200);
		computerCardsPanel.add(computersCardsList);
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
		computerCardsPanel.add(rulesPanel);
		add( computerCardsPanel, BorderLayout.WEST);
		
		JPanel yourCardsPanel = new JPanel();
		yourCardsPanel.setLayout( new BoxLayout(yourCardsPanel,BoxLayout.Y_AXIS));
		JLabel yourCardsLabel = new JLabel( "Your Cards" );
		yourCardsLabel.setBorder( new CompoundBorder(
				yourCardsLabel.getBorder(), new EmptyBorder( 100,0,0,0)));
		yourCardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		yourCardsPanel.add(yourCardsLabel);
		yourCards = new DefaultListModel<String>();
		yourCardsList = new JList(yourCards);
		yourCardsList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		yourCardsList.setVisibleRowCount(10);
		yourCardsList.setFixedCellWidth(200);
		yourCardsPanel.add(yourCardsList);
		add( yourCardsPanel, BorderLayout.EAST);
		
		
		JPanel statisticsPanel = new JPanel( new FlowLayout() );
		JLabel statLabel = new JLabel( "Statistics:   " );
		statisticsPanel.add(statLabel);
		statisticValues = new JLabel( "<Empty Stats>" );
		statisticsPanel.add(statisticValues);
		add( statisticsPanel, BorderLayout.SOUTH );
		
	}
	
	public JFrame getMainWindow() {
		return mainWindow;
	}
	
	public JLabel[][] getBoardCells() {
		return boardCells;
	}

	public JList getComputersCardsList() {
		return computersCardsList;
	}

	public DefaultListModel<String> getComputersCards() {
		return computersCards;
	}

	public JList getYourCardsList() {
		return yourCardsList;
	}

	public DefaultListModel<String> getYourCards() {
		return yourCards;
	}

	public JLabel getStatisticValues() {
		return statisticValues;
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
	
	public void setBoardCellText( int row, int column, String text, Board.CellPlayer playerType ) {
		Color color;
		switch( playerType ) {
		case NONE:
			color = this.noPlayerColor;
			break;
		case HUMAN:
			color = this.humanColor;
			break;
		case COMPUTER:
			color = this.computerColor;
			break;
		default:
			System.out.println( "Error: Invalid player type." );
			return;
		}
		
		JBoardCell boardCell = this.boardCells[row][column];
		boardCell.setText(text);
		boardCell.setBackground(color);
		boardCell.setForeground(new Color(
				255 - color.getRed(),
				255 - color.getGreen(),
				255 - color.getBlue()
			));
	}
}
