package blockscheme.blocks;

import blockscheme.blocks.ports.*;

public class BlockRGBTOYAB extends BaseBlock {
    RGBPort rgb;
    ABPort ab;
    YPort y;
    public BlockRGBTOYAB() {
        rgb = new RGBPort();
        ab = new ABPort();
        y = new YPort();
        input.add(rgb);
        output.add(ab);
        output.add(y);
        name = "BlockRGBTOYAB";
    }

    @Override
    public void calculate() {
        y.setY(rgb.getR() + rgb.getG() + rgb.getB());
        ab.setA(rgb.getR() + rgb.getG());
        ab.setB(rgb.getR() - rgb.getB());
        super.calculate();
    }
}
