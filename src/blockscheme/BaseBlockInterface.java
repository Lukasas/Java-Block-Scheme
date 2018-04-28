package blockscheme;
import blockscheme.ports.Port;

public interface BaseBlockInterface
{

    /**
     * Copies data into input port
     * @param index Index of input port
     * @param p Port with data
     */
    public void SetInput(int index, Port p);

    /**
     * Copies data into output port ... Should never be used.
     * @param index Index of output port
     * @param p Port with data
     */
    public void SetOutput(int index, Port p);

    /**
     * Calculated output Port
     * @param portName Name of the port to be returned
     * @return Calculated values in Port
     */
    public Port GetOutput(String portName);

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