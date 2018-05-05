package blockscheme.blocks.ports;

public class ABPort extends Port {

    public ABPort() {
        super();
        this.nazev = "ABMATH";
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("A");
        add("B");
    }

    public double getA() { return get("A"); }

    public double getB(){
        return get("B");
    }

    public void setA(double value){
        set("A", value);
    }

    public void setB(double value){
        set("B", value);
    }
}
