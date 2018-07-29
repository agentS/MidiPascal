package at.fhhgb.graal.midipascal.launcher;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;

public final class MidiPascalLauncher
{
	private static final String LANGUAGE_NAME = "midipascal";

	public static void main(String[] args) throws Exception
	{
		if (args.length == 1)
		{
			Source input = Source.newBuilder(LANGUAGE_NAME, new File(args[0])).build();
			Context context = Context.newBuilder(LANGUAGE_NAME).in(System.in).out(System.out).build();
			Value executionResult = context.eval(input);

			if (executionResult.asInt() != 0)
			{
				System.err.println("The program terminated with an error state.");
			}
		}
		else
		{
			System.err.println("Please specify exactly one .mp source file.");
		}
	}
}
