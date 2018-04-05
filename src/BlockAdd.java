package blockscheme;
import blockscheme.BaseBlock;
import blockscheme.Port;
public class BlockAdd extends BaseBlock
{
	
	@Override
	public void calculate()
	{
		if (this.inputs.length != 2)
			new Exception("Add block needs two inputs to work.");

		Port p = new Port();
		p.hodnota = this.inputs[0].hodnota + this.inputs[1].hodnota;
		
	}
}