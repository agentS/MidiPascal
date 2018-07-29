package at.fhhgb.graal.midipascal.lang.parser;

public class MidiPascalTextSymbol implements MidiPascalComplexSymbol
{
	private StringBuilder identifier = null;

	public MidiPascalTextSymbol(char firstCharacater)
	{
		this.identifier = new StringBuilder();
		this.append(firstCharacater);
	}

	public void append(char toAdd)
	{
		this.identifier.append(toAdd);
	}

	@Override
	public String toString()
	{
		return this.identifier.toString();
	}
}
