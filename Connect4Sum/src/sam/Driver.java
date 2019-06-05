package sam;

import sam.CellState;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sam.Cell;
import sam.Board;
import sam.NewButton;

public class Driver extends Application {
	final int ButtonHeight = 100;
	final int ButtonWidth = 200;
	private static NewButton[][] slots = new NewButton[7][7];
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		
		CellState CurrentPlayer = CellState.P1;
		GridPane gridPane = new GridPane();		
		Scene mySceneGraph = new Scene(gridPane,500,500);	
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		// setup slots
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
					slots[x][y] = new NewButton(x,y);
					slots[x][y].setPrefSize(ButtonHeight, ButtonWidth);
					slots[x][y].setStyle("-fx-base: #4f678c;");
					slots[x][y].setText("[" + String.valueOf(x)+","+String.valueOf(y)+"]");

					slots[x][y].setOnAction(new EventHandler<ActionEvent>() {
			            @Override
			            public void handle(ActionEvent event) {
			            	int col = ((NewButton)event.getSource()).getCol();
			            }
			        });
			}
		}
		
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				gridPane.add(slots[x][y], y, x + 1);
			}
		}
		stage.setScene(mySceneGraph);
		stage.show();
	}
}