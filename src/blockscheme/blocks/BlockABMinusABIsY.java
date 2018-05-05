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


public class BlockABMinusABIsY extends BaseBlock {
    ABPort ab;
    ABPort ab0;
    YPort y;
    public BlockABMinusABIsY() {
        ab = new ABPort();
        ab0 = new ABPort();
        y = new YPort();
        input.add(ab);
        input.add(ab0);
        output.add(y);
        name = "BlockABMinusABIsY";
    }

    @Override
    public String TextOutput() {
        BlockTextOutput.set(String.format("Inputs:\n\tAB: (%f, %f)\n\tAB: (%f, %f)\nOutputs:\n\tY(0): %f", ab.getA(), ab.getB(), ab0.getA(), ab0.getB(), y.getY()));
        return BlockTextOutput.get();
    }

    @Override
    public void calculate() {
        y.setY(ab.getA() + ab.getB() - (ab0.getA() + ab0.getB()));
        super.calculate();
    }
}