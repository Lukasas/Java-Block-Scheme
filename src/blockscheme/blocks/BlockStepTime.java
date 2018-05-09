package blockscheme.blocks;


/*
	A ----
	       \
	         ---- Y
		   /
	B ----

 */

import blockscheme.blocks.ports.StepPort;
import blockscheme.blocks.ports.TimePort;


public class BlockStepTime extends BaseBlock {
    TimePort ti;
    TimePort to;
    StepPort si;
    StepPort so;
    public BlockStepTime() {
        ti = new TimePort();
        to = new TimePort();
        si = new StepPort();
        so = new StepPort();
        input.add(ti);
        input.add(si);
        output.add(to);
        output.add(so);
        name = "BlockStepTime";
    }

    @Override
    public void calculate() {
        to.setTime(ti.getTime() + si.getStep());
        so.setStep(si.getStep());
        super.calculate();
    }
}