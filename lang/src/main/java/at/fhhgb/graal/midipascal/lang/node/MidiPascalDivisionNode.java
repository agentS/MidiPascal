package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "/", description = "Node implementing an integer division")
public class MidiPascalDivisionNode extends MidiPascalArithmeticNode
{
	public MidiPascalDivisionNode
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
		this.result = (this.leftHandSide.getIntegerResult() / this.rightHandSide.getIntegerResult());
	}
}
