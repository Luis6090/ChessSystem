package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        //Northwest
        positionAux.setValue(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() - 1, positionAux.getColumn() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Northeast
        positionAux.setValue(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() - 1, positionAux.getColumn() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Southwest
        positionAux.setValue(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() + 1, positionAux.getColumn() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Southeast
        positionAux.setValue(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() + 1, positionAux.getColumn() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        return mat;
    }
}
