package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "read", description = "Node reading an integer value from the console")
public class MidiPascalReadNode extends MidiPascalStatementNode
{
	private MidiPascalSymbolNode target;

	public MidiPascalReadNode(final MidiPascalSymbolNode target)
	{
		this.target = target;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		String input = this.readFromConsole();
		frame.setInt(this.target.getFrameSlot(), Integer.parseInt(input));
	}

	@CompilerDirectives.TruffleBoundary
	private String readFromConsole()
	{
		return System.console().readLine();
	}
}
