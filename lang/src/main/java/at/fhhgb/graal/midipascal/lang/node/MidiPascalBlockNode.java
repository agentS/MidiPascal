package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NodeInfo(shortName = "block", description = "Node implementing a block of statements")
public class MidiPascalBlockNode extends MidiPascalStatementNode
{
	@Children
	private final MidiPascalStatementNode[] body;

	public MidiPascalBlockNode(final MidiPascalStatementNode[] body)
	{
		this.body = body;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		for (MidiPascalStatementNode executed : this.body)
		{
			executed.execute(frame);
		}
	}

	public List<MidiPascalStatementNode> getStatements()
	{
		return Collections.unmodifiableList(Arrays.asList(this.body));
	}
}
