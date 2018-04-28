package blockscheme;
import blockscheme.ports.Port;

import java.util.ArrayList;

public class BaseBlock implements BaseBlockInterface
{
	protected ArrayList<Port> input;
	protected ArrayList<Port> output;
	protected String name = "none";

	@Override
	public String TextOutput() {
		return null;
	}

	/**
	* This method calculates outputs value accordingly to the inputs.
	* It contains the calculation formula.
	* @return Nothing
	*/
	@Override
	public void calculate() throws Exception
	{}
}