package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

import java.util.List;

public class MidiPascalRootNode extends RootNode
{
	@Children
	private final MidiPascalStatementNode[] nodes;

	public MidiPascalRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, List<MidiPascalStatementNode> nodes)
	{
		super(language, frameDescriptor);
		this.nodes = nodes.toArray(new MidiPascalStatementNode[] {});
	}

	@Override
	public Object execute(VirtualFrame frame)
	{
		CompilerAsserts.compilationConstant(this.nodes.length);

		for (MidiPascalStatementNode processed : this.nodes)
		{
			processed.execute(frame);
		}
		return 0;
	}
}
