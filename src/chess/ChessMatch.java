package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.*;

public class ChessMatch {

	private Board board;
	private int turn;
	private Color currentPlayer;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	private List<Piece> pieceOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;
		checkMate = false;
		initialSetup();
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

	public boolean getCheckMate() {return checkMate;}

	public ChessPiece getEnPassantVulnerable() { return enPassantVulnerable; }

	public ChessPiece getPromoted() { return promoted; }
	
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

		ChessPiece movedPiece = (ChessPiece)board.piece(target);

		//Special move: promotion
		promoted = null;
		if (movedPiece instanceof Pawn){
			if((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) || (movedPiece.getColor() == Color.BLACK && target.getRow() == 7)){
				promoted = movedPiece;
				promoted = replacePromotedPiece("Q");
			}
		}

		check = testCheck(opponent(currentPlayer));

		if(testCheckMate(opponent(currentPlayer))){
			checkMate = true;
		}
		else{
			nextTurn();
		}

		if(movedPiece instanceof Pawn && (target.getRow() == source.getRow() + 2 || target.getRow() == source.getRow() - 2)) {
			enPassantVulnerable = movedPiece;
		}
		else {
			enPassantVulnerable = null;
		}

		return (ChessPiece) capturedPiece;
	}

	public ChessPiece replacePromotedPiece(String type){
		if(promoted == null){
			throw new IllegalStateException("There is no piece to be promoted");
		}

		if(!type.equals("B") && !type.equals("R") && !type.equals("Q") && !type.equals("N")){
			throw new ChessException("Invalid type for promotion");
		}

		Position positionPawn = promoted.getChessPosition().toPosition();
		Piece pawn = board.removePiece(positionPawn);
		pieceOnTheBoard.remove(pawn);

		ChessPiece newPromovedPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPromovedPiece, positionPawn);
		pieceOnTheBoard.add(newPromovedPiece);

		check = testCheck(currentPlayer);
        checkMate = testCheckMate(currentPlayer);

		return newPromovedPiece;
	}

	private ChessPiece newPiece(String type, Color color){
		if(type.equals("B")) return new Bishop(board, color);
		if(type.equals("R")) return new Rook(board, color);
		if(type.equals("N")) return new Knight(board, color);
		return new Queen(board, color);
	}


	private void validateTargetPosition(Position source,Position target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The choice piece can't move to target.");
		}
	}
	
	private void underMove(Position source,Position target,Piece capturedPiece){
		ChessPiece piece = (ChessPiece) board.removePiece(target);
		piece.decreaseMove();
		board.placePiece(piece, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			this.capturedPieces.remove(capturedPiece);
			pieceOnTheBoard.add(capturedPiece);
		}

		//Casting move: king side rook
		if(piece instanceof King && target.getColumn() == source.getColumn() + 2){
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMove();
		}

		//Casting move: queen side rook
		if(piece instanceof King && target.getColumn() == source.getColumn() - 2){
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(targetRook);
			board.placePiece(rook, sourceRook);
			rook.decreaseMove();
		}

		//Special move: en passant
		if(piece instanceof Pawn){
			if(target.getColumn() != source.getColumn() && capturedPiece != null){
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				Position opponentPawnPosition;
				if(piece.getColor() == Color.WHITE){
					opponentPawnPosition = new Position(3, target.getColumn());
				}
				else{
					opponentPawnPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, opponentPawnPosition);
			}
		}
	}
	
	private Piece makeMove(Position source, Position target) {
		ChessPiece piece = (ChessPiece) board.removePiece(source);
		piece.increaseMove();
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(piece, target);
		
		if(capturedPiece != null) {
			pieceOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		//Casting move: king side rook
		if(piece instanceof King && target.getColumn() == source.getColumn() + 2){
			Position sourceRook = new Position(source.getRow(), source.getColumn() + 3);
			Position targetRook = new Position(source.getRow(), source.getColumn() + 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMove();
		}

		//Casting move: queen side rook
		if(piece instanceof King && target.getColumn() == source.getColumn() - 2){
			Position sourceRook = new Position(source.getRow(), source.getColumn() - 4);
			Position targetRook = new Position(source.getRow(), source.getColumn() - 1);
			ChessPiece rook = (ChessPiece) board.removePiece(sourceRook);
			board.placePiece(rook, targetRook);
			rook.increaseMove();
		}

		//Special move: en passant
		if(piece instanceof Pawn){
			if(target.getColumn() != source.getColumn() && capturedPiece == null){
				Position opponentPawnPosition;
				if(piece.getColor() == Color.WHITE){
					opponentPawnPosition = new Position(target.getRow() + 1, target.getColumn());
				}
				else{
					opponentPawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}

				capturedPiece = board.removePiece(opponentPawnPosition);
				capturedPieces.add(capturedPiece);
				pieceOnTheBoard.remove(capturedPiece);
			}
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

	private boolean testCheckMate(Color color){
		if(!testCheck(color)) {
			return false;
		}

		List<Piece> pieces = pieceOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece piece : pieces) {
			boolean[][] possibleMoves = piece.possibleMoves();
			for(int rows = 0; rows < board.getRows(); rows++) {
				for(int columns = 0; columns < board.getColumns(); columns++) {
					if(possibleMoves[rows][columns]) {
						Position source = ((ChessPiece)piece).getChessPosition().toPosition();
						Position target = new Position(rows, columns);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						underMove(source, target, capturedPiece);
						if(!testCheck) return false;
					}
				}
			}
		}
		return true;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column,row).toPosition());
		pieceOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c',1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f',1, new Bishop(board, Color.WHITE));
		placeNewPiece('g',1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b',8, new Knight(board, Color.BLACK));
		placeNewPiece('c',8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f',8, new Bishop(board, Color.BLACK));
		placeNewPiece('g',8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
}
