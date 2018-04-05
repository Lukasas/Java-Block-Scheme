package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;

import java.util.ArrayList;
public class BlockAdd extends BaseBlock
{
	public BlockAdd()
	{
		this.portCount = 2;
		this.inputs.add(new Port("First Value", 0.0));
		this.inputs.add(new Port("Second Value", 0.0));
		this.outputs.add(new Port("Output Value", 0.0));
	}

	public BlockAdd(double val1, double val2)
	{
		this.portCount = 2;
		this.inputs.add(new Port("First Value", val1));
		this.inputs.add(new Port("Second Value", val2));
		this.outputs.add(new Port("Output Value", 0.0));
	}

	@Override
	public ArrayList<Double> getOutput()
	{
		ArrayList<Double> outs = new ArrayList<Double>();
		outs.add(this.outputs.get(0).get());
		return outs;
	}

	@Override
	public void setInputs(ArrayList<Double> inputs)
	{
		if (inputs.size() != 2)
			new Exception("Add block needs two inputs to be set.");
		for (int i = 0; i < this.portCount; i++)
		{
			this.inputs.get(i).set(inputs.get(i));
		}
	}

	@Override
	public void calculate()
	{
		if (this.inputs.size() != 2)
			new Exception("Add block needs two inputs to work.");
		
		// this.outputs.get(0).hodnota = this.inputs.get(0).hodnota + this.inputs.get(1).hodnota;
		//System.out.format("%f", this.outputs.get(0).hodnota);
		this.outputs.get(0).set(this.inputs.get(0).get() + this.inputs.get(1).get());

	}
}