package at.fhhgb.graal.midipascal.interop;

import org.graalvm.polyglot.*;

public class MidiPascalFromJava
{
	public static void main(String[] args)
	{
		Context polyglot = Context.create();
		polyglot.eval
		(
			"midipascal",
			"PROGRAM fact;\n"
			+ "VAR f, n: INTEGER;\n"
			+ "VAR original: INTEGER;\n"
			+ "BEGIN\n"
				+ "WRITE('Hello from MidiPascal.');\n"
				+ "WRITE('Please enter a number:');\n"
				+ "READ(n);\n"
				+ "original := n;\n"
				+ "IF n THEN BEGIN\n"
					+ "f := n;\n"
					+ "n := n - 1;\n"
					+ "WHILE n DO BEGIN\n"
						+ "f := n * f;\n"
						+ "n := n - 1;\n"
					+ "END;\n"
				+ "END\n"
				+ "ELSE BEGIN\n"
					+ "f := 0;\n"
				+ "END;\n"
				+ "WRITE('The factorial of ' + original + ' is: ' + f);\n"
			+ "END.\n"
		);
		System.out.println("Hello from Java.");
	}
}

