package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;
import boardgame.Position;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        //Above-right
        positionAux.setValue(position.getRow() - 2, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Above-left
        positionAux.setValue(position.getRow() - 2, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Bellow-right
        positionAux.setValue(position.getRow() + 2, position.getColumn() + 1);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Bellow-left
        positionAux.setValue(position.getRow() + 2, position.getColumn() - 1);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Right at top
        positionAux.setValue(position.getRow() - 1, position.getColumn() + 2);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Right at bottom
        positionAux.setValue(position.getRow() + 1, position.getColumn() + 2);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Left at top
        positionAux.setValue(position.getRow() - 1, position.getColumn() - 2);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Left at bottom
        positionAux.setValue(position.getRow() + 1, position.getColumn() - 2);
        if(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        return moves;
    }
}
