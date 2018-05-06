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


public class BlockSub extends BaseBlock {
    ABPort ab;
    YPort y;
    public BlockSub() {
        ab = new ABPort();
        y = new YPort();
        input.add(ab);
        output.add(y);
        name = "BlockSub";
    }


    @Override
    public void calculate() {
        y.setY(ab.getA() - ab.getB());
        super.calculate();
    }
}