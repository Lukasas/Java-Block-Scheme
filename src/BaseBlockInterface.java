package blockscheme;
import blockscheme.Port;

public interface BaseBlockInterface
{
	/**
	* This method sets all inputs for specific block.
	* @return Nothing.
	*/
	public void setInputs(Port[] inputs);


	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	public void calculate();
}