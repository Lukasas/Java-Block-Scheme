
public interface BaseBlockInterface
{
	/**
	* This method sets all inputs for specific block.
	* @return Nothing.
	*/
	void setInputs(/* Some collection of inputs. */);

	/**
	* This method calculates output value accordingly to the inputs.
	* It contains the calculation formula.
	* @return double Returns output value for this block.
	*/
	double operate();
}