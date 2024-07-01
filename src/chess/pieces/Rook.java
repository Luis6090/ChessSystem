package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position positionAux = new Position(0,0);
		
		//above
		positionAux.setValue(position.getRow() - 1, position.getColumn());
		while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
			positionAux.setRow(positionAux.getRow() - 1);
		}
		if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//bellow
		positionAux.setValue(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
			positionAux.setRow(positionAux.getRow() + 1);
		}
		if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//left
		positionAux.setValue(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
			positionAux.setColumn(positionAux.getColumn() - 1);
		}
		if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		
		//right
		positionAux.setValue(position.getRow(), position.getColumn() + 1);
		while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
			positionAux.setColumn(positionAux.getColumn() + 1);
		}
		if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
			mat[positionAux.getRow()][positionAux.getColumn()] = true;
		}
		return mat;
	}


}
