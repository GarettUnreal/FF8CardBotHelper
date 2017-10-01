package viewcontroller;

import java.util.Vector;

import ff8cardgame.Board;

public class WizardObservable {
	private Vector<WizardCompleteListener> listeners = new Vector<WizardCompleteListener>();
	
	private boolean isComplete = false;
	
	protected Board board;
	
	public WizardObservable( Board board ) {
		this.board = board;
	}
	
	public void addWizardCompleteListener( WizardCompleteListener listener ) {
		listeners.addElement( listener );
	}
	
	protected void notifyWizardComplete() {
		isComplete = true;
		for( WizardCompleteListener listener : listeners ) {
			listener.onWizardComplete();
		}
	}
	
	protected void notifyWizardIncomplete() {
		isComplete = false;
		for( WizardCompleteListener listener : listeners ) {
			listener.onWizardIncomplete();
		}
	}
	
	public boolean isComplete() {
		return isComplete;
	}
}
