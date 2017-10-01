package viewcontroller;

import java.io.Serializable;

public class DragCardData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String name;
	public int selectedCardIndex;
	
	public DragCardData( String name, int selectedCardIndex ) {
		this.name = name;
		this.selectedCardIndex = selectedCardIndex;
	}
	
}
