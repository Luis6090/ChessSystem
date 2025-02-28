package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
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
        }

        return moves;
    }

    @Override
    public String toString() {
        return "P";
    }
}
