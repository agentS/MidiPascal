package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;

public class MidiPascalEmptyStatementNode extends MidiPascalStatementNode
{
	@Override
	public void execute(VirtualFrame frame) {}
}
