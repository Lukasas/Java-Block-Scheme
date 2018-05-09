package blockscheme.GUI;

import blockscheme.blocks.*;
import com.sun.glass.ui.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import blockscheme.GUI.*;

import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;

public class FManager {
    static File lastDirUsed = null;

    public static boolean Save(Stage app) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Saving file");
        if (lastDirUsed != null)
            fc.setInitialDirectory(lastDirUsed.getParentFile());
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Block Schemes", "*.jbsf"));

        File selectedFile = fc.showSaveDialog(app);
        if (selectedFile == null)
            return false;

        lastDirUsed = selectedFile;

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
            int ID = block.GetID();
            double X = block.getLayoutX(), Y = block.getLayoutY();
            byte[] intBuffer = IntToByte(ID);
            byte[] doubleBuffer = DoubleToByte(X);
            b.write(intBuffer, 0, 4);
            b.write(doubleBuffer, 0, 8);

            doubleBuffer = DoubleToByte(Y);
            b.write(doubleBuffer, 0, 8);
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
            }
        }


        return b.toByteArray();
    }


    public static boolean Load(Stage app, Pane canvas)
    {
        FileChooser fc = new FileChooser();
        if (lastDirUsed != null)
            fc.setInitialDirectory(lastDirUsed.getParentFile());
        fc.setTitle("Opening file");
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Block Schemes", "*.jbsf"));

        File selectedFile = fc.showOpenDialog(app);
        if (selectedFile == null)
            return false;

        lastDirUsed = selectedFile;
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        byte[] intBuffer = new byte[4];
        byte[] doubleBuffer = new byte[8];
        try {
            FileInputStream fis = new FileInputStream(selectedFile.getAbsolutePath());
            while (true) {

                String name = "";

                // pull name
                int cur;
                while ((cur = fis.read()) != 10)
                {
                    b.write(cur);
                }
                name = b.toString();
                if (name.contentEquals("Split"))
                {
                    break;
                }
                b.reset();
                BlockComponent block = new BlockComponent(BlockSchemeApp.CreateInstanceByName(name));

                int ID;
                double X,Y;

                fis.read(intBuffer);
                ID = ByteToInt(intBuffer);
                fis.read(doubleBuffer);
                X = ByteToDouble(doubleBuffer);
                fis.read(doubleBuffer);
                Y = ByteToDouble(doubleBuffer);
                fis.read();

                BlockSchemeGui.AddBlock(block, ID);
                canvas.getChildren().add(block);
                block.setLayoutX(X);
                block.setLayoutY(Y);
            }

            while (true) {

                int FromBlock, FromPin, ToBlock , ToPin;
                if (fis.read(intBuffer) == -1)
                    break;
                FromBlock = ByteToInt(intBuffer);

                fis.read(intBuffer);
                FromPin = ByteToInt(intBuffer);

                fis.read(intBuffer);
                ToBlock = ByteToInt(intBuffer);

                fis.read(intBuffer);
                ToPin = ByteToInt(intBuffer);

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
