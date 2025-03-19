package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position position = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            position.setValue(this.position.getRow() - 1, this.position.getColumn());
            if(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            position.setValue(this.position.getRow() - 2 , this.position.getColumn());
            Position position2 = new Position(this.position.getRow() - 1, this.position.getColumn());
            if(getBoard().positionExists(position) && !getBoard().thereIsAPiece(position) && getBoard().positionExists(position2) && !getBoard().thereIsAPiece(position2) && getMoveCount() == 0) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            position.setValue(this.position.getRow() - 1, this.position.getColumn() + 1);
            if(getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            position.setValue(this.position.getRow() - 1, this.position.getColumn() - 1);
            if(getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            //Special move: en passant white
            if(this.position.getRow() == 3){
                //NorthWest
                Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() - 1][left.getColumn()] = true;
                }
                //NorthEast
                Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }
        else {
            position.setValue(this.position.getRow() + 1,this.position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }
            position.setValue(this.position.getRow() + 2, this.position.getColumn());
            Position position2 = new Position(this.position.getRow() + 1, this.position.getColumn());
            if (getBoard().positionExists(position) && !getBoard().thereIsAPiece(position) && getBoard().positionExists(position2) && !getBoard().thereIsAPiece(position2) && getMoveCount() == 0) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            position.setValue(this.position.getRow() + 1, this.position.getColumn() + 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            position.setValue(this.position.getRow() + 1, this.position.getColumn() - 1);
            if (getBoard().positionExists(position) && isThereOpponentPiece(position)) {
                moves[position.getRow()][position.getColumn()] = true;
            }

            //Special move: en passant black
            if(this.position.getRow() == 4){
                //NorthWest
                Position left = new Position(this.position.getRow(), this.position.getColumn() - 1);
                if(getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() + 1][left.getColumn()] = true;
                }
                //NorthEast
                Position right = new Position(this.position.getRow(), this.position.getColumn() + 1);
                if(getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return "P";
    }
}
