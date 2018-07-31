PROGRAM fact;

VAR f, n: INTEGER;
VAR original: INTEGER;

BEGIN
	WRITE('Please enter a number:');
	READ(n);
	original := n;

	IF n THEN BEGIN
		f := n;
		n := n - 1;
		WHILE n DO BEGIN
			f := n * f;
			n := n - 1;
		END;
	END
	ELSE BEGIN
		f := 0;
	END;

	POLYGLOT('js', 'let result = { "input": ' + original + ', "factorial": ' + f + '}; console.log(JSON.stringify(result));');
	WRITE('Hello from MidiPascal.');
END.

