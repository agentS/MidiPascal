package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

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
