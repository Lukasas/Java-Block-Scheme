package blockscheme;
import blockscheme.ports.Port;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class BaseBlock implements BaseBlockInterface
{
	protected ArrayList<Port> input = new ArrayList<>();
	protected ArrayList<Port> output = new ArrayList<>();
	protected String name = "none";

	protected StringProperty BlockTextOutput = new SimpleStringProperty();
    public StringProperty blockTextOutputProperty()
    {
        TextOutput();
        return BlockTextOutput;
    }



	@Override
	public String TextOutput() {
	    return null;
	}

    public void SetInput(int index, Port p) {
        if (index >= input.size())
            throw new RuntimeException("Setting input index out of range.");

        input.get(index).CopyData(p);
        TextOutput();
    }

    public void SetOutput(int index, Port p) {
        if (index >= output.size())
            throw new RuntimeException("Setting output index out of range.");

        output.get(index).CopyData(p);
        TextOutput();
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
    * In every child class the super.calculate() must be called at the very end of the calculate function.
    * It's important for values propagation into output.
	* @return Nothing
	*/
	@Override
	public void calculate()
	{TextOutput();}

    @Override
    public String GetName() {
        return name;
    }
}