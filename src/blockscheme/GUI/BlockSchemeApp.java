/**
 * This package serves for everything connected to GUI.
 * It also connects blocks and ports together and presents them on the screen.
 */
package blockscheme.GUI;

import blockscheme.blocks.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.*;
import java.util.ArrayList;

public class BlockSchemeApp extends Application {
    private HBox blockControls;
    private VBox debugControls;
    private Pane canvas;

    private Button buttonStart, buttonStep, buttonReset, buttonSave, buttonLoad;

    /// Creating New block in few easy steps:
    /// 1. Create new file in blockscheme with block name like BlockMul
    /// 2. Copy an existing block like BlockAdd
    /// 3. Change Ports and Calculation
    /// 4. Add block name in ArrayList below.
    /// Ready2Go
    static ArrayList<Class<?>> AllBlocksType = new ArrayList<Class<?>>() {{
        add(BlockAdd.class);
        add(BlockYYMakeAB.class);
        add(BlockSub.class);
        add(BlockMul.class);
        add(BlockDiv.class);
        add(BlockABMinusABIsY.class);
        add(BlockAnd.class);
        add(BlockRGBTOYAB.class);
    }};


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates HBox that is filled with buttons for creating block. Also links these blocks with buttons components.
     */
    private void CreateBlockSection() {
        blockControls = new HBox();
        blockControls.setPadding(new Insets(15, 12, 15, 12));
        blockControls.setSpacing(10);
        blockControls.setStyle("-fx-background-color: #336699;");

        try {
            for (Class<?> cName :
                    AllBlocksType) {

                Class<?> clazz = Class.forName(cName.getName());
                Constructor<?> ctor = clazz.getConstructor();
                Button buttonMaker = new Button(cName.getSimpleName());
//                buttonMaker.setPrefSize(100, 20);
                buttonMaker.setPadding(new Insets(5, 12, 5, 12));
                buttonMaker.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            Object obj = ctor.newInstance(new Object[]{});
                            BaseBlock bb = (BaseBlock) obj;
                            BlockComponent buttonBlock = new BlockComponent(bb);
                            canvas.getChildren().add(buttonBlock);
                            buttonStep.setDisable(true);

                        } catch (Exception ex) {
                            System.out.printf("I broke it... " + ex.getMessage());
                            throw new RuntimeException("Exception when creating block!!");
                        }


                    }
                });
                blockControls.getChildren().add(buttonMaker);
            }
        } catch (Exception ex) {
            System.out.printf("I broke it... " + ex.getMessage());
            throw new RuntimeException("Exception when creating button!!");
        }

    }

    /**
     * Creates Debug menu with Start, Step and Reset
     */
    private void CreateBlockDebug() {
        debugControls = new VBox();
        debugControls.setPadding(new Insets(15, 12, 15, 12));
        debugControls.setSpacing(10);
        debugControls.setStyle("-fx-background-color: #336699;");
        buttonStart = new Button("Start");
        buttonStart.setPrefSize(100, 20);
        buttonStart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!BlockSchemeGui.CheckForCycles()) {
                    BlockSchemeGui.ResetCalculation();
                    BlockSchemeGui.FeedPins();
                    buttonStep.setDisable(false);
                }
                else {
                    BlockSchemeGui.ShowAlertDialog("Scheme contains cycles!", "Connection Error", Alert.AlertType.ERROR);
                }
            }
        });

        buttonStep = new Button("Step");
        BlockSchemeGui.stepButton = buttonStep;
        buttonStep.setPrefSize(100, 20);
        buttonStep.setDisable(true);
        buttonStep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockSchemeGui.MakeCalculationPath();
            }
        });

        buttonReset = new Button("Reset");
        buttonReset.setPrefSize(100, 20);
        buttonReset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BlockSchemeGui.ResetCalculation();
            }
        });

        buttonSave = new Button("Save");
        buttonSave.setPrefSize(100, 20);
        buttonSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FManager.Save();
            }
        });

        buttonLoad = new Button("Load");
        buttonLoad.setPrefSize(100, 20);
        buttonLoad.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FManager.Load();
            }
        });

        debugControls.getChildren().addAll(buttonStart, buttonStep, buttonReset, buttonSave, buttonLoad);
    }

    /**
     * Creates whole blockscheme.GUI.
     * @param stage Stage from main
     */
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
    }
}