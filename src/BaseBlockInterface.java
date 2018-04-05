package blockscheme;
import blockscheme.Port;
import java.util.ArrayList;
public interface BaseBlockInterface
{
	/**
	* This method sets all inputs for specific block.
	* @return Nothing.
	*/
	public void setInputs(ArrayList<Double> inputs);

	/**
	* This method creates ports for inputs and outputs.
	* @return Nothing.
	*/
	public void createPorts();

	/**
	* This method returns an ArrayList of doubles as outputs.
	* @return ArrayList<Double>
	*/
	public ArrayList<Double> getOutput();

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* Can throw exceptions according to blocks calculations.
	* @return Nothing
	*/
	public void calculate() throws Exception;
}