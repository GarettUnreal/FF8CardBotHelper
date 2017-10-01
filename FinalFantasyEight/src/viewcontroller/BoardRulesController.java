package viewcontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ff8cardgame.Board;
import ff8cardgame.Board.Elemental;

public class BoardRulesController extends WizardObservable
	implements ActionListener, ItemListener {
	
	private BoardRulesView view;
	private Boolean computersTurn;
	
	public BoardRulesController( BoardRulesView view, Board board, Boolean computersTurn ) {
		super( board );
		this.computersTurn = computersTurn;
		this.view = view;
		String[] elementStrings = {
				"None",
				"Fire",
				"Ice",
				"Water",
				"Holy",
				"Thunder",
				"Earth",
				"Poison",
				"Wind"
		};
		
		JComboBox[][] elements = view.getElements();
		for( int row = 0; row < elements.length; row++ ) {
			for( int col = 0; col < elements[row].length; col++ ) {
				for( String elementLabel : elementStrings ) {
					elements[row][col].addItem(elementLabel);
				}
				elements[row][col].setSelectedIndex(0);
				elements[row][col].addItemListener(this);
			}
		}
		
		view.getYouStartButton().setSelected(true);
		view.getYouStartButton().addActionListener(this);
		view.getComputersStartsButton().addActionListener(this);
		
		view.getSameRulesCheckbox().addActionListener(this);
		view.getSameWallCheckbox().addActionListener(this);
		view.getPlusRuleCheckbox().addActionListener(this);

		updateRules();
		updateBoardElements();
		this.notifyWizardComplete();
	}
	
	private void updateRules() {
		computersTurn = view.getComputersStartsButton().isSelected();
		board.setPlusRule(view.getPlusRuleCheckbox().isSelected());
		board.setSameRule(view.getSameRulesCheckbox().isSelected());
		board.setSameWall(view.getSameWallCheckbox().isSelected());
	}
	
	private void updateBoardElements() {
		JComboBox[][] elements = view.getElements();
		for( int row = 0; row < elements.length; row++ ) {
			for( int col = 0; col < elements[row].length; col++ ) {
				board.setElement( row, col,
						((String)elements[row][col].getSelectedItem()).toUpperCase() );
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if( event.getSource() == view.getYouStartButton() ) {
			if( !view.getYouStartButton().isSelected() ) {
				view.getYouStartButton().setSelected(true);
			}
			view.getComputersStartsButton().setSelected(false);
		} else if( event.getSource() == view.getComputersStartsButton() ) {
			if( !view.getComputersStartsButton().isSelected() ) {
				view.getComputersStartsButton().setSelected(true);
			}
			view.getYouStartButton().setSelected(false);
		}
		updateRules();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if( event.getSource() instanceof JComboBox ) {
			updateBoardElements();
		}
	}
}
