package blockscheme.GUI;

import blockscheme.blocks.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import blockscheme.GUI.*;

import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;

public class FManager {
    public static boolean Save(Stage app) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Saving file");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Block Schemes", "*.jbsf"));

        File selectedFile = fc.showSaveDialog(app);
        if (selectedFile == null)
            return false;

        try {
            FileOutputStream fos = new FileOutputStream(selectedFile.getAbsolutePath(), false);
            fos.write(SerializeScheme());
            fos.close();
        } catch (Exception ex) {
            BlockSchemeGui.ShowAlertDialog(ex.getMessage(), "Save Error", Alert.AlertType.ERROR);
        }

        return true;
    }

    public static byte[] DoubleToByte(double val)
    {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(val);
        return bytes;

    }

    public static byte[] IntToByte(int val)
    {
        byte[] bytes = new byte[4];
        ByteBuffer.wrap(bytes).putInt(val);
        return bytes;

    }

    public static double ByteToDouble(byte[] val)
    {
        return ByteBuffer.wrap(val).getDouble();
    }

    public static int ByteToInt(byte[] val)
    {
        return ByteBuffer.wrap(val).getInt();
    }

    public static byte[] SerializeScheme() throws IOException
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();

        // First run saves blocks
        for (BlockComponent block :
                BlockSchemeGui.AllBlocks) {
            // Class name for generating
            b.write(block.GetBlockClass().getName().getBytes());
            b.write("\n".getBytes());
            // Id, X, Y
            b.write(IntToByte(block.GetID()));
            b.write(DoubleToByte(block.getLayoutX()));
            b.write(DoubleToByte(block.getLayoutY()));
            b.write("\n".getBytes());
        }
        b.write("Split\n".getBytes());
        // Second run saves connections
        for (BlockComponent block :
                BlockSchemeGui.AllBlocks) {
            // ID Block, ID Port, ID Block, ID Port
            //      int     int     int         int
            // Output Block/Port ID, Input Block Port ID
            //
            for (BlockConnectionBuilder bcb :
                    BlockSchemeGui.GetBlockOutputConnectors(block)) {
                    b.write(IntToByte(block.GetID()));
                    b.write(IntToByte(bcb.GetStartPinIndex()));
                    b.write(IntToByte(bcb.getUiEnd().GetID()));
                    b.write(IntToByte(bcb.GetEndPinIndex()));
                    b.write("\n".getBytes());
            }
        }


        return b.toByteArray();
    }


    public static boolean Load(Stage app, Pane canvas)
    {
        FileChooser fc = new FileChooser();
        fc.setTitle("Opening file");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Block Schemes", "*.jbsf"));

        File selectedFile = fc.showOpenDialog(app);
        if (selectedFile == null)
            return false;

        try {
            FileInputStream fis = new FileInputStream(selectedFile.getAbsolutePath());
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while (!(line = reader.readLine()).contentEquals("Split")) {

                BlockComponent block = new BlockComponent(BlockSchemeApp.CreateInstanceByName(line));
                line = reader.readLine();

                int ID = ByteToInt(line.substring(0, 4).getBytes());
                double X = ByteToDouble(line.substring(4, 12).getBytes()),
                        Y = ByteToDouble(line.substring(12).getBytes());

                BlockSchemeGui.AddBlock(block, ID);
                canvas.getChildren().add(block);
                block.setLayoutX(X);
                block.setLayoutY(Y);
            }

            while ((line = reader.readLine()) != null) {
                int FromBlock = ByteToInt(line.substring(0,4).getBytes()),
                        FromPin = ByteToInt(line.substring(4,8).getBytes()),
                        ToBlock = ByteToInt(line.substring(8,12).getBytes()),
                        ToPin = ByteToInt(line.substring(12).getBytes());
                BlockConnectionBuilder BCB = new BlockConnectionBuilder();
                BCB.setUiStart(BlockSchemeGui.FindBlockById(FromBlock), FromPin);
                BCB.setUiEnd(BlockSchemeGui.FindBlockById(ToBlock), ToPin);
                BlockSchemeGui.AddBCB(BCB);
                canvas.getChildren().add(BCB);

            }

        } catch (Exception ex) {
            BlockSchemeGui.ShowAlertDialog(ex.getMessage(), "Open Error", Alert.AlertType.ERROR);
        }

        return true;
    }
}
