package boardgame;

public class Board {

	private int rows;
	private int colums;
	private Piece pieces[][];
	
	public Board(int rows, int colums) {
		this.rows = rows;
		this.colums = colums;
		pieces = new Piece[rows][colums];
	}

	//Getters and Setters
	public int getRows() {
		return rows;
	}

	public int getColums() {
		return colums;
	}

	//Methods
	
	public Piece piece(int row,int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	
	
}
