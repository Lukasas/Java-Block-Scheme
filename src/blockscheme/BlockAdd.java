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


public class BlockAdd extends BaseBlock {
	ABPort ab;
	YPort y;
	public BlockAdd() {
		ab = new ABPort();
		y = new YPort();
		input.add(ab);
		output.add(y);
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