package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0,0);

        //Above
        positionAux.setValue(position.getRow() - 1, position.getColumn());
        while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Bellow
        positionAux.setValue(position.getRow() + 1, position.getColumn());
        while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Left
        positionAux.setValue(position.getRow(), position.getColumn() - 1);
        while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() - 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Right
        positionAux.setValue(position.getRow(), position.getColumn() + 1);
        while(getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() + 1);
        }
        if(getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Northwest
        positionAux.setValue(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() - 1, positionAux.getColumn() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Northeast
        positionAux.setValue(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() - 1, positionAux.getColumn() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Southwest
        positionAux.setValue(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() + 1, positionAux.getColumn() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Southeast
        positionAux.setValue(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValue(positionAux.getRow() + 1, positionAux.getColumn() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            moves[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        return moves;
    }


}

