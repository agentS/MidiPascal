package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MidiPascalWriteNode extends MidiPascalUnaryStatement
{
	public MidiPascalWriteNode(final MidiPascalExpressionNode parameter)
	{
		super(parameter);
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.parameter.execute(frame);
		System.out.println(this.parameter.getResult());
	}
}
