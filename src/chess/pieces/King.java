package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece instanceof Rook && getMoveCount() == 0 && piece.getColor() == getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position positionAux = new Position(0,0);
		
		//above
		positionAux.setValue(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		//bellow
		positionAux.setValue(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//left
		positionAux.setValue(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//right
		positionAux.setValue(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//nw
		positionAux.setValue(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//ne
		positionAux.setValue(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//sw
		positionAux.setValue(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//se
		positionAux.setValue(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			moves[positionAux.getRow()][positionAux.getColumn()] = true;
		}

		//Castling Move
		if(getMoveCount() == 0 && !chessMatch.getCheck()){
			//King side rook
			Position positionRook1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(positionRook1)) {
				Position position1 = new Position(position.getRow(), position.getColumn() + 1);
				Position position2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(position1) == null && getBoard().piece(position2) == null){
					moves[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			//Queen side rook
			Position positionRook2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(positionRook2)) {
				Position position1 = new Position(position.getRow(), position.getColumn() - 1);
				Position position2 = new Position(position.getRow(), position.getColumn() - 2);
				Position position3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(position1) == null && getBoard().piece(position2) == null && getBoard().piece(position3) == null){
					moves[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}
		
		return moves;
	}

}
