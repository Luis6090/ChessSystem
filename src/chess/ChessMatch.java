package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	
	private List<Piece> pieceOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPiece = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initailSetup();
	}
	
	public ChessPiece[][] getPiece(){
		ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
		
		for(int row = 0; row < board.getRows(); row++) {
			for(int column = 0; column < board.getColumns(); column++) {
				pieces[row][column] = (ChessPiece)board.piece(row, column);
			}
		}
		return pieces;
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean getCheck() {return check;}
	
	public boolean[][] possibleMoves(ChessPosition chessPosition){
		Position position = chessPosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source,target);

		if(testCheck(currentPlayer)){
			underMove(source,target,capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}

		check = testCheck(opponent(currentPlayer));
		nextTurn();
		return (ChessPiece) capturedPiece;
	}
	
	private void validateTargetPosition(Position source,Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The choice piece can't move to target.");
		}
	}
	
	private void underMove(Position source,Position target,Piece capturedPiece){
		Piece piece = board.removePiece(target);
		board.placePiece(piece, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			this.capturedPiece.remove(capturedPiece);
			pieceOnTheBoard.add(capturedPiece);
		}
	}
	
	private Piece makeMove(Position source, Position target) {
		Piece move = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(move, target);
		
		if(capturedPiece != null) {
			pieceOnTheBoard.remove(capturedPiece);
			this.capturedPiece.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position.");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The choice piece is not your.");
		}
		if(!board.piece(position).isTherePossibleMove()) {
			throw new ChessException("There is no possible move for the choice piece.");
		}
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> listPiece = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece piece : listPiece) {
			if(piece instanceof King) {
				return (ChessPiece)piece;
			}
		}
		throw new IllegalStateException("There is no" + color + "king in the board");
	}

	private boolean testCheck(Color color){
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPiece = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());

		for(Piece piece : opponentPiece) {
			boolean[][] possibleMovesOpponentPiece = piece.possibleMoves();
			if(possibleMovesOpponentPiece[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column,row).toPosition());
		pieceOnTheBoard.add(piece);
	}
	
	private void initailSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
}
