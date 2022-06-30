package sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {
	private Set<Integer> checkBoard;
	
	// constructor
	// initialize the set to add numbers 1-9
	// checks to see if each row, column, and square are valid.
	public SudokuSolver() {
		checkBoard = new HashSet<>();
		checkBoard.add(1);
		checkBoard.add(2);
		checkBoard.add(3);
		checkBoard.add(4);
		checkBoard.add(5);
		checkBoard.add(6);
		checkBoard.add(7);
		checkBoard.add(8);
		checkBoard.add(9);
	}
	
	// helper method used in areAllRowsValid
	// checks if each row is unique and doesn't contain repeating numbers
	// uses a hash set to store numbers and if a repeat occurs, does not validate board
	public boolean isRowValid(SudokuBoard sb, int row) {
		Set<Integer> set = new HashSet<>(checkBoard);
		for(int col = 0; col < 9; col++) {
			Integer number = sb.getSudokuBoard(row, col);
			if(number != null) {
				if(!set.contains(number)){
					return false;
				}
				else {
					set.remove(number);
				}
			}
		}
		return true;
	}
	
	
	// helper function used in areAllColsValid
	// checks if each column is unique and doesn't contain repeating numbers
	// uses a hash set to store numbers and if a repeat occurs, does not validate board
	public boolean isColValid(SudokuBoard sb, int col) {
		Set<Integer> set = new HashSet<>(checkBoard);
		for(int row = 0; row < 9; row++) {
			Integer number = sb.getSudokuBoard(row, col);
			if(number != null) {
				if(!set.contains(number)){
					return false;
				}
				else {
					set.remove(number);
				}
			}
		}
		return true;
	}
	
	
	// helper method used in areAllCubesValid
	// checks if each 3x3 square is unique and doesn't contain repeating numbers
	// uses a hash set to store numbers and if a repeat occurs, does not validate board
	public boolean isCubeValid(SudokuBoard sb, int r, int c) {
		Set<Integer> set = new HashSet<>(checkBoard);
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				Integer number = sb.getSudokuBoard(r + row, c + col);
				if(number != null) {
					if(!set.contains(number)){
						return false;
					}
					else {
						set.remove(number);
					}
				}
			}
		}
		return true;
	}
	
	
	// uses isRowValid() method
	// checks all rows to see if numbers are unique in each row
	// if any row is not unique, returns false
	public boolean areAllRowsValid(SudokuBoard sb) {
		for(int row = 0; row < 9; row++) {
			if(!isRowValid(sb, row)) {
				return false;
			}
		}
		return true;
	}
	
	// uses isColValid() method
	// checks all columns to see if numbers are unique in each column
	// if any column is not unique, returns false
	public boolean areAllColsValid(SudokuBoard sb) {
		for(int col = 0; col < 9; col++) {
			if(!isColValid(sb, col)) {
				return false;
			}
		}
		return true;
	}
	
	
	// uses isCubeValid() method
	// checks all cubes to see if numbers are unique in each cube
	// if any cube is not unique, returns false
	// this method advances each row and col by 3 to avoid any repeat checks since it is a cube
	public boolean areAllCubesValid(SudokuBoard sb) {
		for(int row = 0; row < 9; row = row + 3) {
			for(int col = 0; col < 9; col = col + 3) {
				if(!isCubeValid(sb, row, col)) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	// checks if the grid is valid
	public boolean validGrid(SudokuBoard sb) {
		return areAllRowsValid(sb) && areAllColsValid(sb) && areAllCubesValid(sb);
	}
	
	
	// checks if text fields are not null
	public boolean isFull(SudokuBoard sb) {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(sb.getSudokuBoard(row, col) == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	// return true if the board is valid and complete
	public boolean finishedSudokuGrid(SudokuBoard sb) {
		return validGrid(sb) && isFull(sb);
	}
	
	
	// uses backtracking by going through each field and testing a number to solve the board
	// if number works keep advancing
	// if at any point a number doesn't work it goes back and then repeats the test with a different number
	// test keeps repeating until it finds a solution 
	// if no solution is present it returns null
	public SudokuBoard solver(SudokuBoard originalBoard) {
		
		if(!validGrid(originalBoard)) {
			return null;
		}
		
		if(finishedSudokuGrid(originalBoard)) {
			return originalBoard;
		}
		
		SudokuBoard solvedBoard = new SudokuBoard(originalBoard);
		
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				Integer gridNum = solvedBoard.getSudokuBoard(row, col);
				if(gridNum == null) {
					Set<Integer> set = new HashSet<>(checkBoard);
					for(Integer number: set) {
						solvedBoard.setSudokuBoard(row, col, number);
						SudokuBoard solution = solver(solvedBoard);
						if(solution != null) {
							return solution;
						}
					}
					return null;
				}
			}
		}
		return null;
	}
}