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
    public String TextOutput() {
        BlockTextOutput.set(String.format("Inputs:\n\tAB: (%f, %f)\nOutputs:\n\tY(0): %f", ab.getA(), ab.getB(), y.getY()));
        return BlockTextOutput.get();
    }

    @Override
	public void calculate() {
		y.setY(ab.getA() + ab.getB());
        super.calculate();
	}
}