package blockscheme.blocks.ports;

public class RK4Val2StepPort extends Port {
    public RK4Val2StepPort() {
        super();
        this.nazev = "RK4Val2StepPort";
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("Y0");
        add("Y1");
    }

    public double getY0() { return get("Y0"); }

    public double getY1(){
        return get("Y1");
    }

    public void setY0(double value){
        set("Y0", value);
    }

    public void setY1(double value){
        set("Y1", value);
    }
}
