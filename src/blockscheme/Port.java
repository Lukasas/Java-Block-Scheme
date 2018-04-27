package blockscheme;

import java.util.HashMap;
import java.util.Set;
public class Port
{
	private HashMap<String, Double> data = new HashMap<String, Double>();
	private String nazev; 
	private Double hodnota;

	public void add(String nazev)
	{
		this.data.put(nazev, 0.0);
	}

	public void set(String nazev, Double hodnota)
	{
		this.data.put(nazev, hodnota);
	}

	public Set<String> getKeys()
	{
		return this.data.keySet();
	}

	public void remove(String nazev)
	{
		this.data.remove(nazev);
	}

	public Double get(String nazev)
	{
		return this.data.get(nazev);
	}

	public int PortSize()
	{
		return this.data.size();
	}
}