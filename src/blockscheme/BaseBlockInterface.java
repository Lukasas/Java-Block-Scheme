package blockscheme;
import blockscheme.ports.Port;

public interface BaseBlockInterface
{
    /**
     * Creates text output for each block.
     * @return Text output for calculation.
     */
    public String TextOutput();

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* Can throw exceptions according to blocks calculations.
	* @return Nothing
	*/
	public void calculate() throws Exception;
}