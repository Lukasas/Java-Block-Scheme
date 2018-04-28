package GUI;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;


import blockscheme.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javafx.scene.control.ContextMenu;

public class BlockComponent extends Label {
    private BaseBlock block;
    private final double[] dragDelta = new double[2];
    private BlockComponent me;
    private final ContextMenu contextMenu = new ContextMenu();
    private static int counter = 0;
    private static boolean connecting = false;

    public BlockComponent input;
    public BlockComponent output;


    public String name;

    private static BlockConnectionBuilder BCB;

    private void SetTooltipText() {
        getTooltip().textProperty().bind(block.blockTextOutputProperty());
    }

    private void CreatePins() {

    }


    public BlockComponent(BaseBlock block) {
        this.block = block;

        MenuItem item = new MenuItem("Input X");
        MenuItem item2 = new MenuItem("Output Y");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!connecting) {
                    if (BCB == null) {
                        BCB = new BlockConnectionBuilder();
                        ((Pane) getParent()).getChildren().add(BCB);

                    }
                    connecting = true;
                    BCB.setUiStart(me);
                }
            }
        });

        name = "Test " + String.valueOf(counter++);
        contextMenu.getItems().addAll(item, item2);
        setTooltip(new Tooltip("ToolTip what?"));

        SetTooltipText();

        setText("Hey");
        me = this;
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY:
                        dragDelta[0] = me.getLayoutX() - event.getSceneX();
                        dragDelta[1] = me.getLayoutY() - event.getSceneY();
                        me.setCursor(Cursor.MOVE);
                        break;
                    case SECONDARY: {
                        if (!connecting) {
                            contextMenu.show(me, event.getScreenX(), event.getScreenY());
                        }
                    }
                    break;
                }
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY: {

                        me.setLayoutX(event.getSceneX() + dragDelta[0]);
                        me.setLayoutY(event.getSceneY() + dragDelta[1]);
                        if (((Pane) me.getParent()).getWidth() < (me.getLayoutX() + me.getWidth())) {
                            me.setLayoutX(((Pane) me.getParent()).getWidth() - me.getWidth() - 1);
                        } else if (0 > me.getLayoutX()) {
                            me.setLayoutX(0);
                        }

                        if (((Pane) me.getParent()).getHeight() < (me.getLayoutY() + me.getHeight())) {
                            me.setLayoutY(((Pane) me.getParent()).getHeight() - me.getHeight() - 1);
                        } else if (0 > me.getLayoutY()) {
                            me.setLayoutY(0);
                        }
                    }
                    break;
                    case SECONDARY: {

                    }
                    break;
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY:
                        me.setCursor(Cursor.DEFAULT);
                        break;
                    case SECONDARY:
                        if (connecting) {
                            connecting = false;
                            BCB.setUiEnd(me);
                            BlockSchemeGui.AddBCB(BCB);
                            BCB = null;
                        }
                        break;
                }
            }
        });


    }
}