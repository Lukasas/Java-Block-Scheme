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


public class BlockDiv extends BaseBlock {
    ABPort ab;
    YPort y;
    public BlockDiv() {
        ab = new ABPort();
        y = new YPort();
        input.add(ab);
        output.add(y);
        name = "BlockDiv";
    }

    @Override
    public void calculate() {
        y.setY(ab.getA() / ab.getB());
        super.calculate();
    }
}