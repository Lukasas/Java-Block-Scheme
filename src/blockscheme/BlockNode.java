package blockscheme;

import blockscheme.BaseBlock;
import java.util.ArrayList;

public class BlockNode 
{
		BaseBlock value;
		ArrayList<BlockNode> SubNodes = new ArrayList<BlockNode>();
		ArrayList<BaseBlock> kids = new ArrayList<BaseBlock>();
		ArrayList<String> connectionString = new ArrayList<String>();

		public BlockNode(BaseBlock value)
		{
			this.value = value;
		}

		public void AddSubNode(BlockNode subNode)
		{
			SubNodes.add(subNode);
		}

		public void Connect(String outputName, String inputName, BaseBlock kid)
		{
			this.kids.add(kid);
			this.connectionString.add(outputName + inputName);
		}
	
		public void Compute() throws Exception
		{
//			for (int i = 0; i < SubNodes.size(); i++)
//			{
//				SubNodes.get(i).Compute();
//			}
//
//			for (int i = 0; i < kids.size(); i++)
//			{
//				this.value.getInput().set(this.connectionString.get(i).substring(1, 2),
//					kids.get(i).getOutput().get(this.connectionString.get(i).substring(0, 1)));
//			}
//			this.value.calculate();
		}		
}