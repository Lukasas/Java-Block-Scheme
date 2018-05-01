package GUI;


import blockscheme.ports.Port;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import sun.security.ssl.Debug;

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

    public void RemoveMe() {
        ((Pane) getParent()).getChildren().remove(this);
    }

    public void setUiStart(BlockComponent component, Port output) {
        uiStart = component;
        connection.setStart(output);
        startXProperty().bind(component.layoutXProperty());
        startYProperty().bind(component.layoutYProperty());

    }

    public void setUiEnd(BlockComponent component, Port input) {
        uiEnd = component;
        connection.setEnd(input);
        endXProperty().bind(component.layoutXProperty());
        endYProperty().bind(component.layoutYProperty());
    }

    public BlockComponent getUiStart() {
        return uiStart;
    }

    public BlockComponent getUiEnd() {
        return uiEnd;
    }

    public void Propagate() {
        connection.Propagate();
        uiStart.RefreshMe();
        uiEnd.RefreshMe();
    }
}