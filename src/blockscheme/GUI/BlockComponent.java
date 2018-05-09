package blockscheme.GUI;

import blockscheme.blocks.BaseBlock;
import blockscheme.blocks.ports.Port;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;


import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.control.ContextMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;

public class BlockComponent extends Label {
    private BlockComponent me;
    private BaseBlock block;
    private final double[] dragDelta = new double[2];
    private final ContextMenu contextMenuInputs = new ContextMenu();
    private final ContextMenu contextMenuOutputs = new ContextMenu();

    private ArrayList<DoubleProperty> InPinsPosX = new ArrayList<>();
    private ArrayList<DoubleProperty> InPinsPosY = new ArrayList<>();
    private ArrayList<DoubleProperty> OutPinsPosX = new ArrayList<>();
    private ArrayList<DoubleProperty> OutPinsPosY = new ArrayList<>();

    private static boolean connecting = false;

    public String name;
    private boolean valueReady = false;
    private boolean valueReadyTemp = false;

    private boolean active = false;

    private static BlockConnectionBuilder BCB;

    /**
     * Binds Component's tooltip with block info text for MouseOver tooltips.
     */
    private void SetTooltipText() {
        if (getTooltip() == null)
            setTooltip(new Tooltip());
        getTooltip().textProperty().bind(block.blockTextOutputProperty());
    }

    /**
     * Converts an MenuItem in format X - Name Into X
     * @param item MenuItem in specific format
     * @return X from the menu.
     */
    private int GetIndexFromMenuItem(Object item) {
        MenuItem i = ((MenuItem) item);
        return Integer.valueOf(i.getText().substring(0, i.getText().indexOf(' ')));
    }

    /**
     * Creates Port menus for right mouse click.
     */
    private void CreateGUIPorts() {
        for (int i = 0; i < block.GetInputNames().size(); i++) {
            MenuItem item = new MenuItem(String.format("%d - %s", i, block.GetInputNames().get(i)));
            InPinsPosX.add(new SimpleDoubleProperty());
            InPinsPosY.add(new SimpleDoubleProperty());
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    connecting = false; // This will cause problems later when user click an actual action, there is no skip.
                    int Index = GetIndexFromMenuItem(event.getSource());
                    if (BCB.setUiEnd(me, block.GetInput(Index), InPinsPosX.get(Index), InPinsPosY.get(Index))) {
                        BlockSchemeGui.AddBCB(BCB);
                        ((Pane) getParent()).getChildren().add(BCB);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Wrong connection!", "Connection Error", JOptionPane.ERROR_MESSAGE);
                    }
                    event.consume();
                }
            });
            contextMenuInputs.getItems().add(item);
        }
        for (int i = 0; i < block.GetOutputNames().size(); i++) {
            MenuItem item = new MenuItem(String.format("%d - %s", i, block.GetOutputNames().get(i)));
            OutPinsPosX.add(new SimpleDoubleProperty());
            OutPinsPosY.add(new SimpleDoubleProperty());
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    connecting = true;
                    BCB = new BlockConnectionBuilder();
                    int Index = GetIndexFromMenuItem(event.getSource());
                    BCB.setUiStart(me, block.GetOutput(Index), OutPinsPosX.get(Index), OutPinsPosY.get(Index));
                    event.consume();
                }
            });
            contextMenuOutputs.getItems().add(item);
        }

        // Remove Button for Blocks
        MenuItem item = new MenuItem("Remove");
        item.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BlockSchemeGui.RemoveBlock(me);
            }
        });

        contextMenuOutputs.getItems().add(item);
    }


    /**
     * Updataes pins positions according to Block Position
     */
    public void SetPinPositions()
    {
        double x = getLayoutX();
        double endX = x + getWidth();
        double y = getLayoutY();

        double ySpacingIn = getHeight() / (InPinsPosY.size() + 1);
        double ySpacingOut = getHeight() / (OutPinsPosY.size() + 1);

        for (DoubleProperty prop :
                InPinsPosX) {
            prop.set(x);
        }

        for (int i = 0; i < InPinsPosY.size(); i++) {
            InPinsPosY.get(i).set(y + ySpacingIn * (i + 1));
        }

        for (DoubleProperty prop :
                OutPinsPosX) {
            prop.set(endX);
        }

        for (int i = 0; i < OutPinsPosY.size(); i++) {
            OutPinsPosY.get(i).set(y + ySpacingOut * (i + 1));
        }
    }

    /**
     * Returns a Port by it's index in block
     * @param PinIndex Index of the port
     * @return Selected port
     */
    public Port GetPortByIndex(int PinIndex)
    {
        return block.GetInput(PinIndex);
    }

    /**
     * Gets all ports from block.
     * @return List of ports.
     */
    public ArrayList<String> GetPortNames()
    {
        return block.GetInputNames();
    }

    /**
     * Sets a pin with chosen value.
     * @param PortIndex Index of the Port that's pin will be changed
     * @param PortPin Name of the pin that will be assigned a value
     * @param value Value for the pin
     */
    public void SetPin(int PortIndex, String PortPin, double value)
    {
        block.SetInputPortPin(PortIndex, PortPin, value);
    }

    /**
     * Gets all Pins by Port index
     * @param PortIndex Index of the Port that's Pins will be returned
     * @return List of pins
     */
    public Set<String> GetPins(int PortIndex)
    {
        return block.GetPinInputNames(PortIndex);
    }

    /**
     * Make a step in calculation. Sets ready values.
     */
    public void Step()
    {
        this.valueReady = this.valueReadyTemp;
    }


    public BlockComponent(BaseBlock block) {
        me = this;
        this.block = block;
        BlockSchemeGui.AddBlock(this);
        CreateGUIPorts();
        SetTooltipText();

        setText(block.GetName());
        name = block.GetName();
        Active(false);
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY:
                        dragDelta[0] = me.getLayoutX() - event.getSceneX();
                        dragDelta[1] = me.getLayoutY() - event.getSceneY();
                        me.setCursor(Cursor.MOVE);
                        block.calculate();
                        break;
                    case SECONDARY: {
                        if (connecting)
                            contextMenuInputs.show(me, event.getScreenX(), event.getScreenY());
                        else
                            contextMenuOutputs.show(me, event.getScreenX(), event.getScreenY());

                    }
                    break;
                }
            }
        });

        // Moving with the component in scene.
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
                        SetPinPositions();
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
                        BlockSchemeGui.GetBlockInputConnectors(me);
                        BlockSchemeGui.GetBlockOutputConnectors(me);
                        break;
                }
            }
        });
    }

    /**
     * Calculates current block.
     */
    public void CalculateBlock() {
        block.calculate();
        valueReadyTemp = true;
    }

    /**
     * Resets current block calculation.
     */
    public void ResetBlock()
    {
        valueReady = false;
        valueReadyTemp = false;
        Active(false);
        block.ResetPorts();
    }

    /**
     * Checks if this block is ready / already calculated.
     * @return True if already did math.
     */
    public boolean IsReady()
    {
        return valueReady;
    }

    /**
     * Refreshes Tooltip for this block.
     */
    public void RefreshMe()
    {
        block.TextOutput();
    }

    /**
     * Removes this block from scene / scheme.
     */
    public void RemoveMe()
    {
        ((Pane)getParent()).getChildren().remove(me);
    }

    /**
     * Sets this block to active means coloring it with proper color.
     * @param active Index of active 0 - not active, bigger than 0 - active
     */
    public void Active(int active)
    {
        if (active == 1)
            setStyle("-fx-background-color: #b5bbFF; -fx-padding: 10 10 10 10");
        else if(active == 2)
            setStyle("-fx-background-color: #CCFFCA; -fx-padding: 10 10 10 10");
        else
            setStyle("-fx-background-color: #ffffff; -fx-padding: 10 10 10 10");

        this.active = (active != 0);


    }

    /**
     * Paints current block red.
     */
    public void ErrorStyle()
    {
        setStyle("-fx-background-color: #ffCCCA; -fx-padding: 10 10 10 10");
    }

    /**
     * Wrapper for Active with number
     * @param active True - Sets active / False - Sets not active
     */
    public void Active(boolean active)
    {
        Active(active ? 1 : 0);
    }
}