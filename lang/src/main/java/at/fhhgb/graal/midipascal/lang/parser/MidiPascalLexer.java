package at.fhhgb.graal.midipascal.lang.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MidiPascalLexer
{
	private BufferedReader input;

	public MidiPascalLexer(BufferedReader input)
	{
		this.input = input;
	}

	public List<MidiPascalSymbol> readSymbols() throws IOException, MidiPascalSyntaxException
	{
		List<MidiPascalSymbol> symbols = new ArrayList<MidiPascalSymbol>();

		int lineNumber = 0;
		String line = this.input.readLine();
		while (line != null)
		{
			++lineNumber;

			for (int i = 0; i < line.length(); ++i)
			{
				try
				{
					MidiPascalSimpleSymbol nextSymbol = this.getNextSymbol(line.charAt(i));

					if (isPartOfComplexSymbol(nextSymbol))
					{
						MidiPascalComplexSymbol builtComplexSymbol = null;
						if (nextSymbol == MidiPascalSimpleSymbol.DIGIT)
						{
							builtComplexSymbol = new MidiPascalNumberSymbol(line.charAt(i));
						}
						else if (nextSymbol == MidiPascalSimpleSymbol.CHARACTER)
						{
							builtComplexSymbol = new MidiPascalTextSymbol(line.charAt(i));
						}

						++i;
						nextSymbol = this.getNextSymbol(line.charAt(i));
						while
						(
								(i < line.length())
								&& isPartOfComplexSymbol(nextSymbol)
						)
						{
							nextSymbol = this.getNextSymbol(line.charAt(i));
							if (isPartOfComplexSymbol(nextSymbol))
							{
								builtComplexSymbol.append(line.charAt(i));
								++i;
							}
						}

						--i;
						MidiPascalKeywordSymbol keyword = getKeywordSymbol(builtComplexSymbol);
						if (keyword != null)
						{
							symbols.add(keyword);
						}
						else
						{
							symbols.add(builtComplexSymbol);
						}
					}
					else
					{
						if (nextSymbol != MidiPascalSimpleSymbol.BLANK)
						{
							symbols.add(nextSymbol);
						}
					}
				}
				catch (IllegalArgumentException exception)
				{
					throw new MidiPascalSyntaxException(lineNumber, (i + 1), line.charAt(i));
				}
			}

			line = this.input.readLine();
		}

		return symbols;
	}

	private MidiPascalSimpleSymbol getNextSymbol(char examined)
	{
		switch (examined)
		{
			case '+':
				return MidiPascalSimpleSymbol.PLUS;
			case '-':
				return MidiPascalSimpleSymbol.MINUS;
			case '*':
				return MidiPascalSimpleSymbol.MULTIPLICATION;
			case '/':
				return MidiPascalSimpleSymbol.DIVISION;
			case '(':
				return MidiPascalSimpleSymbol.OPEN_PARENTHESIS;
			case ')':
				return MidiPascalSimpleSymbol.CLOSING_PARENTHESIS;
			case ';':
				return MidiPascalSimpleSymbol.SEMICOLON;
			case ',':
				return MidiPascalSimpleSymbol.COMMA;
			case ':':
				return MidiPascalSimpleSymbol.COLON;
			case '=':
				return MidiPascalSimpleSymbol.EQUALS;
			case '.':
				return MidiPascalSimpleSymbol.PERIOD;
			case '\n':
				return MidiPascalSimpleSymbol.BLANK;
			case '\r':
				return MidiPascalSimpleSymbol.BLANK;
			case '\t':
				return MidiPascalSimpleSymbol.BLANK;
			case ' ':
				return MidiPascalSimpleSymbol.BLANK;
			default:
				if ((examined >= '0') && (examined <= '9'))
				{
					return MidiPascalSimpleSymbol.DIGIT;
				}
				if
				(
						((examined >= 'a') && (examined <= 'z'))
						|| ((examined >= 'A') && (examined <= 'Z'))
				)
				{
					return MidiPascalSimpleSymbol.CHARACTER;
				}
		}
		throw new IllegalArgumentException("Invalid character: " + examined);
	}

	private static boolean isPartOfComplexSymbol(MidiPascalSimpleSymbol examined)
	{
		return
		(
				(examined == MidiPascalSimpleSymbol.DIGIT)
				|| (examined == MidiPascalSimpleSymbol.CHARACTER)
		);
	}

	private static MidiPascalKeywordSymbol getKeywordSymbol(MidiPascalComplexSymbol examined)
	{
		if (examined instanceof MidiPascalTextSymbol)
		{
			String stringRepresentation = examined.toString();
			switch (stringRepresentation)
			{
				case "READ":
					return MidiPascalKeywordSymbol.READ;
				case "WRITE":
					return MidiPascalKeywordSymbol.WRITE;
				case "PROGRAM":
					return MidiPascalKeywordSymbol.PROGRAM;
				case "VAR":
					return MidiPascalKeywordSymbol.VARIABLE;
				case "BEGIN":
					return MidiPascalKeywordSymbol.BEGIN;
				case "END":
					return MidiPascalKeywordSymbol.END;
				case "IF":
					return MidiPascalKeywordSymbol.IF;
				case "THEN":
					return MidiPascalKeywordSymbol.THEN;
				case "ELSE":
					return MidiPascalKeywordSymbol.ELSE;
				case "WHILE":
					return MidiPascalKeywordSymbol.WHILE;
				case "DO":
					return MidiPascalKeywordSymbol.DO;
				case "INTEGER":
					return MidiPascalKeywordSymbol.INTEGER_DATA_TYPE;
			}
		}
		return null;
	}
}
