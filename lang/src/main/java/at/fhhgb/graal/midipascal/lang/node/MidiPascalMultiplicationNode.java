package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "*", description = "Node implementing an integer multiplication")
public class MidiPascalMultiplicationNode extends MidiPascalArithmeticNode
{
	public MidiPascalMultiplicationNode
	(
			final MidiPascalExpressionNode leftHandSide,
			final MidiPascalExpressionNode rightHandSide
	)
	{
		super(leftHandSide, rightHandSide);
	}

	@Override
	void performCalculation(VirtualFrame frame)
	{
		this.result = (this.leftHandSide.getIntegerResult(frame) * this.rightHandSide.getIntegerResult(frame));
	}
}
