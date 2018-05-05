package blockscheme.GUI;


import blockscheme.blocks.ports.Port;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class BlockConnectionBuilder extends Line {
    private BlockComponent uiStart; // Output
    private BlockComponent uiEnd; // Input
    private Cable connection = new Cable();
    private BlockConnectionBuilder me;

    public BlockConnectionBuilder() {
        super();
        me = this;
        setStrokeWidth(2.0f);
        setStroke(Color.RED);

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY:
                        BlockSchemeGui.RemoveBCB(me);
                }
            }
        });
    }

    /**
     * Removes this connection from the scene.
     */
    public void RemoveMe() {
        ((Pane) getParent()).getChildren().remove(this);
    }

    /**
     * Sets start of this connection and bind it's start to a block.
     * @param component Block that will be defined as start
     * @param output Block's port that will be bind.
     * @param PinPosX Property that will the cable be bound to X
     * @param PinPosY Property that will the cable be bound to Y
     */
    public void setUiStart(BlockComponent component, Port output, DoubleProperty PinPosX, DoubleProperty PinPosY) {
        uiStart = component;
        connection.setStart(output);
        /*startXProperty().bind(component.layoutXProperty());
        startYProperty().bind(component.layoutYProperty());*/
        startXProperty().bind(PinPosX);
        startYProperty().bind(PinPosY);

    }

    /**
     * Sets end of this connection and bind it's start to a block.
     * @param component Block that will be defined as end
     * @param input Block's port that will be bind.
     * @param PinPosX Property that will the cable be bound to X
     * @param PinPosY Property that will the cable be bound to Y
     * @return True if connection is possible, false otherwise.
     */
    public boolean setUiEnd(BlockComponent component, Port input, DoubleProperty PinPosX, DoubleProperty PinPosY) {
        if (!connection.CanEnd(input))
            return false;
        uiEnd = component;
        connection.setEnd(input);
        endXProperty().bind(PinPosX);
        endYProperty().bind(PinPosY);
        BlockSchemeGui.stepButton.setDisable(true);
        return true;
    }

    /**
     * Returns start block from blockscheme.GUI
     * @return Start block
     */
    public BlockComponent getUiStart() {
        return uiStart;
    }

    /**
     * Returns end block from blockscheme.GUI
     * @return End block
     */
    public BlockComponent getUiEnd() {
        return uiEnd;
    }

    /**
     * Check if Port is connected in Start
     * @param port Port to be checked
     * @return True is port is connected. False otherwise
     */
    public boolean IsPinInStartConnected(Port port)
    {
        return connection.IsEnd(port);
    }

    /**
     * Check if Port is connected in End
     * @param port Port to be checked
     * @return True is port is connected. False otherwise
     */
    public boolean IsPinInEndConnected(Port port)
    {
        return connection.IsStart(port);
    }

    /**
     * Calculates value and propagate values from start to the end
     */
    public void Propagate() {
        connection.Propagate();
        uiStart.RefreshMe();
        uiEnd.RefreshMe();
    }
}