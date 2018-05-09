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
    private int StartPinIndex;
    private int EndPinIndex;

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
     * @param Index Index of Port
     */
    public void setUiStart(BlockComponent component, int Index) {
        uiStart = component;
        connection.setStart(component.GetOutputPortByIndex(Index));
        /*startXProperty().bind(component.layoutXProperty());
        startYProperty().bind(component.layoutYProperty());*/
        startXProperty().bind(component.GetStartPinPositionX(Index));
        startYProperty().bind(component.GetStartPinPositionY(Index));
        StartPinIndex = Index;

    }

    /**
     * Sets end of this connection and bind it's start to a block.
     * @param component Block that will be defined as end
     * @param Index Index of port
     * @return True if connection is possible, false otherwise.
     */
    public boolean setUiEnd(BlockComponent component, int Index) {
        if (!connection.CanEnd(component.GetInputPortByIndex(Index)))
            return false;
        uiEnd = component;
        connection.setEnd(component.GetInputPortByIndex(Index));
        endXProperty().bind(component.GetEndPinPositionX(Index));
        endYProperty().bind(component.GetEndPinPositionY(Index));
        BlockSchemeGui.stepButton.setDisable(true);
        EndPinIndex = Index;
        return true;
    }

    /**
     * Gives index of start pin.
     * @return Index of start pin.
     */
    public int GetStartPinIndex()
    {
        return StartPinIndex;
    }

    /**
     * Gives index of end pin.
     * @return Index of end pin.
     */
    public int GetEndPinIndex()
    {
        return EndPinIndex;
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