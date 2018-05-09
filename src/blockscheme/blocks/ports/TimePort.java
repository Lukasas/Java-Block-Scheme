package blockscheme.blocks.ports;

public class TimePort extends Port {
    public TimePort()
    {
        super();
        this.nazev = "TimePort";
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("Time");
    }

    public double getTime(){
        return get("Time");
    }

    public void setTime(double value){
        set("Time", value);
    }
}
