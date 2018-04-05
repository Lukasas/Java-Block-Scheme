import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertNotNull;
// import org.junit.rules.ExpectedException;
 
import org.junit.Test;

import blockscheme.BlockAdd;
import blockscheme.Port;
public class Blocks_test
{
	@Test
	public void testAddBlocktwoptwoplusfivepfoursevenpsix()
	{
		BlockAdd block = new BlockAdd(2.2, 5.4);
		block.calculate();
		assertEquals(Double.valueOf(7.6), Double.valueOf(block.getOutput().get(0)));
	}	
}