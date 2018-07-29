package at.fhhgb.graal.midipascal.lang.parser;

public class MidiPascalNumberSymbol implements MidiPascalComplexSymbol
{
	private static final int BASE = 10;
	private static final int ASCII_ZERO = 48;

	private int value;

	public MidiPascalNumberSymbol(char firstDigit)
	{
		this.value = (firstDigit-ASCII_ZERO);
	}

	public int getValue()
	{
		return this.value;
	}

	public void append(char toAdd)
	{
		this.value = ((this.value*BASE) + (toAdd-ASCII_ZERO));
	}

	@Override
	public String toString()
	{
		return String.valueOf(this.value);
	}
}
