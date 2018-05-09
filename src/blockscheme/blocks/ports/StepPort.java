package blockscheme.blocks.ports;


public class StepPort extends Port {
    public StepPort() {
        super();
        this.nazev = "StepPort";
    }

    @Override
    protected void createPortData() {
        super.createPortData();
        add("Step");
    }

    public double getStep() {
        return get("Step");
    }

    public void setStep(double value) {
        set("Step", value);
    }
}
