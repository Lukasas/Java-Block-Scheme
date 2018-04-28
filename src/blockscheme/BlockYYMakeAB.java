package blockscheme;



/*
	Y (0) ----    -->   ---- A (Y0)
	           \      /
	             ----
		       /      \
	Y (1) ----    -->   ---- B (Y1)

 */

import blockscheme.ports.*;

public class BlockYYMakeAB extends BaseBlock {
    private YPort y0;
    private YPort y1;
    private ABPort ab;

    public BlockYYMakeAB() {
        y0 = new YPort();
        y1 = new YPort();
        ab = new ABPort();
        input.add(y0);
        input.add(y1);
        output.add(ab);

    }


    public String TextOuptut()
    {
        return String.format("Inputs:\n\tY(0): %f\n\tY(1): %f\nOutputs:\n\tAB: (%f, %f)", y0.getY(), y1.getY(), ab.getA(), ab.getB());
    }

    @Override
    public void calculate() throws Exception {
        super.calculate();
        ab.setA(y0.getY());
        ab.setB(y1.getY());
    }
}