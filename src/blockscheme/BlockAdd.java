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
	public void calculate() throws Exception {
		super.calculate();
		y.setY(ab.getA() + ab.getB());
	}
}