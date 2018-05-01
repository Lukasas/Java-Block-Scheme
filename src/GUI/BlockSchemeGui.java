package GUI;

import blockscheme.BlockAdd;
import blockscheme.BlockYYMakeAB;
import jdk.nashorn.internal.ir.Block;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockSchemeGui {
    ArrayList<Cable> scheme = new ArrayList<Cable>();
    static ArrayList<BlockConnectionBuilder> BCBList = new ArrayList<>();
    static ArrayList<BlockComponent> AllBlocks = new ArrayList<>();

    public BlockSchemeGui() {

    }


    public static void ListAll() {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            System.out.println(bcb.getUiStart().name + " " + bcb.getUiEnd().name);
        }
    }

    public static void RemoveBCB(BlockComponent Block) {
        ArrayList<BlockConnectionBuilder> BCBListTemp = (ArrayList<BlockConnectionBuilder>) BCBList.clone();
        for (BlockConnectionBuilder bcb :
                BCBListTemp) {
            if (bcb.getUiStart().equals(Block) || bcb.getUiEnd().equals(Block)) {
                RemoveBCB(bcb);
            }
        }
    }

    public static void RemoveBCB(BlockConnectionBuilder BCB) {
        BCBList.remove(BCB);
        BCB.RemoveMe();
    }

    public static void AddBCB(BlockConnectionBuilder BCB) {
        BCBList.add(BCB);
    }

    public static void RemoveBlock(BlockComponent Block) {
        RemoveBCB(Block);
        Block.RemoveMe();
        AllBlocks.remove(Block);
    }

    public static void AddBlock(BlockComponent Block) {
        AllBlocks.add(Block);
    }

    /**
     * Find Start and End point
     * Start blocks are blocks that have no connections on their inputs
     * End blocks are block that have no connections on their outputs
     */
    public static ArrayList<BlockComponent> FindEnds() {
        ArrayList<BlockComponent> AllBlocksEnd = (ArrayList<BlockComponent>) AllBlocks.clone();
        for (BlockComponent block :
                AllBlocks) {
            if (HasConnectedOutput(block))
                AllBlocksEnd.remove(block);
        }
        return AllBlocksEnd;
    }

    public static ArrayList<BlockComponent> FindStarts()
    {
        ArrayList<BlockComponent> AllBlocksStart = (ArrayList<BlockComponent>) AllBlocks.clone();
        for (BlockComponent block :
                AllBlocks) {
            if (HasConnectedInput(block))
                AllBlocksStart.remove(block);
        }
        return AllBlocksStart;
    }

    /**
     * Helper function that finds all blocks that are connected into this block
     *
     * @param block Block that is being checked for his connectors.
     */
    public static void GetBlockInputConnectors(BlockComponent block) {
        System.out.printf("InputConnectors:\n");
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
                System.out.printf(bcb.getUiStart().name + "\n");
        }
    }

    /**
     * Helper function that finds all block that are connected into this block output.
     *
     * @param block Block that is being checked for his connectors.
     */
    public static void GetBlockOutputConnectors(BlockComponent block) {
        System.out.printf("OutputConnectors:\n");
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiStart().equals(block))
                System.out.printf(bcb.getUiEnd().name + "\n");
        }
    }

    public static boolean AreInputsReady(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
                if (!bcb.getUiStart().IsReady())
                    return false;
//                else
//                    bcb.getUiStart().Active(1);
        }
        return true;
    }

    public static boolean HasConnectedInput(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
                return true;
        }
        return false;
    }

    public static boolean HasConnectedOutput(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiStart().equals(block))
                return true;
        }
        return false;
    }

    public static void PropagateAll() {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            bcb.Propagate();
        }
    }

    public static void PropagateBlock(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiStart().equals(block))
                bcb.Propagate();
        }
    }

    public static void Step() {
        for (BlockComponent block :
                AllBlocks) {
            block.Step();
        }
    }

    public static void MakeCalculationPath() {
        for (BlockComponent block :
                AllBlocks) {
            if (AreInputsReady(block)) {
                block.CalculateBlock();
                PropagateBlock(block);
                block.Active(true);
            }

        }

        Step();
    }

    public static void ResetCalculation() {
        for (BlockComponent block :
                AllBlocks) {
            block.ResetBlock();

        }
    }

    public static void SetBlockPins(BlockComponent block)
    {
        for (int i = 0; i < block.GetPortNames().size(); i++) {
            //block.Active(true);
            for (String pin :
                    block.GetPins(i)) {
                block.SetPin(i, pin, Double.parseDouble(JOptionPane.showInputDialog(String.format("Set Pin (%s) for block (%s green):", pin, block.name))));
            }
            //block.Active(false);
        }
    }

    public static void FeedStarts()
    {
        ArrayList<BlockComponent> starts = FindStarts();
        for (BlockComponent block:
        starts){
            SetBlockPins(block);
    }
    }
}
