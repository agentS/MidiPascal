package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

public abstract class MidiPascalExpressionNode extends MidiPascalStatementNode
{
	protected int result;

	public int getResult()
	{
		return this.result;
	}
}
