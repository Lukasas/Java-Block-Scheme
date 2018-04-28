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

        name = "BlockYYMakeAB";
    }


    @Override
    public String TextOutput()
    {
        BlockTextOutput.set(String.format("Inputs:\n\tY(0): %f\n\tY(1): %f\nOutputs:\n\tAB: (%f, %f)", y0.getY(), y1.getY(), ab.getA(), ab.getB()));
        return BlockTextOutput.get();
    }


    @Override
    public void calculate() {

        ab.setA(y0.getY());
        ab.setB(y1.getY());

        super.calculate(); // THIS MUST BE CALLED AT THE VERY END OF CALCULATE TO PROPAGATE VALUES INTO OUTPUT !!
    }
}