package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "string", description = "Node for storing a string value")
public class MidiPascalStringNode extends MidiPascalExpressionNode
{
	private String value;

	public MidiPascalStringNode(String value)
	{
		this.value = value;
	}

	@Override
	public Object getResult()
	{
		return this.getStringResult();
	}

	@Override
	public String getStringResult()
	{
		return this.value;
	}

	@Override
	public int getIntegerResult()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void execute(VirtualFrame frame) {}
}
