package blockscheme;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.accessibility.Accessible;
import javafx.scene.control.Control;
import javax.swing.JComponent;
import javafx.scene.control.Label;

import blockscheme.*;
public class BlockComponent extends Label
{
	private BaseBlock block;
	public BlockComponent(BaseBlock block)
	{
		setText("Hello World");
	}

	// @Override
	// public void paint(Graphics g)
	// {
	// 	Dimension dm;
	// 	dm = getSize();
	// 	g.drawString(dm.toString(), 0, 0);
	// }
}