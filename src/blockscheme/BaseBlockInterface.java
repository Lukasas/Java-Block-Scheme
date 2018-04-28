package blockscheme;
import blockscheme.ports.Port;

import java.util.ArrayList;

public interface BaseBlockInterface
{
    /**
     * Method that serves for determining input Ports
     * @return Name of input ports;
     */
    ArrayList<String> GetInputNames();

    /**
     * Method that serves for determining output Ports
     * @return Name of output ports;
     */
    ArrayList<String> GetOutputNames();

    /**
     * Copies data into input port
     * @param index Index of input port
     * @param p Port with data
     */
    void SetInput(int index, Port p);

    /**
     * Copies data into output port ... Should never be used.
     * @param index Index of output port
     * @param p Port with data
     */
    void SetOutput(int index, Port p);

    /**
     * Calculated output Port
     * @param portName Name of the port to be returned
     * @return Calculated values in Port
     */
    Port GetOutput(String portName);

    /**
     * Creates text output for each block.
     * @return Text output for calculation.
     */
    String TextOutput();

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* Can throw exceptions according to blocks calculations.
	* @return Nothing
	*/
	void calculate();

    /**
     * Method for recieving block name.
     * @return Block name.
     */
	String GetName();

}