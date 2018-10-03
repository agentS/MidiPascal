package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MidiPascalStringConcatenationNode extends MidiPascalBinaryNode
{
	protected String result;

	public MidiPascalStringConcatenationNode
	(
			final MidiPascalExpressionNode leftHandSide,
			final MidiPascalExpressionNode rightHandSide
	)
	{
		super(leftHandSide, rightHandSide);
	}

	@Override
	public Object getResult(VirtualFrame frame)
	{
		return this.getStringResult(frame);
	}

	@Override
	public String getStringResult(VirtualFrame frame)
	{
		return this.result;
	}

	@Override
	public int getIntegerResult(VirtualFrame frame)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.leftHandSide.execute(frame);
		this.rightHandSide.execute(frame);
		this.result =
		(
				this.leftHandSide.getResult(frame).toString()
				+ this.rightHandSide.getResult(frame).toString()
		);
	}
}
