package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8,8);
	}
	
	public ChessPiece[][] getPiece(){
		ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColums()];
		
		for(int row = 0; row < board.getRows(); row++) {
			for(int column = 0; column < board.getColums(); column++) {
				pieces[row][column] = (ChessPiece)board.piece(row, column);
			}
		}
		return pieces;
	}
}
