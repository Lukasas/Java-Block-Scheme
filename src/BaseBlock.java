package blockscheme;
import blockscheme.Port;
import blockscheme.BaseBlockInterface;

public class BaseBlock implements BaseBlockInterface
{
	protected Port[] inputs;
	protected Port[] outputs;
	protected double result;
	/**
	* This method sets all inputs for specific block.
	* @return Nothing.
	*/
	@Override
	public void setInputs(Port[] inputs)
	{
		this.inputs = inputs;
	}


	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	@Override
	public void calculate()
	{

	}
}