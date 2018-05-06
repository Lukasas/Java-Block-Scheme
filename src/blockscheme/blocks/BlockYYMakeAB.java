package blockscheme.blocks;



/*
	Y (0) ----    -->   ---- A (Y0)
	           \      /
	             ----
		       /      \
	Y (1) ----    -->   ---- B (Y1)

 */

import blockscheme.blocks.ports.*;

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
    public void calculate() {

        ab.setA(y0.getY());
        ab.setB(y1.getY());

        super.calculate(); // THIS MUST BE CALLED AT THE VERY END OF CALCULATE TO PROPAGATE VALUES INTO OUTPUT !!
    }
}