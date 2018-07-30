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
	public Object getResult()
	{
		return this.getStringResult();
	}

	@Override
	public String getStringResult()
	{
		return this.result;
	}

	@Override
	public int getIntegerResult()
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
				this.leftHandSide.getResult().toString()
				+ this.rightHandSide.getResult().toString()
		);
	}
}
