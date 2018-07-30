package at.fhhgb.graal.midipascal.lang.parser;

public class MidiPascalStringSymbol implements MidiPascalComplexSymbol
{
	private StringBuilder buffer;

	public MidiPascalStringSymbol()
	{
		this.buffer = new StringBuilder();
	}

	@Override
	public void append(char toAdd)
	{
		this.buffer.append(toAdd);
	}

	@Override
	public String toString()
	{
		return this.buffer.toString();
	}
}
