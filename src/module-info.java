module SudokuSolverGUI {
	requires javafx.controls;
	
	opens sudoku to javafx.graphics, javafx.fxml;
}
