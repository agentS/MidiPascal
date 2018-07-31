package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "while", description = "The node implementing the while loop")
public class MidiPascalWhileNode extends MidiPascalStatementNode
{
	private final MidiPascalExpressionNode condition;

	private final MidiPascalStatementNode body;

	public MidiPascalWhileNode
	(
			final MidiPascalExpressionNode condition,
			final MidiPascalStatementNode body
	)
	{
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.condition.execute(frame);
		while (this.condition.getIntegerResult() != 0)
		{
			this.body.execute(frame);
			this.condition.execute(frame);
		}
	}
}
