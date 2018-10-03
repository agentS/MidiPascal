package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(language = "SL", shortName = "expression", description = "The abstract base node for all expressions. An expression always returns a result after executing.")
public abstract class MidiPascalExpressionNode extends MidiPascalStatementNode
{
	public abstract Object getResult(VirtualFrame frame);
	public abstract String getStringResult(VirtualFrame frame);
	public abstract int getIntegerResult(VirtualFrame frame);
}
