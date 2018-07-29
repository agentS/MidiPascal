package at.fhhgb.graal.midipascal.lang.parser;

public class MidiPascalSyntaxException extends Exception
{
	private String message;

	public MidiPascalSyntaxException(String message)
	{
		super();
		this.message = message;
	}

	public MidiPascalSyntaxException(final int lineNumber, final int characterPosition, final char character)
	{
		this("Invalid character '" + character + "' in line " + lineNumber + " at position " + characterPosition + ".");
	}

	@Override
	public String toString()
	{
		return this.message;
	}
}
