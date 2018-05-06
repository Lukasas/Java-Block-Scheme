package blockscheme.blocks;


/*
	A ----
	       \
	         ---- Y
		   /
	B ----

 */

import blockscheme.blocks.ports.ABPort;
import blockscheme.blocks.ports.YPort;


public class BlockMul extends BaseBlock {
    ABPort ab;
    YPort y;
    public BlockMul() {
        ab = new ABPort();
        y = new YPort();
        input.add(ab);
        output.add(y);
        name = "BlockMul";
    }


    @Override
    public void calculate() {
        y.setY(ab.getA() * ab.getB());
        super.calculate();
    }
}