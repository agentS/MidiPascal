package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
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
	protected void performCalculation(VirtualFrame frame)
	{
		this.result = (this.leftHandSide.getIntegerResult(frame) - this.rightHandSide.getIntegerResult(frame));
	}
}
