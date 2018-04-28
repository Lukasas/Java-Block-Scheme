package blockscheme;
import blockscheme.ports.Port;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class BaseBlock implements BaseBlockInterface
{
	protected ArrayList<Port> input;
	protected ArrayList<Port> output;
	protected String name = "none";

	@Override
	public String TextOutput() {
		return null;
	}

    public void SetInput(int index, Port p) {
        if (index >= input.size())
            throw new RuntimeException("Setting input index out of range.");

        input.get(index).CopyData(p);
    }

    public void SetOutput(int index, Port p) {
        if (index >= output.size())
            throw new RuntimeException("Setting output index out of range.");

        output.get(index).CopyData(p);
    }

    @Override
    public Port GetOutput(String portName) {
        for (Port p :
                output) {
            if (p.IsCalled(portName))
                return p;
        }
        return null;
    }


    protected ArrayList<String> StringerizeArray(ArrayList<Port> in)
    {
        ArrayList<String> inputNames = new ArrayList<>();

        for (Port p :
                in) {
            inputNames.add(p.getName());
        }
        return inputNames;
    }

    @Override
    public ArrayList<String> GetInputNames() {
        return StringerizeArray(input);
    }

    @Override
    public ArrayList<String> GetOutputNames() {
        return StringerizeArray(output);
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