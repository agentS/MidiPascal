package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "+", description = "The node performing the addition between two integers")
public class MidiPascalAdditionNode extends MidiPascalArithmeticNode
{
	public MidiPascalAdditionNode
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
		this.result = (this.leftHandSide.getIntegerResult() + this.rightHandSide.getIntegerResult());
	}
}
