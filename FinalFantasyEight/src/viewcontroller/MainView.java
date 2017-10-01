package viewcontroller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView {

	private JFrame mainWindow;
	
	JPanel view;

	private JButton nextButton;
	private JButton previousButton;
	
	private JPanel previousNextPanel;

	public MainView( String title ) {
		mainWindow = new JFrame(title);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		previousNextPanel = new JPanel( new FlowLayout() );
		previousButton = new JButton( "Previous" );
		previousButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		previousNextPanel.add( previousButton );
		nextButton = new JButton( "Next" );
		nextButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		previousNextPanel.add( nextButton );
		mainWindow.getContentPane().add(previousNextPanel, BorderLayout.SOUTH ); 

		mainWindow.pack();
		mainWindow.setVisible(true);
	}
	
	public JFrame getMainWindow() {
		return mainWindow;
	}
	
	public void setView( JPanel view ) {
		previousNextPanel.setVisible(true);
		if( this.view != null ) {
			mainWindow.getContentPane().remove(this.view);
		}
		
		this.view = view;
		mainWindow.getContentPane().add( this.view, BorderLayout.CENTER);
		mainWindow.repaint();
		mainWindow.pack();
	}
	
	public void setFinalView( JPanel view ) {
		setView( view );
		previousNextPanel.setVisible(false);
		mainWindow.repaint();
		mainWindow.pack();
	}

	public JButton getNextButton() {
		return nextButton;
	}
	
	public JButton getPreviousButton() {
		return previousButton;
	}
}
