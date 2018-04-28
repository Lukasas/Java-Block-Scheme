package GUI;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sun.security.ssl.Debug;

public class BlockConnectionBuilder extends Line {
    private BlockComponent uiStart; // Output
    private BlockComponent uiEnd; // Input

    private double value;

    public double getValue() {
        return value;
    }

    public BlockConnectionBuilder() {
        super();
        setStrokeWidth(2.0f);
        setStroke(Color.RED);
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setStart(double x, double y) {
        setStartX(x);
        setStartY(y);
    }

    public void setEnd(double x, double y) {
        setEndX(x);
        setEndY(y);
    }

    public void setUiStart(BlockComponent component) {
        uiStart = component;
        startXProperty().bind(component.layoutXProperty());
        startYProperty().bind(component.layoutYProperty());

    }

    public void setUiEnd(BlockComponent component) {
        uiEnd = component;
        endXProperty().bind(component.layoutXProperty());
        endYProperty().bind(component.layoutYProperty());
    }

    public BlockComponent getUiStart() {
        return uiStart;
    }

    public BlockComponent getUiEnd() {
        return uiEnd;
    }
}