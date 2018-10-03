package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "binary operation", description = "Abstract node implementing the basic operations for binary operations")
@NodeChildren({@NodeChild("leftHandSide"), @NodeChild("rightHandSide")})
abstract class MidiPascalBinaryNode extends MidiPascalExpressionNode
{
	@Child
	@CompilerDirectives.CompilationFinal
	protected MidiPascalExpressionNode leftHandSide;

	@Child
	@CompilerDirectives.CompilationFinal
	protected MidiPascalExpressionNode rightHandSide;

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
