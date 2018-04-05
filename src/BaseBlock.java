package blockscheme;
import blockscheme.Port;
import blockscheme.BaseBlockInterface;

import java.util.ArrayList;

public class BaseBlock implements BaseBlockInterface
{
	protected ArrayList<Port> inputs = new ArrayList<Port>();
	protected ArrayList<Port> outputs = new ArrayList<Port>();
	protected double result;
	protected int iPortCount = 0;
	protected int oPortCount = 0;

	/**
	* This method creates input and output ports.
	* @return Nothing.
	*/
	@Override
	public void createPorts()
	{
		this.iPortCount = 2;
		this.inputs.add(new Port("First Value", 0.0));
		this.inputs.add(new Port("Second Value", 0.0));
		this.oPortCount = 1;
		this.outputs.add(new Port("Output Value", 0.0));
	}


	/**
	* This method sets all inputs for specific block.
	* @return Nothing.
	*/
	@Override
	public void setInputs(ArrayList<Double> inputs)
	{}

	@Override
	public ArrayList<Double> getOutput()
	{
		ArrayList<Double> outs = new ArrayList<Double>();
		for (int i = 0; i < this.oPortCount; i++)
			outs.add(this.outputs.get(i).get());
		return outs;
	}

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	@Override
	public void calculate() throws Exception
	{}
}