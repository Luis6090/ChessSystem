package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(Position position) {
		ChessPiece piece = (ChessPiece) getBoard().piece(position);
		return piece != null && piece.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position positionAux = new Position(0,0);
		
		//above
		positionAux.setValue(position.getRow() - 1, position.getColumn());
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		//bellow
		positionAux.setValue(position.getRow() + 1, position.getColumn());
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//left
		positionAux.setValue(position.getRow(), position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//right
		positionAux.setValue(position.getRow(), position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//nw
		positionAux.setValue(position.getRow() - 1, position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//ne
		positionAux.setValue(position.getRow() - 1, position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//sw
		positionAux.setValue(position.getRow() + 1, position.getColumn() - 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//se
		positionAux.setValue(position.getRow() + 1, position.getColumn() + 1);
		if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		return mat;
	}

}
