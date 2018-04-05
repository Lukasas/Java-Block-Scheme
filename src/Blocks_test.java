import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertNotNull;
// import org.junit.rules.ExpectedException;
 
import org.junit.Test;

import blockscheme.BlockAdd;
import blockscheme.BlockSub;
import blockscheme.BlockMul;
import blockscheme.BlockDiv;
import blockscheme.Port;
public class Blocks_test
{
	@Test
	public void testAddBlocktwoptwoplusfivepfoursevenpsix() throws Exception
	{
		BlockAdd block = new BlockAdd(2.2, 5.4);
		block.calculate();
		assertEquals(Double.valueOf(7.6), block.getOutput().get(0), 0.1);
	}

	@Test
	public void testSubBlocktwoptwosubfivepfourminusthreeptwo() throws Exception
	{
		BlockSub block = new BlockSub(2.2, 5.4);
		block.calculate();
		assertEquals(Double.valueOf(-3.2), block.getOutput().get(0), 0.1);
	}	

	@Test
	public void testSubBlockPositiveValueExpected() throws Exception
	{
		BlockSub block = new BlockSub(5.4, 2.2);
		block.calculate();
		assertEquals(Double.valueOf(3.2), block.getOutput().get(0), 0.1);
	}	

	@Test
	public void testMulBlocktwoptwomulfivepfourelevenpeightyeight() throws Exception
	{
		BlockMul block = new BlockMul(2.2, 5.4);
		block.calculate();
		assertEquals(Double.valueOf(11.88), block.getOutput().get(0), 0.01);
	}	

	@Test
	public void testMulBlockExpectedZero() throws Exception
	{
		BlockMul block = new BlockMul(2.2, 0);
		block.calculate();
		assertEquals(Double.valueOf(0.0), block.getOutput().get(0), 0.1);
	}	

	@Test(expected = Exception.class)
	public void testDivBlockExpectedThrowDivideByZero() throws Exception
	{
		BlockDiv block = new BlockDiv(2.2, 0.0);
		block.calculate();
		assertEquals(Double.valueOf(0.0), block.getOutput().get(0), 0.1);
	}	
	
	@Test
	public void testDivBlockExpectedPass() throws Exception
	{
		BlockDiv block = new BlockDiv(8.0, 4.0);
		block.calculate();
		assertEquals(Double.valueOf(2.0), block.getOutput().get(0), 0.1);
	}
}