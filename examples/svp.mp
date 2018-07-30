PROGRAM svp;
	VAR a, b, cs: INTEGER;
	VAR literal: INTEGER;
BEGIN
    literal := 1180;

	WRITE('Please enter one side of the triangle: ');
	READ(a);
	WRITE('Please enter another side of the triangle: ');
	READ(b);
	cs := (a * a) + (b * b);
	WRITE('Use the square root of this result to get the longest side of the triangle: ' + cs);
END.
