package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "symbol", description = "Node implementing a symbol like a variable assignment")
@CompilerDirectives.ValueType
public class MidiPascalSymbolNode extends MidiPascalExpressionNode
{
	private final String name;
	private int result;

	public MidiPascalSymbolNode(final String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	@Override
	public String toString()
	{
		return "'" + this.name + "'";
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
		return this.result;
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
