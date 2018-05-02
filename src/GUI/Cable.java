package GUI;

import blockscheme.ports.Port;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cable {


    private Port pStart; // This is output that will be copied into end
    private Port pEnd; // This is input of another block that will get data from start.


    public Cable() {
        pStart = null; // This might get useful for determining start point.
        pEnd = null; // This might get useful for determining end point.
    }


    protected StringProperty CableTextOutput = new SimpleStringProperty();

    public StringProperty cableTextOutputProperty() {
        return CableTextOutput;
    }

    private void RefreshText() {
        String temp = pStart.PortText() + "------\n";
        if (pEnd != null)
            temp += pEnd.PortText();
        CableTextOutput.setValue(temp);
    }

    /**
     * This method serves for propagating data from start into end like pipeline.
     */
    public void Propagate() {
        pEnd.CopyData(pStart);
        RefreshText();
    }

    /**
     * This method serves for reversing the flow of data.
     * According to implementation of connecting two blocks, this should never be used.
     */
    public void Reverse() {
        Port temp = pStart;
        pStart = pEnd;
        pEnd = temp;
        RefreshText();
    }

    public void setStart(Port start) {
        pStart = start;
        RefreshText();
    }

    public void setEnd(Port end)
    {
        pEnd = end;
        RefreshText();
    }

    public boolean IsStart(Port port)
    {
        return pStart.equals(port);
    }

    public boolean IsEnd(Port port)
    {
        return pEnd.equals(port);
    }

    public boolean CanEnd(Port port)
    {
        return pStart.getName() == port.getName();
    }
}
