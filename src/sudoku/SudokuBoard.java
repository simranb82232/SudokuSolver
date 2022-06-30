package sudoku;

public class SudokuBoard {
	
	private Integer[][] sudokuBoard = new Integer[9][9];

	public SudokuBoard() {
	}
	
	// constructor for original board that is inputted by user
	public SudokuBoard(SudokuBoard originalBoard) {
		// TODO Auto-generated constructor stub
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				sudokuBoard[row][col] = originalBoard.getSudokuBoard(row, col);
			}
		}
	}
	
	// getter and setter for instance variable created above
	public Integer getSudokuBoard(int row, int col) {
		return sudokuBoard[row][col];
	}

	public void setSudokuBoard(int row, int col, Integer board) {
		sudokuBoard[row][col] = board;
	}

	
	
}