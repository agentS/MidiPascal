# MidiPascal for GraalVM

This repository contains the code for the MidiPascal frontend that runs ontop of GraalVM.
This code is for my bachelor thesis on GraalVM.

So far, the frontend supports basic arithmetics, if-then-else conditions and while loops.

Basic 32-bit signed integers are the only available data type. Input and output is provided via the console.
Additionally, strings can be used directly in the WRITE statement. They can also be concatenated with integers.

MidiPascal can be integrated into other GraalVM languages.
For executing code written in other GraalVM languages use the command `POLYGLOT`. For example: `POLYGLOT('js', 'console.log("The result is: "' + i + ');');`

## Outlook

I'm planning to compare the performance against a self-written MidiPascal-to-JVM compiler.
