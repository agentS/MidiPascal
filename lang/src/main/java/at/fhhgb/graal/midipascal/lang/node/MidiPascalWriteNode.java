package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "write", description = "Node printing an expression on the console")
public class MidiPascalWriteNode extends MidiPascalUnaryStatement
{
	public MidiPascalWriteNode(final MidiPascalExpressionNode parameter)
	{
		super(parameter);
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.parameter.execute(frame);
		this.print();
	}

	@CompilerDirectives.TruffleBoundary
	private void print()
	{
		System.out.println(this.parameter.getResult());
	}
}
