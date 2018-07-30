package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(language = "Midi Pascal", description = "The abstract base node for all expressions")
public abstract class MidiPascalStatementNode extends Node
{
	public abstract void execute(VirtualFrame frame);
}
