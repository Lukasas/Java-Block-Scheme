package GUI;


import blockscheme.ports.Port;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sun.security.ssl.Debug;

public class BlockConnectionBuilder extends Line {
    private BlockComponent uiStart; // Output
    private BlockComponent uiEnd; // Input
    private Cable connection = new Cable();


    public BlockConnectionBuilder() {
        super();
        setStrokeWidth(2.0f);
        setStroke(Color.RED);
    }

    public void setUiStart(BlockComponent component, Port output) {
        uiStart = component;
        connection.start = output;
        startXProperty().bind(component.layoutXProperty());
        startYProperty().bind(component.layoutYProperty());

    }

    public void setUiEnd(BlockComponent component, Port input) {
        uiEnd = component;
        connection.end = input;
        endXProperty().bind(component.layoutXProperty());
        endYProperty().bind(component.layoutYProperty());
    }

    public BlockComponent getUiStart() {
        return uiStart;
    }

    public BlockComponent getUiEnd() {
        return uiEnd;
    }

    public void Propagate()
    {
        connection.Propagate();
        uiStart.RefreshMe();
        uiEnd.RefreshMe();
    }

}