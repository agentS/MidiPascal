package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class MidiPascalArithmeticNode extends MidiPascalBinaryNode
{
	MidiPascalArithmeticNode
	(
			final MidiPascalExpressionNode leftHandSide,
			final MidiPascalExpressionNode rightHandSide
	)
	{
		super(leftHandSide, rightHandSide);
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.leftHandSide.execute(frame);
		this.rightHandSide.execute(frame);
		this.performCalculation();
	}

	abstract void performCalculation();
}
