package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;

import java.util.ArrayList;
public class BlockDiv extends BaseBlock
{
	public BlockDiv()
	{
		super.createPorts();
	}

	public BlockDiv(double val1, double val2)
	{
		super.createPorts();
		this.input.set("A", val1);
		this.input.set("B", val2);
	}

	@Override
	public void calculate() throws Exception
	{		
		if (this.input.get("B") == 0.0)
			throw new Exception("Dividing by zero!!");	

		this.output.set("Y", this.input.get("A") / this.input.get("B"));
	}
}
	