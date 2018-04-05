package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;

import java.util.ArrayList;
public class BlockDiv extends BaseBlock
{
	public BlockDiv()
	{
		this.createPorts();
	}

	public BlockDiv(double val1, double val2)
	{
		this.createPorts();
		ArrayList<Double> vals = new ArrayList<Double>();
		vals.add(val1);
		vals.add(val2);
		setInputs(vals);
	}

	@Override
	public void setInputs(ArrayList<Double> inputs)
	{
		for (int i = 0; i < this.iPortCount; i++)
		{
			this.inputs.get(i).set(inputs.get(i));
		}
	}

	@Override
	public void calculate() throws Exception
	{		
		if (this.inputs.get(1).get() == 0.0)
			throw new Exception("Dividing by zero!!");			

		this.outputs.get(0).set(this.inputs.get(0).get() / this.inputs.get(1).get());
	}
}