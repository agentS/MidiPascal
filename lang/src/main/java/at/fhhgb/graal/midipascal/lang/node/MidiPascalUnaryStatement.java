package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "unary statement", description = "Abstract node providing abstract functionality for unary operations")
abstract class MidiPascalUnaryStatement extends MidiPascalStatementNode
{
	@Child
	@CompilerDirectives.CompilationFinal
	protected MidiPascalExpressionNode parameter;

	MidiPascalUnaryStatement(final MidiPascalExpressionNode parameter)
	{
		this.parameter = parameter;
	}
}
