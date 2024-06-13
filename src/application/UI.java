package application;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] chessPiece) {
		for(int row = 0; row < chessPiece.length; row++) {
			System.out.print((8 - row) + "  ");
			for(int column = 0; column < chessPiece[0].length; column++) {
				printChessPiece(chessPiece[row][column]);
			}
			System.out.println();
		}
		System.out.println("   a b c d e f g h");
	}
	
	public static void printChessPiece(ChessPiece chessPiece) {
		if(chessPiece == null) {
			System.out.print("-");
		}else {
			System.out.print(chessPiece);
		}
		System.out.print(" ");
	}
}
