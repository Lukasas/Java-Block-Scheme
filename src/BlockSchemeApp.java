package blockscheme;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
// import javafx.scene.control.Pos;
import javafx.stage.Stage;

public class BlockSchemeApp extends Application
{
	public static void main(String[] args)
	{

		System.out.println("Hello World.");

		launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		stage.setTitle("Block Scheme Application");

		GridPane grid = new GridPane();
		// grid.setAligment(Pos.CENTER);
		grid.setHgap(0);
		grid.setVgap(0);

		Scene scene = new Scene(grid, 300, 275);
		stage.setScene(scene);
		stage.show();
	}
}