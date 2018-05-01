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
     * Get Port of selected index.
     * @param index Index in ArrayList of the port to be returned
     * @return Port object that can be used in propagation.
     */
    Port GetInput(int index);

    /**
     * Calculated output Port
     * @param index Index in ArrayList of the port to be returned
     * @return Calculated values in Port
     */
    Port GetOutput(int index);

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

    /**
     * Sets value of selected Point and selected Pin
     * @param PortIndex Port it's Pin is going to be changed
     * @param Pin Pin it's value is going to be changed
     * @param value Value for the pin.
     */
    void SetInputPortPin(int PortIndex, String Pin, double value);

    /**
     * Resets all ports to 0.0
     */
    void ResetPorts();
}