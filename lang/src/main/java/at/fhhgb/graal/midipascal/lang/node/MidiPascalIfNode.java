package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ConditionProfile;

@NodeInfo(shortName = "if", description = "The node implementing a conditional statement")
public class MidiPascalIfNode extends MidiPascalStatementNode
{
	@Child
	private MidiPascalExpressionNode condition;

	@Child
	private MidiPascalStatementNode thenNode;

	@Child
	private MidiPascalStatementNode elseNode;

	private final ConditionProfile conditionProfile;

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

		this.conditionProfile = ConditionProfile.createCountingProfile();
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		if (this.conditionProfile.profile(this.evaluateCondition(frame)))
		{
			this.thenNode.execute(frame);
		}
		else if (this.elseNode != null)
		{
			this.elseNode.execute(frame);
		}
	}

	private boolean evaluateCondition(VirtualFrame frame)
	{
		this.condition.execute(frame);
		return (this.condition.getIntegerResult(frame) != 0);
	}
}
