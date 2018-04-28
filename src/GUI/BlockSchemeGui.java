package GUI;

import java.util.ArrayList;

public class BlockSchemeGui {
    ArrayList<Cable> scheme = new ArrayList<Cable>();
    Cable current;
    static ArrayList<BlockConnectionBuilder> BCBList = new ArrayList<>();


    public BlockSchemeGui() {

    }

    public void createCable() {
        current = new Cable();
    }

    public static void ListAll() {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            System.out.println(bcb.getUiStart().name + " " + bcb.getUiEnd().name);
        }
    }


    public static void AddBCB(BlockConnectionBuilder BCB) {
        BCBList.add(BCB);
    }

    /**
     * Find Start and End point
     */
    public static void FindEnds() {

    }

    public static void Propagate()
    {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            bcb.Propagate();
        }
    }
}
