package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;

import java.util.ArrayList;
public class BlockAdd extends BaseBlock
{
	public BlockAdd()
	{
		this.name = "BlockAdd";
		this.createPorts();
	}

	public BlockAdd(double val1, double val2)
	{
		this.createPorts();
		this.input.set("A", val1);
		this.input.set("B", val2);
	}

	@Override
	public void calculate() throws Exception
	{		
		this.output.set("Y", this.input.get("A") + this.input.get("B"));
	}
}