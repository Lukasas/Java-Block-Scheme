package GUI;

import blockscheme.ports.Port;

public class Cable {


    public Port start; // This is output that will be copied into end
    public Port end; // This is input of another block that will get data from start.


    public Cable()
    {
        start = null; // This might get useful for determining start point.
        end = null; // This might get useful for determining end point.
    }



    /**
     * This method serves for propagating data from start into end like pipeline.
     */
    public void Propagate()
    {
        end.CopyData(start);
    }

    /**
     * This method serves for reversing the flow of data.
     * According to implementation of connecting two blocks, this should never be used.
     */
    public void Reverse()
    {
        Port temp = start;
        start = end;
        end = temp;
    }
}
