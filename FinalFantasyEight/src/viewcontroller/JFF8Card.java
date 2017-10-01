package viewcontroller;

import javax.swing.JLabel;

import ff8cardgame.Card;

public class JFF8Card extends JLabel {
	
	private Card card;
	
	public Card getCard() {
		return this.card;
	}
	
	public void setCard( Card card ) {
		this.card = card;
		if( this.card != null ) {
			this.setText( this.card.toString() );
		}
	}
}
