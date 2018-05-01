package GUI;

import java.util.ArrayList;

public class BlockSchemeGui {
    ArrayList<Cable> scheme = new ArrayList<Cable>();
    static ArrayList<BlockConnectionBuilder> BCBList = new ArrayList<>();
    static ArrayList<BlockComponent> AllBlocks = new ArrayList<>();

    public BlockSchemeGui()
    {

    }


    public static void ListAll() {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            System.out.println(bcb.getUiStart().name + " " + bcb.getUiEnd().name);
        }
    }

    public static void RemoveBCB(BlockComponent Block) {
        ArrayList<BlockConnectionBuilder> BCBListTemp = (ArrayList<BlockConnectionBuilder>)BCBList.clone();
        for (BlockConnectionBuilder bcb :
                BCBListTemp) {
            if (bcb.getUiStart().equals(Block) || bcb.getUiEnd().equals(Block))
            {
                RemoveBCB(bcb);
            }
        }
    }

    public static void RemoveBCB(BlockConnectionBuilder BCB){BCBList.remove(BCB); BCB.RemoveMe();}

    public static void AddBCB(BlockConnectionBuilder BCB) {
        BCBList.add(BCB);
    }

    public static void RemoveBlock(BlockComponent Block){
        RemoveBCB(Block);
        Block.RemoveMe();
        AllBlocks.remove(Block);
    }

    public static void AddBlock(BlockComponent Block)
    {
        AllBlocks.add(Block);
    }

    /**
     * Find Start and End point
     * Start blocks are blocks that have no connections on their inputs
     * End blocks are block that have no connections on their outputs
     */
    public static void FindEnds()
    {
        ArrayList<BlockComponent> AllBlocksStart = (ArrayList<BlockComponent>)AllBlocks.clone();
        ArrayList<BlockComponent> AllBlocksEnd = (ArrayList<BlockComponent>)AllBlocks.clone();
        for (BlockComponent block :
                AllBlocks) {
            if (HasConnectedInput(block))
                AllBlocksStart.remove(block);

            if (HasConnectedOutput(block))
                AllBlocksEnd.remove(block);
        }


        System.out.printf("Starts:\n");
        for (BlockComponent ba :
                AllBlocksStart) {
            System.out.printf(ba.name + "\n");
        }
        System.out.printf("Ends:\n");
        for (BlockComponent ba :
                AllBlocksEnd) {
            System.out.printf(ba.name + "\n");
        }
    }

    public static boolean HasConnectedInput(BlockComponent block)
    {
        for (BlockConnectionBuilder bcb:
             BCBList) {
            if (bcb.getUiEnd().equals(block))
                return true;
        }
        return false;
    }

    public static boolean HasConnectedOutput(BlockComponent block)
    {
        for (BlockConnectionBuilder bcb:
                BCBList) {
            if (bcb.getUiStart().equals(block))
                return true;
        }
        return false;
    }

    public static void Propagate()
    {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            bcb.Propagate();
        }
    }
}
