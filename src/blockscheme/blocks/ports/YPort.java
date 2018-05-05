package blockscheme.blocks.ports;

public class YPort extends Port {

    public YPort()
    {
        super();
        this.nazev = "YPORT";
    }

    public double getY(){
        return get("Y");
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("Y");
    }

    public void setY(double value){
        set("Y", value);
    }
}
