package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "unary statement", description = "Abstract node providing abstract functionality for unary operations")
abstract class MidiPascalUnaryStatement extends MidiPascalStatementNode
{
	final protected MidiPascalExpressionNode parameter;

	MidiPascalUnaryStatement(final MidiPascalExpressionNode parameter)
	{
		this.parameter = parameter;
	}
}
