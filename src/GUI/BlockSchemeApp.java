package GUI;

import blockscheme.BlockAdd;
import blockscheme.BlockYYMakeAB;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.paint.Color;

public class BlockSchemeApp extends Application {
    private HBox blockControls;
    private VBox debugControls;
    private Pane canvas;


    public static void main(String[] args) {
        launch(args);
    }

    private void CreateBlockSection() {
        blockControls = new HBox();
        blockControls.setPadding(new Insets(15, 12, 15, 12));
        blockControls.setSpacing(10);
        blockControls.setStyle("-fx-background-color: #336699;");
        Button buttonBlockAdd = new Button("BlockAdd");
        buttonBlockAdd.setPrefSize(100, 20);
        buttonBlockAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockComponent buttonBlockAddw = new BlockComponent(new BlockAdd());
                canvas.getChildren().add(buttonBlockAddw);
            }
        });

        Button buttonBlockYYAB = new Button("BlockYYMakeAB");
        buttonBlockYYAB.setPrefSize(100, 20);
        buttonBlockYYAB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockComponent buttonBlockYYAB = new BlockComponent(new BlockYYMakeAB());
                canvas.getChildren().add(buttonBlockYYAB);
            }
        });
        blockControls.getChildren().addAll(buttonBlockAdd, buttonBlockYYAB);
    }

    private void CreateBlockDebug() {
        debugControls = new VBox();
        debugControls.setPadding(new Insets(15, 12, 15, 12));
        debugControls.setSpacing(10);
        debugControls.setStyle("-fx-background-color: #336699;");
        Button buttonRun = new Button("Run");
        buttonRun.setPrefSize(100, 20);
        buttonRun.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockSchemeGui.ListAll();
            }
        });

        Button buttonStep = new Button("Step");
        buttonStep.setPrefSize(100, 20);

        Button buttonReset = new Button("Reset");
        buttonReset.setPrefSize(100, 20);
        debugControls.getChildren().addAll(buttonRun, buttonStep, buttonReset);
    }

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        canvas = new Pane();
        canvas.setStyle("-fx-background-color: #BCCCFF;");
        CreateBlockSection();
        CreateBlockDebug();
        stage.setTitle("Block Scheme");
        stage.setWidth(800);
        stage.setHeight(600);

        BorderPane border = new BorderPane();
        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.widthProperty());

        border.setStyle("-fx-background-color: #FFCCCB;");
        border.setLeft(debugControls);
        border.setTop(blockControls);
        border.setCenter(canvas);

        root.getChildren().add(border);

        stage.setScene(scene);
        stage.show();
//        Line redLine = LineBuilder.create()
//                .startX(296)
//                .startY(128)
//                .endX(401)
//                .endY(233)
//                .fill(Color.RED)
//                .stroke(Color.RED)
//                .strokeWidth(10.0f)
//                .build();
//
//        canvas.getChildren().add(redLine);
    }
}