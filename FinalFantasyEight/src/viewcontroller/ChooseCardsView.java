package viewcontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class ChooseCardsView extends JPanel {
	
	private static final int CARD_CELL_WIDTH = 200;

	private JTextField searchTextField;
	private JButton searchButton;
	
	private JList cardsToChooseFrom;
	
	private JButton addToCardsToUse;
	
	private JList cardsToUse;
	
	private JButton removeSelectedCardsToUse;
	
	public ChooseCardsView(String title) {
		super(new BorderLayout());
		
		JLabel cardsToChooseLabel = new JLabel( "Available Cards:" );
		cardsToChooseLabel.setBorder( new EmptyBorder( 10, 0, 0, 0 ) );
		cardsToChooseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Search GUI
		searchTextField = new JTextField( "", 12 );
		searchButton = new JButton( "Search" );
		JPanel searchPanel = new JPanel( new FlowLayout());
		searchPanel.add(searchTextField);
		searchPanel.add(searchButton);
		
		// Cards To Choose From GUI
		cardsToChooseFrom = new JList();
		cardsToChooseFrom.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		cardsToChooseFrom.setVisibleRowCount(10);
		cardsToChooseFrom.setFixedCellWidth(CARD_CELL_WIDTH);
		JScrollPane cardsToChooseFromScroller = new JScrollPane( cardsToChooseFrom );
		cardsToChooseFromScroller.setMaximumSize( new Dimension( CARD_CELL_WIDTH+20,500));
		
		// Add to Button GUI
		addToCardsToUse = new JButton( "Use Selected Card" );
		
		// Search and Cards to Choose From and Add To Button GUI
		JPanel westPanel = new JPanel();
		westPanel.setLayout( new BoxLayout( westPanel, BoxLayout.Y_AXIS ) );
		westPanel.add( cardsToChooseLabel );
		searchPanel.setAlignmentX( Component.CENTER_ALIGNMENT );
		westPanel.add(searchPanel);
		cardsToChooseFromScroller.setAlignmentX( Component.CENTER_ALIGNMENT );
		westPanel.add(cardsToChooseFromScroller);
		addToCardsToUse.setAlignmentX( Component.CENTER_ALIGNMENT );
		westPanel.add(addToCardsToUse);
		
		add( westPanel, BorderLayout.WEST );
		
		// Cards to use GUI
		cardsToUse = new JList();
		cardsToUse.setSelectionMode( ListSelectionModel.SINGLE_SELECTION);
		cardsToUse.setVisibleRowCount(ChooseCardsController.EXPECTED_NUMBER_OF_CHOSEN_CARDS);
		cardsToUse.setFixedCellWidth(CARD_CELL_WIDTH);
		JScrollPane cardsToUseScrollPane = new JScrollPane( cardsToUse );
		cardsToUseScrollPane.setMinimumSize( new Dimension( CARD_CELL_WIDTH+20,100));
		cardsToUseScrollPane.setMaximumSize( new Dimension( CARD_CELL_WIDTH+20,300));
		cardsToUseScrollPane.setSize(CARD_CELL_WIDTH+20,200);
		
		removeSelectedCardsToUse = new JButton( "Don't Use Selected" );
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout( new BoxLayout( eastPanel, BoxLayout.Y_AXIS ) );
		JLabel cardsToUseLabel = new JLabel( "Chosen Cards:" );
		cardsToUseLabel.setBorder( new EmptyBorder( 10, 0, 0, 0 ) );
		cardsToUseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add( cardsToUseLabel );
		cardsToUseScrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(cardsToUseScrollPane);
		removeSelectedCardsToUse.setAlignmentX(Component.CENTER_ALIGNMENT);
		eastPanel.add(removeSelectedCardsToUse);
		
		add( eastPanel, BorderLayout.EAST);
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont( new Font( "Serif", Font.PLAIN, 20 ));
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BoxLayout(titlePanel,BoxLayout.Y_AXIS));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add( titleLabel );
		add( titlePanel, BorderLayout.NORTH );
		
		add( new JLabel( "<html><font size=\"20\">&#8658;</font></html>" ), BorderLayout.CENTER);
	}
	
	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JList getCardsToChooseFrom() {
		return cardsToChooseFrom;
	}

	public JButton getAddToCardsToUse() {
		return addToCardsToUse;
	}

	public JList getCardsToUse() {
		return cardsToUse;
	}

	public JButton getRemoveSelectedCardsToUse() {
		return removeSelectedCardsToUse;
	}
}
