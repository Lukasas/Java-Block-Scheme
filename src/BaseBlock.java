package blockscheme;
import blockscheme.Port;
import blockscheme.BaseBlockInterface;

import java.util.ArrayList;

public class BaseBlock implements BaseBlockInterface
{
	protected Port input = new Port();
	protected Port output = new Port();

	@Override
	public void setInput(Port port)
	{
		this.input = port;
	}

	@Override
	public Port getOutput()
	{
		return this.output;
	}

	@Override
	public int outputSize()
	{
		return this.output.PortSize();
	}

	@Override
	public int inputSize()
	{
		return this.input.PortSize();
	}

	@Override
	public void createPorts()
	{	
		this.input.add("A");
		this.input.add("B");
		this.output.add("Y");
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