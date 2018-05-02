package GUI;

import blockscheme.BlockAdd;
import blockscheme.BlockYYMakeAB;
import javafx.scene.control.Button;
import jdk.nashorn.internal.ir.Block;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BlockSchemeGui {

    public static Button stepButton;

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

    /**
     * Removes all connections for a block in scheme.
     * @param Block Block from GUI
     */
    public static void RemoveBCB(BlockComponent Block) {
        ArrayList<BlockConnectionBuilder> BCBListTemp = (ArrayList<BlockConnectionBuilder>) BCBList.clone();
        for (BlockConnectionBuilder bcb :
                BCBListTemp) {
            if (bcb.getUiStart().equals(Block) || bcb.getUiEnd().equals(Block)) {
                RemoveBCB(bcb);
            }
        }
    }

    /**
     * Removes an actual connection between blocks. Only single one.
     * @param BCB
     */
    public static void RemoveBCB(BlockConnectionBuilder BCB) {
        BCBList.remove(BCB);
        BCB.RemoveMe();
    }

    /**
     * Adds an connection created between blocks. (serves for saving the connection)
      * @param BCB Connection already created.
     */
    public static void AddBCB(BlockConnectionBuilder BCB) {
        BCBList.add(BCB);
    }

    /**
     * Removes an block from scheme. Also removes all it's connections.
     * @param Block Block from GUI
     */
    public static void RemoveBlock(BlockComponent Block) {
        RemoveBCB(Block);
        Block.RemoveMe();
        AllBlocks.remove(Block);
    }

    /**
     * Adds a block into scheme.
     * @param Block Block from GUI
     */
    public static void AddBlock(BlockComponent Block) {
        AllBlocks.add(Block);
    }

    /**
     * Find End point
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

    /**
     * Find Start point
     * Start blocks are blocks that have no connections on their inputs
     */
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

    /**
     * Checks if a block can be computed. Checks all it's inputs if they are ready.
     * Also mark this block that is ready to compute.
     * @param block Block to be checked for connections.
     * @return True if can be computed.
     */
    public static boolean AreInputsReady(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
                if (!bcb.getUiStart().IsReady())
                    return false;
                else
                    bcb.getUiStart().Active(true);
        }
        return true;
    }

    /**
     * Checks if a block has connected all inputs.
     * @param block Block that will be checked.
     * @return True if it has connected all inputs.
     */
    public static boolean HasConnectedInput(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
                return true;
        }
        return false;
    }

    /**
     * Checks if a block has connected all outputs.
     * @param block Block that will be checked.
     * @return True if it has connected all outputs.
     */
    public static boolean HasConnectedOutput(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiStart().equals(block))
                return true;
        }
        return false;
    }

    /**
     * Sends all starts into their ends.
     */
    public static void PropagateAll() {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            bcb.Propagate();
        }
    }

    /**
     * Propagates value from one block to it's output connections.
     * @param block Block that will propagate it's values.
     */
    public static void PropagateBlock(BlockComponent block) {
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiStart().equals(block))
                bcb.Propagate();
        }
    }

    /**
     * Makes a step with all blocks.
     */
    public static void Step() {
        for (BlockComponent block :
                AllBlocks) {
            block.Step();
        }
    }

    /**
     * Makes calculation for block that are currently marked for calculation.
     * @return Number of blocks that were calculated.
     */
    public static int MakeCalculationPath() {
        int numBlocks = 0;
        for (BlockComponent block :
                AllBlocks) {
            if (!block.IsReady()) {
                if (AreInputsReady(block)) {
                    block.CalculateBlock();
                    PropagateBlock(block);
                    block.Active(2);
                    numBlocks++;
                }
            }
        }
        Step();
        return numBlocks;
    }

    /**
     * Counts block that are not ready.
     * @return Number of blocks that aren't ready.
     */
    public static int NonReadyBlocks() {
        int numBlocks = 0;
        for (BlockComponent block :
                AllBlocks) {
            if (!block.IsReady()) {
                    numBlocks++;
            }
        }
        return numBlocks;
    }

    /**
     * Resets the whole calculation.
     */
    public static void ResetCalculation() {
        for (BlockComponent block :
                AllBlocks) {
            block.ResetBlock();
        }
    }

    /**
     * Checks for cycles in the scheme.
     * @return True if scheme contains cycle. False otherwise.
     */
    public static boolean CheckForCycles() {

//        for (int i = 0; i < AllBlocks.size(); i++) {
//            for (BlockComponent block :
//                    AllBlocks) {
//                for (BlockConnectionBuilder bcb :
//                        BCBList) {
//                    if (bcb.getUiStart().equals(block))
//                        if (!block.IsReady())
//                            if (bcb.getUiStart().IsReady()) {
//                                ResetCalculation();
//                                block.ErrorStyle();
//                                bcb.getUiStart().ErrorStyle();
//                                return true;
//                            }
//                }
//            }
//        }
//        ResetCalculation();
//        return false;

        while (MakeCalculationPath() > 0);

        if (NonReadyBlocks() > 0)
            return true;
        return false;
    }

    /**
     * Checks if block's pin is connected.
     * @param block Block from GUI
     * @param PinIndex Index of the Pin
     * @return True if is connected. False otherwise.
     */
    public static boolean IsBlockPinConnected(BlockComponent block, int PinIndex)
    {
        boolean connected = false;
        for (BlockConnectionBuilder bcb :
                BCBList) {
            if (bcb.getUiEnd().equals(block))
            {
                connected = bcb.IsPinInStartConnected(block.GetPortByIndex(PinIndex));
                if (connected == true)
                    break;
            }
        }

        return connected;
    }

    /**
     * Checks all pins and pops up a box for input a value.
     * @param block Block from GUI
     */
    public static void SetBlockPins(BlockComponent block)
    {
        for (int i = 0; i < block.GetPortNames().size(); i++) {
            //block.Active(true);
            for (String pin :
                    block.GetPins(i)) {
                if (!IsBlockPinConnected(block, i))
                    block.SetPin(i, pin, Double.parseDouble(JOptionPane.showInputDialog(String.format("Set Pin (%s) for block (%s):", pin, block.name))));
            }
            //block.Active(false);
        }
    }

    /**
     * Starts setting all input ports.
     */
    public static void FeedPins()
    {
        ArrayList<BlockComponent> starts = FindStarts();
        for (BlockComponent block:
        AllBlocks){
            SetBlockPins(block);
        }
    }
}
