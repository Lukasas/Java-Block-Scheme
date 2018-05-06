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


public class BlockAnd extends BaseBlock {
    ABPort ab;
    YPort y;
    public BlockAnd() {
        ab = new ABPort();
        y = new YPort();
        input.add(ab);
        output.add(y);
        name = "BlockAnd";
    }


    @Override
    public void calculate() {
        y.setY((int)ab.getA() & (int)ab.getB());
        super.calculate();
    }
}