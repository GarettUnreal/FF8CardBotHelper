package viewcontroller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ff8cardgame.AlphaBetaStats;
import ff8cardgame.Board;
import ff8cardgame.Move;

public class CalculateAIMove {

	private JFrame mainWindow;
	private Board board;
	private SimulationController simulationController;
	JPanel messagePanel;
	
	public CalculateAIMove( JFrame mainWindow, Board board, SimulationController simulationController ) {
		this.simulationController = simulationController;
		this.mainWindow = mainWindow;
		this.board = board;
		messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
		JLabel message = new JLabel(
				"<html><br><br>Calculating your move.<br><br>.....Please wait...</html>" );
		message.setFont(message.getFont().deriveFont(Font.BOLD, 30F));
		message.setAlignmentX(Component.CENTER_ALIGNMENT);	
		messagePanel.add(message);
		messagePanel.setVisible(false);
		mainWindow.setGlassPane(messagePanel);
		mainWindow.pack();
		mainWindow.repaint();
	}
	
	public void process() {
		showCalculationInProgress();
		Move humanMove = board.useAlphaBetaWithStats(true);
		board.makeMove(humanMove, true);
		board.print();
		hideCalculationInProgress();
	}
	
	private void showCalculationInProgress() {	
		messagePanel.setVisible(true);
		messagePanel.paintImmediately(messagePanel.getVisibleRect());
	}
	
	private void hideCalculationInProgress() {
		messagePanel.setVisible(false);
		messagePanel.paintImmediately(messagePanel.getVisibleRect());
	}
	
}
