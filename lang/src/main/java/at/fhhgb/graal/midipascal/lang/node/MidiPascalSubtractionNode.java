package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "-", description = "Node implementing a subtraction of two integer numbers")
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
		this.result = (this.leftHandSide.getIntegerResult() - this.rightHandSide.getIntegerResult());
	}
}
