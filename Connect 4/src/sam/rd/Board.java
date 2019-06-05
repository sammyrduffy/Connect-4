package sam.rd;

import java.util.Random;
public class Board {
	private Cell[][] board; //the board consists of a 2d array of cell objects
	//set up row and column attributes
	private int rows;
	private int cols;
	//set up variables to track the turns of players
	private int turn;
	private static int nextTurn = 1;
	private boolean win;

	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		turn = nextTurn++;
		win = false;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(defaultColor()); // default color
			}
		}
	}
	
	/**
	 * Keeps track of who's turn it is
	 * 
	 * @return turn number
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * gets the number of rows in the board
	 * 
	 * @return number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * get the number of columns in the board
	 * 
	 * @return number of columns
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * places a piece depending on who's turn it is in the given column
	 * 
	 * @param col
	 */
	public void place(int col) {
		//boolean to determine when a turn is complete
		boolean endTurn = false;
		// player 1s turn
		if (turn % 2 != 0) {
			//initiate counter variable to keep track of number of pieces in a row
			int counter = 0;
			// loops until it finds an empty space to drop the piece
			for (int i = rows - 1; i >= 0; i--) {
				// if the bottom row is empty, drop the piece and end turn
				if (board[i][col].getColor().equals(ColorState.WHITE)) {
					board[i][col].setColor(ColorState.RED);
					endTurn = true;
				
					break;
				}
				// else loop until an empty spot is found
				else if (board[i][col].getColor().equals(ColorState.WHITE) == false) {
					counter++;
					if (board[i][col].getColor().equals(ColorState.WHITE)) {
						board[i][col].setColor(ColorState.RED);
						endTurn = true;
						System.out.println("b");
						break;
					}
				}
				// if the column is full display a message to the user
				if (counter >= rows) {
					System.out.println("That column is full please choose another");
					break;
				}

			}
		} else {// player 2s turn
			int counter = 0;
			// loops until it finds an empty space to drop the piece
			for (int i = rows - 1; i >= 0; i--) {
				// if the bottom row is empty, drop the piece and end turn
				if (board[i][col].getColor().equals(ColorState.WHITE)) {
					board[i][col].setColor(ColorState.BLUE);
					endTurn = true;
					break;
				}
				// else loop until an empty spot is found
				else if (board[i][col].getColor().equals(ColorState.WHITE) == false) {
					counter++;
					if (board[i][col].getColor().equals(ColorState.WHITE)) {
						board[i][col].setColor(ColorState.BLUE);
						endTurn = true;
						break;
					}
					// if the column is full display a message to the user}
					if (counter >= rows) {
						System.out.println("That column is full please choose another");
						break;
					}

				}
			}

		}
		//go to the next player's turn
		if (endTurn) {
			turn += 1;
		}
	}
	
	/**
	 * checks for a horizontal win
	 * 
	 * @param p
	 * @return true if there is a win otherwise return false
	 */
	public boolean checkHor(ColorState p) {
		int counter = 0;
		// check horizontally
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j].getColor().equals(p)) {
					counter++;
					if (counter >= 4) {//if counter = 4 then player has won
						return true;
					}
				} else {
					counter = 0; //reset counter if not 4 in a row
				}
			}
		}
		return false;
	}

	/**
	 * checks for a vertical win
	 * 
	 * @param p
	 * @return true if there is a win otherwise return false
	 */
	public boolean checkVert(ColorState p) {
		int counter = 0;
		// check vertically
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[j][i].getColor().equals(p)) {
					counter++;
					if (counter > 3) {
						return true;
					}
				} else {
					counter = 0;
				}
			}

		}
		return false;
	}

	/**
	 * checks for a diagonal win both ways
	 * 
	 * @param p
	 * @return true if there is a win otherwise return false
	 */
	
	public boolean checkDiag(ColorState p) {
		//make a counter variable to track how many pieces are in a row
		int counter = 0;
		//negative slope
		for (int i = 0; i < rows - 3; i++) {
			for (int j = 0; j < cols - 3; j++) {
				for (int o = 0; o < 4; o++) {
					if (board[j + o][i + o].getColor().equals(p)) {
						counter++;
						if (counter > 3) {
							return true;
						}
					} else {
						counter = 0;
					}

				}
			}
		}
		//reset counter
		//positive sloped diagonal
		counter = 0;
		for (int i = 0; i < rows - 3; i++) {
			for (int j = cols - 1; j > 3; j--) {
				for (int o = 0; o < 4; o++) {
					if (board[i + o][j - o - 1].getColor().equals(p)) {
						counter++;
						if (counter > 3) {
							return true;
						}
					} else {
						counter = 0;
					}
				}
			}
		}

		return false;
	}
	
	
	/**
	 * invokes the getColor() method from the cell class and returns a ColorState enum
	 * @param x location of the piece
	 * @param y location of the piece
	 * @return a ColorState enum
	 */
	public ColorState getColor(int x, int y) {
		return board[x][y].getColor();
	}
	
	/**
	 * default color of cells
	 * @return white color
	 */
	private static ColorState defaultColor() {
		return ColorState.WHITE;
	}
	
	/**
	 * debug method
	 */
	public void display() {
		System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}
}

