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
	public Object getResult()
	{
		return this.getIntegerResult();
	}

	@Override
	public String getStringResult()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int getIntegerResult()
	{
		return this.value;
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
