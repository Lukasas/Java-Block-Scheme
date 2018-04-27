import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
 
import org.junit.Test;

import blockscheme.BlockAdd;
import blockscheme.BlockSub;
import blockscheme.BlockMul;
import blockscheme.BlockDiv;
import blockscheme.Port;
import blockscheme.BlockNode;

public class BlockConnection_test
{
	@Test
	public void testConnection() throws Exception
	{
		BlockAdd blockA = new BlockAdd(4.2, 2.4);
		BlockSub blockB = new BlockSub();

		BlockNode head = new BlockNode(blockB);
		BlockNode kid = new BlockNode(blockA);

		blockA.calculate();
		blockB.getInput().set("B", 0.6);

		head.AddSubNode(kid);
		head.Connect("Y", "A", blockA);

		head.Compute();

		assertEquals(6.0, blockB.getOutput().get("Y"), 0.1);
	}
}