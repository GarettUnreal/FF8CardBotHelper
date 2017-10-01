package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ff8cardgame.Board;
import ff8cardgame.Card;
import ff8cardgame.CardFactory;
import ff8cardgame.Player;

public class ChooseCardsController extends WizardObservable
	implements ActionListener, ListSelectionListener, ListDataListener {
	
	public static final int EXPECTED_NUMBER_OF_CHOSEN_CARDS = 5;
	
	private ChooseCardsView view;
	private DefaultListModel<String> cardListModel;
	private DefaultListModel<String> cardsToUseListModel;
	private String[] defaultCards;
	private Player player;
	
	public ChooseCardsController( String[] cards,  ChooseCardsView view, Player player ) {
		super( null );
		this.player = player;
		this.view = view;

		defaultCards = cards;
		cardsToUseListModel = new DefaultListModel<String>();
		view.getCardsToUse().setModel(cardsToUseListModel);
		
		cardListModel = new DefaultListModel<String>();
		for( String card : cards ) {
			cardListModel.addElement(card);
		}
		view.getCardsToChooseFrom().setModel(cardListModel);
		updateAddCardButton();
		updateRemoveCardButton();
		
		view.getCardsToChooseFrom().addListSelectionListener(this);
		view.getCardsToUse().addListSelectionListener(this);
		view.getAddToCardsToUse().addActionListener(this);
		view.getRemoveSelectedCardsToUse().addActionListener(this);
		view.getSearchButton().addActionListener(this);
		view.getSearchTextField().addActionListener(this);
		
		cardsToUseListModel.addListDataListener(this);
	}
	
	private void updatePlayer() {
		Card[] cards = new Card[EXPECTED_NUMBER_OF_CHOSEN_CARDS];
		for( int index = 0; index < cards.length; index++ ) {
			cards[index] = CardFactory.createCard(cardsToUseListModel.getElementAt(index));
		}
		player.initialize(cards);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( view.getAddToCardsToUse() == event.getSource() ) {
			cardsToUseListModel.addElement(cardListModel.getElementAt(view.getCardsToChooseFrom().getSelectedIndex()));
			updateAddCardButton();
		} else if( view.getRemoveSelectedCardsToUse() == event.getSource() ) {
			cardsToUseListModel.removeElementAt(view.getCardsToUse().getSelectedIndex());
			updateAddCardButton();
		} else if( view.getSearchButton() == event.getSource() ) {
			applySearchFilter();
		} else if( view.getSearchTextField() == event.getSource() ) {
			applySearchFilter();
		}
	}
	
	public String[] getChosenCards() {
		return (String[]) cardsToUseListModel.toArray();
	}

	@Override
	public void valueChanged(ListSelectionEvent event) {
		if( !event.getValueIsAdjusting() && view.getCardsToChooseFrom() == event.getSource() ) {
			updateAddCardButton();
		} else if( !event.getValueIsAdjusting() && view.getCardsToUse() == event.getSource() ) {
			updateRemoveCardButton();
		}
	}
	
	private void updateAddCardButton() {
		int numCards = cardsToUseListModel.getSize();
		if( !view.getCardsToChooseFrom().isSelectionEmpty() &&
				numCards >= 0 && numCards < EXPECTED_NUMBER_OF_CHOSEN_CARDS ) {
			view.getAddToCardsToUse().setEnabled(true);
		} else {
			view.getAddToCardsToUse().setEnabled(false);
		}
	}

	private void updateRemoveCardButton() {
		view.getRemoveSelectedCardsToUse().setEnabled(!view.getCardsToUse().isSelectionEmpty());
	}
	
	private void applySearchFilter() {
		String pattern = view.getSearchTextField().getText();
		cardListModel.removeAllElements();
		for( String str : defaultCards ) {
			if( str.toLowerCase().indexOf( pattern ) != -1 ) {
				cardListModel.addElement( str );
			}
		}
	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		if( cardsToUseListModel.getSize() == EXPECTED_NUMBER_OF_CHOSEN_CARDS ) {
			this.updatePlayer();
			this.notifyWizardComplete();
		}
	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		this.notifyWizardIncomplete();
	}
}
