package sam.rd;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Connect 4 game 
 * @author Benton Kennedy and Sam Richardson-Duffy
 *ICS4U Culminating
 */
public class Driver extends Application {
	// create the board
	private static final int ROWS = 7;
	private static final int COLS = 7;
	private static final int MOVES = ROWS * COLS;
	private static Board board = new Board(ROWS, COLS);
	private static Button[][] slots = new Button[ROWS][COLS];

	public static void main(String[] args) {
		launch(args);//launch the graphics
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// setup size of buttons
		final int BUTTON_WIDTH = 100;
		final int BUTTON_HEIGHT = 100;
		// setup stage dimensions
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		primaryStage.setTitle("Connect 4");
		//red gets the first turn
		ColorState player = ColorState.RED;
		//yellow button code
		String style = "-fx-base: #ffff00;";
		//make the seven yellow buttons at the top
		Button btn0 = new Button();
		btn0.setStyle(style);
		btn0.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn1 = new Button();
		btn1.setStyle(style);
		btn1.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn2 = new Button();
		btn2.setStyle(style);
		btn2.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn3 = new Button();
		btn3.setStyle(style);
		btn3.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn4 = new Button();
		btn4.setStyle(style);
		btn4.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn5 = new Button();
		btn5.setStyle(style);
		btn5.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		Button btn6 = new Button();
		btn6.setStyle(style);
		btn6.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		//add all seven buttons to a grid pane
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.add(btn0, 0, 0);
		gridPane.add(btn1, 1, 0);
		gridPane.add(btn2, 2, 0);
		gridPane.add(btn3, 3, 0);
		gridPane.add(btn4, 4, 0);
		gridPane.add(btn5, 5, 0);
		gridPane.add(btn6, 6, 0);

		// setup slots (where the pieces will be displayed)
		//(they are all white in the beginning)
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				slots[i][j] = new Button();
				slots[i][j].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				slots[i][j].setStyle("-fx-base: #000000;");
			}
		}
		// add slots to the gridPane
		//(so that they can be shown)
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				gridPane.add(slots[i][j], j, i + 1);
			}
		}
		
		//add a mouse clicked event handler for each button
		btn0.setOnAction(e -> { //<--- lambda expression to lessen lines of code (introduced in java 8)
			board.place(0);//place the piece in row 0
			updateBoard(board, slots); //show the piece graphically
			checkWin(board, primaryStage); //check if the player won
		});

		btn1.setOnAction(e -> {
			board.place(1);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		btn2.setOnAction(e -> {
			board.place(2);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		btn3.setOnAction(e -> {
			board.place(3);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		btn4.setOnAction(e -> {
			board.place(4);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		btn5.setOnAction(e -> {
			board.place(5);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		btn6.setOnAction(e -> {
			board.place(6);
			updateBoard(board, slots);
			checkWin(board, primaryStage);
		});

		// draw initial board
		updateBoard(board, slots);
		//add the board to the scene and show it
		Scene myScene = new Scene(gridPane);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}

	/**
	 * fill all empty slots with white and colour in the empty slots where pieces have been placed
	 * @param board
	 * @param slots
	 */
	private void updateBoard(Board board, Button[][] slots) {
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getCols(); j++) {
				switch (board.getColor(i, j)) {
				case RED:
					slots[i][j].setStyle("-fx-base: #ff0000;");
					break;
				case BLUE:
					slots[i][j].setStyle("-fx-base: #0000ff;");
					break;
				case WHITE:
					slots[i][j].setStyle("-fx-base: #ffffff;");
					break;
				}
			}
		}

	}

	/**
	 * checks if a player has won and displays a win screen if they have
	 * @param board
	 * @param primaryStage
	 */
	private void checkWin(Board board, Stage primaryStage) {
		ColorState player = ColorState.RED;
		// display whos turn it is
		if (board.getTurn() % 2 == 0) {
			player = ColorState.RED;
		} else {
			player = ColorState.BLUE;
		}
		//at the beginning of the game no one has won
		boolean win = false;
		//check if a player has won any of the three ways
		if (board.checkHor(player) == true) {
			System.out.println(player.toString() + " wins horizontally");
			win = true;
		}

		if (board.checkDiag(player) == true) {
			System.out.println(player.toString() + " wins diagonally");
			win = true;
		}

		if (board.checkVert(player) == true) {
			System.out.println(player.toString() + " wins vertically");
			win = true;
		}
		//if someone wins then display a win screen and close the old one
		if (win) {
			primaryStage.close();//close the old scene
			Stage stage = new Stage();//make a new stage
			//set the dimensions of the new stage (same as old one)
			stage.setMinHeight(800);
			stage.setMinWidth(800);
			//add a label to display the text on
			Label label = new Label();
			label.setText(player.toString() + " wins!");
			//add that label to a gridpane so that it can be shown on the stage
			GridPane g = new GridPane();
			g.add(label, 0, 0);
			g.setAlignment(Pos.CENTER);
			Scene scene = new Scene(g);
			//show the new scene
			stage.setScene(scene);
			stage.show();

		}

	}

}



