package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "assignment", description = "The node for assigning a value to a variable")
public class MidiPascalAssignmentNode extends MidiPascalExpressionNode
{
	private final MidiPascalSymbolNode symbol;
	private final MidiPascalExpressionNode expression;

	public MidiPascalAssignmentNode(final MidiPascalSymbolNode symbol, final MidiPascalExpressionNode expression)
	{
		this.symbol = symbol;
		this.expression = expression;
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
		try
		{
			return frame.getInt(this.symbol.getFrameSlot());
		}
		catch (FrameSlotTypeException exception)
		{
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.expression.execute(frame);
		frame.setInt(this.symbol.getFrameSlot(), this.expression.getIntegerResult(frame));
	}
}
