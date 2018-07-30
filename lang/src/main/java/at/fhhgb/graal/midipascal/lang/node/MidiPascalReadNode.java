package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "read", description = "Node reading an integer value from the console")
public class MidiPascalReadNode extends MidiPascalStatementNode
{
	private final MidiPascalSymbolNode target;

	public MidiPascalReadNode(final MidiPascalSymbolNode target)
	{
		this.target = target;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		String input = System.console().readLine();
		this.target.setResult(Integer.parseInt(input));
	}
}
