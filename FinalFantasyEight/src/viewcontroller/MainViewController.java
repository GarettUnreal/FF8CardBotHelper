package viewcontroller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;

import ff8cardgame.Board;

public class MainViewController implements WizardCompleteListener, ActionListener {

	private MainView view;
	private int currentWizardIndex = -1;
	private Vector<WizardObservable> wizardControllers;
	private Vector<JPanel> wizardViews;
	
	private SimulationView finalView;
	private SimulationController finalViewController;
	
	private Board board;
	private Boolean computersTurn;
	
	public MainViewController(
			MainView view,
			Vector<WizardObservable> wizardController,
			Vector<JPanel> wizardViews,
			Board board,
			Boolean computersTurn) {
		this.wizardControllers = wizardController;
		this.wizardViews = wizardViews;
		this.view = view;
		this.board = board;
		this.computersTurn = computersTurn;
		for( WizardObservable wizard : wizardControllers ) {
			wizard.addWizardCompleteListener(this);
		}
		
		if( !wizardControllers.isEmpty() ) {
			currentWizardIndex = 0;
			view.setView( wizardViews.elementAt(currentWizardIndex) );
		}
		
		view.getNextButton().addActionListener(this);
		view.getPreviousButton().addActionListener(this);
		
		updateButtons();
	}
	
	private void updateButtons() {
		if( currentWizardIndex != -1 ) {
			view.getNextButton().setEnabled(
					currentWizardIndex < wizardControllers.size() &&
					wizardControllers.elementAt(currentWizardIndex).isComplete() );
			if( currentWizardIndex == wizardControllers.size() - 1) {
				view.getNextButton().setText( "Finish" );
			} else {
				view.getNextButton().setText( "Next" );
			}
			
			view.getPreviousButton().setEnabled( currentWizardIndex > 0 );
		}
	}

	@Override
	public void onWizardComplete() {
		updateButtons();
	}

	@Override
	public void onWizardIncomplete() {
		updateButtons();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( view.getNextButton() == event.getSource() ) {
			if( currentWizardIndex != wizardControllers.size() - 1) {
				currentWizardIndex++;
				view.setView( wizardViews.elementAt(currentWizardIndex) );
			} else {
				finalView = new SimulationView(view.getMainWindow(),
						new Color( 255, 0, 0 ),
						new Color( 0, 0, 255 ));
				finalViewController = new SimulationController( finalView, computersTurn, board ); 
				view.setFinalView( finalView );
			}
		} else if( view.getPreviousButton() == event.getSource() ) {
			currentWizardIndex--;
			view.setView( wizardViews.elementAt(currentWizardIndex) );
		}

		updateButtons();
	}
}
