package at.fhhgb.graal.midipascal.lang.node;

public class MidiPascalSubtractionNode extends MidiPascalArithmeticNode
{
	public MidiPascalSubtractionNode
	(
			final MidiPascalExpressionNode leftHandSide,
			final MidiPascalExpressionNode rightHandSide
	)
	{
		super(leftHandSide, rightHandSide);
	}

	@Override
	void performCalculation()
	{
		this.result = (this.leftHandSide.getResult() - this.rightHandSide.getResult());
	}
}
