package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "arithmetic operation", description = "The abstract node for every arithmetic operation between two integers")
public abstract class MidiPascalArithmeticNode extends MidiPascalBinaryNode
{
	protected int result;

	MidiPascalArithmeticNode
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
		return this.getIntegerResult(frame);
	}

	@Override
	public String getStringResult(VirtualFrame frame)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getIntegerResult(VirtualFrame frame)
	{
		return this.result;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.leftHandSide.execute(frame);
		this.rightHandSide.execute(frame);
		this.performCalculation(frame);
	}

	abstract void performCalculation(VirtualFrame frame);
}
