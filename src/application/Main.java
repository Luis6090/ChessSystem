package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			UI.printBoard(chessMatch.getPiece());
			System.out.println();
			System.out.println("Source: ");
			ChessPosition source = UI.readPiecePosition(sc);
			
			System.out.println();
			System.out.println("Target: ");
			ChessPosition target = UI.readPiecePosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		
	}

}
