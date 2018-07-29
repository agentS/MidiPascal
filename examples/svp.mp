PROGRAM svp;
	VAR a, b, cs: INTEGER;
	VAR literal: INTEGER;
BEGIN
    literal := 1180;

	READ(a);
	READ(b);
	cs := (a * a) + (b * b);
	WRITE(cs);
END.
