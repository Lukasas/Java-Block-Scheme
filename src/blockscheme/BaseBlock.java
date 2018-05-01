package blockscheme;
import blockscheme.ports.Port;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.Set;

public class BaseBlock implements BaseBlockInterface {
    protected ArrayList<Port> input = new ArrayList<>();
    protected ArrayList<Port> output = new ArrayList<>();
    String name = "none";

    protected StringProperty BlockTextOutput = new SimpleStringProperty();

    public StringProperty blockTextOutputProperty() {
        TextOutput();
        return BlockTextOutput;
    }


    @Override
    public String TextOutput() {
        return null;
    }

    @Override
    public void SetInput(int index, Port p) {
        if (index >= input.size())
            throw new RuntimeException("Setting input index out of range.");

        input.get(index).CopyData(p);
        TextOutput();
    }

    @Override
    public void SetOutput(int index, Port p) {
        if (index >= output.size())
            throw new RuntimeException("Setting output index out of range.");

        output.get(index).CopyData(p);
        TextOutput();
    }

    public void SetInputPortPin(int PortIndex, String Pin, double value) {
        input.get(PortIndex).set(Pin, value);
        TextOutput();
    }

    @Override
    public Port GetInput(int index) {
        return input.get(index);
    }

    @Override
    public Port GetOutput(int index) {
        return output.get(index);
    }


    protected ArrayList<String> StringerizeArray(ArrayList<Port> in) {
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

    public Set<String> GetPinInputNames(int PortIndex) {
        return input.get(PortIndex).getKeys();
    }

    /**
     * This method calculates outputs value accordingly to the inputs.
     * It contains the calculation formula.
     * In every child class the super.calculate() must be called at the very end of the calculate function.
     * It's important for values propagation into output.
     *
     * @return Nothing
     */
    @Override
    public void calculate() {
        TextOutput();
    }

    @Override
    public String GetName() {
        return name;
    }

    /**
     * Resets all input and output ports to 0.0
     */
    @Override
    public void ResetPorts()
    {
        for (int i = 0; i < input.size(); i++) {
            input.get(i).Reset();
        }
        for (int i = 0; i < output.size(); i++) {
            output.get(i).Reset();
        }

        TextOutput();
    }
}