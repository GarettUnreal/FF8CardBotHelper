package viewcontroller;

import javax.swing.JLabel;

public class JBoardCell extends JLabel {
	
	private int row;
	private int column;
	
	public JBoardCell( int row, int column, String text ) {
		super( text );
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}
