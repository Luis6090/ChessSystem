package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private Integer moveCount;
	
	public ChessPiece(Board board,Color color) {
		super(board);
		this.color = color;
		this.moveCount = 0;
	}
	
	protected boolean isThereOpponentPiece (Position position) {
		ChessPiece piece = (ChessPiece)getBoard().piece(position);
		return piece != null && piece.getColor() != color;
	}

	protected void increaseMove(){ moveCount++;}

	protected void decreaseMove(){ moveCount--;}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	public Color getColor() {
		return color;
	}

}
