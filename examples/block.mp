PROGRAM block;

VAR b: INTEGER;
BEGIN
	b := 6;
	BEGIN
		WRITE(b);
		WRITE(b);
		BEGIN
			WRITE(b);
		END;
		WRITE(b);
		BEGIN
			WRITE(b);
			WRITE(b);
		END;
	END;
END.
