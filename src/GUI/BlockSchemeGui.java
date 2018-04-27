package GUI;

import java.util.ArrayList;

public final class BlockSchemeGui {
    ArrayList<Cable> scheme = new ArrayList<Cable>();
    Cable current;
    public BlockSchemeGui(){

    }

    public void createCable()
    {
        current = new Cable();
    }
}
