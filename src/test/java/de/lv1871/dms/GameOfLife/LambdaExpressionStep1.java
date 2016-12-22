package de.lv1871.dms.GameOfLife;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LambdaExpressionStep1 extends LambdaExpressionRefactoringBase {

	@Test
	public void testLambdaFilter() {
		// @formatter:off
		String result = BEGRIFF_LISTE
				.stream()
				.filter(value -> value.equals(FILTER_VALUE))
				.findFirst()
				.get();
		// @formatter:on

		assertEquals(result, FILTER_VALUE);
	}

	@Test
	public void testLambdaFilter2() {
		// @formatter:off
		Begriff result = BEGRIFF_OBJEKT_LISTE
				.stream()
				.filter(value -> value.getValue().equals(FILTER_VALUE))
				.findFirst()
				.get();
		// @formatter:on

		assertEquals(result.getValue(), FILTER_VALUE);
	}

}
