package blockscheme;
import blockscheme.Port;
import blockscheme.BaseBlockInterface;

import java.util.ArrayList;

public class BaseBlock implements BaseBlockInterface
{
	protected ArrayList<Port> inputs = new ArrayList<Port>();
	protected ArrayList<Port> outputs = new ArrayList<Port>();
	protected double result;
	protected int portCount = 0;
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
		return new ArrayList<Double>();		
	}

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	@Override
	public void calculate()
	{}
}