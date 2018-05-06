package blockscheme.blocks.ports;

public class RGBPort extends Port {

    public RGBPort() {
        super();
        this.nazev = "RGBPORT";
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("R");
        add("G");
        add("B");
    }

    public double getR() { return get("R"); }

    public double getG(){
        return get("G");
    }

    public double getB(){
        return get("B");
    }

    public void setR(double value){
        set("R", value);
    }

    public void setG(double value){
        set("G", value);
    }

    public void setB(double value){
        set("B", value);
    }
}
