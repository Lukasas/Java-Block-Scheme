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


public class BlockAdd extends BaseBlock {
	ABPort ab;
	YPort y;
	public BlockAdd() {
		ab = new ABPort();
		y = new YPort();
		input.add(ab);
		output.add(y);
        name = "BlockAdd";
	}

    @Override
	public void calculate() {
		y.setY(ab.getA() + ab.getB());
        super.calculate();
	}
}