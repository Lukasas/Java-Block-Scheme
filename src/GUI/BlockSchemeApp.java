package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BlockSchemeApp extends Application {
    HBox blockControls;
    Pane canvas;


    public static void main(String[] args)
    {
        launch(args);
    }

    private void CreateBlockSection()
    {
        blockControls = new HBox();
        blockControls.setPadding(new Insets(15, 12, 15, 12));
        blockControls.setSpacing(10);
        blockControls.setStyle("-fx-background-color: #336699;");
        Button buttonBlockAdd = new Button("BlockAdd");
        buttonBlockAdd.setPrefSize(100, 20);
        buttonBlockAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockComponent buttonBlockAddw = new BlockComponent();
                buttonBlockAdd.setPrefSize(100, 20);
                canvas.getChildren().add(buttonBlockAddw);
            }
        });

        Button buttonProjected = new Button("BlockSub");
        buttonProjected.setPrefSize(100, 20);
        blockControls.getChildren().addAll(buttonBlockAdd, buttonProjected);
    }

    private void ListAllControls()
    {

    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: #BCCCFF;");
        CreateBlockSection();

        stage.setTitle("Block Scheme");
        stage.setWidth(800);
        stage.setHeight(600);

        BorderPane border = new BorderPane();
        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.widthProperty());

        border.setStyle("-fx-background-color: #FFCCCB;");
        border.setTop(blockControls);
        border.setCenter(canvas);

        root.getChildren().add(border);

        stage.setScene(scene);
        stage.show();
    }
}