package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.TypeSystem;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "string", description = "Node for storing a string value")
public class MidiPascalStringNode extends MidiPascalExpressionNode
{
	@CompilerDirectives.CompilationFinal
	private String value;

	public MidiPascalStringNode(String value)
	{
		this.value = value;
	}

	@Override
	public Object getResult(VirtualFrame frame)
	{
		return this.getStringResult(frame);
	}

	@Override
	public String getStringResult(VirtualFrame frame)
	{
		return this.value;
	}

	@Override
	public int getIntegerResult(VirtualFrame frame)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
