# MidiPascal for GraalVM

This repository contains the code for the MidiPascal frontend that runs ontop of GraalVM.
This code is for my bachelor thesis on GraalVM.

So far, the frontend supports basic arithmetics, if-then-else conditions and while loops.

Basic 32-bit signed integers are the only available data type. Input and output is provided via the console.
Additionally, strings can be used directly in the WRITE statement. They can also be concatenated with integers.

## Outlook

I'm planning to compare the performance against a self-written MidiPascal-to-JVM compiler.
Additionally, I want to improve the debugging support by using GraalVM's annotations.
A further goal is to test integration with other GraalVM compatible languages.
