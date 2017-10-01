package viewcontroller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.TransferHandler;

import ff8cardgame.Board;
import ff8cardgame.Card;
import ff8cardgame.Move;

public class CardTransferHandler extends TransferHandler {
	
	private Board board;
	private Vector<Card> cards;
	private Vector<Integer> availableIndices;
	private CalculateAIMove calculateAIMove;
	
	public CardTransferHandler( Board board, Card [] cards, CalculateAIMove calculateAIMove ) {
		this.calculateAIMove = calculateAIMove;
		this.board = board;
		this.cards = new Vector<Card>();
		availableIndices = new Vector<Integer>();
		for( int index = 0; index < cards.length; index++ ) {
			this.cards.addElement(cards[index]);
			availableIndices.add( index );
		}
	}
	
	@Override
	public int getSourceActions(JComponent component) {
		return TransferHandler.COPY_OR_MOVE;
	}
	
	@Override
	public Transferable createTransferable(JComponent source) {
		JList sourceList = (JList) source;
		return new CardTransferable( new DragCardData(
				(String) sourceList.getSelectedValue(),
				sourceList.getSelectedIndex()
			));
	}
	
	@Override
	public void exportDone( JComponent source, Transferable data, int action) {
		if( action == TransferHandler.MOVE ) {
			try {
				CardTransferable cardTransferable = (CardTransferable) data;
				DragCardData dragData = (DragCardData) cardTransferable.getTransferData(null);
				availableIndices.removeElementAt(dragData.selectedCardIndex);
				
				calculateAIMove.process();
			} catch (UnsupportedFlavorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean canImport(TransferSupport support) {
		return support.isDrop() && (support.getComponent() instanceof JLabel) &&
				((JLabel)support.getComponent()).getText().contains( SimulationView.DROP_TEXT );
	}
	
	@Override
	public boolean importData(TransferSupport support) {
		if( canImport(support) ) {
			try {
				DragCardData dragData = (DragCardData) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
				if( dragData == null ) {
					return false;
				}
				
				// Get the card index from the 
				int cardIndex = availableIndices.elementAt(dragData.selectedCardIndex);
				JBoardCell boardCell = (JBoardCell)(support.getComponent());
				
				board.printRules();
				board.print();
				board.printPlayerCards();
				boolean isHuman = false; // We are making the computers move.
				board.makeMove(new Move(boardCell.getRow(),boardCell.getColumn(),cardIndex), isHuman);
				board.printRules();
				board.print();
				board.printPlayerCards();
			} catch(Exception exception) {
				exception.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
