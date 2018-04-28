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

    public static void ListAll()
    {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            System.out.println(bcb.getUiStart().name + " " + bcb.getUiEnd().name);
        }
    }


    public static void AddBCB(BlockConnectionBuilder BCB)
    {
        BCBList.add(BCB);
    }

}
