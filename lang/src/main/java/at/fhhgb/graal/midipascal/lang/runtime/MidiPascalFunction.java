package at.fhhgb.graal.midipascal.lang.runtime;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.frame.MaterializedFrame;

public class MidiPascalFunction
{
	private final RootCallTarget callTarget;
	private MaterializedFrame lexicalScope;

	public MidiPascalFunction(RootCallTarget callTarget)
	{
		this.callTarget = callTarget;
	}

	public RootCallTarget getCallTarget()
	{
		return this.callTarget;
	}

	public MaterializedFrame getLexicalScope()
	{
		return this.lexicalScope;
	}

	public void setLexicalScope(MaterializedFrame lexicalScope)
	{
		this.lexicalScope = lexicalScope;
	}
}
