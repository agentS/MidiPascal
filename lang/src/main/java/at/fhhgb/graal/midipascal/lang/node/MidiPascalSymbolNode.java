package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.dsl.TypeSystem;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "symbol", description = "Node implementing a symbol like a variable assignment")
@CompilerDirectives.ValueType
public class MidiPascalSymbolNode extends MidiPascalExpressionNode
{
	private final FrameSlot frameSlot;

	public MidiPascalSymbolNode(final FrameSlot frameSlot)
	{
		this.frameSlot = frameSlot;
	}

	public FrameSlot getFrameSlot()
	{
		return this.frameSlot;
	}

	@Override
	public String toString()
	{
		return "'" + this.frameSlot.getIdentifier().toString() + "'";
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
			return frame.getInt(this.frameSlot);
		}
		catch (FrameSlotTypeException exception)
		{
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
