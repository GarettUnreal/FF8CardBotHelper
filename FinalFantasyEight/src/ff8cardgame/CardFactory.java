package ff8cardgame;

public class CardFactory {

	public static Card createCard( String string ) {
		return Card.findCard(string);
	}
	
}
