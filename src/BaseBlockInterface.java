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

	public ArrayList<Double> getOutput();

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	public void calculate();
}