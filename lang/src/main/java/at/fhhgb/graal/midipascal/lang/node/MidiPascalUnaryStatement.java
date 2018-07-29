package at.fhhgb.graal.midipascal.lang.node;

abstract class MidiPascalUnaryStatement extends MidiPascalStatementNode
{
	final protected MidiPascalExpressionNode parameter;

	MidiPascalUnaryStatement(final MidiPascalExpressionNode parameter)
	{
		this.parameter = parameter;
	}
}
