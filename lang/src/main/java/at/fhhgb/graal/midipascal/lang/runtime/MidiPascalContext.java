package at.fhhgb.graal.midipascal.lang.runtime;

import at.fhhgb.graal.midipascal.lang.MidiPascalLanguage;
import com.oracle.truffle.api.TruffleLanguage;

public final class MidiPascalContext
{
	private final MidiPascalLanguage language;
	private final TruffleLanguage.Env environment;

	public MidiPascalContext(MidiPascalLanguage language, TruffleLanguage.Env environment)
	{
		this.language = language;
		this.environment = environment;
	}
}
