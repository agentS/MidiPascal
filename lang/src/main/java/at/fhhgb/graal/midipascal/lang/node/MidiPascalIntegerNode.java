package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "integer", description = "Node for storing an integer value")
@CompilerDirectives.ValueType
public class MidiPascalIntegerNode extends MidiPascalExpressionNode
{
	@CompilerDirectives.CompilationFinal
	private int value;

	public MidiPascalIntegerNode(int value)
	{
		this.value = value;
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
		return this.value;
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
