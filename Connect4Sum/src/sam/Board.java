package sam;

import java.util.Random;

public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;

	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(CellState.EMPTY); // no color
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean isValid(int x, int y) {
		return (x >= 0 && x < rows) && (y >= 0 && y < cols);
	}

	public void display() {
		System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}

	/*
	 * Checks the cell state of the board at (x,y)
	 */
	public CellState getState(int x, int y) {
		return board[x][y].getState();
	}

	/*
	 * Checks if column (y) is full
	 */
	public boolean isFull(int cols) {
		cols = cols - 1;
		if (board[0][cols].getState() != CellState.EMPTY) {
			return true;
		}
		return false;
	}

	/*
	 * Checks for a vertical win Horizontal win 
	 * occurs when 1 player has 4 consecutive tiles
	 */
	public boolean vWin() {
		int hold = 0;
		for (int i = 0; i < cols-1; i++) {
			for (int j = 0; j < rows -1; j++) {
				if (board[i][j].getState() == CellState.P1) {
					hold = hold + 1;
				} else {
					hold = 0;
				}
			}
		}
		if (hold >= 4) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Checks for a horizontal win Horizontal win : 
	 * occurs when 1 player has 4 consecutive tiles
	 */
	public boolean hWin() {
		int hold = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j].getState() == CellState.P1) {
					hold = hold + 1;
				} else {
					hold = 0;
				}
			}
		}
		if (hold >= 4) {
			return true;
		} else {
			return false;
		}
	}

	public void fill(CellState player, int col) {
		// TODO Auto-generated method stub
		col = col - 1;
		for (int i = getRows() -1; i > -1; i--) {
			if (board[i][col].getState() == CellState.EMPTY) {
				board[i][col].setState(player);
				return;
			}
		}
	}
}
