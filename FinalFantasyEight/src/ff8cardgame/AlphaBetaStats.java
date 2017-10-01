package ff8cardgame;

public class AlphaBetaStats {
	
	public int value;
	public int numberOfNodes;
	public Move expectedMove;
	
	public AlphaBetaStats( int value, int numberOfNodes, Move expectedMove ) {
		this.value = value;
		this.numberOfNodes = numberOfNodes;
		this.expectedMove = expectedMove;
	}
	
}
