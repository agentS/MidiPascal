PROGRAM fibonacci;

VAR upperBoundary: INTEGER;
VAR previous, current, next: INTEGER;
BEGIN
	READ(upperBoundary);
	previous := 1;
	current := 1;
	next := 1;

	WRITE(0);
	WRITE(1);
	upperBoundary := upperBoundary - 1;
	WHILE upperBoundary DO BEGIN
		WRITE(current);
		next := current + previous;
		previous := current;
		current := next;
		upperBoundary := upperBoundary - 1;
	END;
END.

