package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

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
	public void execute(VirtualFrame frame)
	{
		this.expression.execute(frame);
		this.symbol.setResult(this.expression.getResult());
		this.result = this.expression.getResult();
	}
}
