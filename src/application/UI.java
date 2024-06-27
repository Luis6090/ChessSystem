package application;

import chess.ChessPiece;
import chess.Color;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
		}else if(chessPiece.getColor() == Color.WHITE){
			System.out.print(ANSI_WHITE + chessPiece + ANSI_RESET);
		}
		else {
			System.out.print(ANSI_YELLOW + chessPiece + ANSI_RESET);
		}
		System.out.print(" ");
	}
}
