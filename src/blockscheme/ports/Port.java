package blockscheme.ports;

import java.util.HashMap;
import java.util.Set;
public class Port {
    private HashMap<String, Double> data = new HashMap<String, Double>();
    protected String nazev;

    public Port() {
        createPortData();
    }

    protected void add(String nazev) {
        this.data.put(nazev, 0.0);
    }

    protected void set(String nazev, Double hodnota) {
        this.data.put(nazev, hodnota);
    }

    protected Set<String> getKeys() {
        return this.data.keySet();
    }

    protected void remove(String nazev) {
        this.data.remove(nazev);
    }

    protected Double get(String nazev) {
        return this.data.get(nazev);
    }

    public int PortSize() {
        return this.data.size();
    }

    protected void createPortData() {
    }

    public void CopyData(Port clone) {
        for (String key :
                clone.getKeys()) {
            set(key, clone.get(key));
        }
    }

    public String getName()
    {
        return nazev;
    }

    public boolean IsCalled(String name){
	    return nazev.compareTo(name) == 0;
    }

}