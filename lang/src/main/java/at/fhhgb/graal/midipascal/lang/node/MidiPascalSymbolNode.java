package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MidiPascalSymbolNode extends MidiPascalExpressionNode
{
	private final String name;

	public MidiPascalSymbolNode(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "'" + this.name + "'";
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
