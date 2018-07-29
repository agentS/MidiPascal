package at.fhhgb.graal.midipascal.lang;

import at.fhhgb.graal.midipascal.lang.node.MidiPascalRootNode;
import at.fhhgb.graal.midipascal.lang.node.MidiPascalStatementNode;
import at.fhhgb.graal.midipascal.lang.parser.MidiPascalParser;
import at.fhhgb.graal.midipascal.lang.runtime.MidiPascalContext;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;

@TruffleLanguage.Registration(id = "midipascal", name = "MidiPascal", version = "1.0", mimeType = "application/x-mp")
public final class MidiPascalLanguage extends TruffleLanguage<MidiPascalContext>
{
	@Override
	protected boolean isObjectOfLanguage(Object object)
	{
		return (object instanceof MidiPascalStatementNode);
	}

	@Override
	protected MidiPascalContext createContext(Env environment)
	{
		return new MidiPascalContext(this, environment);
	}

	@Override
	protected CallTarget parse(ParsingRequest request) throws Exception
	{
		MidiPascalParser parser = new MidiPascalParser(request.getSource());
		MidiPascalRootNode root = parser.read(this);
		return Truffle.getRuntime().createCallTarget(root);
	}
}
