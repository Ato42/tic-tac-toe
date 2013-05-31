package ro.opa.tictactoe;

public class Board {
	
	private Integer[][] matrix;
	
	public static final int EMPTY = 0;
	
	public static final int PLAYER_X = 1;
	
	public static final int PLAYER_O = 2;
	
	public Board(Integer size) {
		matrix = new Integer[size][size];
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				matrix[i][j] = Board.EMPTY;
			}
		}
	}

	public Integer[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Integer[][] matrix) {
		this.matrix = matrix;
	}

}
