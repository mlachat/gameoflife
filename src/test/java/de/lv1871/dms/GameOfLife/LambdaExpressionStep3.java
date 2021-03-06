package de.lv1871.dms.GameOfLife;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

public class LambdaExpressionStep3 extends LambdaExpressionRefactoringBase {

	@Test
	public void testLambdaFilterWithEqualWithFunction() {
		// @formatter:off
		Begriff result = BEGRIFF_OBJEKT_LISTE.stream().filter(equal(Begriff::getValue, FILTER_VALUE)).findFirst().get();
		// @formatter:on

		assertEquals(FILTER_VALUE, result.getValue());
	}

	private static  Predicate<Begriff> equal(Function<Begriff, String> supplier, String filter) {
		return  (value) -> supplier.apply(value).equals(filter);
	}
	
}
