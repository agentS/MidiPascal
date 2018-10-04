TARGET_PRODUCT = 741636463;
i = 2;
current_product = 0;
comparison_result = 0;
prime_a = 0;
prime_b = 0;

while i != 0
	j = 2;
	while j != 0
		current_product = (i * j);
		comparison_result = (TARGET_PRODUCT - current_product);
		if comparison_result != 0
			comparison_result = (i - j);
			if comparison_result != 0
				j = j + 1;
			else
				j = 0;
			end;
		else
			prime_a = i;
			prime_b = j;

			i = 0;
			j = 0;

		end;
	end;

	if i != 0
		comparison_result = (TARGET_PRODUCT - i);
		if comparison_result != 0
			i = i + 1;
		else
			i = 0;
		end;
	else
		puts("The prime factors are #{prime_a} and #{prime_b}.");
	end;
end;
