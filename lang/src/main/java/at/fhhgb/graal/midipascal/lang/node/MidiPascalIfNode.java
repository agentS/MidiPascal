package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "if", description = "The node implementing a conditional statement")
public class MidiPascalIfNode extends MidiPascalStatementNode
{
	private final MidiPascalExpressionNode condition;

	private final MidiPascalStatementNode thenNode;

	private final MidiPascalStatementNode elseNode;

	public MidiPascalIfNode
	(
			final MidiPascalExpressionNode condition,
			final MidiPascalStatementNode thenNode
	)
	{
		this(condition, thenNode, null);
	}

	public MidiPascalIfNode
	(
			final MidiPascalExpressionNode condition,
			final MidiPascalStatementNode thenNode,
			final MidiPascalStatementNode elseNode
	)
	{
		this.condition = condition;
		this.thenNode = thenNode;
		this.elseNode = elseNode;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.condition.execute(frame);
		if (this.condition.getIntegerResult() != 0)
		{
			this.thenNode.execute(frame);
		}
		else if (this.elseNode != null)
		{
			this.elseNode.execute(frame);
		}
	}
}
