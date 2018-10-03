package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RepeatingNode;

@NodeInfo(shortName = "while", description = "The node implementing the while loop")
public class MidiPascalWhileNode extends MidiPascalStatementNode
{
	@Child
	private LoopNode loop;

	public MidiPascalWhileNode
	(
			MidiPascalExpressionNode condition,
			MidiPascalStatementNode body
	)
	{

		this.loop = Truffle.getRuntime().createLoopNode(new WhileRepeatingNode(condition, body));
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.loop.executeLoop(frame);
	}

	private static class WhileRepeatingNode extends Node implements RepeatingNode
	{
		@Child
		private MidiPascalExpressionNode condition;

		@Child
		private MidiPascalStatementNode body;

		public WhileRepeatingNode
		(
				MidiPascalExpressionNode condition,
				MidiPascalStatementNode body
		)
		{
			this.condition = condition;
			this.body = body;
		}

		@Override
		public boolean executeRepeating(VirtualFrame frame)
		{
			this.condition.execute(frame);
			if (this.condition.getIntegerResult() != 0)
			{
				this.body.execute(frame);
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
