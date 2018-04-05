package blockscheme;
public class Port
{
	private String nazev; 
	private Double hodnota;
	public Port()
	{
		this.nazev = "Unknown";
		this.hodnota = 0.0;
	}

	public Port(String nazev, Double hodnota)
	{
		this.nazev = nazev;
		this.hodnota = hodnota;
	}

	public void set(Double hodnota)
	{
		this.hodnota = hodnota;
	}

	public Double get()
	{
		return this.hodnota;
	}

	public String getName()
	{
		return nazev;
	}

	public void setName(String nazev)
	{
		this.nazev = nazev;
	}
}