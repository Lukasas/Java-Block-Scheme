package blockscheme;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
// import javafx.scene.control.Pos;
import javax.swing.JPanel;
import blockscheme.*;
import javafx.scene.Node;
import javafx.scene.control.Label;


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
		BlockAdd ba = new BlockAdd();

		BlockComponent bc = new BlockComponent();
		GridPane grid = new GridPane();
		grid.add(bc, 0, 0);
		grid.setHgap(0);
		grid.setVgap(0);

		Scene scene = new Scene(grid, 1280, 720);
		stage.setScene(scene);
		stage.show();
	}
}