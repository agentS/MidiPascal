PROGRAM primeFactorization;

VAR targetProduct : INTEGER;
VAR i, j, currentProduct, comparisonResult : INTEGER;
VAR primeA, primeB: INTEGER;
BEGIN
	targetProduct := 741636463;

	i := 2;
	WHILE i DO BEGIN
		j := 2;
		WHILE j DO BEGIN
			currentProduct := (i * j);
			comparisonResult := (currentProduct - targetProduct);
			IF comparisonResult THEN BEGIN
				comparisonResult := (i - j);
				IF comparisonResult THEN BEGIN
					j := j + 1;
				END
				ELSE BEGIN
					j := 0;
				END;
			END
			ELSE BEGIN
				primeA := i;
				primeB := j;

				j := 0;
				i := 0;
			END;

		END;

		
		IF i THEN BEGIN
			comparisonResult := (targetProduct - i);
			IF comparisonResult THEN BEGIN
				i := i + 1;
			END
			ELSE BEGIN
				i := 0;
			END;
		END;
	END;
	
	IF i THEN	
	ELSE BEGIN
		WRITE('the prime numbers are ' + primeA + ' and ' + primeB);
	END;
END.

