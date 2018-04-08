package blockscheme;
import blockscheme.Port;
import java.util.ArrayList;
public interface BaseBlockInterface
{
	/**
	* This method sets input port.
	* @return Nothing.
	*/
	public void setInput(Port port);

	/**
	* This method creates input and output port.
	* @return Nothing.
	*/
	public void createPorts();

	/**
	* This method returns an ArrayList of doubles as outputs.
	* @return Port
	*/
	public Port getOutput();

	/**
	* This method returns size of output. (Type)
	* @return Int size of output
	*/
	public int outputSize();

	/**
	* This method returns size of input. (Type)
	* @return Int size of input
	*/
	public int inputSize();

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* Can throw exceptions according to blocks calculations.
	* @return Nothing
	*/
	public void calculate() throws Exception;
}