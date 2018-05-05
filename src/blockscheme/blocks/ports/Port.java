/**
 * This package works with ports and it's insides.
 */
package blockscheme.blocks.ports;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class Port {
    private HashMap<String, Double> data = new HashMap<String, Double>();
    protected String nazev;

    /**
     * Makes text from the Port and it's numbers.
     *
     * @return Returns formated text of port insides
     */
    public String PortText() {
        String temp = "";
        for (Map.Entry<String, Double> entry :
                data.entrySet()) {
            temp += String.format("%s - %f\n", entry.getKey(), entry.getValue());
        }
        return temp;
    }

    public Port() {
        createPortData();
    }

    /**
     * Adds a Pin into a Port
     *
     * @param nazev Name of the Pin
     */
    protected void add(String nazev) {
        this.data.put(nazev, 0.0);
    }

    /**
     * Sets a Pin in Port to a value.
     *
     * @param nazev   Name of the Pin
     * @param hodnota Value of the Pin
     */
    public void set(String nazev, Double hodnota) {
        this.data.put(nazev, hodnota);
    }

    /**
     * Gets all pins in port
     *
     * @return Collection of Pins
     */
    public Set<String> getKeys() {
        return this.data.keySet();
    }

    /**
     * Removes a pin from port
     *
     * @param nazev Name of the pin
     */
    protected void remove(String nazev) {
        this.data.remove(nazev);
    }

    /**
     * Gets a value from a Pin
     *
     * @param nazev Name of the pin
     * @return Value of the pin
     */
    public Double get(String nazev) {
        return this.data.get(nazev);
    }

    /**
     * Creates Pins in ports
     */
    protected void createPortData() {
    }

    /**
     * Copies values from one Port to another
     *
     * @param clone Port it's data will be copied into this.
     */
    public void CopyData(Port clone) {
        for (String key :
                clone.getKeys()) {
            set(key, clone.get(key));
        }
    }

    /**
     * Get name of the port.
     *
     * @return Name of the port
     */
    public String getName() {
        return nazev;
    }

    /**
     * Tests if the port is Called with selected name
     *
     * @param name Name that is going to be checked.
     * @return True if name and name of the port are same.
     */
    public boolean IsCalled(String name) {
        return nazev.compareTo(name) == 0;
    }

    /**
     * Resets port insides to 0.0
     */
    public void Reset() {
        for (Map.Entry<String, Double> entry :
                data.entrySet()) {
            data.put(entry.getKey(), 0.0);
        }
    }
}