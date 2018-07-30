package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "binary operation", description = "Abstract node implementing the basic operations for binary operations")
@NodeChildren({@NodeChild("leftHandSide"), @NodeChild("rightHandSide")})
abstract class MidiPascalBinaryNode extends MidiPascalExpressionNode
{
	protected final MidiPascalExpressionNode leftHandSide;
	protected final MidiPascalExpressionNode rightHandSide;

	MidiPascalBinaryNode
	(
			final MidiPascalExpressionNode leftHandSide,
			final MidiPascalExpressionNode rightHandSide
	)
	{
		this.leftHandSide = leftHandSide;
		this.rightHandSide = rightHandSide;
	}
}
