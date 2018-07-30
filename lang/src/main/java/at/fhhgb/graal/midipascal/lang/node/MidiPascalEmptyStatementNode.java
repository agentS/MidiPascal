package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "empty statement", description = "Node doing nothing upon execution")
public class MidiPascalEmptyStatementNode extends MidiPascalStatementNode
{
	@Override
	public void execute(VirtualFrame frame) {}
}
