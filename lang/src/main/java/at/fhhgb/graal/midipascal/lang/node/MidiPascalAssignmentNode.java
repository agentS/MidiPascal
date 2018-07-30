package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "assignment", description = "The node for assigning a value to a variable")
public class MidiPascalAssignmentNode extends MidiPascalExpressionNode
{
	private final MidiPascalSymbolNode symbol;
	private final MidiPascalExpressionNode expression;

	private int result;

	public MidiPascalAssignmentNode(final MidiPascalSymbolNode symbol, final MidiPascalExpressionNode expression)
	{
		this.symbol = symbol;
		this.expression = expression;
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
	public void execute(VirtualFrame frame)
	{
		this.expression.execute(frame);
		this.symbol.setResult(this.expression.getIntegerResult());
		this.result = this.expression.getIntegerResult();
	}
}
