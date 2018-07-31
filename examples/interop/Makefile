JAVA=java
JAVAC=javac

all: run

compile: src/at/fhhgb/graal/midipascal/interop/MidiPascalFromJava.java
	$(JAVAC) -d target/ src/at/fhhgb/graal/midipascal/interop/MidiPascalFromJava.java

run: compile
	$(JAVA) -classpath target/ at.fhhgb.graal.midipascal.interop.MidiPascalFromJava

clean:
	rm -rf target/*
