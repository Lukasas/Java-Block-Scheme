package GUI;

import javafx.event.EventHandler;
import javafx.scene.Cursor;


import blockscheme.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class BlockComponent extends Label
{
	private BaseBlock block;
	final double[] dragDelta = new double[2];
	private BlockComponent me;


	public BlockComponent()
	{
	    setText("Hey");
		me = this;
//		this.block = block;
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			    switch (event.getButton())
                {
                    case PRIMARY:
                        dragDelta[0] = me.getLayoutX() - event.getSceneX();
                        dragDelta[1] = me.getLayoutY() - event.getSceneY();
                        me.setCursor(Cursor.MOVE);
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
                        break;
                }
            }
        });



	}

}