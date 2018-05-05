package blockscheme;


/*
	A ----
	       \
	         ---- Y
		   /
	B ----

 */

import blockscheme.ports.ABPort;
import blockscheme.ports.YPort;


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
    public String TextOutput() {
        BlockTextOutput.set(String.format("Inputs:\n\tAB: (%f, %f)\nOutputs:\n\tY(0): %f", ab.getA(), ab.getB(), y.getY()));
        return BlockTextOutput.get();
    }

    @Override
    public void calculate() {
        y.setY(ab.getA() * ab.getB());
        super.calculate();
    }
}