package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	//Constructor
	public Piece(Board board) {
		this.board = board;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isTherePossibleMove() {
		boolean[][] mat = possibleMoves();
		
		for(int row = 0; row < mat.length; row++) {
			for(int column = 0; column < mat[0].length; column++) {
				if(mat[row][column]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
