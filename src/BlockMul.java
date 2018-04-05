package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;

import java.util.ArrayList;
public class BlockMul extends BaseBlock
{
	public BlockMul()
	{
		this.createPorts();
	}

	public BlockMul(double val1, double val2)
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
		// this.outputs.get(0).hodnota = this.inputs.get(0).hodnota + this.inputs.get(1).hodnota;
		//System.out.format("%f", this.outputs.get(0).hodnota);
		this.outputs.get(0).set(this.inputs.get(0).get() * this.inputs.get(1).get());
	}
}