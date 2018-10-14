package at.fhhgb.graal.midipascal.lang.node;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.graalvm.polyglot.Context;

public class MidiPascalPolyglotNode extends MidiPascalStatementNode
{
	@CompilerDirectives.CompilationFinal
	private String targetLanguage;

	@Child
	private MidiPascalExpressionNode parameter;

	public MidiPascalPolyglotNode(String targetLanguage, MidiPascalExpressionNode parameter)
	{
		this.targetLanguage = targetLanguage;
		this.parameter = parameter;
	}

	@Override
	public void execute(VirtualFrame frame)
	{
		this.parameter.execute(frame);
		Context polyglot = Context.create();
		polyglot.eval(this.targetLanguage, this.parameter.getStringResult(frame));
	}
}
