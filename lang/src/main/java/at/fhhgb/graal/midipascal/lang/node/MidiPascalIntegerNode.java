package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MidiPascalIntegerNode extends MidiPascalExpressionNode
{
	private int value;

	public MidiPascalIntegerNode(int value)
	{
		this.value = value;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.result = this.value;
	}
}
