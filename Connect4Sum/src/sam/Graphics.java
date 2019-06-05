package sam;

import sam.Board;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Graphics {
	private static Board[][] board = new Board[7][7];
	private static NewButton[][] slots = new NewButton[7][7];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		public start(Stage stage) throws Exception{
			
			
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
				            	System.out.println(((NewButton)event.getSource()).getRow());
				            	int col = ((NewButton)event.getSource()).getCol();
				            	int row = board.fill(O, col);
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

}
