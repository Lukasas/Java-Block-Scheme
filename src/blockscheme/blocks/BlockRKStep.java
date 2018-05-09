package blockscheme.blocks;

import blockscheme.blocks.ports.*;


public class BlockRKStep extends BaseBlock {
    TimePort ti;
    TimePort to;
    StepPort hi;
    StepPort ho;
    RK4Val2StepPort yi;
    RK4Val2StepPort yo;
    int SIZE = 2;

    public BlockRKStep() {
        ti = new TimePort();
        to = new TimePort();
        hi = new StepPort();
        ho = new StepPort();
        yi = new RK4Val2StepPort();
        yo = new RK4Val2StepPort();

        input.add(ti);
        input.add(hi);
        input.add(yi);
        output.add(to);
        output.add(ho);
        output.add(yo);
        name = "BlockRKStep";
    }

    @Override
    public String TextOutput()
    {
        super.TextOutput();

        BlockTextOutput.set(String.format("%s\n\nt:%8.3f y(0):% -11g y(1):% -11g  y(0)-sin(t):% e",BlockTextOutput.get(), to.getTime(), yo.getY0(), yo.getY1(), yo.getY0() - Math.sin(to.getTime())));
        return BlockTextOutput.get();
    }

    private void f(double t, RK4Val2StepPort y, double[] yin)
    {
        yin[0] = y.getY1();
        yin[1] = -y.getY0();
    }

    @Override
    public void calculate() {

        double[] k1 = new double[SIZE];
        double[] k2 = new double[SIZE];
        double[] k3 = new double[SIZE];
        double[] k4 = new double[SIZE];

        double[] yin = new double[SIZE];
        double[] ystart = new double[SIZE];

        for (int i = 0; i < SIZE; i++) {
            ystart[i] = yi.get("Y" + String.valueOf(i));
        }
        yo.CopyData(yi);
        f(ti.getTime(), yo, yin);

        for (int i = 0; i < SIZE; i++) {
            k1[i] = hi.getStep() * yin[i];
            yo.set("Y" + String.valueOf(i), ystart[i] + k1[i] / 2);
        }

        f(ti.getTime() + hi.getStep() / 2, yo, yin);

        for (int i = 0; i < SIZE; i++) {
            k2[i] = hi.getStep() * yin[i];
            yo.set("Y" + String.valueOf(i), ystart[i] + k2[i] / 2);
        }

        f(ti.getTime() + hi.getStep() / 2, yo, yin);

        for (int i = 0; i < SIZE; i++) {
            k3[i] = hi.getStep() * yin[i];
            yo.set("Y" + String.valueOf(i), ystart[i] + k3[i]);
        }

        f(ti.getTime() + hi.getStep(), yo, yin);


        for (int i = 0; i < SIZE; i++) {
            k4[i] = hi.getStep() * yin[i];
            yo.set("Y" + String.valueOf(i), ystart[i] + k1[i] / 6 + k2[i] / 3 + k3[i] / 3 + k4[i] / 6);
        }

        to.setTime(ti.getTime() + hi.getStep());
        ho.setStep(hi.getStep());
        // output is in so which is output port
        super.calculate();
    }
}