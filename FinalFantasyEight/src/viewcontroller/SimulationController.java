package viewcontroller;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import ff8cardgame.AlphaBetaStats;
import ff8cardgame.Board;
import ff8cardgame.Board.CellPlayer;
import ff8cardgame.Card;
import ff8cardgame.Player;

public class SimulationController implements Observer {

	private SimulationView view;
	private Boolean computersTurn;
	private Board board;
	
	public SimulationController( SimulationView view, Boolean computersTurn, Board board ) {
		this.view = view;
		board.addObserver(this);
		this.board = board;
		
		view.getComputersCardsList().setDragEnabled(true);
		CalculateAIMove calculateAIMove = new CalculateAIMove( view.getMainWindow(), board, this );
		CardTransferHandler transferHandler = new CardTransferHandler(
				board,
				board.getComputer().getCards(),
				calculateAIMove
			);
		view.getComputersCardsList().setTransferHandler(transferHandler);
		
		JLabel[][] boardCells = view.getBoardCells();
		for( int row = 0; row < boardCells.length; row++ ) {
			for( int col = 0; col < boardCells[row].length; col++ ) {
				boardCells[row][col].setTransferHandler(transferHandler);
			}
		}
		
		updateBoardPanel(board,view);
		
		view.getSameRulesCheckbox().setEnabled(false);
		view.getSameRulesCheckbox().setSelected(board.isSameRule());
		view.getSameWallCheckbox().setEnabled(false);
		view.getSameWallCheckbox().setSelected(board.isSameWall());
		view.getPlusRuleCheckbox().setEnabled(false);
		view.getPlusRuleCheckbox().setSelected(board.isPlusRule());

		updateList( board.getComputer(), view.getComputersCards() );
		updateList( board.getHuman(), view.getYourCards() );
		
		if( !computersTurn ) {
			calculateAIMove.process();
		}
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if( observable == board ) {
			updateList( board.getComputer(), view.getComputersCards() );
			updateList( board.getHuman(), view.getYourCards() );
			updateBoardPanel( board, view );
			updateStatistics( board.getLastMoveStatistics() );
		}
	}
	
	private void updateList( Player player, DefaultListModel<String> cardListModel ) {
		cardListModel.removeAllElements();
		Card[] cards = player.getCards();
		for( int cardIndex = 0; cardIndex< cards.length; cardIndex++ ) {
			if( !player.isUsed( cardIndex ) ) {
				cardListModel.addElement( cards[ cardIndex ].getName() );
			}
		}
	}
	
	private void updateBoardPanel( Board board, SimulationView view ) {
		JLabel[][] boardCells = view.getBoardCells();
		for( int row = 0; row < boardCells.length; row++ ) {
			for( int col = 0; col < boardCells[row].length; col++ ) {
				String cellText =  "<html>&emsp;" + board.getElement(row, col).toString() +
						"<br></br>&emsp;" + SimulationView.DROP_TEXT + "&emsp;</html>";
				int cardIndex = board.getCardIndex(row, col);
				CellPlayer playerType = board.getPlayerAt( row, col );
				if( cardIndex != -1 ) {
					String cardString = "";
					if( playerType == CellPlayer.HUMAN ) {
						cardString = board.getHuman().getCard(cardIndex).toString();
					} else {
						cardString = board.getComputer().getCard(cardIndex).toString();
					}
					String boardElement = board.getElement(row, col).toString();
					cellText = "<html>Board Element(" + boardElement + ")<br></br>" +
						 cardString + "</html>";
				}
				view.setBoardCellText(row, col, cellText, playerType);
			}
		}
	}
	
	private void updateStatistics( AlphaBetaStats statistics ) {
		if( statistics != null ) {
			String outcome = "Draw";
			if( statistics.value > 0 ) {
				outcome = "Win";
			} else if( statistics.value < 0 ) {
				outcome = "Loss";
			}
			String statString = "<html>Expected Outcome: " + outcome + "(Value: " +
					statistics.value + ")<br>" +
					"Number of nodes searched: " + statistics.numberOfNodes + "<br></html>";
			this.view.getStatisticValues().setText(statString);
		}
	}
}
