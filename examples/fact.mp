PROGRAM fact;

VAR f, n: INTEGER;
VAR original: INTEGER;
VAR literal: INTEGER;

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

	literal := 1180;

	WRITE('The factorial of ' + original + ' is: ' + f);
END.

